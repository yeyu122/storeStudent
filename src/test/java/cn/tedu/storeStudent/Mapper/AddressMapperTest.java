package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class AddressMapperTest {
@Autowired(required = false)
AddressMapper mapper;

@Test
void countByUserId(){
    Integer count =mapper.countByUserId(16);
    System.out.println("该用户共有"+count+"条收货地址");
}
    @Test
    void saveAddress() {
        Address address = new Address(null,16,"2582532130","云南省",1,"文山州",
                2,"文山市", 3,1323,"文山学院新校区","15559759282","0823-121",
                "学校",0, "管理员",new Date(),"管理员",new Date());
        Integer row = mapper.saveAddress(address);
        System.out.println("row"+row);
    }

    @Test
    void getAddressByUserId() {
        List<Address>list=mapper.getAddressByUserId(1);
        list.forEach(System.out::println);
    }

    @Test
    void updateAddress() {
        Address address = new Address();
        address.setId(29);
        address.setName("新名称");
        address.setProvinceName("新省份");
        address.setProvinceCode(2);
        address.setCityName("新城市");
        address.setCityCode(3);
        address.setAreaName("新区域");
        address.setAreaCode(4);
        address.setZip(1212);
        address.setAddress("新地址");
        address.setPhone("13900001111");
        address.setTel("010-12345678");
        address.setTag("家庭");
        address.setIsDefault(1);
        address.setModifiedUser("管理员");
        address.setModifiedTime(new Date());

        Integer row = mapper.updateAddress(address);
        System.out.println("修改成功" );
    }
}