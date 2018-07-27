package com.control;

import com.database.StudentWork;
import com.database.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    StudentRepository repository;

    @GetMapping("/main")
    public String main(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "main";
    }

    @GetMapping
    public String general(Map<String, Object> model){
        Iterable<StudentWork> all = repository.findAll();

        model.put("studentWorks", all);
        return "general";
    }

    @PostMapping
    public String add(@RequestParam String discipline, @RequestParam Integer number, Map<String, Object> model){
        final StudentWork studentWork = new StudentWork(discipline, number);
        repository.save(studentWork);
        Iterable<StudentWork> all = repository.findAll();
        model.put("studentWorks", all);
        return "general";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){ // тут параметр фильтр должен быть как и в html name
        Iterable<StudentWork> studentWorkList;
        if(filter != null && !filter.isEmpty()){
            studentWorkList = repository.findByDiscipline(filter);
        } else {
            studentWorkList = repository.findAll();
        }
        model.put("studentWorks", studentWorkList); //тут должно быть одинаково постоянно
        return "general";
    }
}
