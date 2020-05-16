package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TRecruit;

import java.util.List;


public interface TRecruitService {

    TRecruit findById(Integer id);
    /**
     * 热门信息
     * @return
     */
    List<TRecruit> findAllByOrderByHeatDesc(Integer state);

    /**
     * 实时信息
     * @return
     */
    List<TRecruit> findAllByOrderByCreatedTimeDesc(Integer state);
    /**
     * 保存数据
     * @param tRecruit
     */
    void save(TRecruit tRecruit);

    /**
     * 根据uid查找数据
     * @param uid
     * @return
     */
    TRecruit findByUid(Integer uid);

    /**
     * 查询所有招聘信息数据
     * @return
     */
    List<TRecruit> findAll();

    /**
     * 条件，分页查询和排序
     * @param tRecruit
     * @param page
     * @param size
     * @return
     */
    List<TRecruit> selectSortAndPage(TRecruit tRecruit,Integer page, Integer size);

    /**
     * 根据id删除数据
     * @param rid
     */
    void deleteById(Integer rid);

}
