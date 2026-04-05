package dev.rynwllngtn.agorasystem.repositories.account;

import dev.rynwllngtn.agorasystem.dtos.account.AccountResponseDTO;
import dev.rynwllngtn.agorasystem.entities.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query(value = """
                   SELECT NEW dev.rynwllngtn.agorasystem.dtos.account.AccountResponseDTO(a.balance, a.transferLimit, a.transferLimitCap, a.accountType)
                   FROM Account AS a
                   WHERE a.id = :id
                   """)
    Optional<AccountResponseDTO> findAccountById(@Param("id") UUID id);

}