package com.managementsystem.managementsystembackend.Controller;

import com.managementsystem.managementsystembackend.DTO.ProductCreateDTO;
import com.managementsystem.managementsystembackend.DTO.ProductResponseDTO;
import com.managementsystem.managementsystembackend.DTO.ProductUpdateDTO;
import com.managementsystem.managementsystembackend.Services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return ResponseEntity.ok( productService.getAllProducts() );
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody ProductCreateDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.createProduct(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable UUID id,
            @RequestBody ProductUpdateDTO dto
    ) {
        return ResponseEntity.ok(this.productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable UUID id
    ) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
