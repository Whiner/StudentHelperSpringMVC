package com.control;

import com.database.Role;
import com.database.User;
import com.database.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public String delete(@PathVariable("user") User user){
        if(user != null && !user.getRoles().contains(Role.ADMIN)){
            userRepository.delete(user);
        }
        return "redirect:/user";
    }

    @PostMapping
    public String save(
            @RequestParam Map<String, String> form,
            @RequestParam("id") User user,
            @RequestParam("username") String username){

        Set<String> enumRoles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String role: form.keySet()){
            if(enumRoles.contains(role)){
                user.getRoles().add(Role.valueOf(role));
            }
        }
        user.setUsername(username);

        userRepository.save(user);
        return "redirect:/user";
    }


}
