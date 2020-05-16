package com.kmust.recruitment.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_user_collection")
@org.hibernate.annotations.Table(appliesTo = "t_user_collection",comment="收藏信息表")
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)

/**
 * 数据表t_user_collection对应的实体类
 */
public class TUserCollection {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment '收藏信息id'")
    private Integer id;

    @Column(nullable = false,columnDefinition = "int(11) comment '用户信息id'")
    private Integer uid;

    @Column(columnDefinition = "int(11) comment '招聘信息id'")
    private Integer rid;

    @Column(columnDefinition = "int(11) comment '兼职信息id'")
    private Integer tid;

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