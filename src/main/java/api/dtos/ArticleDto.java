package api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class ArticleDto {

	private String id;

	@Indexed(unique = true)
	private String nom;
	private String lien;
	private String date;
	@JsonProperty("auteurNom")
	private String auteurNom;
	@JsonProperty("auteurPrenom")
	private String auteurPrenom;
	@JsonProperty("auteurDesc")
	private String auteurDescription;
	private int salle;
	private int concert;
	private int soiree;
	private int groupe;
	private int artiste;

}
