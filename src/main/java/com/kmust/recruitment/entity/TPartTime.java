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
@Table(name = "t_part_time")
@org.hibernate.annotations.Table(appliesTo = "t_part_time",comment="兼职信息表")
@ToString(callSuper = true)

/**
 * 数据表t_part_time对应的实体类
 */
public class TPartTime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment '兼职信息id'")
    private Integer id;

    @Column(nullable = false,columnDefinition = "int(11) comment '用户信息id'")
    private Integer uid;

    @Column(columnDefinition = "varchar(64) comment '公司'")
    private String company;

    @Column(columnDefinition = "varchar(64) comment '类型'")
    private String type;

    @Column(columnDefinition = "varchar(64) comment '招聘的工作'")
    private String job;

    @Column(columnDefinition = "varchar(16) comment '薪水'")
    private String salary;

    @Column(name = "working_requirements",columnDefinition = "varchar(128) comment '工作要求'")
    private String workingRequirements;

    @Column(name = "working_hours",columnDefinition = "varchar(64) comment '工作时间'")
    private String workingHours;

    @Column(name = "contact_name",columnDefinition = "varchar(32) comment '联系人'")
    private String contactName;

    @Column(name = "contact_mail",columnDefinition = "varchar(64) comment '联系邮箱'")
    private String contactMail;

    @Column(name = "contact_phone",columnDefinition = "varchar(32) comment '联系手机'")
    private String contactPhone;

    @Column(name = "company_address",columnDefinition = "varchar(128) comment '公司地址'")
    private String companyAddress;

    @Column(columnDefinition = "varchar(64) comment '福利待遇'")
    private String perks;

    @Column(columnDefinition = "int(11) comment '热度'")
    private Integer heat;

    @Column(nullable = false,columnDefinition = "int(11) comment '状态码（是否通过审核）' default 0")
    private Integer state;

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

    @Column(name = "end_time",columnDefinition = "datetime comment '信息截止日期'")
    private Date endTime;


}