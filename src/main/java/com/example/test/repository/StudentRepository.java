package com.example.test.repository;

import com.example.test.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Iterable<Student> findAll();
    Student findByFullNameAndGradeNumber(String fullName, int gradeNumber);
    Iterable<Student> findByGradeNumber(int gradeNumber);
}
