package com.quangminh.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.quangminh.laptopshop.domain.Product;
import com.quangminh.laptopshop.service.ProductService;
import com.quangminh.laptopshop.service.UploadService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;
    

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);

        return "admin/product/view";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);

        return "admin/product/detail";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());

        return "admin/product/create";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("currentProduct", currentProduct);

        return "admin/product/update";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("currentProduct", currentProduct);

        return "admin/product/delete";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult newProductBindingResult, @RequestParam("productImageFile") MultipartFile file) {

        if (newProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }

        String image = this.uploadService.handleSaveUploadFile(file, "product");
        newProduct.setImage(image);

        this.productService.handleSaveProduct(newProduct);
        return "redirect:/admin/product";
    }

    @PostMapping("/admin/product/update/{id}")
    public String updateProduct(Model model, @ModelAttribute("currentProduct") @Valid Product newCurrentProduct,
            BindingResult newProductBindingResult, @PathVariable long id,
            @RequestParam("productImageFile") MultipartFile file) {

        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("currentProductImage", currentProduct.getImage());

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/update";
        }

        if (currentProduct != null) {
            // Update new image
            if (!file.isEmpty()) {
                String image = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(image);
            }

            currentProduct.setName(newCurrentProduct.getName());
            currentProduct.setPrice(newCurrentProduct.getPrice());
            currentProduct.setDetailDesc(newCurrentProduct.getDetailDesc());
            currentProduct.setShortDesc(newCurrentProduct.getShortDesc());
            currentProduct.setQuantity(newCurrentProduct.getQuantity());
            currentProduct.setBrand(newCurrentProduct.getBrand());
            currentProduct.setPurpose(newCurrentProduct.getPurpose());

            this.productService.handleSaveProduct(currentProduct);
        }

        return "redirect:/admin/product/{id}";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);

        if (currentProduct != null) {
            this.productService.handleDeleteProduct(id);
        }

        return "redirect:/admin/product";
    }
}
