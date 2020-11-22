package com.example.test.storage;

import com.example.test.entity.TableRow;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

public interface StorageService {

    Pattern ROW_PATTERN = Pattern.compile("^([1-9]|1[0-1]);[А-Яа-я ]+;[А-Яа-я ]+;" +
            "[1-5];[0-2][0-9]:[0-5][0-9]:[0-5][0-9] " +
            "[0-3][0-9]-[0-1][0-2]-[0-2][0-9][0-9][0-9]$");

    void storeFile(MultipartFile file) throws ParseException;

    List<TableRow> loadAll();
}
