package com.example.exceldemo.service.impl;

import com.example.exceldemo.domain.Student;
import com.example.exceldemo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public void readExcel(List<Student> students) {
        for (Student student:students) {
            System.out.println("student="+student);
        }
    }
}
