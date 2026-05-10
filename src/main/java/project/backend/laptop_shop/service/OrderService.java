package project.backend.laptop_shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import project.backend.laptop_shop.entity.Order;
import project.backend.laptop_shop.entity.User;
import project.backend.laptop_shop.repository.OrderDetailRepository;
import project.backend.laptop_shop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public Order findById(long id) {
        return orderRepository.findById(id);
    }

    public void updateOrder(Order order) {
        Order currentOrder = orderRepository.findById(order.getId());
        if (currentOrder != null) {
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);
        }
    }

    public void deleteOrder(long id) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            this.orderRepository.deleteById(id);
        }
    }
}
