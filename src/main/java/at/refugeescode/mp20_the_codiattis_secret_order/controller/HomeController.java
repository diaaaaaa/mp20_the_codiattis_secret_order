package at.refugeescode.mp20_the_codiattis_secret_order.controller;

import at.refugeescode.mp20_the_codiattis_secret_order.persistence.User;
import at.refugeescode.mp20_the_codiattis_secret_order.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/")
public class HomeController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public HomeController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    String page() {
        return "home";
    }

    @ModelAttribute("users")
    List<User> users() {
        return userRepository.findAll();
    }

    @ModelAttribute("newUser")
    User newUser() {
        return new User();
    }

    @ModelAttribute("loggedUserName")
    String principal(Principal principal) {
        return principal.getName();
    }

    @PostMapping
    String post(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Stream.of("USER").collect(Collectors.toSet()));
        userRepository.save(user);
        return "redirect:/";
    }
}
