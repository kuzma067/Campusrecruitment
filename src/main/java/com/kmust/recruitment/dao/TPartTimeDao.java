package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TPartTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * TPartTime实体类的数据库接口
 */
public interface TPartTimeDao extends JpaRepository<TPartTime, String>, JpaSpecificationExecutor<TPartTime> {

    TPartTime findById(Integer rid);
    void deleteById(Integer rid);
    TPartTime findByUid(Integer uid);

    List<TPartTime> findAllByStateOrderByHeatDesc(Integer state);
    List<TPartTime> findAllByStateOrderByCreatedTimeDesc(Integer state);
}