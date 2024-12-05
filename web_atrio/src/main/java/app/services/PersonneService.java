package app.services;

import app.models.Personne;
import app.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public Personne savePersonne(Personne personne) {
        if (!personne.isAgeValid()) {
            throw new IllegalArgumentException("La personne doit avoir moins de 150 ans.");
        }
        return personneRepository.save(personne);
    }

    public int calculateAge(LocalDate dateNaissance) {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public List<Personne> getPersonnesWithCurrentJobs() {
        List<Personne> personnes = personneRepository.findAll();

        return personnes.stream()
                .sorted((p1, p2) -> p1.getNom().compareToIgnoreCase(p2.getNom()))
                .peek(personne -> {
                    personne.setEmplois(
                            personne.getEmplois().stream()
                                    .filter(emplois -> emplois.getDateFin() == null || emplois.getDateFin().isAfter(LocalDate.now()))
                                    .collect(Collectors.toSet())
                    );
                })
                .collect(Collectors.toList());
    }

    public List<Personne> getPersonnesByEntreprise(String nomEntreprise) {
        return personneRepository.findAll().stream()
                .filter(personne -> personne.getEmplois().stream()
                        .anyMatch(emploi -> emploi.getNomEntreprise().equalsIgnoreCase(nomEntreprise)))
                .collect(Collectors.toList());
    }

}
