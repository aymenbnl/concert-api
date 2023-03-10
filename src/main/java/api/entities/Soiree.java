package api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Data
public class Soiree {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_soiree;
    private String nom;

    @ManyToOne
    @JoinColumn(name = "id_salle")
    @JsonBackReference
    private Salle salle;

    @OneToMany(mappedBy = "soiree")
    @JsonManagedReference
    private List<Concert> listConcert;

    public boolean isDispo(Date date, Time heureDebut, Time heureFin) {
        for (Concert concert : listConcert) {
            if (date.equals(concert.getDate())) {
                System.out.println("same date");
                if (!heureDebut.after(concert.getHeureFin()) &&
                            !heureFin.before(concert.getHeureFin())) {
                    System.out.println("case 1");
                    return false;
                }
                if (!heureDebut.after(concert.getHeureDebut()) &&
                        !heureFin.before(concert.getHeureDebut())) {
                    System.out.println("case 2");
                    return false;
                }
                if (!heureDebut.before(concert.getHeureDebut()) &&
                        !heureFin.after(concert.getHeureFin())) {
                    System.out.println("case 3");
                    return false;
                }
            }
        }
        return true;
    }
}
