package api.controllers;

import api.dtos.ConcertDto;
import api.dtos.SoireeDto;
import api.entities.Concert;
import api.entities.Soiree;
import api.services.impl.ConcertServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

	private final ConcertServiceImpl concertService;

	public ConcertController(ConcertServiceImpl concertService) {
		this.concertService = concertService;
	}

	/**
	 * <p>Get all concerts in the system</p>
	 * @return List<ConcertDto>
	 */
	@GetMapping
	public List<ConcertDto> getConcerts() {
		return concertService.getAllConcerts();
	}

	/**
	 * Method to get the concert based on the ID
	 */
	@GetMapping("/{id}")
	public ConcertDto getConcert(@PathVariable Long id){
		return concertService.getConcertById(id);
	}

	/**
	 * Create a new Concert in the system
	 */
	@PostMapping
	public ConcertDto saveConcert(final @RequestBody ConcertDto concertDto){
		System.out.println("save");
		return concertService.saveConcert(concertDto);
	}

	/**
	 * update a Concert in the system
	 */
	@PostMapping("/update")
	public void updateConcert(final @RequestBody ConcertDto concertDto){
		concertService.updateConcert(concertDto);
	}

	/**
	 * Delete a concert by it's id
	 */
	@DeleteMapping("/{id}")
	public Boolean deleteConcert(@PathVariable Long id){
		return concertService.deleteConcert(id);
	}

	@GetMapping("/{id}/test_dispo/ok")
	public Boolean testDispoOk(@PathVariable long id) {
		ConcertDto concertDto = concertService.getConcertById(id);
		Soiree soiree = concertDto.getSoiree();
		boolean test1 = soiree.isDispo(new Date(123, 1, 14), new Time(11,00,00), new Time(12, 30, 00));
		boolean test2 = soiree.isDispo(new Date(123, 1, 14), new Time(15,00,00), new Time(16, 30, 00));
		boolean test3 = soiree.isDispo(new Date(123, 1, 13), new Time(13,00,00), new Time(14, 30, 00));
		return test1 && test2 && test3;
	}

	@GetMapping("/{id}/test_dispo/nok")
	public Boolean testDispoNok(@PathVariable long id) {
		ConcertDto concertDto = concertService.getConcertById(id);
		Soiree soiree = concertDto.getSoiree();
		boolean test1 = soiree.isDispo(new Date(123, 1, 14), new Time(12,00,00), new Time(13, 30, 00));
		boolean test2 = soiree.isDispo(new Date(123, 1, 14), new Time(14,00,00), new Time(15, 30, 00));
		boolean test3 = soiree.isDispo(new Date(123, 1, 14), new Time(13,15,00), new Time(14, 15, 00));
		return test1 || test2 || test3;
	}
	
}
