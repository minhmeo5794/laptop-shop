package project.backend.laptop_shop.controller.client;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import project.backend.laptop_shop.entity.Order;
import project.backend.laptop_shop.entity.Product;
import project.backend.laptop_shop.entity.User;
import project.backend.laptop_shop.dto.request.RegisterRequest;
import project.backend.laptop_shop.service.OrderService;
import project.backend.laptop_shop.service.ProductService;
import project.backend.laptop_shop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequiredArgsConstructor
@Controller
public class HomePageController {
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping
    public String getHomePage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);

        return "client/homepage/view";
    }



    @GetMapping("/order-history")
    public String getOrderHistoryPage(Model model, HttpServletRequest request) {
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        List<Order> orders = this.orderService.findByUser(currentUser);
        model.addAttribute("orders", orders);

        return "client/cart/order-history";
    }
}
