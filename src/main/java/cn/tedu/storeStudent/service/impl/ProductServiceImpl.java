package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.Mapper.ProductMapper;
import cn.tedu.storeStudent.entity.Product;
import cn.tedu.storeStudent.service.IProductService;
import cn.tedu.storeStudent.service.ex.EmptyArgumentException;
import cn.tedu.storeStudent.service.ex.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper mapper;


    @Override
    public List<Product> fondHotList() {
        List<Product>list=mapper.listHotProduct();
        return list;
    }

    @Override
    public Product findById(Integer id) {
        if(StringUtils.isEmpty(id)){
            throw new EmptyArgumentException("查询异常:为传入id信息");
        }
        Product product =  mapper.getbyid(id);
        if (product == null){
            throw new RecordNotFoundException("查询异常: 未查询到该商品信息");
        }
        return product;
    }
}
