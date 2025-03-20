package vn.hcmute.testAPI.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.testAPI.entity.Product;
import vn.hcmute.testAPI.repository.ProductRepository;
import vn.hcmute.testAPI.service.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getLastestProducts(int limit){
        return productRepository.findLastestProducts(limit);
    }

    @Override
    public Optional<Product> findByProductName(String productName){
        return productRepository.findByProductName(productName);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }
}
