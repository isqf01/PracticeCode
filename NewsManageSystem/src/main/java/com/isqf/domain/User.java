package com.isqf.domain;

import java.lang.ref.SoftReference;
import java.util.Date;

public class User {
    private Integer userId;
    private String userName;
    private String loginName;
    private String password;
    private String tel;
    private Date registerTime;
    private String status;
    private Integer roleId;
    private String roleName;

    public User() {
    }

    public User(Integer userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public User(Integer userId, String userName, String password, String tel, Integer roleId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.tel = tel;
        this.roleId = roleId;
    }

    public User(String userName, String loginName, String password, String tel, Integer roleId, String roleName) {
        this.userName = userName;
        this.loginName = loginName;
        this.password = password;
        this.tel = tel;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", registerTime=" + registerTime +
                ", status='" + status + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\''+
                '}'+"\n";
    }
}
