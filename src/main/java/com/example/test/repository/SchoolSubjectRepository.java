package com.example.test.repository;

import com.example.test.entity.SchoolSubject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolSubjectRepository extends CrudRepository<SchoolSubject, Long> {

    SchoolSubject findByName(String name);
}
