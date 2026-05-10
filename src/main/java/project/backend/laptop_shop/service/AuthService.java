package project.backend.laptop_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.backend.laptop_shop.dto.request.RegisterRequest;
import project.backend.laptop_shop.entity.Role;
import project.backend.laptop_shop.entity.User;
import project.backend.laptop_shop.mapper.AuthMapper;
import project.backend.laptop_shop.repository.RoleRepository;
import project.backend.laptop_shop.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthMapper authMapper;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        Role defaultRole = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Default role USER not found"));
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = authMapper.toEntity(request, encodedPassword, defaultRole);

        userRepository.save(user);
    }
}
