package com.control;

import com.Service.GeneralService;
import com.database.entites.StudentWork;
import com.database.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/general")
public class GeneralController {
    private final GeneralService service;

    @Autowired
    public GeneralController(GeneralService service) {
        this.service = service;
    }

    @GetMapping
    public String general(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Long workId,
            Model model) {
        if (workId != null) {
            Optional<StudentWork> byID = service.getStudentWorkByID(workId);
            byID.ifPresent(studentWork -> model.addAttribute("work", studentWork));
        }
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
        return "redirect:/general";
    }

    @GetMapping("/{id}/del")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/general";
    }

    @PostMapping("/add-note") //шоб окрывалось развернутым надо на адрес перенаправлять для конкретной инфы
    public String addNote(
            @RequestParam Long studentWorkId,
            @RequestParam String note) {
        service.addNote(studentWorkId, note);
        return "redirect:/general?workId=" + studentWorkId;
    }


//    @GetMapping("/show_info/{id}")
//    public String show(@PathVariable Long id, Model model){
//        model.addAttribute("studentWorks", service.getStudentsWorksByCurrentUser());
//        Optional<StudentWork> byID = service.getStudentWorkByID(id);
//        byID.ifPresent(studentWork -> model.addAttribute("info", studentWork));
//        return "general";
//    }
//
//    @GetMapping("/close_info")
//    public String close(Model model){
//        model.addAttribute("studentWorks", service.getStudentsWorksByCurrentUser());
//        model.addAttribute("clicked", false);
//        return "general";
//    }
}
