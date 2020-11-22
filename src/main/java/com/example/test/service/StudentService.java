package com.example.test.service;

import com.example.test.entity.Student;
import com.example.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findByFullNameAndGrade(String fullname, int grade) {
        return studentRepository.findByFullNameAndGradeNumber(fullname, grade);
    }

    public boolean isGradeExists(int gradeNumber) {
        Iterable<Student> students = studentRepository.findByGradeNumber(gradeNumber);
        return students.iterator().hasNext();
    }
}
