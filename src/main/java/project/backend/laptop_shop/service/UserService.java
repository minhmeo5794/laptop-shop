package project.backend.laptop_shop.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.backend.laptop_shop.dto.request.CreateUserRequest;
import project.backend.laptop_shop.entity.User;
import project.backend.laptop_shop.mapper.UserMapper;
import project.backend.laptop_shop.repository.OrderRepository;
import project.backend.laptop_shop.repository.ProductRepository;
import project.backend.laptop_shop.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(long id) {
        return userRepository.findById(id);
    }

    public void createUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userMapper.toEntity(request, encodedPassword);


    }











    public void handleSaveUser(User user) {

        userRepository.save(user);
    }

    public User handleDeleteUserById(long id) {
        // Logic to delete user from the database
        return userRepository.deleteById(id);
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
