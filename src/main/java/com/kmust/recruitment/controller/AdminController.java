package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.*;
import com.kmust.recruitment.service.*;
import com.kmust.recruitment.utils.ExportExcel;
import com.kmust.recruitment.utils.JsonResult;
import com.kmust.recruitment.constant.Role;
import com.kmust.recruitment.utils.UpdateUtil;
import com.kmust.recruitment.vo.ExcelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private TUserWorkService tUserWorkService;
    @Autowired
    private TNotRecruitService tNotRecruitService;
    @Autowired
    private TNotPartTimeService tNotPartTimeService;
    @Autowired
    private TPartTimeService tPartTimeService;
    @Autowired
    private TRecruitService tRecruitService;
    @Autowired
    private TUserService tUserService;

    /**
     * 用于导出excel的查询结果
     * @return
     */
    @RequestMapping("/export")
    public JsonResult<Void> export(HttpServletResponse response, HttpSession session) {
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        //如果出现中文乱码请添加下面这句
        //queryJson = URLDecoder.decode(queryJson,"utf-8");
        //需要导入alibaba的fastjson包
        //TUserWork user = com.alibaba.fastjson.JSON.parseObject(queryJson, TUserWork.class);
        List<ExcelVo> userlList = tUserWorkService.findExcelVo();
        ExportExcel<ExcelVo> ee= new ExportExcel<ExcelVo>();
        String[] headers = { "序号", "系","专业","班级","学号", "真实姓名","用户名","是否签署三方协议","签署公司",
                "未签署原因","目前就职公司","期望的公司","期望的工作","期望的工作时间",
                "期望的职位","期望的薪资",
                "创建人","创建时间"};
        String fileName = "用户工作信息表";
        ee.exportExcel(headers,userlList,fileName,response);
        log.info("AdminController--> export() 导出成功！");
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("findNotRecruitByTime")
    public JsonResult<List<TNotRecruit>> findNotRecruitByTime(HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<List<TNotRecruit>> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<List<TNotRecruit>> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        List<TNotRecruit> result = tNotRecruitService.findAllByTime();
        return new JsonResult<>(SUCCESS,result);
    }

    @RequestMapping("findNotPartTimeByTime")
    public JsonResult<List<TNotPartTime>> findNotPartTimeByTime(HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<List<TNotPartTime>> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<List<TNotPartTime>> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        List<TNotPartTime> result = tNotPartTimeService.findAllByTime();
        return new JsonResult<>(SUCCESS,result);
    }

    @RequestMapping("saveNotRecruit")
    public JsonResult<Void> saveNotRecruit(TNotRecruit tNotRecruit,HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        tNotRecruitService.save(tNotRecruit);
        return new JsonResult<>();
    }

    @RequestMapping("saveNotPartTime")
    public JsonResult<Void> saveNotPartTime(TNotPartTime tNotPartTime,HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        tNotPartTimeService.findAllByTime();
        return new JsonResult<>();
    }

    @RequestMapping("check_TPartTime")
    public JsonResult<List<TPartTime>> checkTPartTime(HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<List<TPartTime>> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<List<TPartTime>> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        List<TPartTime> result = tPartTimeService.findAllByOrderByCreatedTimeDesc(1);
        return new JsonResult<>(SUCCESS,result);
    }

    @RequestMapping("check_TRecruit")
    public JsonResult<List<TRecruit>> checkTRecruit(HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<List<TRecruit>> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<List<TRecruit>> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        List<TRecruit> result = tRecruitService.findAllByOrderByCreatedTimeDesc(1);
        return new JsonResult<>(SUCCESS,result);
    }

    @RequestMapping("wait_TPartTime")
    public JsonResult<List<TPartTime>> waitTPartTime(HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<List<TPartTime>> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<List<TPartTime>> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        List<TPartTime> result = tPartTimeService.findAllByOrderByCreatedTimeDesc(0);
        return new JsonResult<>(SUCCESS,result);
    }

    @RequestMapping("wait_TRecruit")
    public JsonResult<List<TRecruit>> waitTRecruit(HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<List<TRecruit>> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<List<TRecruit>> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        List<TRecruit> result = tRecruitService.findAllByOrderByCreatedTimeDesc(0);
        return new JsonResult<>(SUCCESS,result);
    }

    @RequestMapping("pass_TRecruit")
    public JsonResult<Void> passTRecruit(TRecruit tRecruit,HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        tRecruit.setState(1);
        TRecruit old = tRecruitService.findByUid(tRecruit.getId());
        UpdateUtil.copyNullProperties(tRecruit,old);

        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("pass_TPartTime")
    public JsonResult<Void> passTRecruit(TPartTime tPartTime,HttpSession session){
        Integer uid = getUidFromSession(session);
        if(uid==null){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(400);
            jsonResult.setMessage("未获取到用户id,请先登录！");
            return jsonResult;
        }
        if(!Role.ADMIN.equals(tUserService.findById(uid).getIdentity())){
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(401);
            jsonResult.setMessage("该用户无权限进行此操作！");
            return jsonResult;
        }
        tPartTime.setState(1);
        TRecruit old = tRecruitService.findByUid(tPartTime.getId());
        UpdateUtil.copyNullProperties(tPartTime,old);

        return new JsonResult<>(SUCCESS);
    }

}
