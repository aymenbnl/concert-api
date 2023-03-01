package api.services;

import api.dtos.ImageDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface ImageService {
    /**
     * Sauve a image
     */
    ImageDto saveImage(ImageDto imageDto);

    /**
     * Get a image by it's name
     */
    List<ImageDto> getImageByNom(String imageNom);

    /**
     * Get a image by it's salle
     */
    List<ImageDto> getImageBySalle(int imageSalle);

    /**
     * Delete a image by it's name
     */
    boolean deleteImage(String imageNom);

    /**
     * Get all the images
     */
    List<ImageDto> getAllImages();

    /**
     * update a image by it's name
     */
    void updateImage(ImageDto imageDto);


}
