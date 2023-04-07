package com.example.projectbackend.controller;
import com.example.projectbackend.service.JwtService;
import com.example.projectbackend.payload.AuthenticationRequest;
import com.example.projectbackend.payload.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@RestController
public class AuthenticationController {
    private final AuthenticationManager authManager;
    private UserDetailsService userDetailsService;
    private final JwtService jwtService;


    public AuthenticationController(AuthenticationManager authManager, UserDetailsService userDetailsService, JwtService jwtService) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        final String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }

}

