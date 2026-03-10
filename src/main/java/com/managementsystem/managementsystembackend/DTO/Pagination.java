package com.managementsystem.managementsystembackend.DTO;

public record Pagination(
        int limit,
        int offset,
        int currentPage,
        long totalItems,
        int totalPages
) {
}
