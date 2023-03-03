package api.entities;


import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;

@Document(collection = "articles")
@Data
public class Article {

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
	private int concert;
	private int soiree;
	private int groupe;
	private int artiste;
	
}
