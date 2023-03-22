var hotlistUrl="/product/hotList"

var v

$(function () {
    v=new Vue({
        el:'#hot',
        data:{
            hots:[],
            id:"55555",
        }
    })
    listHot()
    console.log("这是数据",v.hots);
    console.log(v.hots);
})


function listHot() {
    $.get(hotlistUrl,function (res) {
        if (res.state == 1000){
            console.log(res.data);
            v.hots = res.data;
        }else{
            alert(res.msg)
        }
    })
}