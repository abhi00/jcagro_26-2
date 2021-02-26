package com.androprex.jcagro.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.androprex.jcagro.model.ProductDescription;


@Repository
public interface DescriptionRepository extends JpaRepository<ProductDescription, Long> {
	Page<ProductDescription> findBySubcategoryId(Long subcategoryId, Pageable pageable);
	    Optional<ProductDescription> findByIdAndSubcategoryId(Long id, Long subcategoryId);

}
