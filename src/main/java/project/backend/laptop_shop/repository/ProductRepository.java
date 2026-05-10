package project.backend.laptop_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.backend.laptop_shop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product newProduct);

    List<Product> findAll();

    Product findById(long id);

    Product deleteById(long id);
}
