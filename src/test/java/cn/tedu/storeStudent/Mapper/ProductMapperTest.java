package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductMapperTest {
    @Autowired
    ProductMapper mapper;

    @Test
    void listHotProduct() {
        List<Product>list=mapper.listHotProduct();
        list.forEach(System.out::println);
    }

    @Test
    void getbyid() {
    Product product = mapper.getbyid(10000019);
        System.out.println(product);
    }
}