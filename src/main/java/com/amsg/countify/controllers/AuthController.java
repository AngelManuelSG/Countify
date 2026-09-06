package com.amsg.countify.controllers;

import com.amsg.countify.dtos.AuthResponse;
import com.amsg.countify.dtos.LoginRequest;
import com.amsg.countify.dtos.RegisterRequest;
import com.amsg.countify.entities.AppUser;
import com.amsg.countify.repositories.AppUserRepository;
import com.amsg.countify.security.JWTUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request){

        // Checking the username
        if (appUserRepository.existsByUserName(request.userName())){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Error: El nombre de usuario ya está en uso"));
        }

        // Map DTO to entity
        AppUser newUser = new AppUser();
        newUser.setUserName(request.userName());
        newUser.setEmail(request.email());
        newUser.setPhone(request.phone());
        newUser.setBirthDate(request.birthDate());
        newUser.setProfilePicture(request.profilePicture());
        // Encrypting the password for storing at the DB
        String encryptedPassword = passwordEncoder.encode(request.password());
        newUser.setEncryptedPassword(encryptedPassword);
        // Save the new user at the DB
        appUserRepository.save(newUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "Usuario creado correctamente"));
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
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        //Verify the user and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.userName(), request.password()));
        //Generate the token
        String jwt = jwtUtils.generateToken(authentication.getName());
        //Returning response with jwt
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
