package com.example.projectbackend.service;
import com.example.projectbackend.dto.UserAccountDto;
import com.example.projectbackend.model.Account;
import com.example.projectbackend.model.User;
import com.example.projectbackend.repository.AccountRepository;
import com.example.projectbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public UserAccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public void createUserAccount(UserAccountDto userAccountDto) {

        User user = new User();
        user.setUsername(userAccountDto.getUsername());
        user.setPassword(userAccountDto.getPassword());


        Account account = new Account();
        account.setId(userAccountDto.getId());
        account.setFirstname(userAccountDto.getFirstname());
        account.setLastname(userAccountDto.getLastname());
        account.setBirthdate(userAccountDto.getBirthdate());
        account.setAddress(userAccountDto.getAddress());
        account.setZipcode(userAccountDto.getZipcode());
        account.setCity(userAccountDto.getCity());
        account.setCountry(userAccountDto.getCountry());
        account.setEmail(userAccountDto.getEmail());


        //Save user to get id
        User savedUser = userRepository.save(user);

        //Set saved user to account
        account.setUser(savedUser);

        //Save account
        Account savedAccount = accountRepository.save(account);

    }

}
