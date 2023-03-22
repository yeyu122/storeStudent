package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.common.Constant;
import cn.tedu.storeStudent.entity.User;
import cn.tedu.storeStudent.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {
@Autowired
    IUserService service;
    @Test
    void regist() {
        User user=new User();
        user.setUsername("test01");
        user.setPassword("test");
        service.regist(user);
        System.out.println("添加用户数据");
    }

    @Test
    void login() {
        service.login("tom1","12345");
    }

    @Test
    void changePassword() {
        service.changePassword("tom","12345","123456");
        System.out.println("修改成功");
    }

    @Test
    void changeUserInfo() {
        User user=new User();
        user.setPhone("15758574451");
        user.setEmail("2023269749@qq.com");
        user.setGender(Constant.USER_GENDER_MALE);
        service.changeUserInfo("tom",user);
    }
}