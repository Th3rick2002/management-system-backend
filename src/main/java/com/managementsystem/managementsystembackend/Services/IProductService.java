package com.managementsystem.managementsystembackend.Services;

import com.managementsystem.managementsystembackend.DTO.PaginatedResponse;
import com.managementsystem.managementsystembackend.DTO.ProductCreateDTO;
import com.managementsystem.managementsystembackend.DTO.ProductResponseDTO;
import com.managementsystem.managementsystembackend.DTO.ProductUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    PaginatedResponse<ProductResponseDTO> getAllProducts(String name, String category, int limit, int offset, String sort, String order);
    ProductResponseDTO getProductById(UUID id);
    ProductResponseDTO createProduct(ProductCreateDTO dto);
    ProductResponseDTO updateProduct(UUID id, ProductUpdateDTO dto);
    void deleteProduct(UUID id);
}
