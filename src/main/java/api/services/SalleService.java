package api.services;

import api.dtos.SalleDto;

import java.util.List;

public interface SalleService {
    /**
     * Sauve a salle
     */
    SalleDto saveSalle(SalleDto salleDto);

    /**
     * Get a salle by it's id
     */
    SalleDto getSalleById(Long salleId);

    /**
     * Delete a salle by it's id
     */
    boolean deleteSalle(Long salleId);

    /**
     * Get all the salles
     */
    List<SalleDto> getAllSalles();

    /**
     * update a salle by it's id
     */
    void updateSalle(SalleDto salleDto);
}
