package vn.hcmute.testAPI.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.hcmute.testAPI.entity.Category;
import vn.hcmute.testAPI.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements vn.hcmute.testAPI.service.ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public <S extends Category> S save(S entity) {
        if(entity.getCategoryId() == null) {
            return categoryRepository.save(entity);
        }
        else {
            Optional<Category> category = categoryRepository.findById(entity.getCategoryId());
            if(category.isPresent()) {
                if(StringUtils.isEmpty(entity.getIcon())) {
                    entity.setIcon(category.get().getIcon());
                }
                else {
                    // lay icon cu
                    entity.setIcon(entity.getIcon());
                }
            }
            return categoryRepository.save(entity);
        }
    }

    @Override
    public Optional<Category> findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllById(Iterable<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return categoryRepository.findOne(example);
    }

    @Override
    public long count(){
        return categoryRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> findByCategoryNameContaining(String categoryName) {
        return categoryRepository.findByCategoryNameContaining(categoryName);
    }

    @Override
    public Page<Category> findByCategoryNameContaining(String categoryName, Pageable pageable) {
        return categoryRepository.findByCategoryNameContaining(categoryName, pageable);
    }
}
