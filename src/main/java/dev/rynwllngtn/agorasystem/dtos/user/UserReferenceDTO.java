package dev.rynwllngtn.agorasystem.dtos.user;

import dev.rynwllngtn.agorasystem.entities.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserReferenceDTO {

    @Id
    private UUID id;
    private String userName;
    private LocalDate birthDate;
    private boolean active;

    public UserReferenceDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.birthDate = user.getBirthDate();
        this.active = user.isActive();
    }

}