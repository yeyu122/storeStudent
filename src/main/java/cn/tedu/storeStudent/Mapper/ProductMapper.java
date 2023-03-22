package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //查询热销商品
    List<Product> listHotProduct();

    //商品详情信息
    Product getbyid(Integer id);
}
