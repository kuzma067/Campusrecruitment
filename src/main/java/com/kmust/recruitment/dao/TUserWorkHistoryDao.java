package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TUserWork;
import com.kmust.recruitment.entity.TUserWorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
/**
 * TUserWorkHistory实体类的数据库接口
 */
public interface TUserWorkHistoryDao extends JpaRepository<TUserWorkHistory, String>, JpaSpecificationExecutor<TUserWorkHistory> {

    TUserWorkHistory findAllById(Integer id);
}