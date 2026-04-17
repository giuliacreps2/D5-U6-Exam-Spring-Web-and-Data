package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities;


import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.enums.TravelStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "travels")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID travelId;

    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDate bookingDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 2, nullable = false)
    private TravelStatus travelStatus;

    public Travel(String destination, LocalDate bookingDate, TravelStatus travelStatus) {
        this.destination = destination;
        this.bookingDate = bookingDate;
        this.travelStatus = travelStatus;
    }

}
