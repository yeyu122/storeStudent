package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceImplTest {
@Autowired
ProductServiceImpl service;
    @Test
    void fondHotList() {
        List<Product> list=service.fondHotList();
        list.forEach(System.out::println);
    }

    @Test
    void findById() {
       Product product = service.findById(10000019);
        System.out.println(product);
    }
}