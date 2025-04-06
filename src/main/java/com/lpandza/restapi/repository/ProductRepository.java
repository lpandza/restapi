package com.lpandza.restapi.repository;

import com.lpandza.restapi.dto.PopularProduct;
import com.lpandza.restapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByCode(String code);

    @Query(value = """
                   select p.name as name, avg(r.rating) as avgRating
                   from review r
                   inner join product p on p.id = r.product_id
                   group by p.id
                   order by avgRating desc
                   limit 3
                   """, nativeQuery = true)
    List<PopularProduct> findTopProductsByAverageRating();
}
