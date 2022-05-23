package ru.otus.lhfvgbh.prototype.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.otus.lhfvgbh.prototype.dto.ProductDTO;
import ru.otus.lhfvgbh.prototype.dto.UserDTO;
import ru.otus.lhfvgbh.prototype.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO addNewProduct(@RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
        return productDTO;
    }

    @PostMapping("/{id}/toCart")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addToCart(@PathVariable Long id, UserDTO userDTO) {
        if (userDTO != null) {
            productService.addToUserCart(id, userDTO.getName());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/outOfStockProduct")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void disableProduct(@PathVariable Long id) {
        productService.disable(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/inStockProduct")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enableProduct(@PathVariable Long id) {
        productService.enable(id);
    }

}

