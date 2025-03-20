package vn.hcmute.testAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmute.testAPI.entity.Category;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryNameContaining(String categoryName);

    // tim kiem va phan trang
    Page<Category> findByCategoryNameContaining(String categoryName, Pageable pageable);
    Optional<Category> findByCategoryName(String categoryName);
}
