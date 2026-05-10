package project.backend.laptop_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.backend.laptop_shop.entity.Cart;
import project.backend.laptop_shop.entity.CartDetail;
import project.backend.laptop_shop.entity.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsByCartAndProduct(Cart cart, Product product);

    CartDetail findByCartAndProduct(Cart cart, Product product);

    CartDetail findById(long id);

    void deleteById(long id);

    void deleteAllByCart(Cart cart);
}
