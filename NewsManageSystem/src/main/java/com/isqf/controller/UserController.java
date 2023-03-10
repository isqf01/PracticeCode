package com.isqf.controller;

import com.isqf.domain.Role;
import com.isqf.domain.User;
import com.isqf.service.RoleService;
import com.isqf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有状态的用户集合（用户列表）
     *
     * @param keywords
     * @param userListRoleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/findUserList.action")
    public String findUserList(String keywords, Integer userListRoleId, Model model) {
        //获取角色列表
        List<Role> roleList = roleService.findRoleList();
        model.addAttribute("roleList", roleList);

        //获取用户列表
        List<User> userList = userService.findUserList(keywords, userListRoleId);
        model.addAttribute("userList", userList);
        model.addAttribute("keywords", keywords);
        model.addAttribute("userListRoleId", userListRoleId);

        return "user/user_list";
    }

    /**
     * 转向添加用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toAddUser.action")
    public String toAddUser(Model model) {
        //获取角色列表,用于添加用户页面中的用户角色下拉列表
        List<Role> roleList = roleService.findRoleList();
        model.addAttribute("roleList", roleList);

        return "user/add_user";
    }


    /**
     * 判断登录账号是否已存在
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/checkLoginName.action")
    @ResponseBody
    public User checkLoginName(@RequestBody User user, Model model) {
        User checkUser = userService.getUserByLoginName(user.getLoginName());

        if (checkUser != null) {
            return checkUser;
        } else {
            checkUser = new User();
            checkUser.setUserId(0);
            return checkUser;
        }
    }


    /**
     * 添加用户
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/addUser.action", method = RequestMethod.POST)
    public String addUser(User user, Model model) {
        //获取角色列表
        List<Role> roleList = roleService.findRoleList();
        model.addAttribute("roleList", roleList);
        model.addAttribute("user", user);
        //检查登录账号是否已存在
        User checkUser = userService.getUserByLoginName(user.getLoginName());

        if (checkUser != null) {
            //登录账号已存在，重新转回添加用户页面
            model.addAttribute("checkUserLoginNameMsg", "登录账号已存在，请重新输入");
            return "user/add_user";
        } else {
            //登录账号可用
            Date date = new Date();
            user.setRegisterTime(date);
            //默认设置用户为启用状态“2”
            user.setStatus("2");
            //调用UserService实例中的添加用户方法
            int rows = userService.addUser(user);
            if (rows > 0) {
                //添加成功，转向用户列表页面
                return "redirect:findUserList.action";
            } else {
                //添加失败，重新转回添加用户页面
                return "user/add_user";
            }
        }
    }

    /**
     * 转向修改用户页面
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/toEditUser.action")
    public String toEditUser(Integer userId, Model model) {
        User user = userService.getUserByUserId(userId);

        if (user != null) {
            model.addAttribute("user", user);
            List<Role> roleList = roleService.findRoleList();
            model.addAttribute("roleList", roleList);
            return "user/edit_user";
        } else {
            return "redirect:findUserList.action";
        }
    }


    /**
     * 修改用户
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/editUser.action", method = RequestMethod.POST)
    public String editUser(User user, Model model) {
        // 获取角色列表
        Date date = new Date();
        user.setRegisterTime(date);
        // 默认设置用户为启用状态"2"
        user.setStatus("2");
        int rows = userService.editUser(user);
        if (rows > 0) {
            // 添加成功，转向用户列表页面
            return "redirect:findUserList.action";
        } else {
            List<Role> roleList = roleService.findRoleList();
            model.addAttribute("roleList", roleList);
            model.addAttribute("user", user);
            // 修改失败，转回修改用户页面
            return "user/edit_user";
        }
    }

    /**
     * 删除用户（前台页面中通过jQuery Ajax方式调用此方法）
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/delUser.action")
    @ResponseBody
    public User delUser(@RequestBody User user, Model model) {
        int rows = userService.delUser(user.getUserId());
        if (rows > 0) {
            return user;
        } else {
            //设置ID为0，标记失败
            user.setUserId(0);
            return user;
        }
    }

    /**
     * 禁用用户（更新status字段值 为'3'，前台页面中通过ajax方式调用此方法）
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/disableUser.action")
    @ResponseBody
    public User disableUser(@RequestBody User user, Model model) {
        int rows = userService.setUserStatus(user);
        if (rows > 0) {
            return user;
        } else {
            //此处设置userId为0，只是作为操作失败的标记用
            user.setUserId(0);
            return user;
        }
    }

    /**
     * 启用用户（更新status字段值 为'2'，前台页面中通过ajax方式调用此方法）
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/enableUser.action")
    @ResponseBody
    public User enableUser(@RequestBody User user, Model model) {
        int rows = userService.setUserStatus(user);
        if (rows > 0) {
            return user;
        } else {
            //此处设置userId为0，只是作为操作失败的标记用
            user.setUserId(0);
            return user;
        }
    }

    /**
     * 用户登录
     *
     * @param loginName
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public String login(String loginName, String password, Model model, HttpSession session) {
        User user = userService.findUser(loginName, password);
        if (user != null) {
            if (user.getStatus().equals("2")) {
                //用户被启用时，允许登录到后台
                session.setAttribute("login_user", user);
                return "main";
            } else {
                //用户未被启用或禁用时，不允许登录到后台
                model.addAttribute("msg", "账号未启用或被禁用，请联系管理员");
                return "login";
            }
        }
        model.addAttribute("msg", "账号或密码错误，请重新登录");
        return "login";
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout.action")
    public String logout(HttpSession session) {
        //清空session
        session.invalidate();
        return "login";
    }


}
