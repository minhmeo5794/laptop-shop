package project.backend.laptop_shop.mapper;

import org.springframework.stereotype.Component;
import project.backend.laptop_shop.dto.request.CreateUserRequest;
import project.backend.laptop_shop.entity.User;

@Component
public class UserMapper {
    public User toEntity(CreateUserRequest request, String encodedPassword) {
        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFirstName() + " " + request.getLastName())
                .password(encodedPassword)
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
//                .role(request.get)
                .build();
    }
}
