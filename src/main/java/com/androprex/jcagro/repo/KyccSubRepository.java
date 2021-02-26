package com.androprex.jcagro.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.androprex.jcagro.model.Subcategory;



@Repository
public interface KyccSubRepository extends JpaRepository<Subcategory, Long> {
	 Page<Subcategory> findByCategoryId(Long categoryId, Pageable pageable);
	    Optional<Subcategory> findByIdAndCategoryId(Long id, Long categoryId);

}
