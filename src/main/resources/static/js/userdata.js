var changeInfoUrl = "/users/findUserInfo"
var updateUserURL = "/users/changeUserInfo"

$(function (){
//    获取当前登录对象
    $.post(changeInfoUrl,function (result){
        var userInfo = result.data;
        $("#infoUsername").val(userInfo.username);
   if (userInfo.phone==null){
       $("#phone").prop("placeholder","首次设置")
   }else{
       $("#phone").val(userInfo.phone)
   }

        if (userInfo.email==null){
            $("#email").prop("placeholder","首次设置")
        }else{
            $("#email").val(userInfo.email)
        }
        if (userInfo.gender==0){
            $("#gender-f")[0].checked=true
        }else{
            $("#gender-m")[0].checked=true
        }
    })

//    给手机号码添加失去焦点事件
    $("#phone").blur(function (){
        checkPhone("phone","电话号码格式有误")
    })
//    给邮箱添加失去焦点事件
    $("#email").blur(function (){
        checkEmail("email","邮箱格式有误")
    })

    $("#btnSubmit").click(function (){
        //    获取表单数据
        var phone = $("#phone").val();
        var email = $("#email").val();
        var gender =$("input[name='gender']:checked").val();
        if (gender=='男'){
            gender = 1;
        }else if (gender == '女'){
            gender = 0;
        }else {
            gender = 2;
        }
        //    判断是否有验证异常
        var divArr = $("div.has-success")
        if (divArr.length!=2){
            return
        }
        //    提交表单
        //    提交参数
        var params={
            phone:phone,
            email:email,
            gender:gender,
        }
        //    发送AJAX请求
        $.post(updateUserURL,params,function (result) {
            if (result.state==1000){
                alert("修改成功")
                $("#phone").val("")
                $("#email").val("")
            }else {
                alert(result.msg);
            }
        })
    })
})

//定义验证手机号码的方法
function checkPhone(name,msg){
    if (!/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/.test($("#"+name).val())){
        $("#"+name).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text(msg);
        return false;
    }else {
        $("#"+name).parents(".form-group").removeClass("has-error").addClass("has-success");
        //
        $("#"+name).next("span").text("");
        return true;
    }
}
//定义邮箱的正则表达式
function checkEmail(name,msg){
    if (!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/.test($("#"+name).val())){
        $("#"+name).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text(msg);
        return false;
    }else {
        $("#"+name).parents(".form-group").removeClass("has-error").addClass("has-success");
        //
        $("#"+name).next("span").text("");
        return true;
    }
}