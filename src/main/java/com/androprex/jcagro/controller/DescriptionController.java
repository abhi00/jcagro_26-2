package com.androprex.jcagro.controller;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.androprex.jcagro.model.ProductDescription;
import com.androprex.jcagro.repo.DescriptionRepository;
import com.androprex.jcagro.repo.KyccSubRepository;



@RestController
public class DescriptionController {

    @Autowired
    private KyccSubRepository subRepository;

    @Autowired
    private DescriptionRepository descriptionRepository;

    @GetMapping("/subcategory/{subcategoryId}/des")
    public Page<ProductDescription> getAllProductDescriptionBySubcategoryId(@PathVariable (value = "subcategoryId") Long subcategoryId,
                                                Pageable pageable) {
        return descriptionRepository.findBySubcategoryId(subcategoryId,pageable);
    }
    @PostMapping("/subcategory/{subcategoryId}/des")
    public ProductDescription createProductDescription(@PathVariable (value = "subcategoryId") Long subcategoryId,
                                 @Valid @RequestBody ProductDescription des) {
        return subRepository.findById(subcategoryId).map(subcategory -> {
            des.setSubcategory(subcategory);
            return descriptionRepository.save(des);
        }).orElseThrow(() -> new RuntimeException("SubcategoryId " + subcategoryId + " not found"));
    }
    @PutMapping("/subcategory/{subcategoryId}/des/{desId}")
    public ProductDescription updateProductDescription(@PathVariable (value = "subcategoryId") Long subcategoryId,
                                 @PathVariable (value = "desId") Long desId,
                                 @Valid @RequestBody ProductDescription desRequest) {
        if(!subRepository.existsById(subcategoryId)) {
            throw new RuntimeException("SubcategoryId " + subcategoryId + " not found");
        }

        return descriptionRepository.findById(desId).map(des -> {
            des.setDescription(desRequest.getDescription());
            des.setVariety(desRequest.getVariety());
            des.setLocation(desRequest.getLocation());
            des.setSeason(desRequest.getSeason());
            des.setProperties(desRequest.getProperties());
            return descriptionRepository.save(des);
        }).orElseThrow(() -> new RuntimeException("ProductDescriptionId " + desId + "not found"));
    }
    @DeleteMapping("/subcategory/{subcategoryId}/des/{desId}")
    public ResponseEntity<?> deleteProductDescription(@PathVariable (value = "subcategoryId") Long subcategoryId,
                              @PathVariable (value = "desId") Long desId) {
        return descriptionRepository.findByIdAndSubcategoryId(desId,subcategoryId).map(des -> {
            descriptionRepository.delete(des);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Product Description not found with id " + desId + " and SubCategoryId " + subcategoryId));
    }
}
