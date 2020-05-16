package com.kmust.recruitment.service;

import com.kmust.recruitment.entity.TPartTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TPartTimeServiceTest {
    @Autowired
    private TPartTimeService tPartTimeService;
    @Autowired
    private TUserService tUserService;
    @Test
    public void save(){
        Date endDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH, +7);//+7今天的时间加一天
        endDate = calendar.getTime();

        String user = tUserService.findById(1).getUsername();

        TPartTime.TPartTimeBuilder builder = TPartTime.builder();
       /* builder.company("优衣库").companyAddress("七彩METOWN购物中心店")
                .contactMail("159357857@gmail.com").contactName("刘经理")
                .contactPhone("1589533981").createdTime(new Date())
                .createdUser(user)
                .endTime(endDate).heat(96).job("收营员").perks("员工购物优惠")
                .salary("18元/小时").uid(1).workingHours("955")
                .workingRequirements("有较强的责任心，做事认真细心");*/
        builder.companyAddress("七彩METOWN购物中心店")
                .contactMail("159357857@gmail.com").contactName("刘经理")
                .contactPhone("1589533981").createdTime(new Date())
                .createdUser(user)
                .endTime(endDate).heat(96).job("快递代取2").perks("员工购物优惠")
                .salary("18元/小时").uid(1).workingHours("955")
                .workingRequirements("有较强的责任心，做事认真细心").type("其他").state(0);
        TPartTime tPartTime = builder.build();

        tPartTimeService.save(tPartTime);

    }

    @Test
    public void deleteById(){
        tPartTimeService.deleteById(3);
    }
    @Test
    public void findAll(){
        System.out.println(tPartTimeService.findAll());
    }
}
