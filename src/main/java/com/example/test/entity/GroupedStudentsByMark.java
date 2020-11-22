package com.example.test.entity;

public class GroupedStudentsByMark {

    String full_name;

    Double mean_mark;

    public GroupedStudentsByMark() {
    }

    public GroupedStudentsByMark(String full_name, Double mean_mark) {
        this.full_name = full_name;
        this.mean_mark = mean_mark;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Double getMean_mark() {
        return mean_mark;
    }

    public void setMean_mark(Double mean_mark) {
        this.mean_mark = mean_mark;
    }

    @Override
    public String toString() {
        return "GroupedStudentsByMark{" +
                "full_name='" + full_name + '\'' +
                ", mean_mark=" + mean_mark +
                '}';
    }
}
