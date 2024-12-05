package app.controllers;

import app.models.Emplois;
import app.models.Personne;
import app.services.EmploisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/emplois")
@Tag(name = "Emploi API", description = "Gérer les emplois")
public class EmploisController {

    @Autowired
    private EmploisService emploisService;

    @PostMapping("/assign")
    @Operation(summary = "Créer un emplois",
            description = "Ajoute un emplois à une personne existante")
    public ResponseEntity<Emplois> createAndAssignEmploi(
            @RequestParam int personneId,
            @RequestBody Emplois emploi) {
        try {
            Emplois savedEmploi = emploisService.createAndAssignEmploi(personneId, emploi);
            return ResponseEntity.ok(savedEmploi);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/between-dates")
    @Operation(summary = "Recherche d'un emplois entre 2 dates",
            description = "Récuperer la liste des emplois d'une personne dans une période donnée")
    public List<Emplois> getEmploisBetweenDates(
            @RequestParam int personneId,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return emploisService.getEmploisBetweenDates(personneId, start, end);
    }
}
