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
@Table(name = "t_user")
@org.hibernate.annotations.Table(appliesTo = "t_user",comment="用户表")
@ToString(callSuper = true)

/**
 * 数据表t_user对应的实体类
 */
public class TUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment 'id'")
    private Integer id;

    @Column(columnDefinition = "varchar(128) comment '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(128) comment '密码'")
    private String password;

    @Column(columnDefinition = "varchar(128) comment '盐值'")
    private String salt;

    @Column(columnDefinition = "varchar(16) comment '验证码'")
    private String code;

    @Column(columnDefinition = "varchar(16) comment '性别'")
    private String gender;
    @Column(columnDefinition = "varchar(64) comment '手机号'")
    private String phone;

    @Column(columnDefinition = "varchar(64) comment '微信'")
    private String wechat;

    @Column(columnDefinition = "varchar(64) comment 'QQ'")
    private String qq;

    @Column(columnDefinition = "int(11) comment '年龄'")
    private int age;

    @Column(columnDefinition = "varchar(64) comment '身份'")
    private String identity;

    @Column(columnDefinition = "varchar(128) comment '邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(128) comment '专业'")
    private String major;

    @Column(columnDefinition = "varchar(128) comment '头像'")
    private String avatar;

    @Column(name = "real_name",columnDefinition = "varchar(64) comment '真实姓名'")
    private String realName;

    @Column(name = "credit_rating",columnDefinition = "varchar(64) comment '信用值'")
    private String creditRating;

    @Column(columnDefinition = "varchar(64) comment '班级'")
    private String grade;

    @Column(columnDefinition = "varchar(64) comment '系'")
    private String dept;

    @Column(columnDefinition = "varchar(64) comment '学号'")
    private String number;


    @Column(columnDefinition = "varchar(64) comment '公司'")
    private String company;

    @Column(name = "is_delete",columnDefinition = "int(11) default 0 comment '是否删除'")
    private int isDelete;

    @Column(name = "is_exist",columnDefinition = "int(11) comment '是否存在'")
    private int isExist;


    @Column(name = "created_user",columnDefinition = "varchar(128) comment '创建人'")
    private String createdUser;

    @CreatedDate
    @Column(name = "created_time",updatable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createdTime;


    @Column(name = "modified_user",columnDefinition = "varchar(128) comment '修改人'")
    private String modifiedUser;

    @LastModifiedDate
    @Column(name = "modified_time",columnDefinition = "datetime comment '修改时间'")
    private Date modifiedTime;


}