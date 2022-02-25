package com.niit.controller;

import com.niit.pojo.Admin;
import com.niit.pojo.Student;
import com.niit.pojo.User;
import com.niit.service.AdminService;
import com.niit.service.StudentService;
import com.niit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    AdminService adminService;

    @RequestMapping("user.login")
    public ModelAndView login(User user){
        ModelAndView mv=new ModelAndView();
        int i=userService.checkUser(user);
        if(i==0){ mv.setViewName("index"); }
        else if(i==1){//是管理员
           Admin admin=adminService.selectAdmin(user.getLoginid());
           List<Student> studentList= studentService.selectAllStudent();
            mv.addObject(admin);
            mv.addObject(studentList);
            mv.setViewName("administrator");
        }else if(i==2){//是学生
           Student student=studentService.selectStudent(user.getLoginid());
           mv.addObject(student);
           mv.addObject(user);
           mv.setViewName("students");
        }

        return mv;

    }



}
