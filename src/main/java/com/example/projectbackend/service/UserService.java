package com.example.projectbackend.service;
import com.example.projectbackend.dto.UserDto;
import com.example.projectbackend.exceptions.UserNotFoundException;
import com.example.projectbackend.model.Authority;
import com.example.projectbackend.model.User;
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
    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setEmail((userDto.getEmail()));
        userDto.setUsername((userDto.getUsername()));
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userDto.setEnabled(true);
        userDto.setApikey(randomString);
        User newUser = userRepository.save(toUser(userDto));
        return newUser.getUsername();
    }

//    public String createUser (UserDto userDto)
//    {
//        User newUser = new User();
//        newUser.setUsername(userDto.username);
//        newUser.setPassword(encoder.encode(userDto.password));
//
//        List<Authority> userAuthorities = new ArrayList<>();
//        for (Authority authority : userDto.authorities) {
//            Optional<Authority> or = authorityRepository.findById(authority);
//
//            userAuthorities.add(or.get());
//        }
//        newUser.setAuthorities(userAuthorities);
//
//        userRepository.save(newUser);
//
//        return "succeed";
//    }

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


//    public Iterable<UserDto> getUsers() {
//        Iterable<User> allUsers = userRepository.findAll();
//        ArrayList<UserDto> userDtoList = new ArrayList<>();
//        for (User user : allUsers) {

//            UserDto userDto = new UserDto();
//            userDto.username = user.getUsername();
//            userDto.password = user.getPassword();
//
//            userDto.enabled = user.isEnabled();
//            userDto.apikey = user.getApikey();
//            userDto.email = user.getEmail();
//            userDto.authorities = user.getAuthorities();
//
//            userDtoList.add(userDto);
//        }
//        return userDtoList;
//    }

    public List<UserDto> getUsers() {
        Iterable<User> allUsers = userRepository.findAll();
        ArrayList<UserDto> userDtoList = new ArrayList<>();

        for (User user : allUsers) {

//            UserDto userDto = new UserDto();
//            userDto.username = user.getUsername();
//            userDto.password = user.getPassword();

            userDtoList.add(fromUser(user));
        }
        return userDtoList;
    }


    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.setPassword(encoder.encode(newUser.getPassword()));
        if (newUser.getEnabled() != null) {
            user.setEnabled(newUser.getEnabled());
        }
        user.setEnabled(newUser.getEnabled());
        user.setApikey(newUser.getApikey());
        user.setEmail(newUser.getEmail());
        userRepository.save(user);
    }


    public boolean userExists(String username) {
        return userRepository.existsById(username);
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
        user.addAuthority(new Authority(username, authority));
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
        userDto.enabled = user.isEnabled();
        userDto.apikey = user.getApikey();
        userDto.email = user.getEmail();
        userDto.authorities = user.getAuthorities();

        return userDto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

}
