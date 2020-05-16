package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.TRecruit;
import com.kmust.recruitment.service.TRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController extends BaseController{
    @Autowired
    TRecruitService tRecruitService;

    /**
     * 热门信息
     * @return
     */
    @RequestMapping("findByHeat")
    public List<TRecruit> findByHeat(){
        return tRecruitService.findAllByOrderByHeatDesc(0);
    }
    /**
     * 实时信息
     * @return
     */
    @RequestMapping("findByTime")
    public List<TRecruit> findByTime(){
        return tRecruitService.findAllByOrderByHeatDesc(0);
    }
}
