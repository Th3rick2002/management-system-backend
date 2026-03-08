package com.managementsystem.managementsystembackend.Repository;

import com.managementsystem.managementsystembackend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsBySku(String sku);

    boolean existsBySkuNotAndId(String sku, UUID id);
}
