package ru.kata.spring.boot_security.demo.controllers;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;


import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImp userService;

    public UserController( UserServiceImp userService) {
        this.userService = userService;

    }

    @GetMapping()
    public String personalPage(Model m, Principal principal, @AuthenticationPrincipal User current) {
        int id = userService.findByUsername(principal.getName()).getId();
        m.addAttribute("user", userService.showUser(id));
        m.addAttribute("current", current);
        return "user/personalPage";
    }
}
