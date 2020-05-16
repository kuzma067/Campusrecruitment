package com.kmust.recruitment.service.impl;


import com.kmust.recruitment.dao.TUserPublishDao;
import com.kmust.recruitment.entity.TUserPublish;
import com.kmust.recruitment.service.TUserPublishService;
import com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo;
import com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TUserPublishServiceImpl implements TUserPublishService {

    @Autowired
    private TUserPublishDao tUserPublishDao;


    @Override
    public List<TUserPublish> findAll() {
        return tUserPublishDao.findAll();
    }

    @Override
    public TUserPublish findById(Integer id) {
        return tUserPublishDao.findById(id);
    }

    /**
     * 根据uid和rid查找发布信息
     * @param uid
     * @param rid
     * @return
     */
    @Override
    public TUserPublish findByUidAndRid(Integer uid, Integer rid) {
        return tUserPublishDao.findByUidAndRid(uid,rid);
    }
    /**
     * 根据uid和rid查找发布信息
     * @param uid
     * @param tid
     * @return
     */
    @Override
    public TUserPublish findByUidAndTid(Integer uid, Integer tid) {
        return tUserPublishDao.findByUidAndTid(uid,tid);
    }

    /**
     * 根据用户id查找发布的招聘信息
     * @param uid
     * @return
     */
    @Override
    public List<TUserCollectionAndPublishRecruitVo> findRecruitByUid(Integer uid) {
        List<TUserCollectionAndPublishRecruitVo> result = tUserPublishDao.findRecruitByUid(uid);
        System.out.println("TUserCollectionServiceImpl--> "+result);
        return result;
    }
    /**
     * 根据用户id查找发布的兼职信息
     * @param uid
     * @return
     */
    @Override
    public List<TUserCollectionAndPublishPartTimeVo> findPartTimeByUid(Integer uid) {
        List<TUserCollectionAndPublishPartTimeVo> result = tUserPublishDao.findPartTimeByUid(uid);
        System.out.println("TUserCollectionServiceImpl--> "+result);
        return result;
    }
    /**
     * 保存用户的发布信息
     * @param tUserPublish
     */
    @Override
    public void save(TUserPublish tUserPublish) {
        tUserPublishDao.save(tUserPublish);
        System.out.println("TUserCollectionServiceImpl--> 存入数据成功！");
    }

    @Override
    public void deleteById(Integer id) {
        tUserPublishDao.deleteById(id);
        System.out.println("TUserCollectionServiceImpl--> 删除数据成功！");
    }
    /**
     * 根据uid，rid删除对应的招聘发布信息
     * @param uid
     * @param rid
     */
    @Override
    public void deleteByUidAndRid(Integer uid, Integer rid) {
        tUserPublishDao.deleteByUidAndRid(uid,rid);
        System.out.println("TUserCollectionServiceImpl--> 删除数据成功！");

    }
    /**
     * 根据uid，rid删除对应的兼职发布信息
     * @param uid
     * @param tid
     */
    @Override
    public void deleteByUidAndTid(Integer uid, Integer tid) {
        tUserPublishDao.deleteByUidAndTid(uid,tid);
        System.out.println("TUserCollectionServiceImpl--> 删除数据成功！");
    }

    @Override
    public void deleteAllByUid(Integer uid) {
        tUserPublishDao.deleteAllByUid(uid);
        System.out.println("TUserCollectionServiceImpl--> 删除数据成功！");
    }

}
