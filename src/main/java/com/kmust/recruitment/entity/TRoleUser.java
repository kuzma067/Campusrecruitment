package com.kmust.recruitment.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_role_user")
@org.hibernate.annotations.Table(appliesTo = "t_role_user",comment="角色表")
@ToString(callSuper = true)
public class TRoleUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment 'id'")
    private Integer id;

    @Column(name="user_id",nullable = false,columnDefinition = "int(11) comment '用户id'")
    private Integer userId;

    @Column(name="role_id",nullable = false,columnDefinition = "int(11) comment '角色id'")
    private Integer roleId;
}
