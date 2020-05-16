package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TRecruit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TRecruitServiceTest {
    @Autowired
    private TRecruitService tRecruitService;

   @Test
    public void save(){
        TRecruit.TRecruitBuilder builder = TRecruit.builder();
       /* builder.company("Alibaba").contactMail("Alibaba@aliyun.com")
                .contactName("Jack Ma").contactPhone("18888888888")
                .department("信息工程系").heat(16333).occupation("软件工程师")
                .perks("五险一金").salary("5000-6000").subject("软件工程").uid(1)
                .workingHours("996").recruitmentRequirements("在校应届生").state(0)
                .endTime(new Date());*/
                /* builder.company("Tencent").contactMail("tencent@qq.com")
                .contactName("马化腾").contactPhone("18866666666")
                .department("信息工程系").heat(16333).occupation("前端工程师")
                .perks("五险一金").salary("7000-10000").subject("软件工程").uid(1)
                .workingHours("996").recruitmentRequirements("在校应届生").state(0)
                .endTime(new Date());*/
       builder.company("Alibaba").contactMail("Alibaba@aliyun.com")
               .contactName("Jack Ma").contactPhone("18888888888")
               .department("语言系").heat(16333).occupation("翻译")
               .perks("五险一金").salary("5000-6000").subject("小语种翻译").uid(1)
               .workingHours("996").recruitmentRequirements("在校应届生").state(0)
               .endTime(new Date()).companyAddress("深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦");
        TRecruit tRecruit = builder.build();

        tRecruitService.save(tRecruit);
    }


    @Test
    public void deleteById(){
        tRecruitService.deleteById(3);
    }
    @Test
    public void findAll(){
        System.out.println(tRecruitService.findAll());
    }
}
