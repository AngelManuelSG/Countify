package com.amsg.countify.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CategoryRequest(

        @NotBlank(message = "El nombre de la categoría es obligatorio")
        String catName,

        String catDescription,

        @Pattern(
                regexp = "^#([A-Fa-f0-9]{6})$",
                message = "El color debe ser un código HEX válido de 6 dígitos (ej. #FF5733)"
        )
        String color
) {
}
