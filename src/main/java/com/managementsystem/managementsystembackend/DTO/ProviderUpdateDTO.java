package com.managementsystem.managementsystembackend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record ProviderUpdateDTO(
        @Size(message = "Name must be between 5 and 50 characters", min = 5, max = 50)
        String name,

        @Size(message = "Address must be between 5 and 50 characters", min = 5, max = 50)
        String address,

        @Size(message = "Phone must be between 10 and 20 characters", min = 8, max = 20)
        String phone,

        @Email(message = "Email must be valid")
        String email,

        @Size(message = "Description must be between 5 and 200 characters", min = 5, max = 200)
        String description
) {
}
