package app.services;

import app.models.Emplois;
import app.models.Personne;
import app.repositories.EmploisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.repositories.PersonneRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmploisService {

    @Autowired
    private EmploisRepository emploisRepository;

    @Autowired
    private PersonneRepository personneRepository;

    public List<Emplois> getAllEmplois() {
        return emploisRepository.findAll();
    }

    public Emplois saveEmplois(Emplois emplois) {
        if (!emplois.isValid()) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        return emploisRepository.save(emplois);
    }

    public void deleteEmplois(int id) {
        emploisRepository.deleteById(id);
    }

    public List<Emplois> getEmploisBetweenDates(int personneId, LocalDate startDate, LocalDate endDate) {
        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new IllegalArgumentException("Personne introuvable"));

        return personne.getEmplois().stream()
                .filter(emplois -> {
                    LocalDate dateDebut = emplois.getDateDebut();
                    LocalDate dateFin = emplois.getDateFin() != null ? emplois.getDateFin() : LocalDate.now();

                    return (dateDebut.isBefore(endDate) || dateDebut.isEqual(endDate)) &&
                            (dateFin.isAfter(startDate) || dateFin.isEqual(startDate));
                })
                .collect(Collectors.toList());
    }
}
