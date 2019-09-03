package com.mart.billing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mart.billing.repo.model.ProductDo;

@Repository
public interface ProductRepository extends JpaRepository<ProductDo, Long> {

}
