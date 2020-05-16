package com.kmust.recruitment.service.impl;

import com.kmust.recruitment.dao.TInformationResourcesDao;
import com.kmust.recruitment.entity.TInformationResources;
import com.kmust.recruitment.service.TInformationResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TInformationResourcesServiceImpl implements TInformationResourcesService {
    @Autowired
    TInformationResourcesDao tInformationResourcesDao;

    /**
     * 保存数据
     * @param tInformationResources
     */
    @Override
    public void save(TInformationResources tInformationResources) {
        tInformationResourcesDao.saveAndFlush(tInformationResources);
        System.out.println("TInformationResourcesServiceImpl--> save()");
    }

    /**
     * 查找所有数据根据热度排序
     * @return
     */
    @Override
    public List<TInformationResources> findAllOrderByHeat() {
        System.out.println("TInformationResourcesServiceImpl--> findAllOrderByHeat()");
        Sort sort = Sort.by(Sort.Direction.DESC,"heat");
        return tInformationResourcesDao.findAll(sort);

    }

    /**
     * 查找所有数据根据创建时间排序
     * @return
     */
    @Override
    public List<TInformationResources> findAllOrderByCreatedTime() {
        System.out.println("TInformationResourcesServiceImpl--> findAllOrderByCreatedTime()");
        Sort sort = Sort.by(Sort.Direction.DESC,"createdTime");
        return tInformationResourcesDao.findAll(sort);
    }

    /**
     * 根据id查找数据
     * @param id
     * @return
     */
    @Override
    public TInformationResources findById(Integer id) {
        System.out.println("TInformationResourcesServiceImpl--> findById(Integer id)");
        return tInformationResourcesDao.findById(id);
    }

    /**
     * 根据id删除数据
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        tInformationResourcesDao.deleteById(id);
    }
}
