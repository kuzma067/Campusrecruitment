package com.kmust.recruitment.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_not_part_time")
@org.hibernate.annotations.Table(appliesTo = "t_not_part_time",comment="未通过审核兼职表")
@ToString(callSuper = true)

/**
 * 数据表t_no_part_time对应的实体类
 */
public class TNotPartTime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment '未通过审核兼职表id'")
    private Integer id;

    @Column(nullable = false,columnDefinition = "int(11) comment '用户信息id'")
    private Integer uid;

    @Column(columnDefinition = "int(11) comment '兼职信息id'")
    private Integer tid;

    @Column(columnDefinition = "varchar(64) comment '未通过原因'")
    private String reason;

    @Column(columnDefinition = "varchar(64) comment '审核人员'")
    private String auditor;

    @CreatedDate
    @Column(name = "created_time",columnDefinition = "datetime comment '审核时间'")
    private Date createdTime;




}