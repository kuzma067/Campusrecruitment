package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.TUserPublish;
import com.kmust.recruitment.service.TUserPublishService;
import com.kmust.recruitment.utils.JsonResult;
import com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo;
import com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/publish")
public class TUserPublishController extends BaseController{
    @Autowired
    private TUserPublishService tUserPublishService;

    /**
     * 查找所有数据
     * @return
     */
    @RequestMapping("findAll")
    public List<TUserPublish> findAll(){
        return tUserPublishService.findAll();
    }

    /**
     * 根据用户id来查找发布的招聘信息
     * @param uid
     * @return
     */
    @RequestMapping("findRecruitByUid")
    public JsonResult<List<TUserCollectionAndPublishRecruitVo>> findRecruitByUid(Integer uid){
        List<TUserCollectionAndPublishRecruitVo> result = tUserPublishService.findRecruitByUid(uid);
        return new JsonResult<>(SUCCESS,result);
    }

    /**
     * 根据用户id来查找发布的兼职信息
     * @param uid
     * @return
     */
    @RequestMapping("findPartTimeByUid")
    public JsonResult<List<TUserCollectionAndPublishPartTimeVo>> findPartTimeByUid(Integer uid){
        List<TUserCollectionAndPublishPartTimeVo> result = tUserPublishService.findPartTimeByUid(uid);
        return new JsonResult<>(SUCCESS,result);
    }

    /**
     * 收藏信息
     * @param TUserPublish
     * @return
     */
    @RequestMapping("publish")
    public JsonResult<Void> publish(@RequestBody TUserPublish TUserPublish){
        Integer uid = TUserPublish.getUid();
        Integer rid = TUserPublish.getRid();
        Integer tid = TUserPublish.getTid();
        if (rid != null){
            if(tUserPublishService.findByUidAndRid(uid,rid)==null){
                tUserPublishService.save(TUserPublish);
                return new JsonResult<>(SUCCESS);
            }
        }
        if (tid != null){
            if(tUserPublishService.findByUidAndTid(uid,tid)==null){
                tUserPublishService.save(TUserPublish);
                return new JsonResult<>(SUCCESS);
            }
        }
        JsonResult<Void> jsonResult = new JsonResult<>();
        jsonResult.setMessage("已发布，请勿重复发布！");
        jsonResult.setState(400);
        return jsonResult;
    }

    @RequestMapping("deleteRecruit")
    public JsonResult<Void> deleteByUidAndRid(@RequestBody TUserPublish TUserPublish){
        Integer uid = TUserPublish.getUid();
        Integer rid = TUserPublish.getRid();
        if(tUserPublishService.findByUidAndRid(uid,rid) != null){
            JsonResult<Void> jsonResult = new JsonResult<>();
            jsonResult.setState(400);
            jsonResult.setMessage("当前并未发布，删除发布失败！");
        }
        tUserPublishService.deleteByUidAndRid(TUserPublish.getUid(),TUserPublish.getRid());
        return new JsonResult<>(SUCCESS);
    }
    @RequestMapping("deletePartTime")
    public JsonResult<Void> deleteByUidAndTid(@RequestBody TUserPublish TUserPublish){
        Integer uid = TUserPublish.getUid();
        Integer tid = TUserPublish.getRid();
        if(tUserPublishService.findByUidAndTid(uid,tid) != null){
            JsonResult<Void> jsonResult = new JsonResult<>();
            jsonResult.setState(400);
            jsonResult.setMessage("当前并未发布，删除发布失败！");
        }
        tUserPublishService.deleteByUidAndTid(uid,tid);
        return new JsonResult<>(SUCCESS);
    }
}
