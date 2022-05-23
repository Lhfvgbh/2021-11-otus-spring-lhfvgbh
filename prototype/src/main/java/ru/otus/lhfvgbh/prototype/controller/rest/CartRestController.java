package ru.otus.lhfvgbh.prototype.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.lhfvgbh.prototype.dto.CartDTO;
import ru.otus.lhfvgbh.prototype.dto.UserDTO;
import ru.otus.lhfvgbh.prototype.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartRestController {

    private final CartService cartService;

    @GetMapping(value = {"/", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartDTO getCart(@RequestBody UserDTO userDTO) {
        CartDTO cartDTO = new CartDTO();
        if (userDTO != null) {
            cartDTO = cartService.getCartByUser(userDTO.getName());
        }
        return cartDTO;
    }

    @PostMapping(value = "/removeItem/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartDTO removeFromCart(@PathVariable Long productId, @RequestBody UserDTO userDTO) {
        CartDTO cartDTO = new CartDTO();
        if (userDTO != null) {
            cartService.deleteProductFromCart(userDTO.getName(), productId);
        }
        return cartDTO;
    }

    @PostMapping("/confirmOrder")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void confirmOrder(@RequestBody UserDTO userDTO) {
        if (userDTO != null) {
            cartService.orderCart(userDTO.getName());
        }
    }

}
