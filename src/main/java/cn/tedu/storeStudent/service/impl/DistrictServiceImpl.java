package cn.tedu.storeStudent.service.impl;

import cn.tedu.storeStudent.Mapper.DistrictMapper;
import cn.tedu.storeStudent.entity.District;
import cn.tedu.storeStudent.service.ex.EmptyArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DistrictServiceImpl implements cn.tedu.storeStudent.service.IDistrictService {
    @Autowired
    DistrictMapper mapper;

    @Override
    public Integer findCodeByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new EmptyArgumentException("查询异常：未传入任何的编号");
        }
        return mapper.getCodeByName(name);
    }

    @Override
    public List<District> findByParent(String parent) {
        if (StringUtils.isEmpty(parent)) {
            throw new EmptyArgumentException("查询异常：未传入任何的父级编号");
        }
        List<District> list = mapper.listByParent(parent);
        if (list == null) {
            throw new EmptyArgumentException("未传入任何的参数信息");

        }
        return list;

    }
}

