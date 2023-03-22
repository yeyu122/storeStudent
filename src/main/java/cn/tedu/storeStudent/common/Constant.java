package cn.tedu.storeStudent.common;

import cn.tedu.storeStudent.entity.JsonResult;
import cn.tedu.storeStudent.entity.User;

/*常用静态常量*/
public class Constant {
    public static final int MD5_HASH_TIMES=5;//密码hash迭代次数
    public static final int USER_GENDER_FEMALE=0;//性别编号女
    public static final int USER_GENDER_MALE=1;//性别编号男
    public static final int USER_GENDER_UNKNOWN=2;//性别编号未知
    public static final int IS_NOT_DELETE=0;//标记数据未被删除
    public static final int IS_DELETE=1;//标记数据已被删除
    public static final  JsonResult<User>JR_NOT_LOGGEDIN =new JsonResult<>(2004,"未登录");
    public static  final int MAX_ADDRESS=5;//最大收货地址条数
    public static  final int IS_NOT_DEFAULT=0;//不是默认收货地址
    public static  final int IS_DEFAULT=1;//是默认地址

}
