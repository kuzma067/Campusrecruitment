package com.kmust.recruitment.dao;

import com.kmust.recruitment.entity.TUserCollection;
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
 * TUserCollection实体类的数据库接口
 */
public interface TUserCollectionDao extends JpaRepository<TUserCollection, String>, JpaSpecificationExecutor<TUserCollection> {



    /*@Query(value = "SELECT t_user_collection.id,t_user_collection.uid,t_user_collection.rid," +
            "t_recruit.subject,t_recruit.company,t_recruit.occupation,t_recruit.salary," +
            "t_user_collection.created_user,t_user_collection.created_time " +
            "FROM t_user_collection LEFT JOIN t_recruit ON t_user_collection.rid=t_recruit.id " +
            "WHERE t_user_collection.uid = :#{#uid} ORDER BY t_user_collection.created_time DESC;",nativeQuery = true)*/
    @Query(value= "SELECT new com.kmust.recruitment.vo.TUserCollectionAndPublishRecruitVo(a.id,a.uid,a.rid,b.subject,b.company,b.occupation,b.salary,a.createdUser,a.createdTime) " +
            "FROM TUserCollection a LEFT JOIN TRecruit b ON a.rid=b.id " +
            "WHERE b.uid = :#{#uid} ORDER BY a.createdTime DESC")
    List<TUserCollectionAndPublishRecruitVo> findRecruitByUid(@Param("uid") Integer uid);

    @Query(value= "SELECT new com.kmust.recruitment.vo.TUserCollectionAndPublishPartTimeVo(a.id,a.uid,a.tid,b.company,b.job,b.salary,b.workingRequirements,b.workingHours,a.createdUser,a.createdTime) " +
            "FROM TUserCollection a LEFT JOIN TPartTime b ON a.tid=b.id " +
            "WHERE b.uid = :#{#uid} ORDER BY a.createdTime DESC")
    List<TUserCollectionAndPublishPartTimeVo> findPartTimeByUid(@Param("uid") Integer uid);

    TUserCollection findById(Integer id);

    TUserCollection findByUidAndRid(Integer uid, Integer rid);

    TUserCollection findByUidAndTid(Integer uid, Integer tid);

    void deleteById(Integer id);
    void deleteByUidAndRid(Integer uid,Integer rid);
    void deleteByUidAndTid(Integer uid,Integer tid);
    void deleteAllByUid(Integer uid);
}