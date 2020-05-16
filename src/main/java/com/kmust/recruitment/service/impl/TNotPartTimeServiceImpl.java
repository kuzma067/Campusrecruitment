package com.kmust.recruitment.service.impl;

import com.kmust.recruitment.dao.TNotPartTimeDao;
import com.kmust.recruitment.entity.TNotPartTime;
import com.kmust.recruitment.service.TNotPartTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TNotPartTimeServiceImpl implements TNotPartTimeService {
    @Autowired
    private TNotPartTimeDao tNotPartTimeDao;

    @Override
    public void save(TNotPartTime tNotPartTime) {
        System.out.println("TNotPartTimeServiceImpl--> save()");
        tNotPartTimeDao.save(tNotPartTime);
    }

    @Override
    public List<TNotPartTime> findAllByTime() {
        System.out.println("TNotPartTimeServiceImpl--> findAllByTime()");
        return tNotPartTimeDao.findAllByOrderByCreatedTimeAsc();
    }
}
