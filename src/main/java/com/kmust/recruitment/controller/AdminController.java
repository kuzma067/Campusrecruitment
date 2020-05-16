package com.kmust.recruitment.controller;

import com.kmust.recruitment.dao.TUserWorkDao;
import com.kmust.recruitment.entity.*;
import com.kmust.recruitment.service.TNotPartTimeService;
import com.kmust.recruitment.service.TNotRecruitService;
import com.kmust.recruitment.service.TPartTimeService;
import com.kmust.recruitment.service.TRecruitService;
import com.kmust.recruitment.utils.ExportExcel;
import com.kmust.recruitment.utils.JsonResult;
import com.kmust.recruitment.utils.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private TUserWorkDao tUserWorkDao;
    @Autowired
    private TNotRecruitService tNotRecruitService;
    @Autowired
    private TNotPartTimeService tNotPartTimeService;
    @Autowired
    private TPartTimeService tPartTimeService;
    @Autowired
    private TRecruitService tRecruitService;

    /**
     * 用于导出excel的查询结果
     * @return
     */
    @RequestMapping("/export")
    public void export( HttpServletResponse response) {
        //如果出现中文乱码请添加下面这句
        //queryJson = URLDecoder.decode(queryJson,"utf-8");
        //需要导入alibaba的fastjson包
        //TUserWork user = com.alibaba.fastjson.JSON.parseObject(queryJson, TUserWork.class);
        List<TUserWork> userlList = tUserWorkDao.findAll();
        ExportExcel<TUserWork> ee= new ExportExcel<TUserWork>();
        String[] headers = { "序号", "用户id", "期望的工作", "期望的公司","期望的薪资","期望的工作时间","期望的职位","是否签署三方协议","签署公司"
                ,"未签署原因","目前就职公司","创建人","创建时间","修改人","修改时间"};
        String fileName = "用户工作信息表";
        ee.exportExcel(headers,userlList,fileName,response);
        System.out.println("AdminController--> export() 导出成功！");
    }

    @RequestMapping("findNotRecruitByTime")
    public List<TNotRecruit> findNotRecruitByTime(){
        return tNotRecruitService.findAllByTime();
    }

    @RequestMapping("findNotPartTimeByTime")
    public List<TNotPartTime> findNotPartTimeByTime(){
        return tNotPartTimeService.findAllByTime();
    }

    @RequestMapping("check_TPartTime")
    public List<TPartTime> checkTPartTime(){
        return tPartTimeService.findAll();
    }

    @RequestMapping("check_TRecruit")
    public List<TRecruit> checkTRecruit(){
        return tRecruitService.findAll();
    }

    @RequestMapping("wait_TPartTime")
    public List<TPartTime> waitTPartTime(){
        return tPartTimeService.findAll();
    }

    @RequestMapping("wait_TRecruit")
    public List<TRecruit> waitTRecruit(){
        return tRecruitService.findAllByOrderByCreatedTimeDesc(1);
    }

    @RequestMapping("pass_TRecruit")
    public JsonResult<Void> passTRecruit(TRecruit tRecruit){
        tRecruit.setState(1);
        TRecruit old = tRecruitService.findByUid(tRecruit.getId());
        UpdateUtil.copyNullProperties(tRecruit,old);

        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("pass_TPartTime")
    public JsonResult<Void> passTRecruit(TPartTime tPartTime){
        tPartTime.setState(1);
        TRecruit old = tRecruitService.findByUid(tPartTime.getId());
        UpdateUtil.copyNullProperties(tPartTime,old);

        return new JsonResult<>(SUCCESS);
    }

}
