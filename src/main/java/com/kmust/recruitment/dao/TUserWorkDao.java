package com.kmust.recruitment.dao;


import com.kmust.recruitment.entity.TUserWork;
import com.kmust.recruitment.vo.ExcelVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * TUserWork实体类的数据库接口
 */
public interface TUserWorkDao extends JpaRepository<TUserWork, String>, JpaSpecificationExecutor<TUserWork> {

    @Query(value= "SELECT new com.kmust.recruitment.vo.ExcelVo(b.id,a.dept,a.major,a.grade,a.number,a.realName,a.username,b.isTripleAgreement,b.tripleAgreementCompany,b.reason,b.nowCompany,b.expectedCompany,b.expectedWork,b.expectedTime,b.expectedPosition,b.expectedSalary,b.createdUser,b.createdTime) " +
            "FROM TUserWork b LEFT JOIN TUser a ON a.id=b.uid where a.identity = :#{#identity}")
    List<ExcelVo> findAllBy(@Param("identity") String identity);
    TUserWork findById(Integer id);
    TUserWork findByUid(Integer uid);
    void deleteById(Integer id);

}