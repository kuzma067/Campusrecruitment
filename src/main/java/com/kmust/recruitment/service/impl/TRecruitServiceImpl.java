package com.kmust.recruitment.service.impl;

import com.kmust.recruitment.dao.TRecruitDao;
import com.kmust.recruitment.dao.TUserDao;
import com.kmust.recruitment.entity.TRecruit;
import com.kmust.recruitment.entity.TUser;
import com.kmust.recruitment.service.TRecruitService;
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
public class TRecruitServiceImpl implements TRecruitService {

    @Autowired
    private TRecruitDao tRecruitDao;
    @Autowired
    private TUserDao tUserDao;

    /**
     * 保存招聘信息
     * @param tRecruit
     */
    @Override
    public void save(TRecruit tRecruit) {

        TUser tUser = tUserDao.findById(tRecruit.getUid());

        // 补全数据：4项日志，用户名为注册的用户名，时间为当前时间
        Date now = new Date();
        tRecruit.setCreatedUser(tUser.getUsername());
        tRecruit.setCreatedTime(now);
        tRecruit.setModifiedUser(tUser.getUsername());
        tRecruit.setModifiedTime(now);
        TRecruit str = tRecruitDao.saveAndFlush(tRecruit);
        if(str!=null){
            System.out.println("TRecruitService--> 数据存入成功！");
        }else {
            System.out.println("TRecruitService--> 数据存入失败！");
        }
    }

    /**
     * 根据uid查找数据
     * @param uid
     * @return
     */
    @Override
    public TRecruit findByUid(Integer uid) {
        return tRecruitDao.findByUid(uid);
    }

    @Override
    public TRecruit findById(Integer id) {
        return tRecruitDao.findById(id);
    }

    /**
     * 热门信息
     * @return
     */
    public List<TRecruit> findAllByOrderByHeatDesc(Integer state){

        return tRecruitDao.findAllByStateOrderByHeatDesc(state);
    }

    /**
     * 实时信息
     * @return
     */
    public List<TRecruit> findAllByOrderByCreatedTimeDesc(Integer state){
        return tRecruitDao.findAllByStateOrderByCreatedTimeDesc(state);
    }


    /**
     * 查找所有招聘信息数据
     * @return
     */
    @Override
    public List<TRecruit> findAll() {

        return tRecruitDao.findAll();
    }
    /**
     * 条件，分页查询和排序
     * @param tRecruit
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<TRecruit> selectSortAndPage(TRecruit tRecruit, Integer page, Integer size) {
        Sort.Direction sort = Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page-1,size,sort,"heat");
        Specification<TRecruit> query = new Specification<TRecruit>() {
            @Override
            public Predicate toPredicate(Root<TRecruit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(tRecruit.getDepartment()!=null){
                    predicates.add(criteriaBuilder.like(root.get("department"),"%"+tRecruit.getDepartment()+"%"));
                }
                if(tRecruit.getCompany()!=null){
                    predicates.add(criteriaBuilder.like(root.get("company"),"%"+tRecruit.getCompany()+"%"));
                }
                if(tRecruit.getOccupation()!=null){
                    predicates.add(criteriaBuilder.like(root.get("occupation"),"%"+tRecruit.getOccupation()+"%"));
                }
                if(tRecruit.getPerks()!=null){
                    predicates.add(criteriaBuilder.like(root.get("perks"),"%"+tRecruit.getPerks()+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return tRecruitDao.findAll(query,pageable).getContent();
    }

    @Override
    public void deleteById(Integer rid) {
            tRecruitDao.deleteById(rid);
            System.out.println("TRecruitServiceImpl--> 删除数据成功！");
    }



}
