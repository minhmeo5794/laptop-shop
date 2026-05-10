package project.backend.laptop_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.backend.laptop_shop.entity.Order;
import project.backend.laptop_shop.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    Order findById(long id);

    Order deleteById(long id);

    List<Order> findByUser(User user);
}
