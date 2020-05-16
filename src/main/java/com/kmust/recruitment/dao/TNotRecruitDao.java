package com.kmust.recruitment.dao;


import com.kmust.recruitment.entity.TNotRecruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * TNoRecruit实体类的数据库接口
 */
public interface TNotRecruitDao extends JpaRepository<TNotRecruit, String>, JpaSpecificationExecutor<TNotRecruit> {

    TNotRecruit findAllById(Integer id);

    List<TNotRecruit> findAllByOrderByCreatedTimeAsc();
}