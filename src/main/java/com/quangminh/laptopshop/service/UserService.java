package com.quangminh.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quangminh.laptopshop.domain.Role;
import com.quangminh.laptopshop.domain.User;
import com.quangminh.laptopshop.domain.dto.RegisterDTO;
import com.quangminh.laptopshop.repository.OrderRepository;
import com.quangminh.laptopshop.repository.ProductRepository;
import com.quangminh.laptopshop.repository.RoleRepository;
import com.quangminh.laptopshop.repository.UserRepository;

@Service
public class UserService {
    // Dependency Injection
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    // Constructor injection
    public UserService(UserRepository userRepository, RoleRepository roleRepository, OrderRepository orderRepository,
            ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUserByEmail(String email) {
        return this.userRepository.findAllByEmail(email);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public User handleSaveUser(User user) {
        // Logic to save user to the database
        return this.userRepository.save(user);
    }

    public User handleDeleteUserById(long id) {
        // Logic to delete user from the database
        return this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        return user;
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByUserName(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long countUsers() {
        return this.userRepository.count();
    }

    public long countProducts() {
        return this.productRepository.count();
    }

    public long countOrders() {
        return this.orderRepository.count();
    }
}
