package com.isqf.service.impl;

import com.isqf.dao.UserDao;
import com.isqf.domain.User;
import com.isqf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findUserList(String keywords, Integer userListRoleId) {
        List<User> userList = this.userDao.selectUserList(keywords, userListRoleId);
        return userList;
    }

    @Override
    public User findUser(String loginName, String password) {
        return this.userDao.findUser(loginName, password);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return this.userDao.getUserByUserId(userId);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return this.userDao.getUserByLoginName(loginName);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int editUser(User user) {
        return this.userDao.updateUser(user);
    }

    @Override
    public int delUser(Integer userId) {
        return userDao.delUser(userId);
    }

    @Override
    public int setUserStatus(User user) {
        return userDao.setUserStatus(user);
    }
}
