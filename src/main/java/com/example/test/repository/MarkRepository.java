package com.example.test.repository;

import com.example.test.entity.GroupedStudentsByMark;
import com.example.test.entity.Mark;
import com.example.test.entity.SchoolSubject;
import com.example.test.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {

    Mark findByMarkAndStudentAndSubjectAndMarkDate(int mark, Student student, SchoolSubject subject, Date markDate);

    @Query("select new com.example.test.entity.GroupedStudentsByMark(s.fullName, AVG(m.mark)) " +
            "from Mark as m join Student as s on m.student = s " +
            "where s.gradeNumber = ?1 " +
            "group by s.fullName")
    List<GroupedStudentsByMark> getMeanMarkByGradeNumber(Integer gradeNumber);
}
