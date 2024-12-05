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

    public Emplois saveEmplois(Emplois emplois) {
        if (!emplois.isValid()) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        return emploisRepository.save(emplois);
    }

    public List<Emplois> getEmploisBetweenDates(int personneId, LocalDate startDate, LocalDate endDate) {
        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new IllegalArgumentException("Personne non trouvée"));

        return personne.getEmplois().stream()
                .filter(emploi -> {
                    LocalDate dateDebut = emploi.getDateDebut();
                    LocalDate dateFin = emploi.getDateFin() != null ? emploi.getDateFin() : LocalDate.now();

                    return (dateDebut.isBefore(endDate) || dateDebut.isEqual(endDate)) &&
                            (dateFin.isAfter(startDate) || dateFin.isEqual(startDate));
                })
                .collect(Collectors.toList());
    }

    public Emplois createAndAssignEmploi(int personneId, Emplois emploi) {
        // Vérifiez si la personne existe
        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new IllegalArgumentException("Personne non trouvée"));

        // Validation des dates
        if (emploi.getDateDebut() == null) {
            throw new IllegalArgumentException("La date de début est obligatoire.");
        }
        if (emploi.getDateFin() == null || emploi.getDateFin().isBefore(emploi.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être avant la date de début.");
        }

        emploi.getPersonnes().add(personne);
        return emploisRepository.save(emploi);
    }
}
