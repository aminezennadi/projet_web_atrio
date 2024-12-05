package app.controllers;

import app.models.Emplois;
import app.models.Personne;
import app.services.EmploisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/emplois")
public class EmploisController {

    @Autowired
    private EmploisService emploisService;

    @PostMapping("/assign")
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
    public List<Emplois> getEmploisBetweenDates(
            @RequestParam int personneId,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return emploisService.getEmploisBetweenDates(personneId, start, end);
    }
}
