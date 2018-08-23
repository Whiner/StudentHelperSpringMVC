package com.control;

import com.Service.UserService;
import com.database.entites.User;
import com.database.other.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN','MODERATOR')")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public String userList(@RequestParam(required = false) String search,
                           Model model) {
        Iterable<User> users = service.searchUserByUsername(search);
        model.addAttribute("users", service.sortByAuthority(users));
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String delete(@PathVariable("user") User user) {
        service.delete(user);
        return "redirect:/user";
    }

    @PostMapping
    public String save(
            @RequestParam Role role,
            @RequestParam("id") User user,
            @RequestParam("username") String username) {
        service.save(user, username, role);
        return "redirect:/user";
    }


}
