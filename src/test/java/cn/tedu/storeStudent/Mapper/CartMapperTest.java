package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.Cart;
import cn.tedu.storeStudent.entity.Cartvo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartMapperTest {
    @Autowired(required = false)
    CartMapper mapper;

    @Test
    void insertCart() {
        Cart cart = new Cart();
        cart.setUserId(18);
        cart.setProductId(10000018);
        cart.setPrice(5462);
        cart.setNum(2);
    mapper.insertCart(cart);
    }



    @Test
    void getByUidAndPid() {
        Cart cart = mapper.getByUidAndPid(18,10000018);
        System.out.println(cart);
    }

    @Test
    void updateNum() {
        mapper.UpdateNum(1,3,"test",new Date());
    }

    @Test
    void listCart() {
        List<Cartvo> l = mapper.listCart(27);
        l.forEach(System.out::println);
    }
}