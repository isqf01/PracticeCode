package com.isqf.service.impl;

import com.isqf.dao.RoleDao;
import com.isqf.domain.Role;
import com.isqf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
     private RoleDao roleDao;
    @Override
    public List<Role> findRoleList() {
        List<Role> roleList = roleDao.selectRoleList();
        return roleList;
    }
}
