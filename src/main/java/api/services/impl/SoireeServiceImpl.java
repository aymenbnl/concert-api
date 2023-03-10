package api.services.impl;

import api.dtos.SoireeDto;
import api.entities.Soiree;
import api.repositories.SoireeRepository;
import api.services.SoireeService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("soireeService")
public class SoireeServiceImpl implements SoireeService {

	private final SoireeRepository soireeRepository;

    public SoireeServiceImpl(SoireeRepository soireeRepository){
        this.soireeRepository = soireeRepository;
    }

    @Override
    public SoireeDto saveSoiree(SoireeDto soireeDto) {
        // Converts the dto to the soiree entity
        Soiree soiree = soireeDtoToEntity(soireeDto);
        // Save the soiree entity
        soiree = soireeRepository.save(soiree);
        // Return the new dto
        return soireeEntityToDto(soiree);
    }

    @Override
    public SoireeDto getSoireeById(Long soireeId) {
        Soiree soiree = soireeRepository.findById(soireeId).orElseThrow(() -> new EntityNotFoundException("Soiree not found"));
        return soireeEntityToDto(soiree);
    }

    @Override
    public boolean deleteSoiree(Long soireeId) {
        soireeRepository.deleteById(soireeId);
        return true;
    }

    @Override
    public List<SoireeDto> getAllSoirees() {
        List<SoireeDto> soireeDtos = new ArrayList<>();
        List<Soiree> soirees = soireeRepository.findAll();
        soirees.forEach(soiree -> {
            soireeDtos.add(soireeEntityToDto(soiree));
        });
        return soireeDtos;
    }

    @Override
    public void updateSoiree(SoireeDto soireeDto) {
        // Converts the dto to the soiree entity
        Optional<Soiree> soireeOriginal = soireeRepository.findById(soireeDto.getId_soiree());

        if (soireeOriginal.isPresent()) {
            Soiree soiree = soireeOriginal.get();
            soiree.setSalle(soireeDto.getSalle());
            soiree.setNom(soireeDto.getNom());
            soiree.setListConcert(soireeDto.getListConcert());
            // Save the soiree entity
            soireeRepository.save(soiree);
        } else {
            throw new EntityNotFoundException("soiree no trouve");
        }
    }

    /**
     * Map soiree dto to soiree entity
     */
    private SoireeDto soireeEntityToDto(Soiree soiree){
        SoireeDto soireeDto = new SoireeDto();
        soireeDto.setId_soiree(soiree.getId_soiree());
        soireeDto.setNom(soiree.getNom());
        soireeDto.setSalle(soiree.getSalle());
        soireeDto.setListConcert(soiree.getListConcert());
        return soireeDto;
    }

    /**
     * Map soiree entity to soiree dto
     */
    private Soiree soireeDtoToEntity(SoireeDto soireeDto){
        Soiree soiree = new Soiree();
        soiree.setId_soiree(soireeDto.getId_soiree());
        soiree.setNom(soireeDto.getNom());
        soiree.setSalle(soireeDto.getSalle());
        soiree.setListConcert(soireeDto.getListConcert());
        return soiree;
    }
}
