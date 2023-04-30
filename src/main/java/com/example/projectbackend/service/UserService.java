package com.example.projectbackend.service;
import com.example.projectbackend.dto.UserAccountDto;
import com.example.projectbackend.dto.UserDto;
import com.example.projectbackend.exceptions.UserNotFoundException;
import com.example.projectbackend.model.Account;
import com.example.projectbackend.model.Authority;
import com.example.projectbackend.model.User;
import com.example.projectbackend.repository.AccountRepository;
import com.example.projectbackend.repository.UserRepository;
import com.example.projectbackend.utility.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    @Autowired
    @Lazy
    private PasswordEncoder encoder;
    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public String createUser(UserAccountDto userAccountDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);

        User user = new User();
        user.setEmail(userAccountDto.getEmail());
        user.setUsername(userAccountDto.getUsername());
        user.setPassword(encoder.encode(userAccountDto.getPassword()));

        Account account = new Account();
        account.setFirstname(userAccountDto.getFirstname());
        account.setLastname(userAccountDto.getLastname());
        account.setAddress(userAccountDto.getAddress());
        account.setZipcode(userAccountDto.getZipcode());
        account.setBirthdate(userAccountDto.getBirthdate());
        account.setCity(userAccountDto.getCity());
        account.setCountry(userAccountDto.getCountry());
        account.setEmail(userAccountDto.getEmail());
        account.setPassword(encoder.encode(userAccountDto.getPassword()));
        account.setUsername(userAccountDto.getUsername());

        account.setUser(user);

        User newUser = userRepository.save(user);
        accountRepository.save(account);
        return newUser.getUsername();
    }

    public UserDto getUser(String username) {
        UserDto userDto;
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            userDto = fromUser(user.get());
        }else {
            throw new UserNotFoundException(username);
        }
        return userDto;
    }

    public List<UserDto> getUsers() {
        Iterable<User> allUsers = userRepository.findAll();
        ArrayList<UserDto> userDtoList = new ArrayList<>();

        for (User user : allUsers) {
//            UserDto userDto = new UserDto();
//            userDto.username = user.getUsername();
            userDtoList.add(fromUser(user));
        }
        return userDtoList;
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.setEmail(newUser.getEmail());
        userRepository.save(user);
    }

    public void deleteUser(@RequestBody String username) {userRepository.deleteById(username);}

    public Set<Authority> getUserAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addUserAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(authority, username));
        userRepository.save(user);
    }

    public void deleteUserAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public static UserDto fromUser(User user){

        var userDto = new UserDto();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.email = user.getEmail();
        userDto.authorities = user.getAuthorities();

        return userDto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        return user;
    }

}
