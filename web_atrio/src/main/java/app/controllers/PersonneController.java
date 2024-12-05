package app.controllers;

import app.models.Personne;
import app.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneService.getAllPersonnes();
    }

    @PostMapping
    public Personne addPersonne(@RequestBody Personne personne) {
        return personneService.savePersonne(personne);
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable int id) {
        personneService.deletePersonne(id);
    }

    @GetMapping("/with-jobs")
    public List<Personne> getPersonnesWithJobs() {
        return personneService.getPersonnesWithCurrentJobs();
    }

    @GetMapping("/by-entreprise")
    public List<Personne> getPersonnesByEntreprise(@RequestParam String entreprise) {
        return personneService.getPersonnesByEntreprise(entreprise);
    }
}
