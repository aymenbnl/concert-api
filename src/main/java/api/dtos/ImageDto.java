package api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class ImageDto {

	private String id;

	@Indexed(unique = true)
	private String nom;
	private String lien;
	private String date;
	@JsonProperty("auteur.nom")
	private String auteurNom;
	@JsonProperty("auteur.prenom")
	private String auteurPrenom;
	@JsonProperty("auteur.descritpion")
	private String auteurDescription;
	private int salle;

}
