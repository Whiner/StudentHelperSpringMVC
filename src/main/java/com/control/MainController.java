package com.control;

import com.database.Status;
import com.database.StudentWork;
import com.database.Type;
import com.database.User;
import com.database.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private StudentRepository repository;

    @Autowired
    public MainController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/general")
    public String general(@RequestParam(required = false) String filter, Model model){
        Iterable<StudentWork> all;
        if(filter != null && !filter.isEmpty()){
            all = repository.findByDiscipline(filter);
        } else {
            all = repository.findAll();
        }
        model.addAttribute("studentWorks", all);
        return "general";
    }

    @PostMapping("/general")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String discipline,
            @RequestParam String type,
            @RequestParam Integer number,
            @RequestParam String status,
            @RequestParam String theme,
            Model model) {
        StudentWork studentWork = new StudentWork(
                Type.valueFromRussian(type),
                discipline,
                number,
                theme,
                Status.valueFromRussian(status),
                user
        );
        repository.save(studentWork);
        Iterable<StudentWork> all = repository.findAll();
        model.addAttribute("studentWorks", all);
        return "general";
    }

}
