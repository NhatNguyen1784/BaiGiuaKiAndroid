package vn.hcmute.testAPI.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import vn.hcmute.testAPI.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    <S extends Category> S save(S entity);

    Optional<Category> findByCategoryName(String categoryName);

    List<Category> findAll();

    List<Category> findAll(Pageable pageable);

    List<Category> findAll(Sort sort);

    List<Category> findAllById(Iterable<Long> ids);

    Optional<Category> findById(Long id);

    <S extends Category> Optional<S> findOne(Example<S> example);

    long count();

    void deleteById(Long id);

    void delete(Category category);

    List<Category> findByCategoryNameContaining(String categoryName);

    Page<Category> findByCategoryNameContaining(String categoryName, Pageable pageable);
}
