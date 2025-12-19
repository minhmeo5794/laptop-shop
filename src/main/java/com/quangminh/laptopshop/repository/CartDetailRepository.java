package com.quangminh.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangminh.laptopshop.domain.Cart;
import com.quangminh.laptopshop.domain.CartDetail;
import com.quangminh.laptopshop.domain.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsByCartAndProduct(Cart cart, Product product);

    CartDetail findByCartAndProduct(Cart cart, Product product);

    CartDetail findById(long id);

    void deleteById(long id);

    void deleteAllByCart(Cart cart);
}
