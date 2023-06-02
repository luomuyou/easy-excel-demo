package com.example.exceldemo.service;

import com.example.exceldemo.domain.Student;

import java.util.List;

public interface StudentService {
    void readExcel(List<Student> students);
}
