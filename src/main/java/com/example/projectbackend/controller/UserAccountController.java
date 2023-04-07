package com.example.projectbackend.controller;
import com.example.projectbackend.dto.UserAccountDto;
import com.example.projectbackend.service.UserAccountService;
import com.example.projectbackend.utility.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/users/accounts")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> createUserAccount(@Valid @RequestBody UserAccountDto userAccountDto, BindingResult br) {
        if (br.hasErrors()) {
            String error = Util.reportErrors(br);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        else {
            //String
            userAccountService.createUserAccount(userAccountDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/users/accounts/registrate/" ).toUriString());
            return ResponseEntity.created(uri).body("UserAccount created!");
        }
    }



//    @GetMapping(value = "/{id}")
//    public ResponseEntity<UserAccountDto> getUserAccount(@PathVariable("id") Long id) {
//        UserAccountDto optionalUserAccount = userAccountService.getUserAccount(id);
//        return ResponseEntity.ok().body(optionalUserAccount);
//    }
//
//
//    @GetMapping("")
//    public ResponseEntity<Iterable<UserAccountDto>> getUserAccounts() {
//        return ResponseEntity.ok(userAccountService.getUserAccounts());
//    }
//
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<UserAccountDto> updateUserAccount(@PathVariable("id") Long id, @RequestBody UserAccountDto userAccountDto) {
//        userAccountService.updateUserAccount(id, userAccountDto);
//        return ResponseEntity.noContent().build();
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteUserAccount (@PathVariable("id") Long id) {
//        userAccountService.deleteUserAccount(id);
//        return ResponseEntity.noContent().build();
//    }
//
//
//    @GetMapping(value = "/{id}/authorities")
//    public ResponseEntity<Object> getUserAccountAuthorities(@PathVariable("id") Long id) {
//        return ResponseEntity.ok().body(userAccountService.getUserAccountAuthorities(id));
//    }
//
//
//
//    @DeleteMapping(value = "/{id}/authorities/{authority}")
//    public ResponseEntity<Object> deleteUserAccountAuthority(@PathVariable("id") Long id, @PathVariable("authority") String authority) {
//        userAccountService.deleteUserAccountAuthority(id, authority);
//        return ResponseEntity.noContent().build();
//    }


}
