package com.kmust.recruitment.service.impl;

import com.kmust.recruitment.dao.TNotRecruitDao;
import com.kmust.recruitment.entity.TNotRecruit;
import com.kmust.recruitment.service.TNotRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TNotRecruitServiceImpl implements TNotRecruitService {

    @Autowired
    private TNotRecruitDao tNotRecruitDao;

    /**
     * 保存未通过招聘信息数据
     * @param tNotRecruit
     */
    @Override
    public void save(TNotRecruit tNotRecruit) {
        System.out.println("TNotRecruitServiceImpl--> save()");
        tNotRecruitDao.save(tNotRecruit);
    }

    /**
     * 根据修改时间排序查找所有数据
     * @return
     */
    @Override
    public List<TNotRecruit> findAllByTime() {
        System.out.println("TNotRecruitServiceImpl--> findAllByTime()");
        return tNotRecruitDao.findAllByOrderByCreatedTimeAsc();
    }
}
