package api.controllers;

import api.dtos.ArticleDto;
import api.services.impl.ArticleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	private final ArticleServiceImpl articleService;

	public ArticleController(ArticleServiceImpl articleService) {
		this.articleService = articleService;
	}

	/**
	 * <p>Get all articles in the system</p>
	 * @return List<ArticleDto>
	 */
	@GetMapping
	public List<ArticleDto> getArticles() {
		return articleService.getAllArticles();
	}

	/**
	 * Method to get the article based on the nom
	 */
	@PostMapping("/get/nom")
	public List<ArticleDto> getArticleNom(@RequestBody String nom){
		return articleService.getArticleByNom(nom);
	}

	/**
	 * Method to get the article based on the salle
	 */
	@GetMapping("/get/salle/{id}")
	public List<ArticleDto> getArticleSalle(@PathVariable int id){
		return articleService.getArticleBySalle(id);
	}

	/**
	 * Create a new Article in the system
	 */
	@PostMapping("/save")
	public ArticleDto saveArticle(final @RequestBody ArticleDto articleDto){
		return articleService.saveArticle(articleDto);
	}

	@PostMapping("/update")
	public void updateArticle(final @RequestBody ArticleDto articleDto){
		articleService.updateArticle(articleDto);
	}

	/**
	 * Delete a article by it's id
	 */
	@DeleteMapping("/{nom}")
	public Boolean deleteArticle(@PathVariable String nom){
		return articleService.deleteArticle(nom);
	}
	
}
