package com.kmust.recruitment.service;


import com.kmust.recruitment.entity.TUserPublish;
import com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo;
import com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo;

import java.util.List;

public interface TUserPublishService {


    List<TUserPublish> findAll();

    TUserPublish findById(Integer id);

    /**
     * 根据uid和rid查找发布信息
     * @param uid
     * @param rid
     * @return
     */
    TUserPublish findByUidAndRid(Integer uid, Integer rid);

    /**
     * 根据uid和tid查找发布信息
     * @param uid
     * @param tid
     * @return
     */
    TUserPublish findByUidAndTid(Integer uid, Integer tid);

    /**
     * 根据用户id查找收藏的发布信息
     * @param uid
     * @return
     */
    List<TUserCollectionAndPublishRecruitVo> findRecruitByUid(Integer uid);

    /**
     * 根据用户id查找收藏的发布信息
     * @param uid
     * @return
     */
    List<TUserCollectionAndPublishPartTimeVo> findPartTimeByUid(Integer uid);

    /**
     * 保存用户的发布信息
     * @param tUserPublish
     */
    void save(TUserPublish tUserPublish);

    void deleteById(Integer id);

    /**
     * 根据uid，rid删除对应的招聘发布信息
     * @param uid
     * @param rid
     */
    void deleteByUidAndRid(Integer uid,Integer rid);

    /**
     * 根据uid，rid删除对应的兼职发布信息
     * @param uid
     * @param tid
     */
    void deleteByUidAndTid(Integer uid,Integer tid);

    /**
     * 根据uid删除所有发布
     * @param uid
     */
    void deleteAllByUid(Integer uid);
}
