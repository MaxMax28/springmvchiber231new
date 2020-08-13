package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello(Model model) {
        String str = "Hello!";
        model.addAttribute("str", str);
        return "hello";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String firstName, @RequestParam String secondName, @RequestParam Long age, ModelMap model) {
        userService.addUser(new User(firstName, secondName, age));
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}