package api.services;

import api.dtos.ConcertDto;

import java.util.List;

public interface ConcertService {
    /**
     * Sauve a concert
     */
    ConcertDto saveConcert(ConcertDto concertDto) throws Exception;

    /**
     * Get a concert by it's id
     */
    ConcertDto getConcertById(Long concertId);

    /**
     * Delete a concert by it's id
     */
    boolean deleteConcert(Long concertId);

    /**
     * Get all the concerts
     */
    List<ConcertDto> getAllConcerts();

    /**
     * update a concert by it's id
     */
    void updateConcert(ConcertDto concertDto);

}
