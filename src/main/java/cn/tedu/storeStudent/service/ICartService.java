package cn.tedu.storeStudent.service;

import cn.tedu.storeStudent.entity.Cart;
import cn.tedu.storeStudent.entity.Cartvo;

import java.util.List;

public interface ICartService {
    //添加一条购物车记录
    void createCart(Integer productId,Integer num ,Integer userId ,String username);
    //查询用户购物车
    List<Cartvo>findCartlist(Integer uid);
}
