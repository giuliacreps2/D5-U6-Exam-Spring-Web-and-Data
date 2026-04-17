package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(unique = true)
    private String username;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
