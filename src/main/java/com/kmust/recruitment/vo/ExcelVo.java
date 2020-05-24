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
public class ExcelVo {
    private Integer id;

    private String dept;

    private String major;

    private String grade;

    private String number;

    private String realName;

    private String username;

    private String isTripleAgreement;

    private String tripleAgreementCompany;

    private String reason;

    private String nowCompany;

    private String expectedCompany;

    private String expectedWork;

    private String expectedTime;

    private String expectedPosition;

    private String expectedSalary;

    private String createdUser;

    private Date createdTime;

}
