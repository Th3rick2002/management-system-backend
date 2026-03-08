package com.managementsystem.managementsystembackend.Services;

import com.managementsystem.managementsystembackend.DTO.ProductCreateDTO;
import com.managementsystem.managementsystembackend.DTO.ProductResponseDTO;
import com.managementsystem.managementsystembackend.DTO.ProductUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(UUID id);
    ProductResponseDTO createProduct(ProductCreateDTO dto);
    ProductResponseDTO updateProduct(UUID id, ProductUpdateDTO dto);
    void deleteProduct(UUID id);
}
