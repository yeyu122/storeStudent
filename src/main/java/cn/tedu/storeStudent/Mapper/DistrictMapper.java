package cn.tedu.storeStudent.Mapper;

import cn.tedu.storeStudent.entity.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {
//   根据名称编号查询所有的省级编号
    Integer getCodeByName(String name);
//    根据父类编号查询信息
    List<District>listByParent(String parent);

}
