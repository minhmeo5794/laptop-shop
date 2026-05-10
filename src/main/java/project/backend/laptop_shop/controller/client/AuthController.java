package project.backend.laptop_shop.controller.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.backend.laptop_shop.dto.request.RegisterRequest;
import project.backend.laptop_shop.service.AuthService;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterRequest());
        return "client/auth/register";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getAccessDenyPage(Model model) {
        return "client/auth/access-deny";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("registerUser") @Valid RegisterRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        authService.register(request);

        return "redirect:/login";
    }
}
