package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
//   查询用户已有的收货地址的条数；
    Integer countByUserId(Integer userId);
//    新增收货地址
    Integer saveAddress(Address address);
    //更具用户id查询对应的收货地址
    List<Address> getAddressByUserId(Integer Uid);
    //删除地址
    Integer deleteById(Integer id);

    //修改地址
    Integer updateAddress(Address address);


}
