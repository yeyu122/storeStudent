package cn.tedu.storeStudent.service;

import cn.tedu.storeStudent.entity.User;

/*用户模块的业务层接口*/
public interface IUserService {
    /*用户注册*/
    void regist(User user);
/*用户登录*/
    User login(String username,String password);
    //    修改密码
    void changePassword(String username,String oldPassword,String newPassword);
//修改个人信息
    Integer changeUserInfo(String username,User user);
//上传头像
    void changeAvatar(String username, String avatar);

}
