package com.example.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subject", schema = "public")
public class SchoolSubject implements Serializable {

    @Id
    @Column(name = "\"subject_id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "\"name\"", unique = true)
    private String name;

    //                              к какому полю маппимся в Mark
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", cascade = {})
    private List<Mark> marks;

    public SchoolSubject() {
    }

    public SchoolSubject(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolSubject)) return false;
        SchoolSubject subject = (SchoolSubject) o;
        return Objects.equals(name, subject.name) &&
                Objects.equals(marks, subject.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, marks);
    }
}
