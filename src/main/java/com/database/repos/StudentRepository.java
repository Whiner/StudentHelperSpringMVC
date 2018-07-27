package com.database.repos;

import com.database.StudentWork;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<StudentWork, Long> {

    List<StudentWork> findByDiscipline(String discipline);
}
