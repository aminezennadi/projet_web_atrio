package app.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "emplois")
public class Emplois {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom_entreprise", nullable = false)
    private String nomEntreprise;

    @Column(name = "poste", nullable = false)
    private String poste;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "personne_emplois",
            joinColumns = @JoinColumn(name = "emploi_id"),
            inverseJoinColumns = @JoinColumn(name = "personne_id")
    )
    private Set<Personne> personnes = new HashSet<>();

    public Emplois() {}

    public Emplois(String nomEntreprise, String poste, LocalDate dateDebut, LocalDate dateFin) {
        this.nomEntreprise = nomEntreprise;
        this.poste = poste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Set<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }

    // Méthode isValid
    public boolean isValid() {
        return dateDebut != null && (dateFin == null || !dateFin.isBefore(dateDebut));
    }
}
