package com.amsg.countify.services;

import com.amsg.countify.dtos.AuthResponse;
import com.amsg.countify.dtos.LoginRequest;
import com.amsg.countify.dtos.RegisterRequest;
import com.amsg.countify.entities.AppUser;
import com.amsg.countify.repositories.AppUserRepository;
import com.amsg.countify.security.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public AuthService(AppUserRepository appUserRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JWTUtils jwtUtils){
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public void register(RegisterRequest request) {
        // 1. Validar si el usuario o email ya existen
        if (appUserRepository.existsByUserName(request.userName())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        // 2. Validar edad mínima (18 años)
        int age = Period.between(request.birthDate(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("Debes tener al menos 18 años para registrarte");
        }

        // 3. Mapear DTO a la Entidad AppUser
        AppUser user = new AppUser();
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setEncryptedPassword(passwordEncoder.encode(request.password()));
        user.setBirthDate(request.birthDate());
        user.setPhone(request.phone());
        user.setProfilePicture(request.profilePicture());

        // 4. Guardar en la base de datos
        appUserRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        // 1. Autenticar usuario con Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.userName(), request.password())
        );

        // 2. Generar el Token JWT
        String token = jwtUtils.generateToken(request.userName());

        // 3. Retornar DTO con el Token
        return new AuthResponse(token, "Bearer");
    }
}
