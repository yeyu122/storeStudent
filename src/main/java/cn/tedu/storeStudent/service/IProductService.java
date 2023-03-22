package cn.tedu.storeStudent.service;

import cn.tedu.storeStudent.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

public interface IProductService {
    //查询热销信息
    List<Product>fondHotList();

    //查询详情信息
    Product findById(Integer id);
}
