package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.Mapper.UserMapper;
import cn.tedu.storeStudent.common.Constant;
import cn.tedu.storeStudent.entity.User;
import cn.tedu.storeStudent.service.IUserService;
import cn.tedu.storeStudent.service.ex.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;


import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper mapper;

    /*用户注册*/
    @Override
    public void regist(User user) {
        //判断user是否为null
        if (user == null) {
            throw new EmptyArgumentException("注册异常：用户名为空");
        }
        //判断用户名或密码是否为空
        if (StringUtils.isEmpty(user.getUsername())) {
            throw new EmptyArgumentException("注册异常：用户名为空");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new EmptyArgumentException("注册异常：密码为空");
        }
        //判断用户是否存在
        User u = null;
        try {
            u = mapper.getByName(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InsertException("注册异常：查询用户名异常", e);
        }
        if (u != null) {
            throw new UserExistException("注册异常：用户名已存在");
        }
        //对密码进行加密
        //生成盐值
        String salt = UUID.randomUUID().toString();
        String md5Password = getMD5Password(user.getPassword(), salt, Constant.MD5_HASH_TIMES);
        user.setPassword(md5Password);//设置user对象加密后的密码
        user.setSalt(salt);//设置user对象的盐值
        user.setGender(Constant.USER_GENDER_UNKNOWN);//设置user对象得性别
        user.setIsDelete(Constant.IS_NOT_DELETE);//设置user对象是否被删除
        user.setCreatedUser(user.getUsername());//设置user对象的创建者
        user.setModifiedUser(user.getUsername());//设置user对象的最后修改者
        //调用持久层 方法，添加用户数据
        int row = 0;
        try {
            row = mapper.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InsertException("注册异常：添加用户信息异常", e);
        }
        if (row != 1) {
            throw new InsertException("注册异常：添加用户信息失败");
        }
    }

    /*用户登录*/
    @Override
    public User login(String username, String password) {
        //参数验证
        if (StringUtils.isEmpty(username)) {
            throw new EmptyArgumentException("登录异常：用户名为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new EmptyArgumentException("登录异常：密码为空");
        }
        //验证用户是否存在
        User u = null;
        try {
            u = mapper.getByName(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException("注册异常：查询用户名失败" + e.getMessage(), e);
        }
        if (u == null) {
            throw new LoginException("注册异常：用户名不存在");
        }
        //获取查询到的用户盐值
        String salt = u.getSalt();
        //对输入的密码进行加密
        String md5Password = getMD5Password(password, salt, Constant.MD5_HASH_TIMES);
        //对比密码
        if (!md5Password.equals(u.getPassword())) {
            throw new LoginException("登录异常：密码错误");
        }
        //判断用户是否被删除
        if (u.getIsDelete().equals(Constant.IS_DELETE)) {
            throw new LoginException("登录异常：用户已被删除");
        }
        System.out.println("业务层用户登录成功！");
        return u;
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        //查询用户信息
        User user = mapper.getByName(username);
        if (user == null) {
            throw new UpdateException("修改失败用户不存在");
        }
        //判断用户是否被删除
        if (user.getIsDelete().equals(Constant.IS_DELETE)) {
            throw new UpdateException("修改异常，用户已被禁用");
        }

        //比对新输入的旧密码和新密码是否一致
        if (oldPassword.equals(newPassword)) {
            throw new UpdateException("输入的新密码与旧密码一致,请重新输入");
        }
        //对输入的旧密码加密并与数据库密码进行比对
        String salt = user.getSalt();
        oldPassword = getMD5Password(oldPassword, user.getSalt(), Constant.MD5_HASH_TIMES);
        if (!oldPassword.equals(user.getPassword())) {
            throw new UpdateException("修改异常：旧密码有误，请重新输入");
        }
        //对新密码进行加密并修改
        newPassword = getMD5Password(newPassword, user.getSalt(), Constant.MD5_HASH_TIMES);
        Integer row = mapper.updatePassword(user.getId(), newPassword, user.getUsername(), new Date());
    }

    @Override
    public Integer changeUserInfo(String username, User user) {
        //根据用户名查询用户信息
        User u = mapper.getByName(username);
        if (u == null) {
            throw new UpdateException("修改异常，用户不存在");
        }
        //判断用户是否已经被删除
        if (u.getIsDelete().equals(Constant.IS_DELETE)) {
            throw new UpdateException("修改异常：该用户已被禁用");
        }

        //修改密码
        user.setId(u.getId());
        user.setModifiedUser(u.getUsername());
        user.setModifiedTime(new Date());
        Integer row = mapper.updateUserInfo(user);
        if (row != 1) {
            throw new UpdateException("修改异常");
        }
        return null;
    }

    //上传头像
    @Override
    public void changeAvatar(String username, String avatar) {
        //判断用户是否存在
        User user = mapper.getByName(username);
        if (user == null) {
            throw new UpdateException("上传异常，用户登录超时");
        }
        //上传头像
        Integer row = mapper.uploadAvatar(user.getId(), avatar, user.getUsername(), new Date());
        if (row != 1) {
            throw new UpdateException("上传失败");
        }
    }

    //对密码 进行加密
// @param password原密码（明文）
// @param salt 盐值(UUID生成的唯一值)
// @param time--hash迭代的次数，
    public String getMD5Password(String password, String salt, int time) {
//组合明文和盐值
        password = password + salt;
        //进行hash迭代
        for (int i = 1; i <= time; i++) {
            password = DigestUtils.md5DigestAsHex(password.getBytes());
        }
        return password;
    }

}
