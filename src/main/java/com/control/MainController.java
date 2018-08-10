package com.control;

import com.database.StudentWork;
import com.database.User;
import com.database.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

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
            @RequestParam Integer number, Model model){
        StudentWork studentWork = new StudentWork(discipline, number, user);
        repository.save(studentWork);
        Iterable<StudentWork> all = repository.findAll();
        model.addAttribute("studentWorks", all);
        return "general";
    }

}
