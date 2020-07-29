package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String users(Model model) {
        String str = "Hello! Its users page";
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String firstName, @RequestParam String secondName, @RequestParam int age, ModelMap model) {
        userService.addUser(new User(firstName, secondName, age));
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        //model.addAttribute("aaa", "hello");
        return "users";
    }

    @GetMapping("/delete")
    public String deleteUsers(@RequestParam String id, ModelMap model) {
        userService.deleteUser(Long.parseLong(id));
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        //model.addAttribute("aaa", "hello");
        return "users";
    }

    @GetMapping("/update")
    public String updateGet(@RequestParam String id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(Long.parseLong(id)));
        return "updateUser";
    }

    @PostMapping("/update")
    public String updatePost(@RequestParam Long id, String firstName, String secondName,
                             int age, ModelMap model) throws IOException {
        User user = userService.getUserById(id);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setAge(age);
        userService.updateUser(user);
        model.addAttribute("users", userService.getAllUsers());

//        userService.updateUser(new User(id, firstName, secondName, age));
//        model.addAttribute("messages", userService.getAllUsers());
        return "redirect:users";
    }

}
