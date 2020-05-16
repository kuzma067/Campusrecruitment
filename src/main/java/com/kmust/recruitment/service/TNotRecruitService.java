package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TNotRecruit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TNotRecruitService {
    /**
     * 保存未通过招聘信息数据
     * @param tNotRecruit
     */
    void save(TNotRecruit tNotRecruit);

    /**
     * 根据修改时间排序查找所有数据
     * @return
     */
    List<TNotRecruit> findAllByTime();




}
