package com.niit.service.impl;

import com.niit.mapper.StudentMapper;
import com.niit.mapper.UserMapper;
import com.niit.pojo.Student;
import com.niit.pojo.StudentDetail;
import com.niit.pojo.User;
import com.niit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private UserMapper userMapper;

	public int updateStudent(StudentDetail student) {
		// TODO Auto-generated method stub
		if(student.getStuID()==null||student==null ) return 0;
		Student stu=new Student();
		stu.setAddress(student.getAddress());
		stu.setAge(student.getAge());
		stu.setBatch(student.getBatch());
		stu.setContactnumber(student.getContactNumber());
		stu.setEmail(student.getEmail());
		stu.setName(student.getName());
		stu.setStuid(student.getStuID());
		if(student.getPassword()!=null){
			User user=new User();
			user.setLoginid(student.getStuID());
			user.setPassword(student.getPassword());
			userMapper.updateUser(user);

		}


		return studentMapper.updateStudent(stu);
	}

	public int deleteStudent(Integer stuId) {
		// TODO Auto-generated method stub
		if(stuId==null||selectStudent(stuId)==null) return 0;
		return studentMapper.deleteStudent(stuId);
	}

	public Student selectStudent(Integer stuId) {
		// TODO Auto-generated method stub
		if(stuId==null||studentMapper.selectStudent(stuId)==null) return null;
		return studentMapper.selectStudent(stuId);
	}

	public int addStudent(StudentDetail student) {
		if(student==null) return 0;
        Student stu=new Student();
        stu.setAddress(student.getAddress());
        stu.setAge(student.getAge());
        stu.setBatch(student.getBatch());
        stu.setContactnumber(student.getContactNumber());
        stu.setEmail(student.getEmail());
        stu.setName(student.getName());
        return studentMapper.addStudent(stu);
    }

    public List<Student> selectAllStudent() {
        List<Student> studentList = studentMapper.selectAllStudent();
        return studentList;
    }
}
