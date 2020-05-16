package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TUserWork;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TUserWorkServiceTest {
    @Autowired
    TUserWorkService tUserWorkService;
    @Test
    public void save(){
        TUserWork.TUserWorkBuilder builder = TUserWork.builder();
        builder.expectedCompany("alibaba").expectedPosition("软件工程师").expectedSalary("12000")
                .expectedTime("965").expectedWork("Java开发工程师").isTripleAgreement(1).createdTime(new Date())
                .createdUser("admin").nowCompany("新浪").reason("无").uid(1).tripleAgreementCompany("新浪");
        TUserWork tUserWork = builder.build();
        tUserWorkService.save(tUserWork);
        System.out.println(tUserWorkService.findAll());
    }
}
