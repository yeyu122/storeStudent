package cn.tedu.storeStudent.service;

import cn.tedu.storeStudent.entity.Address;

import java.util.List;

public interface IAddressService {
//    添加收货地址
    void createAddress(Integer userId, String username, Address address);
    //根据用户id查地址信息
    List<Address>addressList(Integer Uid);
    //删除地址信息
    void removeAddress(Integer id,Integer Uid);
    //修改地址
    void updateAddress(Address address);

}
