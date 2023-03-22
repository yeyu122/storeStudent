package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.Mapper.AddressMapper;
import cn.tedu.storeStudent.common.Constant;
import cn.tedu.storeStudent.entity.Address;
import cn.tedu.storeStudent.service.IAddressService;
import cn.tedu.storeStudent.service.IDistrictService;
import cn.tedu.storeStudent.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.zip.DataFormatException;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper mapper;
    @Autowired
    IDistrictService service;

    @Override
    public void createAddress(Integer userId, String username, Address address) {
        //1非空验证
        if (StringUtils.isEmpty(userId)) {
            throw new EmptyArgumentException("添加异常：用户id不能为空");
        }
        //2基于用户id查询用户地址数量
        int count = 0;
        try {
            count = mapper.countByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InsertException("添加异常：查询用户地址数量有误");
        }
        //3判断数量是否达到上限
        if (count >= Constant.MAX_ADDRESS) {
            throw new AddressCountLimitException("添加异常：地址数量已达上限");
        }
        //4判断是否是首次添加收获地址
        if (count == 0) {
            address.setIsDefault(Constant.IS_DEFAULT);
        } else {
            address.setIsDefault(Constant.IS_NOT_DELETE);
        }
    //    5补全省市区信息
        Integer provinceCode=service.findCodeByName(address.getProvinceName());
        Integer cityCode=service.findCodeByName(address.getCityName());
        Integer areaCode=service.findCodeByName(address.getAreaName());
        address.setProvinceCode(provinceCode);
        address.setCityCode(cityCode);
        address.setAreaCode(areaCode);


        //6执行添加地址操作
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        Integer row = 0;
        try {
            row = mapper.saveAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InsertException("添加异常:查询信息有误");
        }
        if (row != 1) {
            throw new InsertException("添加收货地址异常");
        }
    }

    @Override
    public List<Address> addressList(Integer Uid) {
        List<Address> list=mapper.getAddressByUserId(Uid);
        return list;
    }
//删除地址
    @Override
    public void removeAddress(Integer id,Integer Uid) {
        if (StringUtils.isEmpty(Uid)){
            throw new DeleteException("删除异常：用户未登录");
        }
        mapper.deleteById(id);
    }

    @Override
    public void updateAddress(Address address) {
        if (StringUtils.isEmpty(address.getId())){
            throw new UpdateException("修改：没得到id");
        }
        System.out.println(address);
        mapper.updateAddress(address);
    }
}
