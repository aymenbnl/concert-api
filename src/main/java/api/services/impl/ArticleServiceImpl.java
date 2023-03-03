package api.services.impl;

import api.dtos.ArticleDto;
import api.entities.Article;
import api.repositories.ArticleRepository;
import api.services.ArticleService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        // Converts the dto to the article entity
        Article article = articleDtoToEntity(articleDto);

        if(articleRepository.findByNom(articleDto.getNom()).isPresent()) {
            throw new DuplicateKeyException("nom deja present");
        }
        // Save the article entity
        article = articleRepository.save(article);
        // Return the new dto
        return articleEntityToDto(article);
    }

    @Override
    public List<ArticleDto> getArticleByNom(String nom) {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> {
                if (Objects.equals(article.getNom(), nom)) articleDtos.add(articleEntityToDto(article));
        });
        return articleDtos;
    }

    @Override
    public List<ArticleDto> getArticleBySalle(int salle) {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> {
            if (article.getSalle() == salle) articleDtos.add(articleEntityToDto(article));
        });
        return articleDtos;
    }

    @Override
    public boolean deleteArticle(String nom) {
        System.out.println(articleRepository.deleteByNom(nom));
        return true;
    }

    @Override
    public List<ArticleDto> getAllArticles() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> {
            articleDtos.add(articleEntityToDto(article));
        });
        return articleDtos;
    }

    @Override
    public void updateArticle(ArticleDto articleDto) {
        Optional<Article> articleOriginal = articleRepository.findByNom(articleDto.getNom());

        if(articleOriginal.isPresent()) {
            Article article = articleOriginal.get();
            article.setLien(articleDto.getLien());
            article.setDate(articleDto.getDate());
            article.setSalle(articleDto.getSalle());
            article.setConcert(articleDto.getConcert());
            article.setSoiree(articleDto.getSoiree());
            article.setGroupe(articleDto.getGroupe());
            article.setArtiste(articleDto.getArtiste());
            article.setAuteurNom(articleDto.getAuteurNom());
            article.setAuteurPrenom(articleDto.getAuteurPrenom());
            article.setAuteurDescription(articleDto.getAuteurDescription());
            articleRepository.save(article);
        } else {
            throw new EntityNotFoundException("article non trouve");
        }
    }

    /**
     * Map article dto to article entity
     */
    private ArticleDto articleEntityToDto(Article article){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setNom(article.getNom());
        articleDto.setDate(article.getDate());
        articleDto.setLien(article.getLien());
        articleDto.setAuteurNom(article.getAuteurNom());
        articleDto.setAuteurPrenom(article.getAuteurPrenom());
        articleDto.setAuteurDescription(article.getAuteurDescription());
        articleDto.setSalle(article.getSalle());
        articleDto.setConcert(article.getConcert());
        articleDto.setSoiree(article.getSoiree());
        articleDto.setGroupe(article.getGroupe());
        articleDto.setArtiste(article.getArtiste());
        return articleDto;
    }

    /**
     * Map article entity to article dto
     */
    private Article articleDtoToEntity(ArticleDto articleDto){
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setNom(articleDto.getNom());
        article.setDate(articleDto.getDate());
        article.setLien(articleDto.getLien());
        article.setAuteurNom(articleDto.getAuteurNom());
        article.setAuteurPrenom(articleDto.getAuteurPrenom());
        article.setAuteurDescription(articleDto.getAuteurDescription());
        article.setSalle(articleDto.getSalle());
        return article;
    }
}
