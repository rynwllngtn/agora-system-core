package dev.rynwllngtn.agorabank.repositories.user;

import dev.rynwllngtn.agorabank.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorabank.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = """
                   SELECT NEW dev.rynwllngtn.agorasystem.dtos.user.UserResponseDTO(u.userName, u.birthDate, u.status)
                   FROM User AS u
                   WHERE u.id = :id
                   """)
    Optional<UserResponseDTO> getResponseById(@Param("id") UUID id);

}