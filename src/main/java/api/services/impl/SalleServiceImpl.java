package api.services.impl;

import api.dtos.SalleDto;
import api.entities.Salle;
import api.repositories.SalleRepository;
import api.services.SalleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("salleService")
public class SalleServiceImpl implements SalleService {

	private final SalleRepository salleRepository;

    public SalleServiceImpl(SalleRepository salleRepository){
        this.salleRepository = salleRepository;
    }

    @Override
    public SalleDto saveSalle(SalleDto salleDto) {
        // Converts the dto to the salle entity
        Salle salle = salleDtoToEntity(salleDto);
        // Save the salle entity
        salle = salleRepository.save(salle);
        // Return the new dto
        return salleEntityToDto(salle);
    }

    @Override
    public SalleDto getSalleById(Long salleId) {
        Salle salle = salleRepository.findById(salleId).orElseThrow(() -> new EntityNotFoundException("Salle not found"));
        return salleEntityToDto(salle);
    }

    @Override
    public boolean deleteSalle(Long salleId) {
        salleRepository.deleteById(salleId);
        return true;
    }

    @Override
    public List<SalleDto> getAllSalles() {
        List<SalleDto> salleDtos = new ArrayList<>();
        List<Salle> salles = salleRepository.findAll();
        salles.forEach(salle -> {
            salleDtos.add(salleEntityToDto(salle));
        });
        return salleDtos;
    }

    @Override
    public void updateSalle(SalleDto salleDto) {
        // Converts the dto to the salle entity
        Optional<Salle> salleOriginal = salleRepository.findById(salleDto.getId_salle());

        if (salleOriginal.isPresent()) {
            Salle salle = salleOriginal.get();
            salle.setAdresse(salleDto.getAdresse());
            salle.setCapacite(salleDto.getCapacite());
            salle.setNom(salleDto.getNom());
            salle.setListSoiree(salleDto.getListSoiree());
            salle.setNom_gest(salleDto.getNom_gest());
            salle.setPrenom_gest(salleDto.getPrenom_gest());
            salle.setAssociation(salleDto.getAssociation());
            // Save the salle entity
            salleRepository.save(salle);
        } else {
            throw new EntityNotFoundException("salle no trouve");
        }
    }

    /**
     * Map salle dto to salle entity
     */
    private SalleDto salleEntityToDto(Salle salle){
        SalleDto salleDto = new SalleDto();
        salleDto.setId_salle(salle.getId_salle());
        salleDto.setNom(salle.getNom());
        salleDto.setAdresse(salle.getAdresse());
        salleDto.setAssociation(salle.getAssociation());
        salleDto.setCapacite(salle.getCapacite());
        salleDto.setNom_gest(salle.getNom_gest());
        salleDto.setPrenom_gest(salle.getPrenom_gest());
        salleDto.setListSoiree((salle.getListSoiree()));
        return salleDto;
    }

    /**
     * Map salle entity to salle dto
     */
    private Salle salleDtoToEntity(SalleDto salleDto){
        Salle salle = new Salle();
        salle.setId_salle(salleDto.getId_salle());
        salle.setNom(salleDto.getNom());
        salle.setAdresse(salleDto.getAdresse());
        salle.setAssociation(salleDto.getAssociation());
        salle.setCapacite(salleDto.getCapacite());
        salle.setNom_gest(salleDto.getNom_gest());
        salle.setPrenom_gest(salleDto.getPrenom_gest());
        salle.setListSoiree((salleDto.getListSoiree()));
        return salle;
    }
}
