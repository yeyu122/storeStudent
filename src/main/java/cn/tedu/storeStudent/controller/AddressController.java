package cn.tedu.storeStudent.controller;

import cn.tedu.storeStudent.entity.Address;
import cn.tedu.storeStudent.entity.JsonResult;
import cn.tedu.storeStudent.entity.User;
import cn.tedu.storeStudent.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController

@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    IAddressService service;
//    添加收货地址
@RequestMapping("/createAddress")

    public JsonResult<Void>createAddress(Address address, HttpSession session){
        User user=(User)session.getAttribute("user");
        address.setUserId(user.getId());
        address.setModifiedUser(user.getUsername());
        address.setModifiedTime(new Date());
        service.createAddress(user.getId(),user.getUsername(),address);
        return JsonResult.getSuccessJR();
    }

    @RequestMapping("/list")
    public JsonResult<List<Address>>addressList(HttpSession session){
    User user=(User)session.getAttribute("user");
    List<Address> list=service.addressList(user.getId());
    return JsonResult.getSuccessJR(list);
    }

    @RequestMapping("/remove")
    public JsonResult<Void>removeBtId(Integer id,HttpSession session){
    User user=(User)session.getAttribute("user");
        System.out.println("当前删除的id为"+id);
    service.removeAddress(id,user.getId());
    return JsonResult.getSuccessJR();
    }

    @RequestMapping("/updateAddress")
    public JsonResult<Void>updateAddress(Address address,HttpSession session){
        service.updateAddress(address);
        return JsonResult.getSuccessJR();
    }
}
