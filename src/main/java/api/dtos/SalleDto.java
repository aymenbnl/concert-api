package api.dtos;

import api.entities.Soiree;
import lombok.Data;

import java.util.List;

@Data
public class SalleDto {

    private long id_salle;
    private String nom;
    private String adresse;
    private long capacite;
    private String nom_gest;
    private String prenom_gest;
    private String association;

    private List<Soiree> listSoiree;
}
