package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
/**
 * TUser实体类的数据库接口
 */
public interface TUserDao extends JpaRepository<TUser, String>, JpaSpecificationExecutor<TUser> {


    TUser findById(Integer uid);
    TUser findByUsername(String username);
    TUser findByPhone(String phone);
    void deleteById(Integer uid);
}
