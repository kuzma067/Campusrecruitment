package com.kmust.recruitment.service.impl;

import com.kmust.recruitment.dao.TPartTimeDao;
import com.kmust.recruitment.dao.TUserDao;
import com.kmust.recruitment.entity.TPartTime;
import com.kmust.recruitment.entity.TUser;
import com.kmust.recruitment.service.TPartTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TPartTimeServiceImpl implements TPartTimeService {

    @Autowired
    private TPartTimeDao tPartTimeDao;
    @Autowired
    private TUserDao tUserDao;

    @Override
    public TPartTime findById(Integer id) {
        return tPartTimeDao.findById(id);
    }

    /**
     * 保存招聘信息
     * @param tPartTime
     */
    @Override
    public void save(TPartTime tPartTime) {

        TUser tUser = tUserDao.findById(tPartTime.getUid());

        // 补全数据：4项日志，用户名为注册的用户名，时间为当前时间
        Date now = new Date();
        tPartTime.setCreatedUser(tUser.getUsername());
        tPartTime.setCreatedTime(now);
        tPartTime.setModifiedUser(tUser.getUsername());
        tPartTime.setModifiedTime(now);
        TPartTime str = tPartTimeDao.saveAndFlush(tPartTime);
        if(str!=null){
            System.out.println("TRecruitService--> 数据存入成功！");
        }else {
            System.out.println("TRecruitService--> 数据存入失败！");
        }
    }

    /**
     * 查找所有招聘信息数据
     * @return
     */
    @Override
    public List<TPartTime> findAll() {

        return tPartTimeDao.findAll();
    }

    @Override
    public TPartTime findByUid(Integer uid) {
        return tPartTimeDao.findByUid(uid);
    }

    /**
     * 条件，分页查询和排序
     * @param tPartTime
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<TPartTime> selectSortAndPage(TPartTime tPartTime, Integer page, Integer size) {
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page-1,size,sort,"heat");
        Specification<TPartTime> query = new Specification<TPartTime>() {
            @Override
            public Predicate toPredicate(Root<TPartTime> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(tPartTime.getCompany()!=null){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("company")),"%"+tPartTime.getCompany().toLowerCase()+"%"));
                }
                if(tPartTime.getJob()!=null){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("job")),"%"+tPartTime.getJob().toLowerCase()+"%"));
                }
                if(tPartTime.getSalary()!=null){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("salary")),"%"+tPartTime.getSalary()+"%"));
                }
                if(tPartTime.getWorkingHours()!=null){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("workingHours")),"%"+tPartTime.getWorkingHours().toLowerCase()+"%"));
                }
                if(tPartTime.getWorkingRequirements()!=null){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("workingRequirements")),"%"+tPartTime.getWorkingRequirements().toLowerCase()+"%"));
                }
                if(tPartTime.getPerks()!=null){
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("perks")),"%"+tPartTime.getPerks().toLowerCase()+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return tPartTimeDao.findAll(query,pageable).getContent();
    }

    @Override
    public void deleteById(Integer rid) {
        tPartTimeDao.deleteById(rid);
        System.out.println("TRecruitServiceImpl--> 删除数据成功！");
    }

    /**
     * 热门信息
     * @return
     */
    public List<TPartTime> findAllByOrderByHeatDesc(Integer state){

        return tPartTimeDao.findAllByStateOrderByHeatDesc(state);
    }

    /**
     * 实时信息
     * @return
     */
    public List<TPartTime> findAllByOrderByCreatedTimeDesc(Integer state){
        return tPartTimeDao.findAllByStateOrderByCreatedTimeDesc(state);
    }

}