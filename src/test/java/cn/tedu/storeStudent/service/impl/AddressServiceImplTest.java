package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.entity.Address;
import cn.tedu.storeStudent.service.IAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    IAddressService service;

    @Test
    void createAddress() {
        Address address = new Address(null,16,"2582532130","云南省",1,"文山州",
                2,"文山市", 3,1323,"文山学院新校区","15559759282","0823-121",
                "学校",0, "管理员",new Date(),"管理员",new Date());
     service.createAddress(2,"tom",address);
    }

    @Test
    void addressList() {
        List<Address> list=service.addressList(1);
        for (Address address:list) {
            System.out.println(address);
        }
    }

    @Test
    void removeAddress() {
        service.removeAddress(10,8);
    }

    @Test
    void updateAddress() {
        Address address = new Address(23,16,"2582532130","云南省",1,"文山州",
                2,"文山市", 3,1323,"文山学院新校区","15559759282","0823-121",
                "学校",0, "管理员",new Date(),"管理员",new Date());
        service.updateAddress(address);
    }
}