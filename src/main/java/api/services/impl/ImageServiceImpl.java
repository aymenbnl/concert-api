package api.services.impl;

import api.dtos.ImageDto;
import api.entities.Image;
import api.repositories.ImageRepository;
import api.services.ImageService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageDto saveImage(ImageDto imageDto) {
        // Converts the dto to the image entity
        Image image = imageDtoToEntity(imageDto);

        if(imageRepository.findByNom(imageDto.getNom()).isPresent()) {
            throw new DuplicateKeyException("nom deja present");
        }
        // Save the image entity
        image = imageRepository.save(image);
        // Return the new dto
        return imageEntityToDto(image);
    }

    @Override
    public List<ImageDto> getImageByNom(String nom) {
        List<ImageDto> imageDtos = new ArrayList<>();
        List<Image> images = imageRepository.findAll();
        images.forEach(image -> {
            if (Objects.equals(image.getNom(), nom)) imageDtos.add(imageEntityToDto(image));
        });
        return imageDtos;
    }

    @Override
    public List<ImageDto> getImageBySalle(int salle) {
        List<ImageDto> imageDtos = new ArrayList<>();
        List<Image> images = imageRepository.findAll();
        images.forEach(image -> {
            if (image.getSalle() == salle) imageDtos.add(imageEntityToDto(image));
        });
        return imageDtos;
    }

    @Override
    public boolean deleteImage(String nom) {
        System.out.println(imageRepository.deleteByNom(nom));
        return true;
    }

    @Override
    public List<ImageDto> getAllImages() {
        List<ImageDto> imageDtos = new ArrayList<>();
        List<Image> images = imageRepository.findAll();
        images.forEach(image -> {
            imageDtos.add(imageEntityToDto(image));
        });
        return imageDtos;
    }

    @Override
    public void updateImage(ImageDto imageDto) {
        Optional<Image> imageOriginal = imageRepository.findByNom(imageDto.getNom());

        if(imageOriginal.isPresent()) {
            Image image = imageOriginal.get();
            image.setLien(imageDto.getLien());
            image.setDate(imageDto.getDate());
            image.setSalle(imageDto.getSalle());
            image.setConcert(imageDto.getConcert());
            image.setSoiree(imageDto.getSoiree());
            image.setGroupe(imageDto.getGroupe());
            image.setArtiste(imageDto.getArtiste());
            image.setAuteurNom(imageDto.getAuteurNom());
            image.setAuteurPrenom(imageDto.getAuteurPrenom());
            image.setAuteurDescription(imageDto.getAuteurDescription());
            imageRepository.save(image);
        } else {
            throw new EntityNotFoundException("image non trouve");
        }
    }

    /**
     * Map image dto to image entity
     */
    private ImageDto imageEntityToDto(Image image){
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setNom(image.getNom());
        imageDto.setDate(image.getDate());
        imageDto.setLien(image.getLien());
        imageDto.setAuteurNom(image.getAuteurNom());
        imageDto.setAuteurPrenom(image.getAuteurPrenom());
        imageDto.setAuteurDescription(image.getAuteurDescription());
        imageDto.setSalle(image.getSalle());
        imageDto.setConcert(image.getConcert());
        imageDto.setSoiree(image.getSoiree());
        imageDto.setGroupe(image.getGroupe());
        imageDto.setArtiste(image.getArtiste());
        return imageDto;
    }

    /**
     * Map image entity to image dto
     */
    private Image imageDtoToEntity(ImageDto imageDto){
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setNom(imageDto.getNom());
        image.setDate(imageDto.getDate());
        image.setLien(imageDto.getLien());
        image.setAuteurNom(imageDto.getAuteurNom());
        image.setAuteurPrenom(imageDto.getAuteurPrenom());
        image.setAuteurDescription(imageDto.getAuteurDescription());
        image.setSalle(imageDto.getSalle());
        return image;
    }
}
