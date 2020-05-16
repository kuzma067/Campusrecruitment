package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TInformationResources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TInformationResourcesTests {
    @Autowired
    TInformationResourcesService tInformationResourcesService;
    @Test
    public void save(){
        TInformationResources.TInformationResourcesBuilder builder = TInformationResources.builder();
        /*builder.content("2020考研资料").createdTime(new Date()).createdUser("kuzma").heat(1)
                .link("https://yz.chsi.com.cn/kyinfo/").title("考研资料分享").uid(1);*/
        builder.content("2020物理学习资料").createdTime(new Date()).createdUser("kuzma").heat(1)
                .link("https://yz.chsi.com.cn/kyinfo/").title("考公资料分享").uid(2).type("综合");
        TInformationResources result = builder.build();
        tInformationResourcesService.save(result);
        System.out.println(tInformationResourcesService.findById(1));
    }
    @Test
    public void find(){
        System.out.println("热度"+tInformationResourcesService.findAllOrderByHeat());
        System.out.println("时间"+tInformationResourcesService.findAllOrderByCreatedTime());
    }
}
