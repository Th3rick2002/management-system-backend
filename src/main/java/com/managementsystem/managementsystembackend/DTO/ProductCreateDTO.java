package com.managementsystem.managementsystembackend.DTO;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCreateDTO(
        @NotBlank
        @NotEmpty(message = "The SKU is required")
        @Size(message = "SKU must be between 5 and 20 characters", min = 5, max = 20)
        String sku,

        @NotBlank
        @NotEmpty(message = "The name is required")
        @Size(message = "Name must be between 5 and 25 characters", min = 5, max = 25)
        String name,

        @NotNull
        @PositiveOrZero(message = "Stock quantity must be greater than or equal to 0")
        Integer stockQuantity,

        @NotBlank
        @NotEmpty(message = "The category is required")
        @Size(message = "Category must be between 5 and 25 characters", min = 5, max = 25)
        String category,

        @URL
        String imageUrl,

        @NotNull
        @PositiveOrZero(message = "Price must be greater than or equal to 0")
        Double weight,

        @NotBlank
        @NotEmpty(message = "The dimensions are required")
        @Size(message = "Category must be between 5 and 25 characters", min = 5, max = 25)
        String dimensions,

        @NotNull
        @PositiveOrZero(message = "Price must be greater than or equal to 0")
        BigDecimal price,

        @NotBlank
        @NotEmpty(message = "The description is required")
        @Size(message = "Description must be between 5 and 200 characters", min = 5, max = 200)
        String description,

        UUID providerId

) {
}
