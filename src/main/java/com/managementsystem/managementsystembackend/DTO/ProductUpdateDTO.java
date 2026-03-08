package com.managementsystem.managementsystembackend.DTO;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductUpdateDTO(
        @Size(message = "SKU must be between 5 and 20 characters", min = 5, max = 20)
        String sku,

        @Size(message = "Name must be between 5 and 25 characters", min = 5, max = 25)
        String name,

        @PositiveOrZero(message = "Stock quantity must be greater than or equal to 0")
        Integer stockQuantity,

        @Size(message = "Category must be between 5 and 25 characters", min = 5, max = 25)
        String category,

        @URL
        String imageUrl,

        @PositiveOrZero(message = "Price must be greater than or equal to 0")
        Double weight,

        @Size(message = "Category must be between 5 and 25 characters", min = 5, max = 25)
        String dimensions,

        @PositiveOrZero(message = "Price must be greater than or equal to 0")
        BigDecimal price,

        @Size(message = "Description must be between 5 and 200 characters", min = 5, max = 200)
        String description,

        UUID providerId
) {
}
