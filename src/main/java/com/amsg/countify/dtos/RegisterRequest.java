package com.amsg.countify.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * dto which the user will send us for registration
 * @param userName String with the name
 * @param email String with the email
 * @param password String with the password
 * @param birthDate LocalDate with format dd/mm/yyyy
 * @param phone String with a format
 * @param profilePicture String with the url of the photo.
 */
public record RegisterRequest(

        @NotBlank(message = "El nombre de usuario es obligatorio")
        String userName,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato de email no es válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        String password,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Past(message = "No has podido nacer en el futuro...")
        LocalDate birthDate,

        // It's optional. With a format given by the shown pattern
        @Pattern(regexp = "^\\+?[1-9]\\d{8,14}$", message = "El formato de teléfono no es válido")
        String phone,

        // Optional
        String profilePicture

) {}
