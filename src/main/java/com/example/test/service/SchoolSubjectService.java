package com.example.test.service;

import com.example.test.entity.SchoolSubject;
import com.example.test.entity.Student;
import com.example.test.repository.SchoolSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolSubjectService {

    @Autowired
    SchoolSubjectRepository subjectRepository;

    public SchoolSubject save(SchoolSubject subject) {
        return subjectRepository.save(subject);
    }

    public SchoolSubject findByName(String name) {
        return subjectRepository.findByName(name);
    }

}
