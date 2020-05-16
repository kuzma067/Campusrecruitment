package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TNotPartTime;

import java.util.List;

public interface TNotPartTimeService {
    /**
     * 保存未通过兼职信息数据
     * @param tNotPartTime
     */
    void save(TNotPartTime tNotPartTime);
    /**
     * 根据修改时间排序查找所有数据
     * @return
     */
    List<TNotPartTime> findAllByTime();
}
