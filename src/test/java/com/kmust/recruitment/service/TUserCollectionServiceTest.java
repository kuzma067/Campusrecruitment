package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TUserCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TUserCollectionServiceTest {
    @Autowired
    private TUserCollectionService tUserCollectionService;
    @Test
    public void save(){
        TUserCollection.TUserCollectionBuilder builder = TUserCollection.builder();
        builder.createdUser("admin").rid(5).tid(5).uid(2);
        TUserCollection tUserCollection = builder.build();
        tUserCollectionService.save(tUserCollection);
    }
    @Test
    public void findRecruitByUid(){
        System.out.println(tUserCollectionService.findRecruitByUid(1));
    }
    @Test
    public void findPartTimeByUid(){
        System.out.println(tUserCollectionService.findPartTimeByUid(1));
    }
}
