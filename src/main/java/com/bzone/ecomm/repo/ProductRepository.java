package com.bzone.ecomm.repo;

import com.bzone.ecomm.dto.Search;
import com.bzone.ecomm.entiry.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    @Query(value = "SELECT DISTINCT new com.bzone.ecomm.dto.Search(p.productId as id, p.name, p.price) FROM Products p" +
            " LEFT JOIN p.categories c" +
            " LEFT JOIN p.tags t" +
//            " LEFT JOIN c.subCategories sc" +
            " WHERE LOWER(CONCAT(COALESCE(p.name,''), '', '',COALESCE(c.name,''), '',COALESCE(t.name,''), '',COALESCE(t.description,''))) LIKE LOWER(concat('%', :keyword, '%'))")
    public List<Search> productSearch(@Param("keyword") String keyword);
}
