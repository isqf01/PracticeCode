package com.isqf.dao;

import com.isqf.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    /**
     * 获取所有角色列表
     * @return
     */
    @Select("select roleId,roleName from t_role")
    public List<Role> selectRoleList();
}
