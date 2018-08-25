package com.Service;

import com.database.entites.StudentWork;
import com.database.entites.StudentWorkInfo;
import com.database.entites.StudentWorkNote;
import com.database.entites.User;
import com.database.other.GregorianCalendar;
import com.database.other.Status;
import com.database.other.Type;
import com.database.repos.StudentWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Service
public class GeneralService {
    private final StudentWorkRepository repository;
    private final UserService userService;

    @Autowired
    public GeneralService(StudentWorkRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Iterable<StudentWork> filter(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return repository.findByDiscipline(filter);
        } else {
            return getStudentsWorksByCurrentUser();
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

    public Iterable<StudentWork> getStudentWorksByOwner(User user) {
        return repository.findByOwner(user);
    }

    public Iterable<StudentWork> getStudentsWorksByCurrentUser() {
        return getStudentWorksByOwner(userService.getCurrentUser());
    }

    public Optional<StudentWork> getStudentWorkByID(Long id) {

        return repository.findById(id);
    }

    public void addNote(Long studentWorkId, String note) {
        if (studentWorkId != null && !StringUtils.isEmpty(note)) {
            Optional<StudentWork> byId = repository.findById(studentWorkId);
            if (byId.isPresent()) {
                StudentWork studentWork = byId.get();
                StudentWorkInfo studentWorkInfo = studentWork.getInfo();
                if (studentWorkInfo == null) {
                    studentWorkInfo = new StudentWorkInfo();
                }
                StudentWorkNote workNote = new StudentWorkNote();
                workNote.setNote(note);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                workNote.setDate(gregorianCalendar);
                workNote.setInfo(studentWorkInfo);
                studentWorkInfo.getNotes().add(workNote);
                studentWork.setInfo(studentWorkInfo);
                repository.save(studentWork);
            }
        }

    }

    public String getCurrentURI() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getRequest().getRequestURI();
    }

}
