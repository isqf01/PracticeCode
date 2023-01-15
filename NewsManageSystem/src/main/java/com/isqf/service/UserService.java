package com.isqf.service;

import com.isqf.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public List<User> findUserList(String keywords,Integer userListRoleId);

    public User findUser(String loginName,String password);

    public User getUserByUserId(Integer userId);

    public User getUserByLoginName( String loginName);

    public int addUser(User user);

    public int editUser(User user);

    public int delUser(Integer userId);

    public int setUserStatus(User user);

}
