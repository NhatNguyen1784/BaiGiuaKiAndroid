package vn.hcmute.testAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hcmute.testAPI.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // tim theo ten
    List<Product> findByProductNameContaining(String name);

    // tim kiem va phan trang
    Page<Product> findByProductNameContaining(String name, Pageable pageable);
    Optional<Product> findByProductName(String name);
    Optional<Product> findByProductId(Integer id);

    @Query("SELECT p FROM Product p ORDER BY p.createdAt DESC LIMIT :limit")
    List<Product> findLastestProducts(int limit);
}
