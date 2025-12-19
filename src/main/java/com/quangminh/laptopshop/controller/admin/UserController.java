package com.quangminh.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quangminh.laptopshop.domain.User;
import com.quangminh.laptopshop.service.UploadService;
import com.quangminh.laptopshop.service.UserService;

import jakarta.validation.Valid;

// MVC
@Controller
public class UserController {
    // Dependency Injection
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    // User list
    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users", users);

        return "admin/user/view";
    }

    // User detail
    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);

        return "admin/user/detail";
    }

    // Create user
    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());

        return "admin/user/create";
    }

    // Update user
    @GetMapping("/admin/user/update/{id}")
    public String updateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("currentUser", currentUser);

        return "admin/user/update";
    }

    // Delete user
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("currentUser", currentUser);

        return "admin/user/delete";
    }

    // Handle form submission for creating a new user
    @PostMapping("/admin/user/create")
    public String createUser(@ModelAttribute("newUser") @Valid User newUser, // gán dữ liệu từ form vào đối tượng newUser
            BindingResult bindingResult,
            @RequestParam("userImageFile") MultipartFile file) {

        if (bindingResult.hasErrors()) { // kiểm tra lỗi và quay lại trang tạo user nếu có lỗi
            return "admin/user/create";
        }

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(newUser.getPassword());

        newUser.setAvatar(avatar);
        newUser.setPassword(hashPassword);
        newUser.setRole(this.userService.getRoleByName(newUser.getRole().getName()));

        // Save user to database
        this.userService.handleSaveUser(newUser);
        return "redirect:/admin/user";
    }

    // Handle form submission for updating an existing user
    @PostMapping("/admin/user/update/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute("currentUser") User newCurrentUser) {
        User currentUser = this.userService.getUserById(id);

        if (currentUser != null) {
            currentUser.setAddress(newCurrentUser.getAddress());
            currentUser.setFullName(newCurrentUser.getFullName());
            currentUser.setPhoneNumber(newCurrentUser.getPhoneNumber());
            // Save user to database
            this.userService.handleSaveUser(currentUser);
        }

        return "redirect:/admin/user/{id}";
    }

    // Handle form submission for deleting a user
    @PostMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User currentUser = this.userService.getUserById(id);

        if (currentUser != null) {
            this.userService.handleDeleteUserById(id);
        }

        return "redirect:/admin/user";
    }
}
