package api.services.impl;

import api.dtos.ConcertDto;
import api.entities.Concert;
import api.entities.Salle;
import api.entities.Soiree;
import api.repositories.ConcertRepository;
import api.services.ConcertService;
import net.bytebuddy.description.NamedElement;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("concertService")
public class ConcertServiceImpl implements ConcertService {

	private final ConcertRepository concertRepository;

    public ConcertServiceImpl(ConcertRepository concertRepository){
        this.concertRepository = concertRepository;
    }

    @Override
    public ConcertDto saveConcert(ConcertDto concertDto) {
        if(isSoireeDispo(concertDto.getDate(), concertDto.getHeureDebut(), concertDto.getHeureFin(), concertDto.getSoiree()) &&
                isGroupDispo(concertDto.getDate(), concertDto.getHeureDebut(), concertDto.getHeureFin(), concertDto.getId_groupe())) {
            // Converts the dto to the concert entity
            Concert concert = concertDtoToEntity(concertDto);
            // Save the concert entity
            concert = concertRepository.save(concert);
            // Return the new dto
            return concertEntityToDto(concert);
        } else {
            throw new PersistenceException("concert deja present");
        }
    }

    @Override
    public ConcertDto getConcertById(Long concertId) {
        Concert concert = concertRepository.findById(concertId).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        return concertEntityToDto(concert);
    }

    @Override
    public boolean deleteConcert(Long concertId) {
        concertRepository.deleteById(concertId);
        return true;
    }

    @Override
    public List<ConcertDto> getAllConcerts() {
        List<ConcertDto> concertDtos = new ArrayList<>();
        List<Concert> concerts = concertRepository.findAll();
        concerts.forEach(concert -> {
            concertDtos.add(concertEntityToDto(concert));
        });
        return concertDtos;
    }

    @Override
    public void updateConcert(ConcertDto concertDto) {
        // Converts the dto to the concert entity
        Optional<Concert> concertOriginal = concertRepository.findById(concertDto.getId_concert());
        if(isSoireeDispo(concertDto.getDate(), concertDto.getHeureDebut(), concertDto.getHeureFin(), concertDto.getSoiree()) &&
            isGroupDispo(concertDto.getDate(), concertDto.getHeureDebut(), concertDto.getHeureFin(), concertDto.getId_groupe())) {
            if (concertOriginal.isPresent()) {
                Concert concert = concertOriginal.get();
                concert.setDate(concertDto.getDate());
                concert.setHeureDebut(concertDto.getHeureDebut());
                concert.setHeureFin(concertDto.getHeureFin());
                concert.setIdGroupe(concertDto.getId_groupe());
                concert.setSoiree(concertDto.getSoiree());
                // Save the concert entity
                concertRepository.save(concert);
            } else {
                throw new EntityNotFoundException("concert non trouve");
            }
        } else {
            throw new PersistenceException("concert deja present");
        }
    }

    /**
     * Map concert dto to concert entity
     */
    private ConcertDto concertEntityToDto(Concert concert){
        ConcertDto concertDto = new ConcertDto();
        concertDto.setId_concert(concert.getId_concert());
        concertDto.setDate(concert.getDate());
        concertDto.setHeureDebut(concert.getHeureDebut());
        concertDto.setHeureFin(concert.getHeureFin());
        concertDto.setSoiree(concert.getSoiree());
        concertDto.setId_groupe(concert.getIdGroupe());
        return concertDto;
    }

    /**
     * Map concert entity to concert dto
     */
    private Concert concertDtoToEntity(ConcertDto concertDto){
        Concert concert = new Concert();
        concert.setId_concert(concertDto.getId_concert());
        concert.setDate(concertDto.getDate());
        concert.setHeureDebut(concertDto.getHeureDebut());
        concert.setHeureFin(concertDto.getHeureFin());
        concert.setSoiree(concertDto.getSoiree());
        concert.setIdGroupe(concertDto.getId_groupe());
        return concert;
    }



    public boolean isGroupDispo(Date date, Time heureDebut, Time heureFin, long idGroupe) {
        List<Optional<Concert>> concertByGroupe = concertRepository.findByIdGroupe(idGroupe);
        for (Optional<Concert> concertOptionnal : concertByGroupe) {
            Concert concert = concertOptionnal.get();
            if (date.equals(concert.getDate())) {
                System.out.println("same date");
                if (!heureDebut.after(concert.getHeureFin()) &&
                        !heureFin.before(concert.getHeureFin())) {
                    System.out.println("case 1");
                    return false;
                }
                if (!heureDebut.after(concert.getHeureDebut()) &&
                        !heureFin.before(concert.getHeureDebut())) {
                    System.out.println("case 2");
                    return false;
                }
                if (!heureDebut.before(concert.getHeureDebut()) &&
                        !heureFin.after(concert.getHeureFin())) {
                    System.out.println("case 3");
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isSoireeDispo(Date date, Time heureDebut, Time heureFin, Soiree soiree) {
        List<Optional<Concert>>concertBySalle = concertRepository.findBySoiree(soiree);
        for (Optional<Concert> concertOptionnal : concertBySalle) {
            Concert concert = concertOptionnal.get();
            if (date.equals(concert.getDate())) {
                System.out.println("same date");
                if (!heureDebut.after(concert.getHeureFin()) &&
                        !heureFin.before(concert.getHeureFin())) {
                    System.out.println("case 1");
                    return false;
                }
                if (!heureDebut.after(concert.getHeureDebut()) &&
                        !heureFin.before(concert.getHeureDebut())) {
                    System.out.println("case 2");
                    return false;
                }
                if (!heureDebut.before(concert.getHeureDebut()) &&
                        !heureFin.after(concert.getHeureFin())) {
                    System.out.println("case 3");
                    return false;
                }
            }
        }
        return true;
    }
}
