package cn.tedu.storeStudent.controller;

import cn.tedu.storeStudent.entity.JsonResult;
import cn.tedu.storeStudent.entity.Product;
import cn.tedu.storeStudent.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService service;

    /**/
    @RequestMapping("/hotList")
    JsonResult<List<Product>> findHotList() {
        List<Product> list=service.fondHotList();
        return JsonResult.getSuccessJR(list);
    }
    //
    @RequestMapping("/{id}/get")
    public JsonResult<Product>findbyid(@PathVariable("id") Integer id){
        Product product = service.findById(id);
        return JsonResult.getSuccessJR(product);
    }
}