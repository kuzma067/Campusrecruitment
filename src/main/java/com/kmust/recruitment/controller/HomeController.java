package com.kmust.recruitment.controller;

import com.kmust.recruitment.constant.Role;
import com.kmust.recruitment.entity.TRecruit;
import com.kmust.recruitment.service.TRecruitService;
import com.kmust.recruitment.service.TUserWorkService;
import com.kmust.recruitment.utils.RoleCheck;
import com.kmust.recruitment.vo.ExcelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/home")

public class HomeController extends BaseController{
    @Autowired
    TRecruitService tRecruitService;
    @Autowired
    TUserWorkService tUserWorkService;

    /**
     * 人才信息
     * @return
     */
    //@RoleCheck(Role.ENTERPRISE)
    @RequestMapping("findStudent")
    public List<ExcelVo> findStudent(){
        List<ExcelVo> result = tUserWorkService.findExcelVo();
        log.info("HomeController--> findStudent()方法执行！");
        return result;
    }

    /**
     * 热门信息
     * @return
     */
    @RoleCheck(Role.ADMIN)
    @RequestMapping("findByHeat")
    public List<TRecruit> findByHeat(){
        log.info("HomeController-->findByHeat()方法执行！");
        return tRecruitService.findAllByOrderByHeatDesc(1);
    }
    /**
     * 实时信息
     * @return
     */
    @RequestMapping("findByTime")
    public List<TRecruit> findByTime(){
        log.info("HomeController-->findByTime()方法执行！");
        return tRecruitService.findAllByOrderByHeatDesc(1);
    }
}
