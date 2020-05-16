package com.kmust.recruitment.vo;

import lombok.*;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TUserCollectionAndPublishRecruitVo {
    private Integer id;

    private Integer uid;

    private Integer rid;

    private String subject;

    private String company;

    private String occupation;

    private String salary;

    private String createdUser;

    private Date createdTime;


}
