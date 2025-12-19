package com.quangminh.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangminh.laptopshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product newProduct);

    List<Product> findAll();

    Product findById(long id);

    Product deleteById(long id);
}
