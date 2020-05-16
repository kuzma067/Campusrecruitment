package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TAdmin;
import com.kmust.recruitment.entity.TNotPartTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
/**
 * TAdmin实体类的数据库接口
 */
public interface TAdminDao extends JpaRepository<TAdmin, String>, JpaSpecificationExecutor<TAdmin> {

    TAdmin findAllById(Integer id);
}