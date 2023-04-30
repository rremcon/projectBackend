package com.example.projectbackend.controller;
import com.example.projectbackend.dto.AccountDto;
import com.example.projectbackend.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")

public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("username") String username) {
        AccountDto optionalAccount = accountService.getAccount(username);
        return ResponseEntity.ok().body(optionalAccount);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto accountDto) {
        accountService.updateAccount(id, accountDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount (@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/authorities")
    public ResponseEntity<Object> getAccountAuthorities(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(accountService.getAccountAuthorities(id));
    }

}

