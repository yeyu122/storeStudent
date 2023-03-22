package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.entity.District;
import cn.tedu.storeStudent.service.IDistrictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
    class DistrictServiceImplTest {

        @Autowired
        IDistrictService service;
        @Test
        void findCodeByName() {
            Integer name=service.findCodeByName("110100");
            System.out.println(name);
        }

        @Test
        void findByParent() {
           List<District> byParent=service.findByParent("86");
            byParent.forEach(item->System.out.println(item));
        }
    }