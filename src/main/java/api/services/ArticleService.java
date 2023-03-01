package api.services;

import api.dtos.ArticleDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface ArticleService {
    /**
     * Sauve a article
     */
    ArticleDto saveArticle(ArticleDto articleDto);

    /**
     * Get a article by it's name
     */
    List<ArticleDto> getArticleByNom(String articleNom);

    /**
     * Get a article by it's salle
     */
    List<ArticleDto> getArticleBySalle(int articleSalle);

    /**
     * Delete a article by it's name
     */
    boolean deleteArticle(String articleNom);

    /**
     * Get all the articles
     */
    List<ArticleDto> getAllArticles();

    /**
     * update a article by it's name
     */
    void updateArticle(ArticleDto articleDto);


}
