package com.androprex.jcagro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.androprex.jcagro.model.Category;


@Repository
public interface CategoryRest extends JpaRepository<Category, Long> {

	

}
