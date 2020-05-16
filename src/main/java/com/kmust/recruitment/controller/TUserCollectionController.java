package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.TUserCollection;
import com.kmust.recruitment.service.TUserCollectionService;
import com.kmust.recruitment.utils.JsonResult;
import com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo;
import com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user/collection")
public class TUserCollectionController extends BaseController{
    @Autowired
    private TUserCollectionService tUserCollectionService;

    /**
     * 查找所有数据
     * @return
     */
    @RequestMapping("findAll")
    public List<TUserCollection> findAll(){
        return tUserCollectionService.findAll();
    }

    /**
     * 根据用户id来查找收藏的招聘信息
     * @param uid
     * @return
     */
    @RequestMapping("findRecruitByUid")
    public JsonResult<List<TUserCollectionAndPublishRecruitVo>> findRecruitByUid(Integer uid){
        List<TUserCollectionAndPublishRecruitVo> result = tUserCollectionService.findRecruitByUid(uid);
        return new JsonResult<>(SUCCESS,result);
    }

    /**
     * 根据用户id来查找收藏的兼职信息
     * @param uid
     * @return
     */
    @RequestMapping("findPartTimeByUid")
    public JsonResult<List<TUserCollectionAndPublishPartTimeVo>> findPartTimeByUid(Integer uid){
        List<TUserCollectionAndPublishPartTimeVo> result = tUserCollectionService.findPartTimeByUid(uid);
        return new JsonResult<>(SUCCESS,result);
    }

    /**
     * 收藏信息
     * @param tUserCollection
     * @return
     */
    @RequestMapping("collect")
    public JsonResult<Void> collect(@RequestBody TUserCollection tUserCollection){
        Integer uid = tUserCollection.getUid();
        Integer rid = tUserCollection.getRid();
        Integer tid = tUserCollection.getTid();
        System.out.println(tUserCollection);
        if (rid != null){
            if(tUserCollectionService.findByUidAndRid(uid,rid)==null){
                System.out.println("rid"+rid);
                tUserCollectionService.save(tUserCollection);
                return new JsonResult<>(SUCCESS);
             }
        }
        if (tid != null){
            if(tUserCollectionService.findByUidAndTid(uid,tid)==null){
                System.out.println("tid"+tid);
                tUserCollectionService.save(tUserCollection);
                return new JsonResult<>(SUCCESS);
            }
        }
        JsonResult<Void> jsonResult = new JsonResult<>();
        jsonResult.setMessage("已收藏，收藏失败！");
        jsonResult.setState(400);
        return jsonResult;
    }

    @RequestMapping("deleteRecruit")
    public JsonResult<Void> deleteByUidAndRid(@RequestBody TUserCollection tUserCollection){
        Integer uid = tUserCollection.getUid();
        Integer rid = tUserCollection.getRid();
        if(tUserCollectionService.findByUidAndRid(uid,rid) != null){
            JsonResult<Void> jsonResult = new JsonResult<>();
            jsonResult.setState(400);
            jsonResult.setMessage("当前并未收藏，取消收藏失败！");
        }
        tUserCollectionService.deleteByUidAndRid(tUserCollection.getUid(),tUserCollection.getRid());
        return new JsonResult<>(SUCCESS);
    }
    @RequestMapping("deletePartTime")
    public JsonResult<Void> deleteByUidAndTid(@RequestBody TUserCollection tUserCollection){
        Integer uid = tUserCollection.getUid();
        Integer tid = tUserCollection.getRid();
        if(tUserCollectionService.findByUidAndTid(uid,tid) != null){
            JsonResult<Void> jsonResult = new JsonResult<>();
            jsonResult.setState(400);
            jsonResult.setMessage("当前并未收藏，取消收藏失败！");
        }
        tUserCollectionService.deleteByUidAndTid(uid,tid);
        return new JsonResult<>(SUCCESS);
    }
}

