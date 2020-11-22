package com.example.test.service;

import com.example.test.entity.GroupedStudentsByMark;
import com.example.test.entity.Mark;
import com.example.test.entity.SchoolSubject;
import com.example.test.entity.Student;
import com.example.test.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarkService {

    @Autowired
    private MarkRepository markRepository;

    public Mark find(int mark, Student student, SchoolSubject subject, Date markDate) {
        return markRepository.findByMarkAndStudentAndSubjectAndMarkDate(mark, student, subject, markDate);
    }

    public void save(Mark mark) {
        markRepository.save(mark);
    }

    public List<Mark> findAll() {
        List<Mark> result = new ArrayList<>();
        markRepository.findAll().forEach(result::add);
        return result;
    }

    public List<GroupedStudentsByMark> getMeanMarkByGrade(Integer gradeNumber) {
        return markRepository.getMeanMarkByGradeNumber(gradeNumber);
    }
}
