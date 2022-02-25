package com.niit.controller;

import com.niit.pojo.*;
import com.niit.service.AdminService;
import com.niit.service.LeaveService;
import com.niit.service.StudentService;
import com.niit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class StudentController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    AdminService adminService;
    @Autowired
    LeaveService leaveService;
    

  @RequestMapping("Student.look_detail")
  public ModelAndView lookdetail(Student student, User user){
      ModelAndView mv=new ModelAndView();
      mv.setViewName("students");
      mv.addObject("student", student);
      mv.addObject("user", user);
      return mv;

  }

  @RequestMapping("Student.to_modify_detail")
  public ModelAndView tomodifydetail(StudentDetail studentDetail){
      ModelAndView mv=new ModelAndView();
      mv.setViewName("studentsmodify");
      mv.addObject("studentail", studentDetail);
      return mv;

  }
  @RequestMapping("Student.modify_detail")
  public ModelAndView modifydetail(StudentDetail studentDetail){
      ModelAndView mv=new ModelAndView();
      mv.setViewName("studentsmodify");
      studentService.updateStudent(studentDetail);
      mv.addObject("studentail", studentDetail);
      return mv;
  }
  @RequestMapping("Student.ask_leave")
  public ModelAndView askleave(LeaveDTO leaveDTO,Student student, User user) throws ParseException{
      ModelAndView mv=new ModelAndView();

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
      Date date = simpleDateFormat.parse(leaveDTO.getLeavedate());
      Leave leave=new Leave(leaveDTO.getLeaveid(),leaveDTO.getStuid(),date,leaveDTO.getReason());

      leaveService.addLeaveInfo(leave);
      mv.setViewName("students");
      return mv;

  }
  
}
