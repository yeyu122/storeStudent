package cn.tedu.storeStudent.controller;

import cn.tedu.storeStudent.entity.Cartvo;
import cn.tedu.storeStudent.entity.JsonResult;
import cn.tedu.storeStudent.entity.User;
import cn.tedu.storeStudent.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired(required = false)
    ICartService service;
    @RequestMapping("/create")
    public JsonResult<Void> createCart(Integer productId, Integer num, HttpSession session){
        User user=(User)session.getAttribute("user");
        service.createCart(productId,num,user.getId(),user.getUsername());
        return JsonResult.getSuccessJR();
    }

    @RequestMapping("/list")
    public JsonResult<List<Cartvo>> findCartlist(HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Cartvo> cartlist= service.findCartlist(user.getId());
        return JsonResult.getSuccessJR(cartlist);
    }
}
