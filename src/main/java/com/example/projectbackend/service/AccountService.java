package com.example.projectbackend.service;
import com.example.projectbackend.dto.AccountDto;
import com.example.projectbackend.exceptions.UserNotFoundException;
import com.example.projectbackend.model.Account;
import com.example.projectbackend.model.Authority;
import com.example.projectbackend.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long createAccount(AccountDto accountDto)
    {
        Account account = new Account();
        account.setFirstname(accountDto.firstname);
        account.setLastname(accountDto.lastname);
        account.setBirthdate(accountDto.birthdate);
        account.setAddress(accountDto.address);
        account.setZipcode(accountDto.zipcode);
        account.setCity(accountDto.city);
        account.setCountry(accountDto.country);
        account.setEmail(accountDto.email);
        account.setUsername(accountDto.username);
        account.setPassword(accountDto.password);

        Account savedAccount = accountRepository.save(toAccount(accountDto));
        return savedAccount.getId();
    }

    public AccountDto getAccount(String username) {
        AccountDto accountDto;
        Optional <Account> account = accountRepository.findByUsername(username);
        if (account.isPresent()){
            accountDto = fromAccount(account.get());
        }else {
            throw new UserNotFoundException();
        }
        return accountDto;
    }

    public Iterable<AccountDto> getAccounts() {
        Iterable<Account> allAccounts = accountRepository.findAll();
        ArrayList<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : allAccounts) {

            AccountDto accountDto = new AccountDto();
            accountDto.id = account.getId();
            accountDto.firstname = account.getFirstname();
            accountDto.lastname = account.getLastname();
            accountDto.birthdate = account.getBirthdate();
            accountDto.address = account.getAddress();
            accountDto.zipcode = account.getZipcode();
            accountDto.city = account.getCity();
            accountDto.country = account.getCountry();
            accountDto.email = account.getEmail();
            accountDto.username = account.getUsername();
            accountDto.password = account.getPassword();

            accountDtoList.add(accountDto);
        }
        return accountDtoList;
    }

    public void updateAccount(Long id, AccountDto newAccount) {
        if (!accountRepository.existsById(id)) throw new UserNotFoundException();
        Account account = accountRepository.findById(id).get();

        account.setId(newAccount.getId());
        account.setFirstname(newAccount.getFirstname());
        account.setLastname(newAccount.getLastname());
        account.setBirthdate(newAccount.getBirthdate());
        account.setAddress(newAccount.getAddress());
        account.setZipcode(newAccount.getZipcode());
        account.setCity(newAccount.getCity());
        account.setCountry(newAccount.getCountry());
        account.setEmail(newAccount.getEmail());
        account.setUsername(newAccount.getUsername());
        account.setPassword(newAccount.getPassword());

        accountRepository.save(account);
    }

    public void deleteAccount(@RequestBody Long id) {
        accountRepository.deleteById(id);
    }

    public Set<Authority> getAccountAuthorities(Long id) {
        if (!accountRepository.existsById(id)) throw new UserNotFoundException();
        Account account = accountRepository.findById(id).get();
        return account.getUser().getAuthorities();
    }

    public static AccountDto fromAccount(Account account){

        var accountDto = new AccountDto();
        accountDto.id = account.getId();
        accountDto.firstname = account.getFirstname();
        accountDto.lastname = account.getLastname();
        accountDto.birthdate = account.getBirthdate();
        accountDto.address = account.getAddress();
        accountDto.zipcode = account.getZipcode();
        accountDto.city = account.getCity();
        accountDto.country = account.getCountry();
        accountDto.email = account.getEmail();
        accountDto.username = account.getUsername();
        accountDto.password = account.getPassword();

        accountDto.userDto = UserService.fromUser(account.getUser());
        return accountDto;
    }

    public Account toAccount(AccountDto accountDto) {

        var account = new Account();
        account.setId(accountDto.getId());
        account.setFirstname(accountDto.getFirstname());
        account.setLastname(accountDto.getLastname());
        account.setBirthdate(accountDto.getBirthdate());
        account.setAddress(accountDto.getAddress());
        account.setZipcode(accountDto.getZipcode());
        account.setCity(accountDto.getCity());
        account.setCountry(accountDto.getCountry());
        account.setEmail(accountDto.getEmail());
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());

        return account;
    }

}