package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.controllers;

import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Travel;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services.TravelsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/travels")
public class TravelsController {
    //Dipendenze
    private final TravelsService travelsService;

    //Costruttore
    public TravelsController(TravelsService travelsService) {
        this.travelsService = travelsService;
    }


    //1. POST

    //2. GET (findAll)
    @GetMapping
    public List<Travel> findAll() {
        return this.travelsService.findAll();
    }

    //2.1 GET (findById)
    @GetMapping("/{travelId}")
    public Travel getById(@PathVariable UUID travelId) {
        return this.travelsService.findById(travelId);
    }


    //3. DELETE
    @DeleteMapping("/{travelId}")
    public void delete(@PathVariable UUID travelId) {
        this.travelsService.findByIdAndDelete(travelId);
    }

    //PUT
    @PatchMapping("/{travelId}/status")
    public Travel updateStatus(@PathVariable UUID travelId, @RequestBody String travelStatus) {
        return this.travelsService.findByIdAndUpdate(travelId, travelStatus);
    }
}
