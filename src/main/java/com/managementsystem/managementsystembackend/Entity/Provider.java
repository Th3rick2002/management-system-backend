package com.managementsystem.managementsystembackend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "providers")
@SQLDelete(sql = "UPDATE providers SET deleted_at = now() WHERE id = ?")
@SQLRestriction(value = "deleted_at IS NULL")
public class Provider extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    @NotBlank
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Column(name = "address")
    @NotBlank
    @Size(min = 5, max = 50, message = "Address must be between 5 and 50 characters")
    private String address;

    @Column(name = "phone")
    @NotBlank
    @Size(min = 8, max = 20, message = "Phone must be between 10 and 20 characters")
    private String phone;

    @Column(name = "email")
    @NotBlank
    @Email(message = "Email must be valid")
    private String email;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "PROVIDER_STATUS_ENUM")
    @NotNull(message = "Status is required")
    private ProviderStatus status;

    @Column(name = "description")
    @NotBlank
    @Size(min = 5, max = 200, message = "Description must be between 5 and 200 characters")
    private String description;
}
