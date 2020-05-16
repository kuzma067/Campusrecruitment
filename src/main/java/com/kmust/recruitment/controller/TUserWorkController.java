package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.TUserWork;
import com.kmust.recruitment.service.TUserService;
import com.kmust.recruitment.service.TUserWorkService;
import com.kmust.recruitment.utils.JsonResult;
import com.kmust.recruitment.utils.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user/work")
public class TUserWorkController extends BaseController {
    @Autowired
    TUserWorkService tUserWorkService;
    @Autowired
    TUserService tUserService;

    /**
     * 保存或更新操作
     * @param payload
     * @return
     */
    @RequestMapping("save")
    public JsonResult<Void> saveOrUpdate(@RequestBody TUserWork payload){
        TUserWork oldTUserWork = tUserWorkService.findByUid(payload.getUid());
        if(oldTUserWork==null){//新增
            payload.setCreatedTime(new Date());
            payload.setCreatedUser(tUserService.findById(payload.getUid()).getUsername());
            TUserWork newTUserWork = new TUserWork();
            UpdateUtil.copyNullProperties(payload,newTUserWork);
            tUserWorkService.save(newTUserWork);
        }else {//更新
            payload.setModifiedTime(new Date());
            payload.setModifiedUser(tUserService.findById(payload.getUid()).getUsername());
            //将不为空的数据更新到老实体类中
            UpdateUtil.copyNullProperties(payload,oldTUserWork);
            tUserWorkService.save(oldTUserWork);
        }

        return new JsonResult<>(SUCCESS);
    }

    /**
     * 根据uid查询数据
     * @param uid
     * @return
     */
    @RequestMapping("findByUid")
    public JsonResult<TUserWork> findByUid(Integer uid){
        TUserWork result = tUserWorkService.findByUid(uid);
        return new JsonResult<>(SUCCESS,result);
    }
}
