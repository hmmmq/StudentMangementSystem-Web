package com.niit.mapper;

import com.niit.pojo.Student;
import java.util.List;

public interface StudentMapper {
    

    
    int deleteStudent(Integer stuId);
    Student selectStudent(Integer stuId);
    int updateStudent(Student student);
    List<Student> selectAllStudent();
    int addStudent(Student student);
}