package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TNotPartTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * TNoRecruit实体类的数据库接口
 */
public interface TNotPartTimeDao extends JpaRepository<TNotPartTime, String>, JpaSpecificationExecutor<TNotPartTime> {

    TNotPartTime findAllById(Integer id);

    List<TNotPartTime> findAllByOrderByCreatedTimeAsc();
}