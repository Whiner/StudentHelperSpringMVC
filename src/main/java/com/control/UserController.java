package com.control;

import com.database.Role;
import com.database.User;
import com.database.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userList(
            @PathVariable User user,
            Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userListEditor";
    }

    @GetMapping("{user}/del")
    public String delete(@PathVariable("user") User user) {
        if (user != null && !user.isAdmin()) {
            userRepository.delete(user);
        }
        return "redirect:/user";
    }

    @PostMapping
    public String save(
            @RequestParam Role role,
            @RequestParam("id") User user,
            @RequestParam("username") String username) {


        if (role != null) {
            user.setRole(role);
        }
        user.setUsername(username);
        userRepository.save(user);
//        if (!user.getRole().equals(Role.ADMIN)) {
//            return "redirect:/general";
//        } else {
        return "redirect:/user";
//        }
    }


}
