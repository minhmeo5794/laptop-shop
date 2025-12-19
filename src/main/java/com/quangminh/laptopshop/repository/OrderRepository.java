package com.quangminh.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangminh.laptopshop.domain.Order;
import com.quangminh.laptopshop.domain.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    Order findById(long id);

    Order deleteById(long id);

    List<Order> findByUser(User user);
}
