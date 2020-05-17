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
@Table(name = "t_user_work_history")
@org.hibernate.annotations.Table(appliesTo = "t_user_work_history",comment="用户就业历史表")
@ToString(callSuper = true)

/**
 * 数据表t_user_work_history对应的实体类
 */
public class TUserWorkHistory
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment '用户就业情况表id'")
    private Integer id;

    @Column(nullable = false,columnDefinition = "int(11) comment '用户信息id'")
    private Integer uid;

    @Column(columnDefinition = "varchar(64) comment '就职的公司'")
    private String company;

    @Column(name = "start_time",columnDefinition = "varchar(64) comment '开始时间'")
    private Date startTime;

    @Column(name = "end_time",columnDefinition = "varchar(64) comment '开始时间'")
    private Date endTime;

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