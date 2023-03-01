package api.entities;

import java.sql.Time;
import java.sql.Date;

import javax.persistence.*;

import api.repositories.ConcertRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
@Data
public class Concert {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_concert;
    private Date date;
    private Time heureDebut;
    private Time heureFin;
    @Column(name = "id_groupe")
    private long idGroupe;

    @ManyToOne
    @JoinColumn(name = "id_soiree")
    @JsonBackReference
    private Soiree soiree;
}
