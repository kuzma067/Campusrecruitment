package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.TPartTime;
import com.kmust.recruitment.entity.TUserPublish;
import com.kmust.recruitment.service.TPartTimeService;
import com.kmust.recruitment.service.TUserPublishService;
import com.kmust.recruitment.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/part_time")
public class TPartTimeController extends BaseController{
    @Autowired
    TPartTimeService tPartTimeService;
    @Autowired
    TUserPublishService tUserPublishService;

    /**
     * 查找所有数据
     * @return
     */
    @RequestMapping("findAll")
    public List<TPartTime> findAll(){
        return tPartTimeService.findAll();
    }

    /**
     * 热门信息
     * @return
     */
    @RequestMapping("findByHeat")
    public List<TPartTime> findByHeat(){
        return tPartTimeService.findAllByOrderByHeatDesc(0);
    }
    /**
     * 实时信息
     * @return
     */
    @RequestMapping("findByTime")
    public List<TPartTime> findByTime(){
        return tPartTimeService.findAllByOrderByHeatDesc(0);
    }

    /**
     * 排序、筛选、分页功能接口
     * @param tPartTime
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("select_sort_page")
    public List<TPartTime> selectSortAndPage(@RequestBody TPartTime tPartTime,Integer page ,Integer size){
        return tPartTimeService.selectSortAndPage(tPartTime,page,size);
    }

    /**
     * 发布招聘信息
     * @param tPartTime
     * @param session
     * @return
     */
    @RequestMapping("publish")
    public JsonResult<Void> publish(@RequestBody TPartTime tPartTime, HttpSession session){
        Integer uid = getUidFromSession(session);
        tPartTime.setUid(uid);
        tPartTimeService.save(tPartTime);
        System.out.println("TRecruitController-->publish 招聘信息保存成功！");
        //将发布信息保存到我的发布
        Integer rid = tPartTimeService.findByUid(uid).getId();
        TUserPublish.TUserPublishBuilder builder = TUserPublish.builder();
        builder.rid(rid).uid(uid).createdTime(new Date()).modifiedUser(tPartTimeService.findByUid(uid).getCreatedUser());
        TUserPublish tUserPublish = builder.build();
        tUserPublishService.save(tUserPublish);
        System.out.println("TRecruitController-->publish 我的发布招聘信息保存成功！");
        JsonResult<Void> jsonResult = new JsonResult();
        jsonResult.setState(2000);
        jsonResult.setMessage("发布成功,等待审核！");
        return jsonResult;
    }
}