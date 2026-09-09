package com.amsg.countify.dtos;

import com.amsg.countify.entities.Category;

public record CategoryResponse(
        String catName,
        String catDescription,
        String color

) {

    // Mapping from Entity to CategoryResponse
    public static CategoryResponse fromEntity(Category category) {
        return new CategoryResponse(
                category.getCatName(),
                category.getCatDescription(),
                category.getColor()
        );
    }
}