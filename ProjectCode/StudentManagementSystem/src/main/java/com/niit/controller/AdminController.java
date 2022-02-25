package com.niit.controller;

import com.niit.pojo.Student;
import com.niit.pojo.StudentDetail;
import com.niit.pojo.StudentLeave;
import com.niit.pojo.StudentLeaveDTO;
import com.niit.service.LeaveService;
import com.niit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController
{
    @Autowired
    StudentService studentService;
    @Autowired
    LeaveService leaveService;

    @RequestMapping("admin.to-home")
    public ModelAndView tohome(){
        ModelAndView mv=new ModelAndView();
        List<Student> studentList= studentService.selectAllStudent();
        mv.addObject(studentList);
        mv.setViewName("administrator");
        return  mv;
    }


    @RequestMapping("Admin.modify_students_info")
    public ModelAndView administratorchange(Student student){
        ModelAndView mv=new ModelAndView();
        StudentDetail studentDetail=
      new StudentDetail(
              student.getStuid(),student.getName(),student.getBatch(),
              null,student.getAge(),student.getAddress(),
              student.getContactnumber(),student.getEmail());

        studentService.updateStudent(studentDetail);
        mv.addObject(student);
        mv.setViewName("administratorchange");
        return  mv;
    }

    @RequestMapping("Admin.add_new_students")
    public ModelAndView administratoradd(StudentDetail student){
        studentService.addStudent(student);
        return tohome();
    }

    @RequestMapping("Admin.look_students_leave")
    public ModelAndView administratorcheck() throws ParseException {
        ModelAndView mv=new ModelAndView();
        List<StudentLeave> studentLeaveList = leaveService.selectAllStudentLeave();
        List<StudentLeaveDTO> studentLeaveDTOList = new ArrayList<StudentLeaveDTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(StudentLeave studentLeave:studentLeaveList){
            StudentLeaveDTO slDTO = new StudentLeaveDTO();
            slDTO.setName(studentLeave.getName());
            slDTO.setBatch(studentLeave.getBatch());
            slDTO.setAge(studentLeave.getAge());
            slDTO.setLeaveDate(sdf.format(studentLeave.getLeaveDate()));
            slDTO.setReason(studentLeave.getReason());
            studentLeaveDTOList.add(slDTO);
        }
        mv.addObject(studentLeaveDTOList);
        mv.setViewName("administratorcheck");
        return  mv;
    }

    @RequestMapping("Admin.delete_new_students")
   public ModelAndView administratordelete(@RequestParam("stuid")Integer stuid){
        studentService.deleteStudent(stuid);
        return tohome();
    }



}
