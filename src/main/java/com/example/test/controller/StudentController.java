package com.example.test.controller;

import com.example.test.entity.GroupedStudentsByMark;
import com.example.test.entity.TableRow;
import com.example.test.service.MarkService;
import com.example.test.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    @Autowired
    private MarkService markService;

    @Autowired
    private StudentService studentService;

    /**
     * Метод для получения фио и среднего балла учеников по месяцам.
     * Получает на входе номер класса.
     * На выходе возвращает ФИО, месяц, средний бал.
     * Если класс не найден нужно вернуть 404 ошибку с текстом «Класс не найден»
     * Реализовать через stream api.
     */
    @ResponseBody
    @GetMapping("/student/monthMeanMark")
    public ResponseEntity<String> getMeanMarkByMonth(@RequestParam(defaultValue = "-1") Integer gradeNumber) {

        boolean isGradeExists = studentService.isGradeExists(gradeNumber);
        if (!isGradeExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Класс не найден\"}");
        }

        try {
            Map<Object, Map<Object, Double>> collected = markService.findAll().stream()
                    .filter(mark -> mark.getStudent().getGradeNumber() == gradeNumber)
                    .map(mark -> new TableRow(mark.getStudent().getFullName(), mark.getStudent().getGradeNumber(),
                            mark.getSubject().getName(), mark.getMark(), mark.getMarkDate()))
                    .collect(Collectors.groupingBy(row -> row.getMarkDate().substring(12),
                            Collectors.groupingBy(TableRow::getFullName,
                                    Collectors.averagingDouble(TableRow::getMark))));
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(collected));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Ошибка сервера\"}");
    }

    /**
     * Метод для получения фио и среднего балла учеников за весь период по номеру класса.
     * Реализовать на стороне бд
     * Если класс не найден, нужно вернуть 404 ошибку с текстом «Класс не найден»
     */
    @GetMapping("/student/meanMark")
    public ResponseEntity<String> getStudents(@RequestParam(defaultValue = "-1") Integer gradeNumber) {

        boolean isGradeExists = studentService.isGradeExists(gradeNumber);
        if (!isGradeExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Класс не найден\"}");
        }

        try {
            List<GroupedStudentsByMark> marks = markService.getMeanMarkByGrade(gradeNumber);
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(marks));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Ошибка сервера\"}");
    }
}
