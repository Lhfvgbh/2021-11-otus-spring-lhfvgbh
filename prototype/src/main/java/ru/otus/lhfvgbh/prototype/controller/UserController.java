package ru.otus.lhfvgbh.prototype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.lhfvgbh.prototype.domain.User;
import ru.otus.lhfvgbh.prototype.dto.UserDTO;
import ru.otus.lhfvgbh.prototype.exceptions.InvalidUserException;
import ru.otus.lhfvgbh.prototype.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("!hasAuthority('ADMIN')")
    @GetMapping("/sign_up")
    public String signUp(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @PreAuthorize("!hasAuthority('ADMIN')")
    @PostMapping("/sign_up")
    public String signUp(UserDTO userDTO, Model model) {
        model.addAttribute("user", new UserDTO());
        if (userService.save(userDTO)) {
            return "redirect:/sign_in";
        } else {
            model.addAttribute("user", userDTO);
            return "user";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/", ""})
    public String listUsersPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model, Principal principal) {
        if (principal == null) {
            throw new InvalidUserException("Cannot save please check your input!");
        }
        User user = userService.findByName(principal.getName());
        UserDTO userDTO = UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .build();
        model.addAttribute("user", userDTO);
        return "userProfile";
    }

    @PostMapping("/editProfile")
    public String editProfile(UserDTO userDTO, Model model, Principal principal) {
        if (principal == null) {
            throw new InvalidUserException("Cannot save, please check your input!");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()
                || !userDTO.getPassword().equals(userDTO.getConfirmedPassword())) {
            model.addAttribute("user", userDTO);
            return "userProfile";
        }
        userService.updateProfile(userDTO);
        model.addAttribute("user", userDTO);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/ban")
    public String ban(@PathVariable Long id) {
        userService.deactivate(id);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/unban")
    public String unban(@PathVariable Long id) {
        userService.activate(id);
        return "redirect:/";
    }
}
