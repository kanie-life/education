package com.kanie.education.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//主程序和切面类并不在一个包中，那么主程序扫描不到切面类，自然就不会注册切面了
@ComponentScan("com.kanie.education.*")
public class EducationAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationAdminApplication.class, args);
    }

}
