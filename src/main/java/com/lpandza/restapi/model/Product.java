package com.lpandza.restapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 15, columnDefinition = "CHAR(15)")
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "price_eur", precision = 18, scale = 6)
    private BigDecimal priceEur;

    @Column(nullable = false, name = "price_usd", precision = 18, scale = 6)
    private BigDecimal priceUsd;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    public Product(String code, String name, BigDecimal priceEur, BigDecimal priceUsd, String description) {
        this.code = code;
        this.name = name;
        this.priceEur = priceEur;
        this.priceUsd = priceUsd;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
