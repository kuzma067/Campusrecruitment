package com.kmust.recruitment.controller;

import com.kmust.recruitment.controller.ex.*;
import com.kmust.recruitment.dao.TUserDao;
import com.kmust.recruitment.entity.TUser;
import com.kmust.recruitment.payload.ChangePasswordPayload;
import com.kmust.recruitment.payload.LoginPayload;
import com.kmust.recruitment.payload.MissPasswordPayload;
import com.kmust.recruitment.payload.PhoneLoginPayload;
import com.kmust.recruitment.service.TUserService;
import com.kmust.recruitment.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class TUserController extends BaseController{
    @Autowired
    TUserService tUserService;

    @Autowired
    TUserDao tUserDao;
    /**
     * 随机生成的验证码，从ver方法中获得，用于在reg方法中比较
     */
    private String code;

    @RequestMapping("/index")
    public String index(Model model) {

        return "请登录！";
    }

    /**
     * 注销功能
     * @param session
     * @param sessionStatus
     * @return
     */
    @RequestMapping("/logout")
    public JsonResult<String> logout(HttpSession session, SessionStatus sessionStatus){
        log.info(session.getAttribute("username")+"已注销！");
        session.invalidate();
        sessionStatus.setComplete();
        return new JsonResult<>(400,"已注销，请登录！");
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("reg")
    public JsonResult<Void> reg(@RequestBody TUser user) {
        System.out.println("controller--> reg方法的payload:" + user);
        /*TUser.TUserBuilder builder = TUser.builder();
        builder.username(payload.getUsername()).password(payload.getPassword()).phone(payload.getPhone());
        TUser user = builder.build();*/
        if (tUserService.findByUsername(user.getUsername())!=null){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(500);
            jsonResult.setMessage("用户名已存在！");
            System.out.println("用户名已存在！注册失败！");
            return jsonResult;
        }

        //进行验证码的验证
        if(user.getCode().equals(code)){
            tUserService.reg(user);
            System.out.println("注册成功！");
        }else{
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("验证码不正确");
            System.out.println("注册失败！");
            return jsonResult;
        }
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 注册发送验证码
     * @param phone
     * @return
     */
    @RequestMapping("ver")
    @ResponseBody
    public JsonResult<Void> ver(String phone){
//        System.out.println("ver方法的User:" + user);

//		String phoneNumber = user.getPhone();
        String phoneNumber = phone;
        System.out.println("ver方法的phoneNumber：" + phoneNumber);
        if(phoneNumber != null) {
            //判断该号码是否已经被使用
            if (phoneNumber.equals(tUserService.getByPhone(phoneNumber).getPhone())) {
                //已被使用，返回错误信息
                //创建Json结果，绑定错误信息
                JsonResult<Void> jsonResult = new JsonResult();
                jsonResult.setState(400);
                jsonResult.setMessage("号码已被使用");
                return jsonResult;
            } else {
                System.out.println("号码可以使用");
                //未使用，进行验证操作
                String result[] = tUserService.sendSMS(phoneNumber);

                //将验证码保存下来
                code = result[1];
                System.out.println("返回的验证码:" + code);

                if (result[0].equals("success")) {
                    JsonResult<Void> jsonResult = new JsonResult();
                    jsonResult.setState(2000);
                    jsonResult.setMessage("号码可以使用！");
                    return jsonResult;
                } else {
                    JsonResult<Void> jsonResult = new JsonResult();
                    jsonResult.setMessage("服务器繁忙，验证码发送失败");
                    return jsonResult;
                }
            }
        }else {
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("号码为空");
            return jsonResult;
        }
    }

    /**
     * 登录发送验证码
     * @param phone 用户手机号
     * @return
     */
    @RequestMapping("login_code")
    @ResponseBody
    public JsonResult<Void> loginCode(String phone){

        String phoneNumber = phone;
        System.out.println("loginCode方法的phoneNumber:" + phoneNumber);
        if(phoneNumber != null) {
             //判断该号码是否存在
            if (phoneNumber.equals(tUserService.getByPhone(phoneNumber).getPhone())) {
                System.out.println("号码可以使用");
                //已注册，进行验证操作
                String result[] = tUserService.sendSMS(phoneNumber);

                //将验证码保存下来
                code = result[1];
                System.out.println("login_code返回的验证码:" + code);
                TUser user = tUserService.findByPhone(phone);
                user.setCode(code);
                tUserService.updateInfo(user.getId(),user);

                if (result[0].equals("success")) {
                    JsonResult<Void> jsonResult = new JsonResult();
                    jsonResult.setState(2000);
                    jsonResult.setMessage("发送成功！");
                    return jsonResult;
                } else {
                    JsonResult<Void> jsonResult = new JsonResult();
                    jsonResult.setMessage("服务器繁忙，验证码发送失败");
                    return jsonResult;
                }

            } else {
                //创建Json结果，绑定错误信息
                JsonResult<Void> jsonResult = new JsonResult();
                jsonResult.setState(400);
                jsonResult.setMessage("号码未注册");
                return jsonResult;
            }
        }else {
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("号码为空");
            return jsonResult;
        }
    }
    /**
     * 手机号登录
     * @param session
     * @return
     */
    @RequestMapping("phone_login")
    public JsonResult<TUser> phoneLogin(@RequestBody PhoneLoginPayload payload, HttpSession session) {
        String phone = payload.getPhone();
        String phoneCode = payload.getCode();
        System.out.println("控制器phoneLogin方法的phone:" + phone + " code：" + phoneCode);
        TUser tUser = tUserService.getByPhone(phone);

        //进行验证码的验证
        if(tUser.getCode().equals(phoneCode)){
            System.out.println("controller phoneLogin验证码正确");
            TUser data = tUserService.phoneLogin(phone);
            session.setAttribute("uid", data.getId());
            session.setAttribute("username", data.getUsername());
            session.setAttribute("role",data.getIdentity());
            log.info(session.getAttribute("username")+"已登录！");
            return new JsonResult<>(SUCCESS , data);
        }else{
            JsonResult<TUser> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("验证码不正确");
            System.out.println("验证码不正确");
            return jsonResult;
        }


    }

    /**
     * 账号密码登录
     * @param session
     * @return
     */

    @RequestMapping("login")
    public JsonResult<TUser> login(@RequestBody LoginPayload payload, HttpSession session) {
        String username = payload.getUsername();
        String password = payload.getPassword();
        System.out.println("控制器login方法的username:" + username + " password:" + password);
        TUser data = tUserService.login(username, password);
        session.setAttribute("uid", data.getId());
        session.setAttribute("username", data.getUsername());
        session.setAttribute("role",data.getIdentity());
        log.info(session.getAttribute("username")+"已登录！");
        return new JsonResult<>(SUCCESS , data);
    }



    /**
     * 修改密码
     * @return
     */
    @RequestMapping(path="change_password", method=RequestMethod.POST)
    public JsonResult<Void> changePassword(@RequestBody ChangePasswordPayload passwordPayload) {
        String oldPassword = passwordPayload.getOldPassword();
        String newPassword = passwordPayload.getNewPassword();
        String username = passwordPayload.getUsername();
        System.out.println("changePassword方法的username：" + username + " oldpassword: " + oldPassword + " newpassword " + newPassword);

        TUser user = tUserService.findByUsername(passwordPayload.getUsername());
        if(user==null){
            JsonResult<Void> jsonResult = new JsonResult<>();
            jsonResult.setState(400);
            jsonResult.setMessage("用户不存在!");
            return jsonResult;
        }
        tUserService.changePassword(user.getId(),username, oldPassword, newPassword);
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 修改个人资料
     * @param user
     * @return
     */
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(@RequestBody TUser user) {

        System.out.println(user);
        Integer uid = user.getId();
        tUserService.updateInfo(uid,user);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("findbyid")
    public JsonResult<TUser> findById(Integer uid) {
        TUser data = tUserService.findById(uid);
        return new JsonResult<>(SUCCESS, data);
    }

    @PostMapping("check")
    public JsonResult<Void> check(String username,String phone) {
        System.out.println("check方法的username：" + username + " phone: " + phone);
        boolean flag = tUserService.check(username,phone);
        if(flag==true){
            String result[] = tUserService.sendSMS(phone);

            //将验证码保存下来
            code = result[1];
            System.out.println("返回的验证码:" + code);

            if (result[0].equals("success")) {
                return new JsonResult<>(SUCCESS);
            } else {
                JsonResult<Void> jsonResult = new JsonResult();
                jsonResult.setMessage("服务器繁忙，验证码发送失败");
                return jsonResult;
            }
        }
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 忘记密码
     * @return
     */
    @RequestMapping(path="miss_password", method=RequestMethod.POST)
    public JsonResult<Void> missPassword(@RequestBody MissPasswordPayload passwordPayload) {
        String phone = passwordPayload.getPhone();
        String newPassword = passwordPayload.getPassword();
        String userCode = passwordPayload.getCode();
        System.out.println("missPassword方法的phone：" + phone + " newPassword: " + newPassword + " userCode" + userCode);
        if (tUserService.getByPhone(phone)==null){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("账号不存在！");
            return jsonResult;

        }
        if(tUserService.getByPhone(phone).getCode().equals(userCode)){
            if(tUserService.missPassword(phone, newPassword) != null){
                return new JsonResult<>(SUCCESS);
            }else{
                JsonResult<Void> jsonResult = new JsonResult();
                jsonResult.setMessage("修改密码失败！");
                return jsonResult;
            }
        }else{
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setMessage("验证码错误");
            return jsonResult;
        }
    }

    /**
     * 允许上传的文件的最大大小
     */
    private static final long FILE_MAX_SIZE = 1000 * 1024;
    /**
     * 允许上传的文件的类型
     */
    private static final List<String> FILE_TYPES = new ArrayList<>();

    static {
        FILE_TYPES.add("image/jpeg");
        FILE_TYPES.add("image/png");
        FILE_TYPES.add("image/jpg");
    }

    // 基于SpringMVC的上传
    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, Integer uid, HttpSession session) {
        // 检查：上传的文件是否为空
        if (file.isEmpty()) {
            // 是：没有选择文件，或选择的文件是0字节的
            throw new FileEmptyException("没有选择文件，或选择的文件是0字节的");
        }

        // 检查：上传的文件大小
        if (file.getSize() > FILE_MAX_SIZE) {
            throw new FileSizeException("不允许上传超过" + (FILE_MAX_SIZE / 1024) + "KB的文件");
        }

        // 检查：上传的文件类型
        if (!FILE_TYPES.contains(file.getContentType())) {
            throw new FileTypeException("仅支持上传" + FILE_TYPES + "类型的文件");
        }

        // 上传到的文件夹
        String dirPath = session.getServletContext().getRealPath("upload_avatar");
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 扩展名
        String suffix = "";
        int index = originalFilename.lastIndexOf(".");
        if (index > 0) {
            suffix = originalFilename.substring(index);
        }
        // 文件全名
        String filename = UUID.randomUUID().toString() + suffix;

        // 保存到的文件
        File dest = new File(dir, filename);
        // 保存用户上传的头像
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new FileUploadStateException( "文件可能已经被移动或删除，不可访问到该文件");
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadIOException( "出现读写错误");
        }

        // 头像路径
        String avatar = "/upload_avatar/" + filename;
        // 从Session中获取uid和username
        String username = tUserService.findById(uid).getUsername();
        // 执行更新数据库
        tUserService.changeAvatar(uid, username, avatar);
        // 返回结果
        return new JsonResult<>(SUCCESS, avatar);
    }




    /**
     * 查找所有用户数据
     * @return
     */
    @RequestMapping("findAll")
    public List<TUser> findAll(){
        System.out.println("控制器findAll方法");
        List<TUser> result = tUserService.findALL();
        return result;
    }



}
