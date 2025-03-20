package vn.hcmute.testAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.testAPI.entity.Category;
import vn.hcmute.testAPI.model.Response;
import vn.hcmute.testAPI.service.ICategoryService;
import vn.hcmute.testAPI.service.IStorageService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IStorageService storageService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping(path = "/getCategory", produces = "application/json")
    public ResponseEntity<?> getCategory(@Validated @RequestParam("categoryId") Long id){
        Optional<Category> category = categoryService.findById(id);

        if(category.isPresent()){
            return new ResponseEntity<>(new Response(true, "thanh cong", category.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Response(false, "That bai", null), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/addCategory", produces = "application/json")
    public ResponseEntity<?> addCategory(@Validated @RequestParam("categoryName") String name, @Validated @RequestParam("icon") MultipartFile icon){
        Optional<Category> optionalCategory = categoryService.findByCategoryName(name);
        if(optionalCategory.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category already exists");

        }
        else {
            Category category = new Category();
            // kiem tra ton tai file, luu file
            if(!icon.isEmpty()){
                UUID uuid = UUID.randomUUID();
                String uuidString = uuid.toString();
                // luu file vao truong image
                category.setIcon(storageService.getStorageFileName(icon, uuidString));

                storageService.store(icon, category.getIcon());
            }
            category.setCategoryName(name);

            categoryService.save(category);
            return new ResponseEntity<>(new Response(true, "Them Thanh cong", category), HttpStatus.OK);
        }
    }

    @PutMapping(path = "/updateCategory", produces = "application/json")
    public ResponseEntity<?> updateCategory(@Validated @RequestParam("categoryId") Long id,
                                            @Validated @RequestParam("categoryName") String name,
                                            @Validated @RequestParam("icon") MultipartFile icon){
        Optional<Category> category = categoryService.findById(id);
        if(category.isEmpty()){
            return new ResponseEntity<>(new Response(false, "Khong tim thay category", null), HttpStatus.NOT_FOUND);
        } else if (category.isPresent()) {
            // kiem tra ton tai file, luu file
            if(!icon.isEmpty()){
                UUID uuid = UUID.randomUUID();
                String uuidString = uuid.toString();
                // luu file vao truong image
                category.get().setIcon(storageService.getStorageFileName(icon, uuidString));

                storageService.store(icon, category.get().getIcon());
            }
            category.get().setCategoryName(name);
            categoryService.save(category.get());

            return new ResponseEntity<>(new Response(true, "Cap nhat Thanh cong", category.get()), HttpStatus.OK);
        }
        return null;
    }

    @DeleteMapping(path = "/deleteCategory", produces = "application/json")
    public ResponseEntity<?> deleteCategory(@Validated @RequestParam("categoryId") Long id){
        Optional<Category> category = categoryService.findById(id);
        if(category.isEmpty()){

            return new ResponseEntity<>(new Response(false, "Khong tim thay category", null), HttpStatus.NOT_FOUND);
        } else if (category.isPresent()) {
            categoryService.delete(category.get());

            return new ResponseEntity<>(new Response(true, "Xoa thanh cong category", category.get()), HttpStatus.OK);
        }

        return null;
    }
}
