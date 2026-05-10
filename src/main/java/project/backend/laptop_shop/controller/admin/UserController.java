package project.backend.laptop_shop.controller.admin;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import project.backend.laptop_shop.dto.request.CreateUserRequest;
import project.backend.laptop_shop.entity.Test;
import project.backend.laptop_shop.entity.User;
import project.backend.laptop_shop.service.UploadService;
import project.backend.laptop_shop.service.UserService;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admin/user")
@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    // Read
    @GetMapping
    public String getAllUsersPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "admin/user/view";
    }

    @GetMapping("/{userId}")
    public String getUserPage(Model model, @PathVariable("userId") long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);

        return "admin/user/detail";
    }

    @GetMapping("/update/{userId}")
    public String getUpdateUserPage(Model model, @PathVariable("userId") long id) {
        User currentUser = userService.getUser(id);
        model.addAttribute("currentUser", currentUser);

        return "admin/user/update";
    }

    @GetMapping("/delete/{userId}")
    public String getDeleteUserPage(Model model, @PathVariable("userId") long id) {
        User currentUser = userService.getUser(id);
        model.addAttribute("currentUser", currentUser);

        return "admin/user/delete";
    }

    // Test
    @GetMapping("/test")
    public String getTestPage(Model model) {
        Test testObj = new Test();
        testObj.setEmail("minh@gmail.com");
        model.addAttribute("test", new Test());
        Test test2 = (Test) model.getAttribute("test");
        test2.setEmail("newemail@gmail.com");

        return "admin/user/test";
    }

    // Create
    @GetMapping("/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());

        return "admin/user/create";
    }

    @PostMapping("/create")
    public String createUser(
            @ModelAttribute("newUser") @Valid CreateUserRequest request,
            BindingResult bindingResult,
            @RequestParam("userImageFile") MultipartFile file
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/user/create";
        }

        String avatar = uploadService.handleSaveUploadFile(file, "avatar");
        userService.createUser(request);

        String hashPassword = passwordEncoder.encode(request.getPassword());

//        request.setAvatar(avatar);
//        request.setPassword(hashPassword);
//        request.setRole(userService.getRoleByName(request.getRole().getName()));

        // Save user to database
//        userService.handleSaveUser(request);
        return "redirect:/admin/user";
    }

    @PostMapping("/update/{userId}")
    public String updateUser(@PathVariable("userId") long id, @ModelAttribute("currentUser") User newCurrentUser) {
        User currentUser = this.userService.getUser(id);

        if (currentUser != null) {
            currentUser.setAddress(newCurrentUser.getAddress());
            currentUser.setFullName(newCurrentUser.getFullName());
            currentUser.setPhoneNumber(newCurrentUser.getPhoneNumber());
            // Save user to database
            this.userService.handleSaveUser(currentUser);
        }

        return "redirect:/admin/user/{id}";
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") long id) {
        User currentUser = this.userService.getUser(id);

        if (currentUser != null) {
            this.userService.handleDeleteUserById(id);
        }

        return "redirect:/admin/user";
    }
}
