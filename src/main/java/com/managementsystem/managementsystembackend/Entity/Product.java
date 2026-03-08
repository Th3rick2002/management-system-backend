package com.managementsystem.managementsystembackend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "sku")
    @NotBlank(message = "SKU code is required")
    @Size(max = 20, message = "The SKU cannot exceed 20 characters")
    private String sku;

    @Column(name = "name")
    @NotBlank
    @Size(min = 5, max = 25, message = "Name must be between 5 and 25 characters")
    private String name;

    @Column(name = "stock_quantity")
    @NotNull(message = "Stock quantity is required")
    @PositiveOrZero(message = "Stock quantity must be greater than or equal to 0")
    private Integer stockQuantity;

    @Column(name = "category")
    @NotBlank(message = "Category is required")
    @Size(min = 5, max = 25, message = "Name must be between 5 and 25 characters")
    private String category;

    @Column(name = "image_url", nullable = true)
    @URL
    private String imageUrl;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "weight")
    @NotNull(message = "Weight is required")
    @DecimalMin(value = "0.01", message = "Weight must be greater than 0")
    private Double weight;

    @Column(name = "dimensions")
    @NotBlank
    private String dimensions;

    @Column(name = "price")
    @NotNull(message = "price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @Column(name = "description")
    @NotBlank
    @Size(min = 5, max = 200, message = "Description must be between 5 and 200 characters")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;
}
