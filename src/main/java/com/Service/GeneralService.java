package com.Service;

import com.database.entites.StudentWork;
import com.database.entites.User;
import com.database.other.GregorianCalendar;
import com.database.other.Status;
import com.database.other.Type;
import com.database.repos.StudentWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Service
public class GeneralService {
    private final StudentWorkRepository repository;

    @Autowired
    public GeneralService(StudentWorkRepository repository) {
        this.repository = repository;
    }

    public Iterable<StudentWork> filter(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return repository.findByDiscipline(filter);
        } else {
            return getAllStudentWorks();
        }
    }

    public void add(
            User user,
            String discipline,
            String type,
            String date,
            Integer number,
            String status,
            String theme) {
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
    }

    public boolean delete(Long id) {
        if (id != null) {
            Optional<StudentWork> byId = repository.findById(id);
            if (byId.isPresent()) {
                StudentWork studentWork = byId.get();
                repository.delete(studentWork);
                return true;
            }
        }
        return false;
    }

    public Iterable<StudentWork> getAllStudentWorks() {
        return repository.findAll();
    }
}
