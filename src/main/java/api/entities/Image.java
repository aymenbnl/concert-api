package api.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "images")
@Data
public class Image {

	@Id
	private String id;
	@Indexed(unique = true)
	private String nom;
	private String lien;
	private String date;
	@Field("auteur.nom")
	private String auteurNom;
	@Field("auteur.prenom")
	private String auteurPrenom;
	@Field("auteur.description")
	private String auteurDescription;
	private int salle;
	
}
