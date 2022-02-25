package com.niit.mapper;

import com.niit.pojo.Leaveisagreed;
import java.util.List;

public interface LeaveisagreedMapper {
    int deleteByPrimaryKey(Integer leaveid);

    int insert(Leaveisagreed record);

    Leaveisagreed selectByPrimaryKey(Integer leaveid);

    List<Leaveisagreed> selectAll();

    int updateByPrimaryKey(Leaveisagreed record);
}