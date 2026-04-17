package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services;

import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Travel;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.enums.TravelStatus;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.BadRequestException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.NotFoundException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.TravelDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories.TravelsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TravelsService {
    //Dipendenze
    private final TravelsRepository travelsRepository;

    public TravelsService(TravelsRepository travelsRepository) {
        this.travelsRepository = travelsRepository;
    }

    //1.SAVE
    public Travel saveTravel(TravelDTO body) {
        //Controllo che la data sia corretta
        if (body.travelDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("La data non può essere nel passato");
        }
        //Converto String di TravelDTO in Enum con gestione degli errori
        TravelStatus travelStatus;
        try {
            travelStatus = TravelStatus.valueOf(body.travelStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Stato non valido. Usa 'SCHEDULED' o 'COMPLETED");
        }

        Travel newTravel = new Travel(body.destination(), body.travelDate(), travelStatus);
        Travel savedTravel = this.travelsRepository.save(newTravel);
        log.info("Viaggio con id: " + savedTravel.getTravelId() + "è stato salvato con successo!");
        return savedTravel;
    }

    //2.FINDBYID
    public Travel findById(UUID travelId) {
        return this.travelsRepository.findById(travelId).orElseThrow(() -> new NotFoundException(travelId));
    }


    //4.FIND ALL TRAVELS
    public List<Travel> findAll() {
        return this.travelsRepository.findAll();
    }

    //5. DELETE TRAVEL
    public void findByIdAndDelete(UUID travelId) {
        Travel found = this.travelsRepository.findById(travelId).orElseThrow(() -> new NotFoundException(travelId));
        this.travelsRepository.delete(found);
    }

    //6. UPLOAD
    public Travel findByIdAndUpdate(UUID travelId, String travelStatus) {
        //Controllo che il viaggio non esista di già
        Travel found = this.travelsRepository.findById(travelId).orElseThrow(() -> new NotFoundException(travelId));
        //Controllo che la data non sia passata
        if (found.getTravelDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("La data non può essere nel passato");
        }
        //Controllo se il viaggio ha lo stato COMPLETATO
        try {
            found.setTravelStatus(TravelStatus.valueOf(travelStatus.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Stato non valido. Usa 'SCHEDULED' o 'COMPLETED");
        }

        Travel updatedTravel = this.travelsRepository.save(found);
        log.info("La modifica al viaggio con id: " + updatedTravel.getTravelId() + "è avvenuta con successo!");
        return updatedTravel;
    }


}
