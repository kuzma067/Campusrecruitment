package com.kmust.recruitment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_admin")
@org.hibernate.annotations.Table(appliesTo = "t_admin",comment="管理员信息表")
@ToString(callSuper = true)

/**
 * 数据表t_admin对应的实体类
 */
public class TAdmin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment '管理员id'")
    private Integer id;

    @Column(columnDefinition = "varchar(128) comment '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(128) comment '密码'")
    private String password;

    @Column(columnDefinition = "varchar(128) comment '盐值'")
    private String salt;

    @Column(columnDefinition = "varchar(16) comment '姓名'")
    private String name;

    @Column(columnDefinition = "varchar(64) comment '手机号'")
    private String phone;

    @Column(name = "is_delete",columnDefinition = "int(11) default 0 comment '是否删除'")
    private int isDelete;

    @Column(name = "created_user",columnDefinition = "varchar(128) comment '创建人'")
    private String createdUser;

    @Column(name = "created_time",columnDefinition = "datetime comment '创建时间'")
    private Date createdTime;

    @Column(name = "modified_user",columnDefinition = "varchar(128) comment '修改人'")
    private String modifiedUser;

    @Column(name = "modified_time",columnDefinition = "datetime comment '修改时间'")
    private Date modifiedTime;


}