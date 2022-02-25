package com.niit;

import com.niit.mapper.AdminMapper;
import com.niit.mapper.LeaveMapper;
import com.niit.pojo.Admin;
import com.niit.pojo.Leave;
import com.niit.pojo.StudentLeave;
import com.niit.pojo.User;
import com.niit.service.AdminService;
import com.niit.service.LeaveService;
import com.niit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hmqhmq
 */
//@SpringBootTest(classes = TestApplication.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest {
    @Autowired
    private UserService userService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private AdminMapper adminMapper;

    //@Test
    public void testleaveMapper(){
       Leave leave= leaveMapper.selectLeave(1);
        List<Leave> allLeave =leaveMapper.selectAllLeave();

        System.out.println(leave.toString());
        for (Leave leave1: allLeave) {
            System.out.print(leave1.toString());
        }

    }

    //@Test
    public void testadminMapper(){
        adminMapper.selectAdmin(0);

    }




   // @Test
    public void testselectAllStudentLeave(){

        List<StudentLeave>  studentLeaveList=leaveService.selectAllStudentLeave();

        for (StudentLeave studentleave: studentLeaveList)
             System.out.println(studentleave.toString());
 }


    //@Test
    public void testselectAdmin(){
       adminService.selectAdmin(null);
       Admin admin=  adminService.selectAdmin(0);
        System.out.println( admin.toString());
    }

    //@Test
    public void testcheckUser(){
        int i;
        User user=new User();
        user.setLoginid(0);
        user.setPassword(null);

        i=userService.checkUser(user);
        System.out.println(i);

    }
}
