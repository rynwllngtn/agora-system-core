package dev.rynwllngtn.agorabank.entities.user;

import dev.rynwllngtn.agorabank.enums.user.UserStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    public User(String cpf, String password, String userName, LocalDate birthDate) {
        this.cpf = cpf;
        this.password = password;
        this.userName = userName;
        this.birthDate = birthDate;
        status = UserStatus.ACTIVE;
    }

    public void deactivate() {
        status = UserStatus.CLOSED;
    }

    public void reactivate() {
        status = UserStatus.ACTIVE;
    }

}