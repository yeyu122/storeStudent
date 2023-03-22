//页面加载时间获取
$(function (){
    var defaultOption="<option value='0'>---请选择---</option>>"
//    为三个<select>添加默认选项
    $("#province-list").append(defaultOption);
    $("#city-list").append(defaultOption);
    $("#area-list").append(defaultOption);
//    自动加载省级列表
    appendList("86","province-list")
//
    $("province-list").change(function (){
        var code =$("#province-list").val()//自动加载省级编号
        appendList(code,"city-list")//自动加载市级编号

    })
    $("city-list").change(function (){
        var code =$("#city-list").val()//自动加载市级编号
        appendList(code,"area-list")//自动加载区级编号

    })
    //为提交按钮添加点击事件
    $("#btnSubmit").click(function (){
        $.ajax({
            "url":"/addresses/createAddress",
            "data":$("#addAddressFrom").serialize(),
            "type":"post",
            "dataType":"json",
            "success":function (json){
                if (json.state==1000){
                    alert("添加成功")
                }else {
                    alert(json.msg)
                }
            }
        })
    })
})

function appendList(parent,sid){
//    发送ajax请求
    $.ajax({
        "url":"/districts/",
        "data":"parent="+parent,
        "type":"get",
        "dataType":"json",
        "success":function (json){
            //    获取列表的数据
            var list =json.data
            //    遍历数组
            for(var i=0;i<list.length ;i++){
                //遍历获取到的option的选择值
                var option = "<option value='"+list[i].code+"'>"+list[i].name+"</option>"
                //将option添加到对应的是select的内部，作为下拉框的选择值
                $("#"+sid).append(option)
            }
        },
    })
}
// var a = "http://127.0.0.1:8080/web/addAddress.html?id=27";
// console.log(a);
// var b =a.split("=")[1];
// console.log("这是b"+b)
// 获取当前页面的查询字符串
var id =location.search.split("=")[1];


// id[0]为分隔后的第一个部分，id[1]为第二个部分
console.log("这是"+id[1]); // 输出第二个部分