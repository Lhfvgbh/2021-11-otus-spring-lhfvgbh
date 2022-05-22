package ru.otus.lhfvgbh.prototype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CommonController {

    @GetMapping({"/", ""})
    public String index() {
        return "index";
    }

    @GetMapping({"/sign_in"})
    public String signIn() {
        return "login";
    }

    @GetMapping({"/sign_in?error"})
    public String signUp(Model model) {
        model.addAttribute("error", true);
        return "error";
    }
}
