package project.backend.laptop_shop.mapper;

import org.springframework.stereotype.Component;
import project.backend.laptop_shop.dto.request.RegisterRequest;
import project.backend.laptop_shop.entity.Role;
import project.backend.laptop_shop.entity.User;

@Component
public class AuthMapper {
    public User toEntity(RegisterRequest request, String encodedPassword, Role defaultRole) {
        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFirstName() + " " + request.getLastName())
                .password(encodedPassword)
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .role(defaultRole)
                .build();
    }
}
