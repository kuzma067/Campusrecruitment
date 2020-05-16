package com.kmust.recruitment.dao;


import com.kmust.recruitment.entity.TUserWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
/**
 * TUserWork实体类的数据库接口
 */
public interface TUserWorkDao extends JpaRepository<TUserWork, String>, JpaSpecificationExecutor<TUserWork> {

    TUserWork findById(Integer id);
    TUserWork findByUid(Integer uid);
    void deleteById(Integer id);

}