package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.TRecruit;
import com.kmust.recruitment.entity.TUserPublish;
import com.kmust.recruitment.service.TRecruitService;
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
@RequestMapping("/recruit")
public class TRecruitController extends BaseController{
    @Autowired
    TRecruitService tRecruitService;
    @Autowired
    TUserPublishService tUserPublishService;

    /**
     * 查找所有数据
     * @return
     */
    @RequestMapping("findAll")
    public List<TRecruit> findAll(){
        return tRecruitService.findAll();
    }

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

    /**
     * 排序、筛选、分页功能接口
     * @param tRecruit

     * @return
     */
    @RequestMapping("select_sort_page")
    public List<TRecruit> selectSortAndPage(@RequestBody TRecruit tRecruit){
        Integer page =100;
        Integer size =10;
        return tRecruitService.selectSortAndPage(tRecruit,page,size);
    }

    /**
     * 发布招聘信息
     * @param tRecruit
     * @param session
     * @return
     */
    @RequestMapping("publish")
    public JsonResult<Void> publish(@RequestBody TRecruit tRecruit, HttpSession session){
            Integer uid = getUidFromSession(session);
            tRecruit.setUid(uid);
            tRecruitService.save(tRecruit);
            System.out.println("TRecruitController-->publish 招聘信息保存成功！");
            //将发布信息保存到我的发布
            Integer rid = tRecruitService.findByUid(uid).getId();
            TUserPublish.TUserPublishBuilder builder = TUserPublish.builder();
            builder.rid(rid).uid(uid).createdTime(new Date()).modifiedUser(tRecruitService.findByUid(uid).getCreatedUser());
            TUserPublish tUserPublish = builder.build();
            tUserPublishService.save(tUserPublish);
            System.out.println("TRecruitController-->publish 我的发布招聘信息保存成功！");
            JsonResult<Void> jsonResult = new JsonResult();
            jsonResult.setState(2000);
            jsonResult.setMessage("发布成功,等待审核！");
            return jsonResult;
    }
}
