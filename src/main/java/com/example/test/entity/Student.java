package com.example.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "public")
public class Student implements Serializable {

    @Id
    @Column(name = "\"student_id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "\"full_name\"", unique = true)
    private String fullName;

    @Column(name = "\"grade_number\"")
    private int gradeNumber;

    //                              к какому полю маппимся в Mark
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = {})
    private List<Mark> marks;

    public Student() {
    }

    public Student(String fullName, int gradeNumber) {
        this.fullName = fullName;
        this.gradeNumber = gradeNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String FIO) {
        this.fullName = FIO;
    }

    public int getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(int classNumber) {
        this.gradeNumber = classNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return gradeNumber == student.gradeNumber &&
                Objects.equals(fullName, student.fullName) &&
                Objects.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, gradeNumber, marks);
    }
}
