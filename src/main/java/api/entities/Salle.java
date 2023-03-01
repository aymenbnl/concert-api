package api.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Salle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_salle;
    private String nom;
    private String adresse;
    private long capacite;
    private String nom_gest;
    private String prenom_gest;
    private String association;

    @OneToMany(mappedBy = "salle")
    @JsonManagedReference
    private List<Soiree> listSoiree;
}
