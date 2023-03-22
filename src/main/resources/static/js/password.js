var changePasswordUrl="/users/changePassword"
$(function (){
    $("#oldPwd").blur(function (){
        checkEmpty("oldPwd","不能为空")
    })
    //为新原密码添加失去焦点事件
    $("#newPwd").blur(function (){
        checkOldPwd("oldPwd","newPwd","原密码不能为空")
    })
    //为确认新密码添加失去焦点事件
    $("#rePwd").blur(function (){
        checkRePwd("newPwd","rePwd","确认新密码不能为空")
    })
    //为提交按钮添加点击事件
    $("#btnSubmit").click(function (){
        //    获取表单数据
        var oldPwd = $("#oldPwd").val();
        var newPwd =$("#newPwd").val();
        var rePwd =$("#rePwd").val();
        //检查是否都验证成功不存在异常
        var divArr=$("div.has-success")
        console.log(divArr.length)
        if (divArr.length!=3){
            return;
        }
        //发送请求
        var params={
            oldPassword:oldPwd,
            newPassword:newPwd
        }
        $.post(changePasswordUrl,params,function (result){
            if (result.state==1000){
                alert("修改密码成功");
                //清空表单数据
                $("#newPwd").val("");
                $("rePwd").val("");
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
//    定义是否一致
function checkOldPwd(name1,name2,msg){
    if ($("#"+name1).val() ==$("#"+name2).val()){
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