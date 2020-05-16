package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TRecruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * TRecruit实体类的数据库接口
 */
public interface TRecruitDao extends JpaRepository<TRecruit, String>, JpaSpecificationExecutor<TRecruit> {

    TRecruit findById(Integer rid);
    void deleteById(Integer rid);
    TRecruit findByUid(Integer uid);
    void deleteByUid(Integer uid);
    List<TRecruit> findAllByStateOrderByHeatDesc(Integer state);
    List<TRecruit> findAllByStateOrderByCreatedTimeDesc(Integer state);


}