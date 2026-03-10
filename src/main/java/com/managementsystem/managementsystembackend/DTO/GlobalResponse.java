package com.managementsystem.managementsystembackend.DTO;

import java.time.LocalDateTime;

public record GlobalResponse<T>(
        LocalDateTime timestamp,
        int status,
        String message,
        T data
) {}
