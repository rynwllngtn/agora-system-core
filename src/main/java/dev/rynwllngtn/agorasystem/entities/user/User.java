package dev.rynwllngtn.agorasystem.entities.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private boolean active = true;

}