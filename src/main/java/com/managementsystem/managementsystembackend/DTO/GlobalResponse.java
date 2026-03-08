package com.managementsystem.managementsystembackend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


public record GlobalResponse<T>(
        LocalDateTime timestamp,
        int status,
        String message,
        T data
) {}
