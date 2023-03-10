package api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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
