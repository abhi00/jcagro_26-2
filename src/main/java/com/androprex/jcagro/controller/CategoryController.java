package com.androprex.jcagro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.androprex.jcagro.model.Category;
import com.androprex.jcagro.repo.CategoryRest;



@RestController
public class CategoryController {

    @Autowired
    private CategoryRest categoryRepository;

    @GetMapping("/category")
    public Page<Category> getAllPosts(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @PostMapping("/category")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/category/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @Valid @RequestBody Category categoryRequest) {
        return categoryRepository.findById(categoryId).map(category -> {
        	category.setTitle(categoryRequest.getTitle());
        	category.setImgurl(categoryRequest.getImgurl());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("CategoryId " + categoryId + " not found"));
    }


    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        return categoryRepository.findById(categoryId).map(category -> {
            categoryRepository.delete(category);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("CategoryId " + categoryId + " not found"));
    }

}
