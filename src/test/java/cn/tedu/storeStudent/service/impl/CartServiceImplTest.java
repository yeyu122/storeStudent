package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.service.ICartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartServiceImplTest {
    @Autowired
    ICartService service;
    @Test
    void createCart() {
        service.createCart(10000018,1,18,"666666");
    }
}