package api.controllers;

import api.dtos.ImageDto;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import api.services.impl.ImageServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

	private final ImageServiceImpl imageService;

	public ImageController(ImageServiceImpl imageService) {
		this.imageService = imageService;
	}

	/**
	 * <p>Get all images in the system</p>
	 * @return List<ImageDto>
	 */
	@GetMapping
	public List<ImageDto> getImages() {
		return imageService.getAllImages();
	}

	/**
	 * Method to get the image based on the nom
	 */
	@PostMapping("/get/nom")
	public List<ImageDto> getImageNom(@RequestBody String nom){
		return imageService.getImageByNom(nom);
	}

	/**
	 * Method to get the image based on the salle
	 */
	@GetMapping("/get/salle/{id}")
	public List<ImageDto> getImageSalle(@PathVariable int id){
		return imageService.getImageBySalle(id);
	}

	/**
	 * Create a new Image in the system
	 */
	@PostMapping("/save")
	public ImageDto saveImage(final @RequestBody ImageDto imageDto){
		return imageService.saveImage(imageDto);
	}

	@PostMapping("/update")
	public void updateImage(final @RequestBody ImageDto imageDto){
		imageService.updateImage(imageDto);
	}

	/**
	 * Delete a image by it's id
	 */
	@DeleteMapping("/{nom}")
	public Boolean deleteImage(@PathVariable String nom){
		return imageService.deleteImage(nom);
	}

}
