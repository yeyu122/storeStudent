package cn.tedu.storeStudent.service;

import cn.tedu.storeStudent.entity.District;

import java.util.List;

public interface IDistrictService {
    //    基于编号查询名称
    Integer findCodeByName(String code);
    //    基于父类编号查询自己信息
    List<District> findByParent(String parent);
}
