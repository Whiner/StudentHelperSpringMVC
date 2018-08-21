package com.database.repos;

import com.database.entites.StudentWork;
import com.database.entites.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentWorkRepository extends CrudRepository<StudentWork, Long> {

    List<StudentWork> findByDiscipline(String discipline);

    List<StudentWork> findByOwner(User owner);
}
