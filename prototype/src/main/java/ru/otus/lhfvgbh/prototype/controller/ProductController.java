package ru.otus.lhfvgbh.prototype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.lhfvgbh.prototype.dto.ProductDTO;
import ru.otus.lhfvgbh.prototype.service.ProductService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public String listProducts(Model model) {
        List<ProductDTO> products = productService.getAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/{id}/toCart")
    public String addToCart(@PathVariable Long id, Principal principal) {
        if (principal != null) {
            productService.addToUserCart(id, principal.getName());
        }
        return "redirect:/products";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "newProduct";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addNewProduct")
    public String addNewProduct(Model model, ProductDTO productDTO) {
        model.addAttribute("product", new ProductDTO());
        productService.save(productDTO);
        return "redirect:/products";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/deleteProduct")
    //@DeleteMapping("/deleteProduct")
    public String disableProduct(@PathVariable Long id) {
        productService.disable(id);
        return "redirect:/products";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/addProduct")
    //@DeleteMapping("/deleteProduct")
    public String enableProduct(@PathVariable Long id) {
        productService.enable(id);
        return "redirect:/products";
    }

}
