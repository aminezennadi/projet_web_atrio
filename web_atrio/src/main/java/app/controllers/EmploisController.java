package app.controllers;

import app.models.Emplois;
import app.services.EmploisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/emplois")
public class EmploisController {

    @Autowired
    private EmploisService emploisService;

    @GetMapping
    public List<Emplois> getAllEmplois() {
        return emploisService.getAllEmplois();
    }

    @PostMapping
    public Emplois addEmplois(@RequestBody Emplois emplois) {
        return emploisService.saveEmplois(emplois);
    }

    @DeleteMapping("/{id}")
    public void deleteEmplois(@PathVariable int id) {
        emploisService.deleteEmplois(id);
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
