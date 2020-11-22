package com.example.test.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TableRow implements Serializable {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");

    private String fullName;
    private int gradeNumber;
    private String subjectName;
    private int mark;
    private Date markDate;

    public TableRow() {}

    public TableRow(String fullName, int gradeNumber, String subjectName, int mark, Date markDate) {
        this.fullName = fullName;
        this.gradeNumber = gradeNumber;
        this.subjectName = subjectName;
        this.mark = mark;
        this.markDate = new Date(markDate.getTime());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(int gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getMarkDate() {
        return DATE_FORMAT.format(this.markDate);
    }

    public boolean setMarkDate(String markDate) {
        boolean isOk = true;
        try {
            this.markDate = DATE_FORMAT.parse(markDate);
        } catch (ParseException e) {
            e.printStackTrace();
            isOk = false;
        }
        return isOk;
    }

    public static TableRow getRowFromStringArray(String[] parts) {

        if (parts.length != 5) {
            return null;
        }

        TableRow row = new TableRow();
        row.setGradeNumber(Integer.parseInt(parts[0]));
        row.setFullName(parts[1]);
        row.setSubjectName(parts[2]);
        row.setMark(Integer.parseInt(parts[3]));
        boolean isDateValid = row.setMarkDate(parts[4]);
        if (!isDateValid) {
            return null;
        }

        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableRow)) return false;
        TableRow tableRow = (TableRow) o;
        return gradeNumber == tableRow.gradeNumber &&
                mark == tableRow.mark &&
                Objects.equals(fullName, tableRow.fullName) &&
                Objects.equals(subjectName, tableRow.subjectName) &&
                Objects.equals(markDate, tableRow.markDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, gradeNumber, subjectName, mark, markDate);
    }
}
