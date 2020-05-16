package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TUserWork;

import java.util.List;

public interface TUserWorkService {
    /**
     * 保存数据
     * @param tUserWork
     */
    void save(TUserWork tUserWork);

    /**
     * 根据id查找数据
     * @param id
     * @return
     */
    TUserWork findById(Integer id);

    /**
     * 根据uid查找数据
     * @param uid
     * @return
     */
    TUserWork findByUid(Integer uid);

    List<TUserWork> findAll();

    /**
     * 根据id删除数据
     * @param id
     */
    void deleteById(Integer id);
}
