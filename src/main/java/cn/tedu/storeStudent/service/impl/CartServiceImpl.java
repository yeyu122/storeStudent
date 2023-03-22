package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.Mapper.CartMapper;
import cn.tedu.storeStudent.Mapper.ProductMapper;
import cn.tedu.storeStudent.Mapper.UserMapper;
import cn.tedu.storeStudent.entity.Cart;
import cn.tedu.storeStudent.entity.Cartvo;
import cn.tedu.storeStudent.entity.Product;
import cn.tedu.storeStudent.entity.User;
import cn.tedu.storeStudent.service.ICartService;
import cn.tedu.storeStudent.service.ex.InsertException;
import cn.tedu.storeStudent.service.ex.RecordNotFoundException;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired(required = false)
    CartMapper mapper;

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    ProductMapper productMapper;

    @Autowired(required = false)
    Product product;
    @Override
    public void createCart(Integer productId, Integer num, Integer userId, String username) {
        User u = userMapper.getByName(username);
        if (u == null){
            throw new InsertException("添加数据失败:未登录请先登录");
        }

        //查看商品是否存在
        Product product = productMapper.getbyid(productId);
        if (product == null){
            throw new InsertException("添加异常:为查询到商品");
        }

        //基于用户id和商品id查询购物车记录
//        Cart cart = mapper.getByUidAndPid(userId,productId);
//        if (cart ==null){
//            throw new InsertException("添加失败:未查询商品信息");
//        }
        //基于用户id和商品id查询购物车记录
        Cart cart=mapper.getByUidAndPid(userId,productId);
        if (cart ==null) {
            Cart c1 = new Cart(null, userId, productId,product.getPrice(), num, username, new Date(), username, new Date());
            Integer row=mapper.insertCart(c1);
            if (row!=1){
    throw new InsertException("添加数据异常");
        }
        }else{
            int newNum = cart.getNum()+num;
            Integer row = mapper.UpdateNum(cart.getId(),newNum,username,new Date());
            if (row!=1){
                throw new InsertException("更新数据异常");
            }
        }
    }

    @Override
    public List<Cartvo> findCartlist(Integer uid) {
        List<Cartvo> Cartlist = mapper.listCart(uid);
        if(Cartlist == null){
            throw new RecordNotFoundException("查询购物车列表失败: 未查询到记录");
        }
        return Cartlist;
    }




}
