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
    private LocalDate bookingDate;
    @Column
    private String notes;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    public Booking(LocalDate bookingDate, String notes, Employee employee) {
        this.bookingDate = bookingDate;
        this.notes = notes;
        this.employee = employee;
    }
}
