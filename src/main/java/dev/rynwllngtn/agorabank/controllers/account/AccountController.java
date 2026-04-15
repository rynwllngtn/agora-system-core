package dev.rynwllngtn.agorabank.controllers.account;

import dev.rynwllngtn.agorabank.dtos.account.AccountCreateRequestDTO;
import dev.rynwllngtn.agorabank.dtos.account.AccountResponseDTO;
import dev.rynwllngtn.agorabank.dtos.account.DepositRequestDTO;
import dev.rynwllngtn.agorabank.dtos.account.WithdrawalRequestDTO;
import dev.rynwllngtn.agorabank.services.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountResponseDTO> findById(@PathVariable UUID id) {
        AccountResponseDTO responseDTO = accountService.getResponseById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> insert(@RequestBody AccountCreateRequestDTO accountCreateRequestDTO) {
        AccountResponseDTO responseDTO = accountService.insert(accountCreateRequestDTO);
        URI uri = ServletUriComponentsBuilder.
                  fromCurrentRequest().
                  path("/{id}").
                  buildAndExpand(responseDTO.id()).
                  toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PostMapping(value = "/{id}/deposit")
    public ResponseEntity<AccountResponseDTO> deposit(@PathVariable UUID id, @RequestBody DepositRequestDTO depositRequestDTO) {
        AccountResponseDTO responseDTO = accountService.deposit(id,depositRequestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping(value = "/{id}/withdrawal")
    public ResponseEntity<AccountResponseDTO> withdrawal(@PathVariable UUID id, @RequestBody WithdrawalRequestDTO withdrawalRequestDTO) {
        AccountResponseDTO responseDTO = accountService.withdrawal(id,withdrawalRequestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

}