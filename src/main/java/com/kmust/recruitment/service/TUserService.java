package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TUser;

import java.util.List;

/**
    TUserService接口
 */
public interface TUserService {

    /**
     * 用户注册
     * @param user 新用户数据
     */
    void reg(TUser user);

    /**
     * 用户账号密码登录
     * @param username 用户名
     * @param password 密码
     * @return 成功登录的用户的数据
     */
    TUser login(String username, String password);

    /**
     * 手机号码登录
     * @param phone 手机号
     * @return
     */
    TUser phoneLogin(String phone);

    /**
     * 修改密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid, String username,
                        String oldPassword, String newPassword);

    /**
     * 修改头像
     * @param uid 用户id
     * @param username 用户名
     * @param avatar 新头像的路径
     */
    void changeAvatar(Integer uid, String username,
                      String avatar);

    /**
     * 修改个人资料
     * @param uid 用户id
     * @param user 新的个人资料
     */
    void updateInfo(Integer uid, TUser user);

    /**
     * 根据用户号码查询用户数据
     * @param phone 用户号码
     * @return 用户数据
     */
    TUser getByPhone(String phone);
    /**
     * 验证密码是否正确
     * @param uid 用户id
     * @param password 用户所填密码
     * @return
     */
    //Integer Password(Integer uid, String password);

    /**
     * 忘记，修改密码
     * @param username 用户名
     * @param newPassword 新密码
     */
    Integer missPassword(String username, String newPassword);


    /**
     * 核对验证码
     * @param userCode
     * @param code
     * @return
     */
    boolean checkCode(String userCode, String code);

    /**
     * 验证用户名和手机号
     * @param username
     * @param phone
     * @return
     */
    boolean check(String username, String phone);

    /**
     * 根据用户id查找用户数据
     * @param id
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    TUser findById(Integer id);

    /**
     * 根据用户id删除用户数据
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    TUser findByUsername(String username);

    /**
     * 根据phone查找
     * @param phone
     * @return
     */
    TUser findByPhone(String phone);

    /**
     * 查找所有用户数据
     * @return
     */
    List<TUser> findALL();

    /**
     * 存入用户数据
     * @param tUser
     */
    void saveUser(TUser tUser);
    /**
     * 根据电话号码发送验证码
     * @param phone 用户所填的电话号码
     * @return 状态码
     */
    String[] sendSMS(String phone);
}
