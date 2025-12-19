package com.quangminh.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quangminh.laptopshop.domain.Cart;
import com.quangminh.laptopshop.domain.CartDetail;
import com.quangminh.laptopshop.domain.Order;
import com.quangminh.laptopshop.domain.OrderDetail;
import com.quangminh.laptopshop.domain.Product;
import com.quangminh.laptopshop.domain.User;
import com.quangminh.laptopshop.repository.CartDetailRepository;
import com.quangminh.laptopshop.repository.CartRepository;
import com.quangminh.laptopshop.repository.OrderDetailRepository;
import com.quangminh.laptopshop.repository.OrderRepository;
import com.quangminh.laptopshop.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartDetailRepository cartDetailRepository,
            CartRepository cartRepository, UserService userService, OrderDetailRepository orderDetailRepository,
            OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleSaveProduct(Product product) {
        this.productRepository.save(product);
    }

    public void handleDeleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session, long quantity) {
        User user = this.userService.getUserByUserName(email);

        if (user != null) {
            Cart cart = user.getCart();

            if (cart == null) {
                // Create a new cart
                cart = new Cart();
                cart.setUser(user);
                cart.setSum(0);

                user.setCart(cart);
                cart = this.cartRepository.save(cart);
            }

            Product product = this.productRepository.findById(productId);
            if (product != null) {
                // Check if the product is already in the cart
                boolean isExistedProductInCart = this.cartDetailRepository.existsByCartAndProduct(cart, product);

                if (isExistedProductInCart) {
                    // Update the quantity of the existing product in the cart
                    CartDetail presentCartDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);
                    presentCartDetail.setQuantity(presentCartDetail.getQuantity() + quantity);

                    this.cartDetailRepository.save(presentCartDetail);
                } else {
                    // Add a new product to the cart
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(product);
                    cartDetail.setPrice(product.getPrice());
                    cartDetail.setQuantity(quantity);

                    this.cartDetailRepository.save(cartDetail);

                    // Update the cart sum
                    int sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    this.cartRepository.save(cart);
                    // Update the session
                    session.setAttribute("sum", sum);
                }
            }
        }
    }

    public Cart fetchCartByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public CartDetail fetchCartDetailById(long id) {
        return this.cartDetailRepository.findById(id);
    }

    @Transactional
    public void handleDeleteCartDetail(long id, HttpSession session) {
        CartDetail cartDetail = this.cartDetailRepository.findById(id);
        Cart cart = cartDetail.getCart();
        int sum = cart.getSum();

        // Delete the cart detail
        this.cartDetailRepository.deleteById(id);

        // Update the cart sum
        if (sum > 1) {
            cart.setSum(sum - 1);
            this.cartRepository.save(cart);
            session.setAttribute("sum", cart.getSum());
        } else { // sum <= 1
            // Delete the cart from the repository
            User user = cart.getUser();
            if (user != null) {
                user.setCart(null);
            }

            this.cartRepository.delete(cart);
            session.setAttribute("sum", 0);
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            CartDetail currentCartDetail = this.cartDetailRepository.findById(cartDetail.getId());

            if (currentCartDetail != null) {
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    @Transactional
    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverPhone,
            String receiverAddress) {

        // Get the cart of the user
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            Order order = new Order();
            order.setUser(user);
            order.setReceiverName(receiverName);
            order.setReceiverPhone(receiverPhone);
            order.setReceiverAddress(receiverAddress);
            order.setStatus("PENDING");

            // Calculate total price
            double sum = 0;
            for (CartDetail cd : cartDetails) {
                sum += cd.getPrice() * cd.getQuantity();
            }
            order.setTotalPrice(sum);

            // Save the order
            order = this.orderRepository.save(order);

            for (CartDetail cartDetail : cartDetails) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(cartDetail.getProduct());
                orderDetail.setPrice(cartDetail.getPrice());
                orderDetail.setQuantity(cartDetail.getQuantity());

                this.orderDetailRepository.save(orderDetail);
            }

            // Clear the cart after placing the order
            User currentUser = cart.getUser();
            if (currentUser != null) {
                currentUser.setCart(null);
            }
            this.cartRepository.delete(cart);

            // Update the session
            session.setAttribute("sum", 0);
        }
    }
}
