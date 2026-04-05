package dev.rynwllngtn.agorasystem.controllers.user;

import dev.rynwllngtn.agorasystem.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorasystem.entities.user.User;
import dev.rynwllngtn.agorasystem.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
        UserResponseDTO userResponseDTO = userService.findUserById(id);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody User user) {
        user = userService.insert(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userResponseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id, @RequestBody User user) {
        user = userService.update(id, user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return ResponseEntity.ok().body(userResponseDTO);
    }

}