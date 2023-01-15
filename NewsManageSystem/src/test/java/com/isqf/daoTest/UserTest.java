package com.isqf.daoTest;

import com.isqf.config.SpringConfig;
import com.isqf.dao.UserDao;
import com.isqf.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserTest {
    @Autowired
    UserDao userDao;

    @Test
    public void testFindUserList(){
        List<User> userList = userDao.selectUserList("无",1);
        System.out.println(userList);
    }


    @Test
    public void testFindUser(){
        System.out.println(userDao.findUser("admin", "123456"));
    }


    @Test
    public void testGetUserByUserId(){
        System.out.println(userDao.getUserByUserId(3));
    }

    @Test
    public void testGetUserByLoginName(){
        System.out.println(userDao.getUserByLoginName("user"));
    }

    @Test
    public void testAddUser(){
        User user=new User("Hello","hello","123456","",2,"用户");
        System.out.println(userDao.addUser(user));
    }

    @Test
    public void testUpdateUser(){
        User user=new User(3,"hello","123456",null,2);
        System.out.println(userDao.updateUser(user));
    }

    @Test
    public void testDelUser(){
        System.out.println(userDao.delUser(5));
    }

    @Test
    public void testSetUserStatus(){
        User user = new User(5,"2");
        System.out.println(userDao.setUserStatus(user));
    }

}
