package com.managementsystem.managementsystembackend.DTO;

import com.managementsystem.managementsystembackend.Entity.ProviderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProviderResponseDTO(
        UUID id,
        String name,
        String address,
        String phone,
        String email,
        ProviderStatus status,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
