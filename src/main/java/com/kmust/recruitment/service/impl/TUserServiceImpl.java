package com.kmust.recruitment.service.impl;


import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.kmust.recruitment.dao.TUserDao;
import com.kmust.recruitment.entity.TUser;
import com.kmust.recruitment.service.TUserService;
import com.kmust.recruitment.service.ex.*;
import com.kmust.recruitment.utils.UpdateUtil;
import org.hibernate.Session;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserDao tUserDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void reg(TUser user) {
        // 从参数user对象中获取用户名
        String username = user.getUsername();
        // 调用userMapper根据用户名查询用户数据
        TUser result = tUserDao.findByUsername(username);
        // 判断查询结果是否不为null
        if (result != null) {
            // 是：用户名已存在，不允许注册，抛出UsernameConflictException异常
            throw new UsernameConflictException("用户名(" + username + ")已经被占用");
        }

        // 参数user是客户端提交的注册数据，并不包含隐藏数据
        // 执行加密
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        // 补全数据：salt
        user.setSalt(salt);
        // 补全数据：加密后的密码
        user.setPassword(md5Password);
        // 补全数据：is_delete，值为0
        user.setIsDelete(0);
        // 补全数据：avatar 默认头像
        user.setAvatar("/upload_avatar/default.png");
        // 补全数据：4项日志，用户名为注册的用户名，时间为当前时间
        Date now = new Date();
        user.setCreatedUser(username);
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setModifiedTime(now);
        tUserDao.save(user);
    }

    @Override
    public TUser login(String username, String password) {
        //获取session
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        // 根据参数username查询用户数据
        TUser result = tUserDao.findByUsername(username);

        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户名不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户名不存在");
        }

        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete()==1) {
            // 是：用户被标记为已删除，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据已被删除");
        }

        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 调用getMd5Password()，基于参数password和盐值进行加密
        String md5Password = getMd5Password(password, salt);
        // 测试输出
        System.err.println("TUserServiceImpl.login() ---> 验证登录");
        System.err.println("password=" + password);
        System.err.println("salt=" + salt);
        System.err.println("md5Password=" + md5Password);
        System.err.println("result.getPassword()=" + result.getPassword());
        // 判断加密后的密码与查询结果中的密码是否不匹配
        if (!result.getPassword().equals(md5Password)) {
            // 是：密码错误，抛出PasswordNotMatchException
            throw new PasswordNotMatchException("密码错误");
        }

        /*if(entityManager.contains(result)){
            //"实体对象属于托管状态下时，往这个对象里面的某个属性 set 新的值，这个新的值会被更新到数据表中去。"
              //      + "使用 EntityManager#contains(entity) 方法可以得知某个实体对象是否处于托管状态，也就是说是否处于 persistence context 中")
            entityManager.clear();
            //"entityManager.clear()方法被调用，已经被清理")
        }*/

        // 将查询结果中不应该返回的字段设置为null
        // 例如：isDelete、createdUser、createdTime、modifiedUser、modifiedTime、password、salt
        result.setIsDelete(0);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);
        result.setPassword(null);
        result.setSalt(null);
        session.evict(result);
        // 返回查询结果
        return result;
    }

    @Override
    public TUser phoneLogin(String phone){
        //HibernateEntityManager hibernateEntityManager = (HibernateEntityManager) entityManager;
        //获取session
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        // 根据参数username查询用户数据
        TUser result = tUserDao.findByPhone(phone);

        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户不存在");
        }

        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete()==1) {
            // 是：用户被标记为已删除，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据已被删除");
        }

        /*if(entityManager.contains(result)){
            //"实体对象属于托管状态下时，往这个对象里面的某个属性 set 新的值，这个新的值会被更新到数据表中去。"
              //      + "使用 EntityManager#contains(entity) 方法可以得知某个实体对象是否处于托管状态，也就是说是否处于 persistence context 中")
            entityManager.clear();
            //"entityManager.clear()方法被调用，已经被清理")
        }*/


        // 将查询结果中不应该返回的字段设置为null
        // 例如：isDelete、createdUser、createdTime、modifiedUser、modifiedTime、password、salt
        result.setIsDelete(0);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);
        result.setPassword(null);
        result.setSalt(null);
        session.evict(result);
        // 返回查询结果
        return result;
    }

    @Override
    public TUser getByPhone(String phone) {
        // 根据参数phone查询用户数据T
        TUser result = tUserDao.findByPhone(phone);

        if (result == null) {
            System.out.println("没有找到号码");
            //如果没有该号码的对象，则创建一个错误对象返回，避免空指针异常
            TUser user = new TUser();
            user.setPhone("999999");
            return user;
        }else{
            //如果找到了，就返回正确对象
            return result;
        }
    }

    @Override
    public Integer missPassword(String phone, String newPassword) {
        TUser result = tUserDao.findByPhone(phone);

        if(result==null){
            throw new UserNotFoundException("用户名不存在");
        }
        if(result.getIsDelete()==1) {
            //是：用户数据已被标记为删除
            throw new UserNotFoundException("用户数据不存在");
        }
        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 将参数newPassword进行加密，得到newMd5Password
        String newMd5Password = getMd5Password(newPassword, salt);
        // 执行更新密码，获取返回值
        result.setPassword(newMd5Password);


        TUser rows = tUserDao.saveAndFlush(result);
        // 判断返回的受影响的行数是否不为1
        if (rows == null) {
            // 是：更新失败，抛出UpdateException
            throw new UpdateException("更新数据时出现未知错误");
        }

        return 1;
    }

    @Override
    public String[] sendSMS(String phone) {
        String code = randomCode();
        System.out.println("生成的验证码：" + code);
        String reStr = code; //定义返回值 。
        // 短信应用SDK AppID   // 1400开头       
        int appid =1400249822;
        // 短信应用SDK AppKey        
        String appkey = "2170f2b35bb48196b5939d607a8b3b21";
        // 短信模板ID，需要在短信应用中申请       
        int templateId =407313;
        // 签名，使用的是`签名内容`，而不是`签名ID`        
        String smsSign = "校园招聘网";
        String time = "5";
        try {
            //参数，一定要对应短信模板中的参数顺序和个数， 
            String[] params = {code,time};
            //创建ssender对象
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            //发送
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone, templateId, params, smsSign, "", "");
            // 签名参数未提供或者为空时，会使用默认签名发送短信            
            System.out.println(result.toString());
            if (result.result == 0) {
                reStr = "success";
            } else {
                reStr = "error";
            }
        } catch (HTTPException e) {
            // HTTP响应码错误           
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误          
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误            
            e.printStackTrace();
        } catch (Exception e) {
            // 网络IO错误            
            e.printStackTrace();
        }
        return new String[]{reStr,code};
    }

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    public TUser findByUsername(String username){
        return tUserDao.findByUsername(username);
    }

    /**
     * 根据phone查找
     * @param phone
     * @return
     */
    public TUser findByPhone(String phone){
        return tUserDao.findByPhone(phone);
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        // 根据参数uid查询用户数据
         TUser result = tUserDao.findById(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户数据不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }

        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete()==1) {
            // 是：用户被标记为已删除，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据已被删除");
        }

        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 将参数oldPassword进行加密，得到oldMd5Password
        String oldMd5Password = getMd5Password(oldPassword, salt);
        // 判断查询结果中的password与oldMd5Password是否不匹配
        if (!result.getPassword().equals(oldMd5Password)) {
            // 是：密码验证失败，抛出PasswordNotMatchException
            throw new PasswordNotMatchException(
                    "原密码错误");
        }

        // 将参数newPassword进行加密，得到newMd5Password
        String newMd5Password = getMd5Password(newPassword, salt);

        result.setId(uid);
        result.setPassword(newMd5Password);
        result.setModifiedUser(username);
        result.setModifiedTime(new Date());
        tUserDao.saveAndFlush(result);

    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        // 根据参数uid查询用户数据
        TUser result = tUserDao.findById(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户数据不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }

        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete()==1) {
            // 是：用户被标记为已删除，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据已被删除");
        }

        result.setAvatar(avatar);
        result.setModifiedUser(username);
        result.setModifiedTime(new Date());
        // 执行修改头像，并获取返回值
        tUserDao.saveAndFlush(result);
    }

    @Override
    public void updateInfo(Integer uid,  TUser user) {
        // 根据参数uid查询用户数据
        TUser oldUser = tUserDao.findById(uid);
        // 判断查询结果是否为null
        if (StringUtils.isEmpty(oldUser)) {
            // 是：用户数据不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }

        // 判断查询结果中的isDelete是否为1
        if (oldUser.getIsDelete()==1) {
            // 是：用户被标记为已删除，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据已被删除");
        }


        // 将参数uid封装到参数user的uid中
        // user.setId(uid);
        // 将参数username封装到参数user的modifiedUser中
        user.setModifiedUser(oldUser.getUsername());
        // 创建时间对象，封装到参数user的modifiedTime中
        user.setModifiedTime(new Date());


        //将前端传来的不为空参数(也即是要修改值)copy覆盖原始对象属性值

        UpdateUtil.copyNullProperties(user,oldUser);  //修改的字段覆盖原对象

        // 执行修改，并获取返回值
        tUserDao.saveAndFlush(oldUser);
        System.out.println("TUserServiceImpl--> updateInfo 更新成功！");

    }

    @Override
    public boolean check(String username, String phone) {
        TUser result = tUserDao.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户名不存在");
        }
        if (result.getIsDelete()==1) {
            //是：用户数据已被标记为删除
            throw new UserNotFoundException("用户数据不存在");
        }
        if (!result.getPhone().equals(phone)) {
            throw new PhoneNotMatchException("手机号不匹配");
        }
        return true;
    }

    @Override
    public boolean checkCode(String userCode, String code) {
        if(code.equals(userCode)){
            return true;
        }else{
            throw new CodeNotMatchException("验证码错误！");
        }
    }

    /**
     * 根据id查找数据
     * @param id
     * @return
     */
    @Override
    public TUser findById(Integer id) {
        TUser result = tUserDao.findById(id);
        return result;
    }

    /**
     * 根据用户id删除用户数据
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        tUserDao.deleteById(id);
        System.out.println("TUserService--> 删除成功！");
    }

    /**
     * 查找所有用户数据
     * @return
     */
    @Override
    public List<TUser> findALL() {
        List<TUser> result = tUserDao.findAll();
        return result;
    }

    /**
     * 存入用户数据
     * @param tUser
     */
    @Override
    public void saveUser(TUser tUser) {
        tUserDao.saveAndFlush(tUser);
        System.out.println("存入数据完成!");
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
    /**
     * 验证码随机生成
     * @return 生成的随机验证码
     */
    public static String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
