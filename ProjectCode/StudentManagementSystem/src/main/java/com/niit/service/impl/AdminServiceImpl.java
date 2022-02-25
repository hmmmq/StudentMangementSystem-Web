package com.niit.service.impl;

import com.niit.mapper.AdminMapper;
import com.niit.pojo.Admin;
import com.niit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    public Admin selectAdmin(Integer adminId) {
        if(adminId==null)
        return null;
        return  adminMapper.selectAdmin(adminId);
    }
}
