package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities;


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
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookingId;

    @Column(nullable = false)
    private LocalDate requestDate;
    @Column
    private String notes;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    public Booking(LocalDate requestDate, String notes, LocalDate startDate, LocalDate endDate, Employee employee) {
        this.requestDate = requestDate;
        this.notes = notes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }
}
