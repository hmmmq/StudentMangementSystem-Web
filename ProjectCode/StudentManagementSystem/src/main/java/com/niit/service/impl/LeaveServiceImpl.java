package com.niit.service.impl;

import com.niit.mapper.LeaveMapper;
import com.niit.mapper.StudentMapper;
import com.niit.pojo.Leave;
import com.niit.pojo.Student;
import com.niit.pojo.StudentLeave;
import com.niit.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LeaveServiceImpl implements LeaveService {
       @Autowired
    LeaveMapper leaveMapper;
       @Autowired
    StudentMapper studentMapper;



    public int addLeaveInfo(Leave leave) {
       if(leave!=null)
           if (leave.getStuid()!=null)
              return leaveMapper.addLeaveInfo(leave);
           else
               return 0;
       else
           return 0;

    }

    public List<StudentLeave> selectAllStudentLeave() {
       List<Leave> leaveList= leaveMapper.selectAllLeave();
           if (leaveList.isEmpty()) return null;


       List<Student> studentList= studentMapper.selectAllStudent();
          if (studentList.isEmpty()) return null;

       List<StudentLeave>  studentLeaveList=new ArrayList<StudentLeave>();


        for (Leave leave:leaveList ) {
          for (Student student:studentList){
              if(leave.getStuid()==student.getStuid()){
                  StudentLeave studentLeave=new StudentLeave(leave.getLeaveid(),student.getName(),student.getBatch(),student.getAge(),leave.getLeavedate(),leave.getReason());
                  studentLeaveList.add(studentLeave);
              }

          }
        }
        return studentLeaveList;
    }


}
