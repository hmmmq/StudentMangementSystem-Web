package com.niit.mapper;

import com.niit.pojo.Leave;
import java.util.List;

public interface LeaveMapper {

    List<Leave> selectAllLeave();

    Leave  selectLeave(Integer stuId);
    int addLeaveInfo(Leave leave);
}