var tamplate="<tr>"+
    "<td>[tag]</td>" +
    "<td>[name]</td>" +
    "<td>[provinceName][cityName][areaName][address]</td>" +
    "<td>[phone]</td>" +
    "<td><a aid='[aid]' class='btn btn-xs btn-info' href='addAddress.html?id=[aid]'><span class='fa fa-edit'></span> 修改</ a></td>" +
    "<td><a aid='[aid]' class='btn btn-xs add-del btn-info btn-delete'><span class='fa fa-trash-o'></span> 删除</ a></td>" +
    "<td><a aid='[aid]' class='btn btn-xs add-def btn-default'>设为默认</ a></td>" +
    "</tr>"
var addressUrl="/addresses/list"
$(function (){
    listAddress();
})
function listAddress(){
    $.get(addressUrl,function (result){
        if (result.state==1000){
            for (var index in result.data){
                var addr =result.data[index]
                var td=tamplate.replace("[tag]",addr.tag)
                    .replace("[name]",addr.name)
                    .replace("[provinceName]",addr.provinceName)
                    .replace("[cityName]",addr.cityName)
                    .replace("[areaName]",addr.areaName)
                    .replace("[address]",addr.address)
                    .replace("[phone]",addr.phone)
                    .replace(/\[aid\]/g,addr.id)
                var tdObj=$(td)
                var btnDefault=tdObj.find(".btn-default")
                if (addr.isDefault==1){
                    btnDefault.remove()
                }
                $("#tbody").append(tdObj)
            }
        }else {
            alert(result.msg)
        }
        //为删除按钮添加点击事件
        $(".btn-delete").bind("click",function (){
            var flag=confirm("确定要删除地地址信息吗？")
            if(flag==false){
                return;
            }
            //获取id
            var id=$(this).attr("aid");
            params={
                id:id
            }
            var deleteUrl="/addresses/remove"
            //发送请求
            $.post(deleteUrl,params,function (result){
                if (result.state==1000){
                    alert("删除成功")
                    window.location.reload()
                }else {
                    alert(result.msg)
                }
            })
        })
    })
}
