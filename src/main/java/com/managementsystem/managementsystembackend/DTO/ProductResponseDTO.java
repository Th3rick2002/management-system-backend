package com.managementsystem.managementsystembackend.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String sku,
        String name,
        Integer stockQuantity,
        String category,
        String imageUrl,
        Double weight,
        String dimensions,
        BigDecimal price,
        String description,
        UUID providerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
