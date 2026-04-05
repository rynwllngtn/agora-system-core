package dev.rynwllngtn.agorasystem.repositories.user;

import dev.rynwllngtn.agorasystem.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorasystem.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = """
                   SELECT NEW dev.rynwllngtn.agorasystem.dtos.user.UserResponseDTO(u.userName, u.birthDate, u.active)
                   FROM User AS u
                   WHERE u.id = :id
                   """)
    Optional<UserResponseDTO> findUserById(@Param("id") UUID id);

}