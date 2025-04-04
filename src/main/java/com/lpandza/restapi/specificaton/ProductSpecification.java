package com.lpandza.restapi.specificaton;

import com.lpandza.restapi.model.Product;
import com.lpandza.restapi.request.ProductFilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductSpecification implements Specification<Product> {

    ProductFilterRequest productFilterRequest;

    public ProductSpecification(ProductFilterRequest productFilterRequest) {
        this.productFilterRequest = productFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!Objects.isNull(productFilterRequest.code())) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("code")),
                            wrapToLike(productFilterRequest.code()).toLowerCase()
                    )
            );
        }

        if (!Objects.isNull(productFilterRequest.name())) {
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")),
                            wrapToLike(productFilterRequest.name()).toLowerCase()
                    )
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private String wrapToLike(String value) {
        return "%" + value + "%";
    }
}
