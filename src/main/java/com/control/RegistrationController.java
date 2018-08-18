package com.control;

import com.Service.UserService;
import com.database.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService service;

    @Autowired
    RegistrationController(UserService service) {
        this.service = service;
    }


    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (service.register(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("user_exist", "Sorry, but user already exist");
            return "redirect:/registration";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        if (service.checkActivationCode(code)) {
            System.out.println(service.activate(code));
        } else {
            model.addAttribute("activation_error", "Ошибка активации");
        }
        return "redirect:/login";
    }

}
