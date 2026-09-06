package com.amsg.countify.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Debe introducir su nombre de usuario")
        String userName,

        @NotBlank(message = "Debe introducir su contraseña")
        String password
) {
}
