package com.kmust.recruitment.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TUserCollectionAndPublishPartTimeVo {
    private Integer id;

    private Integer uid;

    private Integer tid;

    private String company;

    private String job;

    private String salary;

    private String workingRequirements;

    private String workingHours;

    private String createdUser;

    private Date createdTime;


}