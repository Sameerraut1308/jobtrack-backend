package jobtrack.controller;

import jakarta.validation.Valid;
import jobtrack.dto.LoginRequest;
import jobtrack.dto.UserRegisterRequest;
import jobtrack.entity.User;
import jobtrack.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register endpoint with validation
    @PostMapping
    public User registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return userService.registerUser(request);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}