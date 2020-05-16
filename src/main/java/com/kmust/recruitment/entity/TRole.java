package com.kmust.recruitment.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_role")
@org.hibernate.annotations.Table(appliesTo = "t_role",comment="角色表")
@ToString(callSuper = true)
public class TRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(11) comment 'id'")
    private Integer id;

    @Column(columnDefinition = "varchar(128) comment '角色'")
    private String name;

}
