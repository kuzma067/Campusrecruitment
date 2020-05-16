package com.kmust.recruitment.controller;

import com.kmust.recruitment.entity.TUser;
import com.kmust.recruitment.service.TUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TUserControllerTest {
    @Autowired
    private TUserService tUserService;
    TUserController tUserController = new TUserController();

    @Test
    public void ver(){
       // tUserController.ver("18687232377");
    }
    @Test
    public void reg(){

        TUser.TUserBuilder builder = TUser.builder();
        builder.username("james").password("123456").phone("18687232377").code("726102");
        TUser tUser = builder.build();
        tUserController.reg(tUser);

    }

}
