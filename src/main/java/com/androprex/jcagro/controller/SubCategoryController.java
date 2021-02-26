package com.androprex.jcagro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.androprex.jcagro.model.Subcategory;
import com.androprex.jcagro.repo.CategoryRest;
import com.androprex.jcagro.repo.KyccSubRepository;

import javax.validation.Valid;
@RestController
public class SubCategoryController {
	
	
	

	    @Autowired
	    private KyccSubRepository subRepository;

	    @Autowired
	    private CategoryRest categoryRepository;

	    @GetMapping("/category/{categoryId}/subcategory")
	    public Page<Subcategory> getAllSubcategoryByCategoryId(@PathVariable (value = "categoryId") Long categoryId,
	                                                Pageable pageable) {
	        return subRepository.findByCategoryId(categoryId, pageable);
	    }

	    @PostMapping("/category/{categoryId}/subcategory")
	    public Subcategory createSubcategory(@PathVariable (value = "categoryId") Long categoryId,
	                                 @Valid @RequestBody Subcategory subcategory) {
	        return categoryRepository.findById(categoryId).map(category -> {
	            subcategory.setCategory(category);
	            return subRepository.save(subcategory);
	        }).orElseThrow(() -> new RuntimeException("CategoryId " + categoryId + " not found"));
	    }

	    @PutMapping("/category/{categoryId}/subcategory/{subcategoryId}")
	    public Subcategory updateSubcategory(@PathVariable (value = "categoryId") Long categoryId,
	                                 @PathVariable (value = "subcategoryId") Long subcategoryId,
	                                 @Valid @RequestBody Subcategory subRequest) {
	        if(!categoryRepository.existsById(categoryId)) {
	            throw new RuntimeException("CategoryId " + categoryId + " not found");
	        }

	        return subRepository.findById(subcategoryId).map(subcategory -> {
	            subcategory.setTitle(subRequest.getTitle());
	            subcategory.setImgurl(subRequest.getImgurl());
	            return subRepository.save(subcategory);
	        }).orElseThrow(() -> new RuntimeException("SubcategoryId " + subcategoryId + "not found"));
	    }

	    @DeleteMapping("/category/{categoryId}/subcategory/{subcategoryId}")
	    public ResponseEntity<?> deleteSubcategory(@PathVariable (value = "categoryId") Long categoryId,
	                              @PathVariable (value = "subcategoryId") Long subcategoryId) {
	        return subRepository.findByIdAndCategoryId(subcategoryId, categoryId).map(subcategory -> {
	            subRepository.delete(subcategory);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new RuntimeException("Subcategory not found with id " + subcategoryId + " and CategoryId " + categoryId));
	    }
	}

