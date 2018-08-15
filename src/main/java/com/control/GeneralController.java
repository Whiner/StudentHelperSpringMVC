package com.control;

import com.database.*;
import com.database.repos.StudentWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Controller
@RequestMapping("/general")
public class GeneralController {
    private StudentWorkRepository repository;

    @Autowired
    public GeneralController(StudentWorkRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String general(@RequestParam(required = false) String filter, Model model) {
        Iterable<StudentWork> all;
        if (filter != null && !filter.isEmpty()) {
            all = repository.findByDiscipline(filter);
        } else {
            all = repository.findAll();
        }
        model.addAttribute("studentWorks", all);
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
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.fromString(date, new SimpleDateFormat("yyyy-MM-dd"));
        } catch (ParseException e) {
            gregorianCalendar.set(2000, Calendar.JANUARY, 1);
        }
        StudentWork studentWork = new StudentWork(
                Type.valueFromRussian(type),
                gregorianCalendar,
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

    @GetMapping("{id}/del")
    public String deleteCard(@PathVariable Long id) {

        if (id != null) {
            Optional<StudentWork> byId = repository.findById(id);
            if (byId.isPresent()) {
                StudentWork studentWork = byId.get();
                repository.delete(studentWork);
            }
        }

        return "redirect:/";
    }
}
