package com.kmust.recruitment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_information_resources")
@org.hibernate.annotations.Table(appliesTo = "t_information_resources",comment="信息资源共享表")
@ToString(callSuper = true)

/**
 * 数据表t_information_resources对应的实体类
 */
public class TInformationResources{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment '信息资源区id'")
    private Integer id;

    @Column(nullable = false,columnDefinition = "int(11) comment '用户id'")
    private Integer uid;

    @Column(columnDefinition = "varchar(64) comment '类型'")
    private String type;

    @Column(columnDefinition = "varchar(64) comment '标题'")
    private String title;

    @Column(columnDefinition = "varchar(256) comment '内容'")
    private String content;

    @Column(columnDefinition = "varchar(128) comment '链接'")
    private String link;

    @Column(columnDefinition = "int(11) comment '热度'")
    private Integer heat;

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