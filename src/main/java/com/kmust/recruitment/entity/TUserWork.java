package com.kmust.recruitment.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_user_work")
@org.hibernate.annotations.Table(appliesTo = "t_user_work",comment="用户就业情况表")
@ToString(callSuper = true)

/**
 * 数据表t_user_work对应的实体类
 */
public class TUserWork {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment '用户就业情况表id'")
    private Integer id;

    @Column(nullable = false,columnDefinition = "int(11) comment '用户信息id'")
    private Integer uid;

    @Column(name = "expected_work",columnDefinition = "varchar(64) comment '期望的工作'")
    private String expectedWork;

    @Column(name = "expected_company",columnDefinition = "varchar(64) comment '期望的公司'")
    private String expectedCompany;

    @Column(name = "expected_salary",columnDefinition = "varchar(64) comment '期望的薪资'")
    private String expectedSalary;

    @Column(name = "expected_time",columnDefinition = "varchar(64) comment '期望的工作时间'")
    private String expectedTime;

    @Column(name = "expected_position",columnDefinition = "varchar(64) comment '期望的职位'")
    private String expectedPosition;

    @Column(name = "is_triple_agreement",columnDefinition = "int(11) comment '是否签署三方协议'")
    private Integer isTripleAgreement;

    @Column(name = "triple_agreement_company",columnDefinition = "varchar(64) comment '签署公司'")
    private String tripleAgreementCompany;

    @Column(columnDefinition = "varchar(64) comment '未签署原因'")
    private String reason;

    @Column(name = "now_company",columnDefinition = "varchar(64) comment '目前就职公司'")
    private String nowCompany;

    @Column(name = "created_user",columnDefinition = "varchar(128) comment '创建人'")
    private String createdUser;

    @CreatedDate
    @Column(name = "created_time",columnDefinition = "datetime comment '创建时间'")
    private Date createdTime;

    @Column(name = "modified_user",columnDefinition = "varchar(128) comment '修改人'")
    private String modifiedUser;

    @LastModifiedDate
    @Column(name = "modified_time",columnDefinition = "datetime comment '修改时间'")
    private Date modifiedTime;


}