package com.kmust.recruitment.service;


import com.kmust.recruitment.dao.TUserDao;
import com.kmust.recruitment.entity.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TUserServiceTest {
    @Autowired
    private TUserService tUserService;
    @Autowired
    private TUserDao tUserDao;

    @Test
    public void codeTest() {
        String phone = "18687232377";
        String result[] = tUserService.sendSMS(phone);
        System.out.println(result[0]);
        System.out.println("返回的验证码:" + result[1]);
    }

    @Test
    public void getByPhone(){
        String phone = "18687232377";
        tUserService.getByPhone(phone);
        System.out.println(tUserService.findALL());
    }

    @Test
    public void reg(){

        TUser.TUserBuilder builder = TUser.builder();
        builder.username("kuzma").password("123456");
        TUser tUser = builder.build();
        System.err.println(tUser);
        tUserService.reg(tUser);

    }

    @Test
    public void findById(){
        Integer id = 1;
        System.err.println("用户id为 "+id+" 的用户数据:"+tUserService.findById(id));
        System.out.println(tUserService.findById(1).getUsername());

    }

    @Test
    public void saveUser(){
        TUser tUser = new TUser();
        // 创建当前时间对象now
        Date now = new Date();
        // 封装Order属性：uid
        tUser.setId(2);
        /*tUser.setUsername("admin");
        tUser.setGender("男");
        tUser.setEmail("1639269722@qq.com");
        tUser.setAge(1);
        tUser.setAvatar("/src/avatar/1.jpg");
        tUser.setCreditRating("优");
        tUser.setIdentity("学生");
        tUser.setMajor("软件工程");
        tUser.setWechat("kuz0824");
        tUser.setRealName("王五");
        tUser.setPhone("13115679880");
        tUser.setQq("1639269722");*/
       /* tUser.setUsername("kuzma");
        tUser.setGender("男");
        tUser.setEmail("2210347210@qq.com");
        tUser.setAge(40);
        tUser.setAvatar("/src/avatar/default.png");
        tUser.setCreditRating("优");
        tUser.setIdentity("学生");
        tUser.setMajor("软件工程");
        tUser.setWechat("kobe0824");
        tUser.setRealName("罗志祥");
        tUser.setPhone("13915679880");
        tUser.setQq("16989269722");*/
        tUser.setCompany("alibaba");

        // 执行加密
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password( "123456", salt);
        // 补全数据：salt
        tUser.setSalt(salt);
        // 补全数据：加密后的密码
        tUser.setPassword(md5Password);
        // 封装Order属性：4项日志
        //tUser.setCreatedUser(tUser.getUsername());
        //tUser.setCreatedTime(now);
        tUser.setModifiedUser(tUser.getUsername());
        tUser.setModifiedTime(now);

        tUserService.updateInfo(tUser.getId(),tUser);
        //tUserService.saveUser(tUser);


    }
    @Test
    public void delete(){

       tUserService.deleteById(3);
    }

    @Test
    public void missPassword(){
        tUserService.missPassword("admin","123456");
        System.out.println(tUserService.findById(1));


    }
    /**
     * 执行密码加密
     * @param password 原密码
     * @param salt 盐值
     * @return 加密后密码
     */
    private String getMd5Password(String password, String salt) {
        // 加密规则：在原密码的左侧和右侧均拼接盐值，循环加密5次
        String str = salt + password + salt;
        for (int i = 0; i < 5; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
        }
        return str;
    }

    @Test
    public void tests(){
        String phone = "18313761330";
        String phoneCode = "669553";
        TUser tUser = tUserService.getByPhone(phone);
        System.out.println(tUser.getCode().equals(phoneCode));
    }
}
