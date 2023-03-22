package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface UserMapper {
//    添加用户信息
    Integer insertUser(User user);
    //根据用户名查询用户信息
    User getByName(String username);
    //修改密码
    Integer updatePassword(Integer id, String password, String modifiedUser, Date modifiedTime);
//修改个人信息
Integer updateUserInfo(User user);
//上传头像
    Integer uploadAvatar(Integer id,String avatar,String modifiedUser,Date modifiedTime);
}
