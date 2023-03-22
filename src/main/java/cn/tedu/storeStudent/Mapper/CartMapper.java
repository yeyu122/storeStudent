package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.Cart;
import cn.tedu.storeStudent.entity.Cartvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CartMapper {

    //添加购物车
    Integer insertCart(Cart cart);

    //基于用户id和商品id查询购物车记录
    Cart getByUidAndPid(Integer uid,Integer pid);
    //基于商品id更新购物车数量
    Integer UpdateNum(Integer id, Integer num, String username, Date modifiedTime);
    //根据id查询购物车id
    List<Cartvo>listCart(Integer uid);
}
