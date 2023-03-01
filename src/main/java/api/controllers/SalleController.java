package api.controllers;

import api.dtos.SalleDto;
import api.services.SalleService;
import api.services.impl.SalleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class SalleController {
	
	private final SalleServiceImpl salleService;

	public SalleController(SalleServiceImpl salleService) {
		this.salleService = salleService;
	}

	/**
	 * <p>Get all salles in the system</p>
	 * @return List<SalleDto>
	 */
	@GetMapping
	public List<SalleDto> getSalles() {
		return salleService.getAllSalles();
	}

	/**
	 * Method to get the salle based on the ID
	 */
	@GetMapping("/{id}")
	public SalleDto getSalle(@PathVariable Long id){
		return salleService.getSalleById(id);
	}

	/**
	 * Create a new Salle in the system
	 */
	@PostMapping
	public SalleDto saveSalle(final @RequestBody SalleDto salleDto){
		return salleService.saveSalle(salleDto);
	}

	/**
	 * Delete a salle by it's id
	 */
	@DeleteMapping("/{id}")
	public Boolean deleteSalle(@PathVariable Long id){
		return salleService.deleteSalle(id);
	}

	/**
	 * update a Concert in the system
	 */
	@PostMapping("/update")
	public void updateSalle(final @RequestBody SalleDto salleDto){
		salleService.updateSalle(salleDto);
	}
	
}
