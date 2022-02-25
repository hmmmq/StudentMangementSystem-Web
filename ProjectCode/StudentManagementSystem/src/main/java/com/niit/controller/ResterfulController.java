package com.niit.controller;

import com.niit.mapper.LeaveisagreedMapper;
import com.niit.pojo.*;
import com.niit.service.AdminService;
import com.niit.service.LeaveService;
import com.niit.service.StudentService;
import com.niit.service.UserService;
import com.niit.util.ServerResponse;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/restful")
public class ResterfulController {
    @Autowired
    StudentService studentService;
    @Autowired
    LeaveService leaveService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    LeaveisagreedMapper leaveisagreedMapper;

    @PostMapping("/User/login")
    ServerResponse userLogin(@RequestBody User user) {
        int i = userService.checkUser(user);
        if (i == 0) {
            return ServerResponse.createByresponseCode(ResponseCode.FailGetData);
        } else if (i == 1) {//是管理员
            Admin admin = adminService.selectAdmin(user.getLoginid());
            List<Student> studentList = studentService.selectAllStudent();
            //Map<String,Object> map=new HashMap<String, Object>();
            return ServerResponse.createByresponseCodeData(ResponseCode.SuccessGetData, studentList);

        } else if (i == 2) {//是学生
            Student student = studentService.selectStudent(user.getLoginid());
            return ServerResponse.createByresponseCodeData(ResponseCode.SuccessGetData, student);
        } else {

            return ServerResponse.createByresponseCode(ResponseCode.FailGetData);
        }
    }


    @PostMapping("/Admin/modify_students_info")
    ServerResponse adminModify(@RequestBody Student student) {
        StudentDetail studentDetail=
                new StudentDetail( student.getStuid(),student.getName(),student.getBatch(),
               null,student.getAge(),student.getAddress(),
                        student.getContactnumber(),student.getEmail());
                   int i=studentService.updateStudent(studentDetail);
          if (i==0)
        return ServerResponse.createByresponseCode(ResponseCode.FailUpdate);
          else
        return ServerResponse.createByresponseCode(ResponseCode.SuccessfullyUpdate);

    }
    @PostMapping("/Admin/add_new_students")
    ServerResponse administratoradd(@RequestBody StudentDetail student){
        if(studentService.addStudent(student)==1){
            return ServerResponse.createByresponseCodeData(ResponseCode.SuccessfullyAdd,student);
        }else {
            return ServerResponse.createByresponseCode(ResponseCode.FailAdd);
        }
    }

    @GetMapping("/Admin/look_students_leave")
    ServerResponse administratorcheck() throws ParseException {
        List<StudentLeave> studentLeaveList = leaveService.selectAllStudentLeave();
        List<StudentLeaveDTO> studentLeaveDTOList = new ArrayList<StudentLeaveDTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Leaveisagreed> leaveisagreeds = leaveisagreedMapper.selectAll();
        for (StudentLeave studentLeave : studentLeaveList) {
            for (Leaveisagreed leaveisagreed:leaveisagreeds){
                if (leaveisagreed.getLeaveid()==studentLeave.getLeaveid()){

                    StudentLeaveDTO slDTO = new StudentLeaveDTO();
                    slDTO.setName(studentLeave.getName());
                    slDTO.setBatch(studentLeave.getBatch());
                    slDTO.setAge(studentLeave.getAge());
                    slDTO.setLeaveDate(sdf.format(studentLeave.getLeaveDate()));
                    slDTO.setReason(studentLeave.getReason());
                    slDTO.setLeaveId(leaveisagreed.getLeaveid());
                    slDTO.setleaveIsagreed(leaveisagreed.getIsagreed());
                    studentLeaveDTOList.add(slDTO);


                }

            }

        }
        return ServerResponse.createByresponseCodeData(ResponseCode.SuccessGetData, studentLeaveDTOList);

    }

    @GetMapping("/Admin/delete_new_students")
    ServerResponse administratordelete(@RequestParam("stuid")Integer stuid){
        if(studentService.deleteStudent(stuid)==1){
            return ServerResponse.createByresponseCodeData(ResponseCode.SuccessfullyDelete,stuid);
        }else{
            return ServerResponse.createByresponseCode(ResponseCode.FailDelete);
        }
    }

    @PostMapping("/Student/look_detail")
    Map lookdetail(@RequestBody HashMap map){
        Map<String,Object> map1=new HashMap<String, Object>();
        map1.put("student", ServerResponse.createByresponseCodeData(ResponseCode.SuccessGetData,map.get("student")));
        map1.put("user", ServerResponse.createByresponseCodeData(ResponseCode.SuccessGetData,map.get("user")));
        return map1;
    }

    @PostMapping("/Student/modify_detail")
    ServerResponse modifydetail(@RequestBody StudentDetail studentDetail){
        if(studentService.updateStudent(studentDetail)>0)
            return ServerResponse.createByresponseCodeData(ResponseCode.SuccessfullyUpdate,studentDetail);
        else
            return ServerResponse.createByresponseCode(ResponseCode.FailUpdate);
    }

    @PostMapping("/Student/ask_leave")
    ServerResponse askleave(@RequestBody LeaveDTO leaveDTO) throws ParseException, java.text.ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(leaveDTO.getLeavedate());
        Leave leave=new Leave(leaveDTO.getLeaveid(),leaveDTO.getStuid(),date,leaveDTO.getReason());
        if(leaveService.addLeaveInfo(leave)>0)
            return ServerResponse.createByresponseCode(ResponseCode.SuccessfullyAdd);
        else
            return ServerResponse.createByresponseCode(ResponseCode.FailAdd);
    }
    @GetMapping("/admin/to-home")
    ServerResponse admintohome(){
        List<Student> studentList= studentService.selectAllStudent();
        return ServerResponse.createByresponseCodeData(ResponseCode.SuccessGetStusData, studentList);
    }

    @PostMapping("/Admin/agree_leave")
    ServerResponse agree_leave(@RequestBody Leaveisagreed leaveisagreed){
        if(leaveisagreed==null||leaveisagreed.getLeaveid()==null||leaveisagreed.getIsagreed()==null)
            return ServerResponse.createByresponseCode(ResponseCode.FailToAgree);
        int i=leaveisagreedMapper.updateByPrimaryKey(leaveisagreed);
        if(i==1)
             return ServerResponse.createByresponseCode(ResponseCode.SuccessfullyToAgree);
        else
            return ServerResponse.createByresponseCode(ResponseCode.FailToAgree);
    }
}