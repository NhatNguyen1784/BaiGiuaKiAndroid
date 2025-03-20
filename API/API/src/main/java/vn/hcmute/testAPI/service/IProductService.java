package vn.hcmute.testAPI.service;

import vn.hcmute.testAPI.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getLastestProducts(int limit);
    Optional<Product> findByProductName(String productName);
    <S extends Product> S save(S entity);
}
