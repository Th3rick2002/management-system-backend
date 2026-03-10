package com.managementsystem.managementsystembackend.DTO;

import java.util.List;

public record PaginatedResponse<T>(
        List<T> data,
        Pagination pagination
) {}
