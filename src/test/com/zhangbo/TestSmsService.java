package com.zhangbo;

import com.zhangbo.config.RootConfig;
import com.zhangbo.model.SmsDown;
import com.zhangbo.service.SmsDownService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


/**
 * Created by zhangbo on 2017/5/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@WebAppConfiguration
public class TestSmsService {


    @Autowired
    private SmsDownService smsDownService;

    @Test
    public void testFindAll() {
        List<SmsDown> smsDownList = smsDownService.findAll();
        for (SmsDown smsDown : smsDownList) {
           // System.out.println(smsDown.toString());
        }
    }


    @Test
    public void testFindPage() {
        Pageable pageable = new PageRequest(1, 10);
        Page<SmsDown> page = smsDownService.findAll(pageable);
        System.out.println("总页数:" + page.getTotalPages());
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("页记录：");
        List<SmsDown> smsDowns = page.getContent();
        for (SmsDown smsDown : smsDowns) {
           // System.out.println(smsDown.toString());
        }
    }

}
