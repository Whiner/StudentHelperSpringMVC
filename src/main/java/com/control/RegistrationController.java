package com.control;

import com.database.Role;
import com.database.User;
import com.database.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    private UserRepository repository;

    @Autowired
    public RegistrationController(UserRepository repository) {
        this.repository = repository;
    }

    RegistrationController(){}


    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User byUsername = repository.findByUsername(user.getUsername());
        if(byUsername != null){
            model.put("user_exist", "Sorry, but user already exist");
            return "registration";
        } else {
            user.setActivity(true);
            user.setRole(Role.USER);
        }
        repository.save(user);

        return "redirect:/login";
    }

}
