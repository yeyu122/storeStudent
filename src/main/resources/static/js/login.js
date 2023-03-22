var LoginUrl = "/users/login"
$(function (){
//    为用户名输入框和密码输入框添加失去焦点事件
    $("#username").blur(function (){
        if (!checkEmpty("username","用户名不能为空")){
            return;
        }
        if (!checkLength("username","6","20")){
            return;
        }
    })
    //    为密码输入框添加失去焦点事件
    $("#password").blur(function (){
        if (!checkEmpty("password","密码不能为空")){
            return;
        }
    })

    //添加按钮点击事件
    $("#btn-login").click(function (){
        //获取表单数据
        var username = $("#username").val();
        var password = $("#password").val();

        //    判断页面中的input是否都进行了验证
        var divArr = $("div.has-success");
        if (divArr.length != 2){
            return;
        }
        //    非空验证
        flag = checkEmpty("username","账号不能为空");
        flag = checkEmpty("password","密码不能为空");


        //    提交表单
        var params = { //提交参数，请求参数属性名=属性值（属性名需和后端一致）
            username:username,
            password:password,
        }
        //    发送AJAX请求
        $.post(LoginUrl,params,function (result) {
            //    处理相应数据
            if (result.state == 1000){ //相应成功状态
                alert("登录成功，点击跳转首页")
                window.location.href="index.html"
            }else {
                alert(result.msg)
            }
        })
    })
})

//    定义验证是否为空方法
function checkEmpty(name,msg){
    if ($("#"+name).val() ==""){
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

//    定义是否一致
function checkRePwd(name1,name2,msg){
    if ($("#"+name1).val() !=$("#"+name2).val()){
        $("#"+name2).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name2).next("span").text(msg);
        return false;
    }else {
        $("#"+name2).parents(".form-group").removeClass("has-error").addClass("has-success");
        //
        $("#"+name2).next("span").text("");
        return true;
    }
}

//定义验证用户名长度的方法
function checkLength(name,minLength,maxLength){
    if ($("#"+name).val().length<minLength){ //小于长度
        $("#"+name).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text("长度不能小于"+minLength);
        return false;
    }else if ($("#"+name).val().length>maxLength){ //大于长度
        $("#"+name).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text("长度不能大于"+maxLength);
        return false;
    }else {
        $("#"+name).parents(".form-group").removeClass("has-error").addClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text("");
        return true;
    }
}