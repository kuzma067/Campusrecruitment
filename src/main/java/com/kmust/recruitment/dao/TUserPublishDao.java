package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TUserPublish;
import com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo;
import com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * TUserPublish实体类的数据库接口
 */
public interface TUserPublishDao extends JpaRepository<TUserPublish, String>, JpaSpecificationExecutor<TUserPublish> {

    @Query(value= "SELECT new com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo(a.id,a.uid,a.rid,b.subject,b.company,b.occupation,b.salary,a.createdUser,a.createdTime) " +
            "FROM TUserPublish a LEFT JOIN TRecruit b ON a.rid=b.id " +
            "WHERE b.uid = :#{#uid} ORDER BY a.createdTime DESC")
    List<TUserCollectionAndPublishRecruitVo> findRecruitByUid(@Param("uid") Integer uid);

    @Query(value= "SELECT new com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo(a.id,a.uid,a.tid,b.company,b.job,b.salary,b.workingRequirements,b.workingHours,a.createdUser,a.createdTime) " +
            "FROM TUserPublish a LEFT JOIN TPartTime b ON a.tid=b.id " +
            "WHERE b.uid = :#{#uid} ORDER BY a.createdTime DESC")
    List<TUserCollectionAndPublishPartTimeVo> findPartTimeByUid(@Param("uid") Integer uid);

    TUserPublish findById(Integer id);

    TUserPublish findByUidAndRid(Integer uid, Integer rid);

    TUserPublish findByUidAndTid(Integer uid, Integer tid);

    void deleteById(Integer id);
    void deleteByUidAndRid(Integer uid,Integer rid);
    void deleteByUidAndTid(Integer uid,Integer tid);
    void deleteAllByUid(Integer uid);
}