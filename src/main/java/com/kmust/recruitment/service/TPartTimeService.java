package com.kmust.recruitment.service;


import com.kmust.recruitment.entity.TPartTime;

import java.util.List;

public interface TPartTimeService {

    /**
     * 热门信息
     * @return
     */
    List<TPartTime> findAllByOrderByHeatDesc(Integer state);

    /**
     * 实时信息
     * @return
     */
    List<TPartTime> findAllByOrderByCreatedTimeDesc(Integer state);

    TPartTime findById(Integer id);
    /**
     * 保存数据
     * @param tPartTime
     */
    void save(TPartTime tPartTime);

    /**
     * 查询所有兼职信息数据
     * @return
     */
    List<TPartTime> findAll();

    /**
     * 根据uid查找兼职信息
     * @param uid
     * @return
     */
    TPartTime findByUid(Integer uid);

    /**
     * 条件，分页查询和排序
     * @param tPartTime
     * @param page
     * @param size
     * @return
     */
    List<TPartTime> selectSortAndPage(TPartTime tPartTime,Integer page, Integer size);

    /**
     * 根据id删除数据
     * @param rid
     */
    void deleteById(Integer rid);

}
