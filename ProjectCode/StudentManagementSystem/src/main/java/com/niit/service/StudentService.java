package com.niit.service;

import com.niit.pojo.Student;
import com.niit.pojo.StudentDetail;

import java.util.List;

public interface StudentService {
    int updateStudent(StudentDetail student);
    int deleteStudent(Integer stuId);
    Student selectStudent(Integer stuId);
    List<Student> selectAllStudent();
    int addStudent(StudentDetail student);

}
