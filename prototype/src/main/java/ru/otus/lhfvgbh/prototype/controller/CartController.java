package ru.otus.lhfvgbh.prototype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.lhfvgbh.prototype.dto.CartDTO;
import ru.otus.lhfvgbh.prototype.service.CartService;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping({"/", ""})
    public String details(Model model, Principal principal) {
        CartDTO cartDTO = new CartDTO();
        if (principal != null) {
            cartDTO = cartService.getCartByUser(principal.getName());
        }

        model.addAttribute("cart", cartDTO);
        return "cart";
    }

    @PostMapping("/{id}/remove")
    //@DeleteMapping("/{id}/remove")
    public String removeFromCart(@PathVariable Long id, Principal principal) {
        if (principal != null) {
            cartService.deleteProductFromCart(principal.getName(), id);
        }
        return "redirect:/cart";
    }

    @PostMapping("/confirmOrder")
    public String confirmOrder(Principal principal){
        if(principal != null){
            cartService.orderCart(principal.getName());
        }
        return "redirect:/cart";
    }

}
