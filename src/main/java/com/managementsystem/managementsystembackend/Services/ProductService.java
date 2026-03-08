package com.managementsystem.managementsystembackend.Services;

import com.managementsystem.managementsystembackend.DTO.ProductCreateDTO;
import com.managementsystem.managementsystembackend.DTO.ProductResponseDTO;
import com.managementsystem.managementsystembackend.DTO.ProductUpdateDTO;
import com.managementsystem.managementsystembackend.Entity.Product;
import com.managementsystem.managementsystembackend.Exceptions.ConflictException;
import com.managementsystem.managementsystembackend.Exceptions.NotFoundException;
import com.managementsystem.managementsystembackend.Repository.ProductRepository;
import com.managementsystem.managementsystembackend.Repository.ProviderRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Slf4j
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = this.productRepository.findAll();

        log.info("Getting all products");
        return products.stream().map(
                product -> new ProductResponseDTO(
                        product.getId(),
                        product.getSku(),
                        product.getName(),
                        product.getStockQuantity(),
                        product.getCategory(),
                        product.getImageUrl(),
                        product.getWeight(),
                        product.getDimensions(),
                        product.getPrice(),
                        product.getDescription(),
                        product.getProvider().getId(),
                        product.getCreatedAt(),
                        product.getUpdatedAt()
                )
        ).toList();
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) {
        var product = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        log.info("Getting product with id: {}", id);
        return new ProductResponseDTO(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getStockQuantity(),
                product.getCategory(),
                product.getImageUrl(),
                product.getWeight(),
                product.getDimensions(),
                product.getPrice(),
                product.getDescription(),
                product.getProvider().getId(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Override
    public ProductResponseDTO createProduct(ProductCreateDTO dto) {
        var existProduct = this.isProductExistsBySKU(dto.sku());
        var existProvider = this.providerRepository.findById(dto.providerId());

        if(existProduct)
            throw new ConflictException("Product already exists with this SKU: " + dto.sku());

        if (existProvider.isEmpty())
            throw new NotFoundException("Provider not found");

        Product product = new Product();
        product.setSku(dto.sku());
        product.setName(dto.name());
        product.setStockQuantity(dto.stockQuantity());
        product.setCategory(dto.category());
        product.setImageUrl(dto.imageUrl());
        product.setWeight(dto.weight());
        product.setImageUrl(dto.imageUrl());
        product.setIsActive(true);
        product.setWeight(dto.weight());
        product.setDimensions(dto.dimensions());
        product.setPrice(dto.price());
        product.setDescription(dto.description());
        product.setProvider(existProvider.get());

        log.info("Creating product with sku: {}", dto.sku());
        return new ProductResponseDTO(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getStockQuantity(),
                product.getCategory(),
                product.getImageUrl(),
                product.getWeight(),
                product.getDimensions(),
                product.getPrice(),
                product.getDescription(),
                product.getProvider().getId(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Override
    public ProductResponseDTO updateProduct(UUID id, ProductUpdateDTO dto) {
        var product = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        var canUpdateSKU = this.canProductSKUUpdate(dto.sku(), id);
        if(!canUpdateSKU)
            throw new ConflictException("Product already exists with this SKU: " + dto.sku());

        var existProvider = this.providerRepository.findById(dto.providerId());
        if (existProvider.isEmpty())
            throw new NotFoundException("Provider not found");

        product.setName(dto.name());
        product.setStockQuantity(dto.stockQuantity());
        product.setCategory(dto.category());
        product.setImageUrl(dto.imageUrl());
        product.setWeight(dto.weight());
        product.setImageUrl(dto.imageUrl());
        product.setIsActive(true);
        product.setWeight(dto.weight());
        product.setDimensions(dto.dimensions());
        product.setPrice(dto.price());
        product.setDescription(dto.description());
        product.setProvider(existProvider.get());

        this.productRepository.save(product);
        log.info("Updating product with id: {}", id);
        return new ProductResponseDTO(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getStockQuantity(),
                product.getCategory(),
                product.getImageUrl(),
                product.getWeight(),
                product.getDimensions(),
                product.getPrice(),
                product.getDescription(),
                product.getProvider().getId(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Override
    public void deleteProduct(UUID id) {
        var product = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        log.info("Deleting product with id: {}", id);
        this.productRepository.delete(product);
    }

    private boolean isProductExistsBySKU(String sku) {
        return this.productRepository.existsBySku(sku);
    }

    private boolean canProductSKUUpdate(String sku, UUID id) {
        return this.productRepository.existsBySkuNotAndId(sku, id);
    }
}
