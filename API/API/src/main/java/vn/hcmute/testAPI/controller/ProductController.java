package vn.hcmute.testAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.testAPI.entity.Category;
import vn.hcmute.testAPI.entity.Product;
import vn.hcmute.testAPI.model.Response;
import vn.hcmute.testAPI.service.ICategoryService;
import vn.hcmute.testAPI.service.IProductService;
import vn.hcmute.testAPI.service.IStorageService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private IStorageService storageService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getLastestProduct() {
        return ResponseEntity.ok(productService.getLastestProducts(10));
    }

    @PostMapping(path = "/addProduct", produces = "application/json")
    public ResponseEntity<?> addCategory(@Validated @RequestParam("productName") String name,
                                         @Validated @RequestParam("description") String description,
                                         @Validated @RequestParam("price") double price,
                                         @Validated @RequestParam("image") MultipartFile icon,
                                         @Validated @RequestParam("categoryId") Long categoryId){
        Optional<Product> optionalProduct = productService.findByProductName(name);
        if(optionalProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category already exists");

        }
        // Kiểm tra category có tồn tại không
        Optional<Category> categoryOpt = categoryService.findById(categoryId);
        if (categoryOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
        }

        Product product = new Product();
        // kiem tra ton tai file, luu file
        if(!icon.isEmpty()){
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            // luu file vao truong image
            product.setImage(storageService.getStorageFileName(icon, uuidString));

            storageService.store(icon, product.getImage());
        }
        product.setProductName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(categoryOpt.get());

        productService.save(product);
        return new ResponseEntity<>(new Response(true, "Them Thanh cong", product), HttpStatus.OK);
    }
}