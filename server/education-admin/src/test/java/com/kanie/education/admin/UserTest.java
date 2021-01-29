package com.kanie.education.admin;

import com.kanie.education.admin.basicfunction.service.IUmUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kanie on 2021/1/29 11:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private IUmUserService umUserService;

    @Test
    public void userTests() {
        System.out.println(umUserService.queryResourceListAll().toString());
    }
}
