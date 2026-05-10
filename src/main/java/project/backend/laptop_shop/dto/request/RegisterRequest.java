package project.backend.laptop_shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.backend.laptop_shop.service.validator.RegisterChecked;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@RegisterChecked
public class RegisterRequest {
    @NotBlank(message = "First name can not be blank")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private String firstName;

    @NotBlank(message = "Last name can not be blank")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private String lastName;

    @NotBlank(message = "Email can not be blank")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm can not be blank")
    private String confirmPassword;

    private String address;
    private String phoneNumber;
}
