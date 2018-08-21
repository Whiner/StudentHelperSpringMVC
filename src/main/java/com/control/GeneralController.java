package com.control;

import com.Service.GeneralService;
import com.database.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/general")
public class GeneralController {
    private final GeneralService service;

    @Autowired
    public GeneralController(GeneralService service) {
        this.service = service;
    }

    @GetMapping
    public String general(@RequestParam(required = false) String filter, Model model) {
        model.addAttribute("studentWorks", service.filter(filter));
        return "general";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String discipline,
            @RequestParam String type,
            @RequestParam String date,
            @RequestParam Integer number,
            @RequestParam String status,
            @RequestParam String theme,
            Model model) {
        service.add(user, discipline, type, date, number, status, theme);
        model.addAttribute("studentWorks", service.getStudentsWorksByCurrentUser());
        return "general";
    }

    @GetMapping("{id}/del")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
