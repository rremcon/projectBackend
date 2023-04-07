package com.example.projectbackend.controller;
import com.example.projectbackend.dto.UserDto;
import com.example.projectbackend.exceptions.BadRequestException;
import com.example.projectbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @PostMapping("/create")
//    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto, BindingResult br) {
//        if (br.hasErrors()) {
//            String error = Util.reportErrors(br);
//            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//        }
//        else {
//            String createdId = userService.createUser(userDto);
//            URI uri = URI.create(
//                    ServletUriComponentsBuilder
//                            .fromCurrentContextPath()
//                            .path("/users/create/" + createdId).toUriString());
//            return ResponseEntity.created(uri).body("User " + createdId + " created!");
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        String newUsername = userService.createUser(userDto);
        userService.addUserAuthority(newUsername, "ROLE_USER");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {
        UserDto optionalUser = userService.getUser(username);
        return ResponseEntity.ok().body(optionalUser);
    }


    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userService.getUsers();
        return ResponseEntity.ok().body(userDtos);
    }


    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto) {
        userService.updateUser(username, userDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getUserAuthorities(username));
    }


    @PostMapping("/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addUserAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }


    @DeleteMapping("/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.deleteUserAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }


    ////////////

    //    @GetMapping("/users/{id}")
    @GetMapping("/welcome")
    public String sayHello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() instanceof UserDetails) {
            UserDetails ud = (UserDetails) auth.getPrincipal();
            return "Welcome USER! " + ud.getUsername();
//                    + ud.getPassword();

        } else {
            return "You are a strange USER..";
        }

    }

}
