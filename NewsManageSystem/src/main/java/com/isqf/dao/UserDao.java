package com.isqf.dao;

import com.isqf.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户
     *
     * @param keywords
     * @param userListRoleId
     * @return
     */

    //注解中使用动态SQL语句查询是，加上<script>标签包围，然后像xml语法一样书写
    @Select({"<script>select u.*,r.roleName from t_user as u,t_role as r" +
            "<where>u.roleId=r.roleId" +
            "<if test='keywords!=null and keywords!=\"\"'>" +
            "  and (u.username like CONCAT('%',#{keywords},'%') or u.loginName like CONCAT('%',#{keywords},'%'))" +
            "</if>" +
            "<if test='userListRoleId!=null and userListRoleId!=\"\"'>" +
            "and (u.roleId=#{userListRoleId})" +
            "</if>" +
            "</where>" +
            "order by registerTime desc</script>"})
    public List<User> selectUserList(@Param("keywords") String keywords, @Param("userListRoleId") Integer userListRoleId);

    /**
     * 通过账号和密码查询用户
     *
     * @param loginName
     * @param password
     * @return
     */

    @Select("select * from t_user where loginName=#{loginName} and password = #{password} limit 0,1")
    public User findUser(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 通过用户id查询用户
     *
     * @param userId
     * @return
     */
    @Select("select * from t_user where userId = #{userId}")
    public User getUserByUserId(@Param("userId") Integer userId);


    /**
     * 通过用户登录名查询用户（用于判断用户名是否已存在）
     *
     * @param loginName
     * @return
     */
    @Select("select * from t_user where loginName = #{loginName}")
    public User getUserByLoginName(@Param("loginName") String loginName);


    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Insert("insert into t_user(userName,loginName,password,tel,registerTime,status,roleId) " +
            "values(#{userName},#{loginName},#{password},#{tel},#{registerTime},#{status},#{roleId})")
    public int addUser(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Update("<script>update t_user " +
            "<set>registerTime=#{registerTime},status=#{status}," +
            "<if test=\"userName!=null and userName!=''\">userName=#{userName},</if>" +
            "<if test=\"password!=null and password!=''\">password=#{password},</if>" +
            "<if test=\"tel!=null and tel!=''\">tel=#{tel},</if>" +
            "<if test=\"roleId!=null and roleId!=''\">roleId=#{roleId},</if>" +
            "</set>" +
            " where userId=#{userId}</script>")
    public int updateUser(User user);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Delete("delete from t_user where userId=#{userId}")
    public int delUser(@Param("userId") Integer userId);

    /**
     * 设置用户状态（‘1’:未启用，‘2’:已启用，‘3’:被禁用）
     *
     * @param user
     * @return
     */
    @Update("update t_user set status = #{status} where userId = #{userId}")
    public int setUserStatus(User user);


}
