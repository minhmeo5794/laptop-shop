package project.backend.laptop_shop.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "First name can not be blank")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private final String firstName;

    @NotBlank(message = "Last name can not be blank")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private final String lastName;

    @NotBlank(message = "Email can not be blank")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private final String email;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private final String password;

    @NotBlank(message = "Confirm can not be blank")
    private final String confirmPassword;

    private final String address;
    private final String phoneNumber;
    private final String avatar;
}
