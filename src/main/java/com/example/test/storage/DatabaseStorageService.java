package com.example.test.storage;

import com.example.test.entity.Mark;
import com.example.test.entity.SchoolSubject;
import com.example.test.entity.Student;
import com.example.test.entity.TableRow;
import com.example.test.service.MarkService;
import com.example.test.service.SchoolSubjectService;
import com.example.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@Service
public class DatabaseStorageService implements StorageService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolSubjectService subjectService;

    @Autowired
    private MarkService markService;

    @Async
    @Override
    public void storeFile(MultipartFile file) throws ParseException {

        String content;
        try {
            content = new String(file.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String[] parts;
        Matcher matcher;
        Student student;
        SchoolSubject subject;
        Mark mark;

        for (String row : content.split("\r\n")) {
            matcher = ROW_PATTERN.matcher(row);
            if (!matcher.matches()) {
                continue;
            }

            parts = row.split(";");

            student = studentService.findByFullNameAndGrade(parts[1], Integer.parseInt(parts[0]));
            if (student == null) {
                student = new Student(parts[1], Integer.parseInt(parts[0]));
                try {
                    student = studentService.save(student);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }

            subject = subjectService.findByName(parts[2]);
            if (subject == null) {
                subject = new SchoolSubject(parts[2]);
                try {
                    subject = subjectService.save(subject);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }

            mark = markService.find(Integer.parseInt(parts[3]), student, subject, new Date(TableRow.DATE_FORMAT.parse(parts[4]).getTime()));
            if (mark == null) {
                mark = new Mark(Integer.parseInt(parts[3]), student, subject,
                        new Date(TableRow.DATE_FORMAT.parse(parts[4]).getTime()));
                try {
                    markService.save(mark);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<TableRow> loadAll() {
        List<TableRow> rows = new ArrayList<>();
        List<Mark> marks = markService.findAll();
        for(Mark mark: marks) {
            rows.add(new TableRow(mark.getStudent().getFullName(),
                    mark.getStudent().getGradeNumber(),
                    mark.getSubject().getName(),
                    mark.getMark(), mark.getMarkDate()));
        }

        return rows;
    }
}
