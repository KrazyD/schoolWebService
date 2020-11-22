package com.example.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mark", schema = "public")
public class Mark implements Serializable {

    @Id
    @Column(name = "\"mark_id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mark_id;

    @Column(name = "\"mark\"")
    private int mark;

    // Колонка в текущей таблице, которая будет использована
    // для маппинга в первичный ключ таблицы Student
    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    // Колонка в текущей таблице, которая будет использована
    // для маппинга в первичный ключ таблицы Subject
    @JoinColumn(name = "subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SchoolSubject subject;

    @Column(name = "\"mark_date\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date markDate;

    public Mark() {
    }

    public Mark(int mark, Student student, SchoolSubject subject, Date markDate) {
        this.mark = mark;
        this.student = student;
        this.subject = subject;
        this.markDate = markDate;
    }

    public long getMark_id() {
        return mark_id;
    }

    public void setId(long id) {
        this.mark_id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SchoolSubject getSubject() {
        return subject;
    }

    public void setSubject(SchoolSubject subject) {
        this.subject = subject;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;
        Mark mark1 = (Mark) o;
        return mark == mark1.mark &&
                Objects.equals(student, mark1.student) &&
                Objects.equals(subject, mark1.subject) &&
                Objects.equals(markDate, mark1.markDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, student, subject, markDate);
    }
}
