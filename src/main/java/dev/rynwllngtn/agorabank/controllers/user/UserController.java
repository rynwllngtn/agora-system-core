package dev.rynwllngtn.agorabank.controllers.user;

import dev.rynwllngtn.agorabank.dtos.user.UserCreateRequestDTO;
import dev.rynwllngtn.agorabank.dtos.user.UserResponseDTO;
import dev.rynwllngtn.agorabank.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
        UserResponseDTO responseDTO = userService.getResponseById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        UserResponseDTO responseDTO = userService.insert(userCreateRequestDTO);
        URI uri = ServletUriComponentsBuilder.
                  fromCurrentRequest().
                  path("/{id}").
                  buildAndExpand(responseDTO.id()).
                  toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PatchMapping(value = "/{id}/deactivate")
    public ResponseEntity<UserResponseDTO> deactivate(@PathVariable UUID id) {
        UserResponseDTO responseDTO = userService.deactivate(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PatchMapping(value = "/{id}/reactivate")
    public ResponseEntity<UserResponseDTO> reactivate(@PathVariable UUID id) {
        UserResponseDTO responseDTO  = userService.reactivate(id);
        return ResponseEntity.ok().body(responseDTO);
    }

}