package com.managementsystem.managementsystembackend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ProviderCreateDTO(
        @Size(message = "Name must be between 5 and 50 characters", min = 5, max = 50)
        @NotEmpty(message = "The name is required")
        @NotBlank
        String name,

        @Size(message = "Address must be between 5 and 50 characters", min = 5, max = 50)
        @NotBlank
        String address,

        @Size(message = "Phone must be between 10 and 20 characters", min = 10, max = 20)
        @NotBlank
        String phone,

        @Email(message = "Email must be valid")
        @NotBlank
        String email,

        @Size(message = "Description must be between 5 and 200 characters", min = 5, max = 200)
        @NotEmpty
        @NotBlank
        String description
) {
}
