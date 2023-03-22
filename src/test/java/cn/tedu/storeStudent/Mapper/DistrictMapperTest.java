package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DistrictMapperTest {
@Autowired
DistrictMapper mapper;
@Test
void findCodeByName(){
    Integer name=mapper.getCodeByName("11000");
    System.out.println(name);
}
    @Test
    void listByParent() {
        List<District> list = mapper.listByParent("86");
        list.forEach(item-> System.out.println(item));
    }
}