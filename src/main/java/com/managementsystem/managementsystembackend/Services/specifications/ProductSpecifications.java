package com.managementsystem.managementsystembackend.Services.specifications;

import com.managementsystem.managementsystembackend.Entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> nameContains(String name) {
        return (root, query, cb) ->
                name == null
                        ? null
                        : cb.like(
                                cb.lower(root.get("name")),
                                "%" + name.toLowerCase() + "%"
                        );
    }

    public static Specification<Product> categoryEquals(String category) {
        return (root, query, cb) ->
                category == null
                        ? null
                        : cb.equal(
                                root.get("category"),
                                category
                        );
    }

}
