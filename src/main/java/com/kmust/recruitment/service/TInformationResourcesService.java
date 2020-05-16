package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TInformationResources;

import java.util.List;

public interface TInformationResourcesService {
    /**
     * 保存数据
     * @param tInformationResources
     */
    void save(TInformationResources tInformationResources);

    /**
     * 查找所有数据根据热度排序
     * @return
     */
    List<TInformationResources> findAllOrderByHeat();

    /**
     * 查找所有数据根据创建时间排序
     * @return
     */
    List<TInformationResources> findAllOrderByCreatedTime();

    /**
     * 根据id查找数据
     * @param id
     * @return
     */
    TInformationResources findById(Integer id);

    /**
     * 根据id删除数据
     * @param id
     */
    void deleteById(Integer id);
}
