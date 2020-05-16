package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.TInformationResources;
import com.kmust.recruitment.service.TInformationResourcesService;
import com.kmust.recruitment.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/info")
public class TInformationResourcesController extends BaseController{
    @Autowired
    TInformationResourcesService tInformationResourcesService;

    /**
     * 发布信息
     * @param tInformationResources
     * @return
     */
    @RequestMapping("publish")
    public JsonResult<Void> publish(TInformationResources tInformationResources){
        tInformationResourcesService.save(tInformationResources);
        System.out.println("TInformationResourcesController--> publish()");
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 根据热度排序查找数据
     * @return
     */
    @RequestMapping("findAllByHeat")
    public JsonResult<List<TInformationResources>> findAllByHeat(){
        System.out.println("TInformationResourcesController--> findAllByHeat()");
        return new JsonResult<>(SUCCESS,tInformationResourcesService.findAllOrderByHeat());
    }

    /**
     * 根据发布时间查找数据-
     * @return
     */
    @RequestMapping("findAllByTime")
    public JsonResult<List<TInformationResources>> findAllByTime(){
        System.out.println("TInformationResourcesController--> findAllByHeat()");
        return new JsonResult<>(SUCCESS,tInformationResourcesService.findAllOrderByCreatedTime());
    }
}
