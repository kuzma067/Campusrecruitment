package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TUserCollection;
import com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo;
import com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo;

import java.util.List;

public interface TUserCollectionService {

    List<TUserCollection> findAll();

    TUserCollection findById(Integer id);

    /**
     * 根据uid和rid查找收藏信息
     * @param uid
     * @param rid
     * @return
     */
    TUserCollection findByUidAndRid(Integer uid, Integer rid);

    /**
     * 根据uid和tid查找收藏信息
     * @param uid
     * @param tid
     * @return
     */
    TUserCollection findByUidAndTid(Integer uid, Integer tid);

    /**
     * 根据用户id查找收藏的招聘信息
     * @param uid
     * @return
     */
    List<TUserCollectionAndPublishRecruitVo> findRecruitByUid(Integer uid);

    /**
     * 根据用户id查找收藏的兼职信息
     * @param uid
     * @return
     */
    List<TUserCollectionAndPublishPartTimeVo> findPartTimeByUid(Integer uid);

    /**
     * 保存用户的收藏信息
     * @param tUserCollection
     */
    void save(TUserCollection tUserCollection);

    void deleteById(Integer id);

    /**
     * 根据uid，rid删除对应的招聘收藏信息
     * @param uid
     * @param rid
     */
    void deleteByUidAndRid(Integer uid,Integer rid);

    /**
     * 根据uid，rid删除对应的兼职收藏信息
     * @param uid
     * @param tid
     */
    void deleteByUidAndTid(Integer uid,Integer tid);

    void deleteAllByUid(Integer uid);




}
