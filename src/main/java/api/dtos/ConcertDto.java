package api.dtos;

import api.entities.Soiree;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ConcertDto {

    private long id_concert;
    private Date date;
    private Time heureDebut;
    private Time heureFin;
    private long id_groupe;
    private Soiree soiree;
}
