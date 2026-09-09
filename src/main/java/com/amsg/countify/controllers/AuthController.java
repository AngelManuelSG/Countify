package com.amsg.countify.controllers;

import com.amsg.countify.dtos.AuthResponse;
import com.amsg.countify.dtos.LoginRequest;
import com.amsg.countify.dtos.RegisterRequest;
import com.amsg.countify.repositories.AppUserRepository;
import com.amsg.countify.security.JWTUtils;
import com.amsg.countify.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }



    // ------------------------------------------------------------------------------
    // REGISTER ENDPOINT
    // ------------------------------------------------------------------------------
    // This endpoint checks if the user can be created and returns a response
    //
    // Responses:
    // 400 BAD REQUEST      -- not valid email, birthday, password...
    // 409 CONFLICT         -- username not available
    // 201 CREATED          -- user created correctly
    // 500 UNEXPECTED ERROR -- otherwise
    // ------------------------------------------------------------------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Usuario registrado exitosamente"));
    }


    // ------------------------------------------------------------------------------
    // LOGIN ENDPOINT
    // ------------------------------------------------------------------------------
    // This endpoint controls the login of the user
    // Responses:
    // 401 UNAUTHORIZED     -- userName or password is not correct
    // 200 OK + JWT         -- everything ok
    // 500 UNEXPECTED ERROR -- otherwise
    // -----------------------------------------------------------------------------
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

}
