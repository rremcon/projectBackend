package com.example.projectbackend.controller;
import com.example.projectbackend.dto.AccountDto;
import com.example.projectbackend.model.FileUploadDownload;
import com.example.projectbackend.service.AccountService;
import com.example.projectbackend.utility.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.io.File;
import java.net.URI;

@RestController
@RequestMapping("/accounts")

public class AccountController {
    private final AccountService accountService;

//    private final UploadDownloadController uploadDownloadController;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    public AccountController(AccountService accountService, UploadDownloadController uploadDownloadController) {
//        this.accountService = accountService;
//        this.uploadDownloadController = uploadDownloadController;
//    }

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountDto accountDto, BindingResult br) {
        if (br.hasErrors()) {
            String error = Util.reportErrors(br);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        else {
            //String
            Long createdId = accountService.createAccount(accountDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/accounts/register/" + createdId).toUriString());
            return ResponseEntity.created(uri).body("Account " + createdId + " created!");
        }
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long id) {
        AccountDto optionalAccount = accountService.getAccount(id);
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



    //    @PostMapping("/{id}/photo")
//    public void assignPhotoToAccount(@PathVariable("id") Long id,
//                                     @RequestBody MultipartFile file) {
//
//        FileUploadDownload photo = uploadDownloadController.uploadFile(file);
//        accountService.assignPhotoToAccount(photo.getFileName(), id);
//    }


//    //////////////////////////
//    @DeleteMapping(value = "/{id}/authorities/{authority}")
//    public ResponseEntity<Object> deleteAccountAuthority(@PathVariable("id") Long id, @PathVariable("authority") String authority) {
//        accountService.deleteAccountAuthority(id, authority);
//        return ResponseEntity.noContent().build();
//    }

//
//    @GetMapping("/afterbirthdate")
//    public ResponseEntity<Iterable<Account>> getAccountsBirthdate(
//            @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date) {
//        return ResponseEntity.ok(repos.findByBirthdate(date));
//    }


//        @GetMapping("/findbylastname")
//    public ResponseEntity<Iterable<Account>> getAccountsContaining(
//            @RequestParam String query) {
//        return ResponseEntity.ok(repos.findByLastNameContaining(query));
//    }
/////////////////////////

}

