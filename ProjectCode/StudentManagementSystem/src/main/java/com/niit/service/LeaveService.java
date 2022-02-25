package com.niit.service;

import com.niit.pojo.Leave;
import com.niit.pojo.StudentLeave;

import java.util.List;

public interface LeaveService {
    int addLeaveInfo(Leave leave);
    List<StudentLeave> selectAllStudentLeave();
}
