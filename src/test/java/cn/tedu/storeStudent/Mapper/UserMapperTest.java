package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.common.Constant;
import cn.tedu.storeStudent.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class UserMapperTest {

    @Autowired(required = false)
    UserMapper mapper;

    @Test
    void insertUser() {
    User user = new User();
    user.setUsername("test01");
    user.setPassword("test01");
    Integer i = mapper.insertUser(user);
        System.out.println("注册成功，共实效"+i+"行");
    }

    @Test
    void getByName() {
        User user=mapper.getByName("test01");
        System.out.println(user);
    }

    @Test
    void updateUserInfo() {
        User user =new User();
        user.setPhone("18388660912");
        user.setEmail("18388660912@163.com");
        user.setGender(Constant.USER_GENDER_MALE);
        user.setId(16);
        mapper.updateUserInfo(user);
        System.out.println("修改成功");
    }

    @Test
    void uploadAvatar() {
        mapper.uploadAvatar(15,"abc/a.jpg","管理员",new Date());
    }
}