package app.controllers;

import app.models.Personne;
import app.services.PersonneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnes")
@Tag(name = "Personnes API", description = "Gérer les personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @PostMapping
    @Operation(summary = "Créer une nouvelle personne",
            description = "Ajoute une nouvelle personne avec les détails fournis")
    public Personne addPersonne(@RequestBody Personne personne) {
        return personneService.savePersonne(personne);
    }

    @GetMapping("/with-jobs")
    @Operation(summary = "Liste des personnes avec emplois",
            description = "Retourne la liste des personnes ayant un emplois en cours")
    public List<Personne> getPersonnesWithJobs() {
        return personneService.getPersonnesWithCurrentJobs();
    }

    @GetMapping("/by-entreprise")
    @Operation(summary = "Liste des personnes par entreprise",
            description = "Retourne la liste des personnes travaillant dans une entreprise précise")
    public List<Personne> getPersonnesByEntreprise(@RequestParam String entreprise) {
        return personneService.getPersonnesByEntreprise(entreprise);
    }
}
