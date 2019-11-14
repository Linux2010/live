$(function(){

    // 显示订单详情
    $.ajax({
        async: true,
        type: "POST",
        url: base + "order/searchOrderById",
        data: {
            orderId:$("#orderId").val()
        },
        dataType: "json",
        success: function (data) {
            if(data != null){
                // 给订单信息赋值
                $("#orderNo").text(data.orderNo);
                if(data.status == 1){
                    $("#status").text("正常");
                }else if(data.status == 2){
                    $("#status").text("已取消");
                    $("#coBtn").hide();// 隐藏取消订单按钮
                }else{
                    $("#status").text("正常");
                }
                if(data.payStatus == 1){
                    $("#payStatus").text("待付款");
                }else if(data.payStatus == 2){
                    $("#payStatus").text("已付款");
                }else if(data.payStatus == 3){
                    $("#payStatus").text("退款");
                }else{
                    $("#payStatus").text("待付款");
                }
                if(data.orderStatus == 1){
                    $("#orderStatus").text("待发货");
                }else if(data.orderStatus == 2){
                    $("#orderStatus").text("待收货");
                }else if(data.orderStatus == 3){
                    $("#orderStatus").text("待评价");
                }else{
                    $("#orderStatus").text("待发货");
                }
                if(data.orderType == 1){
                    $("#orderTypeSpan").text("金额商品订单");
                }else if(data.orderType == 2){
                    $("#orderTypeSpan").text("银两商品订单");
                }else{
                    $("#orderTypeSpan").text("金额商品订单");
                }
                $("#orderTime").text(new Date(data.createTime).pattern("yyyy-MM-dd HH:mm:ss"));
                // 给用户信息赋值
                $("#userName").text(data.userName);
                $("#userPhone").text(data.userPhone);
                $("#userAddress").text(data.userAddress);
                $("#totalVal").text(data.orderMoney);
                $("#orderYh").text(data.orderYh);
                $("#orderYf").text(data.orderYf);
                $("#orderSf").text(data.orderSf);
                $("#addressId").val(data.addressId);
                $("#orderNoVal").val(data.orderNo);
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 展示快递公司列表
    $.ajax({
        async: true,
        type: "POST",
        url: base + "order/searchLogiList",
        dataType: "json",
        success: function (data) {
            if(data != null && data.length > 0){
                var htmlVal = "";
                for(var i = 0;i < data.length;i++){
                    htmlVal += "<option value='"+data[i].logiId+"'>"+data[i].name+"</option>";
                }
                $("#logiId").append(htmlVal);
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 发货
    $("#fhBtn").click(function(){
        if($("#logiId").val().length == 0){
            Modal.alert({msg: "请选择快递公司！", title: "提示", btnok: "确定"});
            return false;
        }
        if($("#kdNo").val().length == 0){
            Modal.alert({msg: "请输入快递单号！", title: "提示", btnok: "确定"});
            return false;
        }else{
            var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
            if(reg.test($("#kdNo").val())){
                Modal.alert({msg: "快递单号不能包含中文！",title: "提示", btnok: "确定"});
                return false;
            }
        }
        Modal.confirm({msg: "确认发货吗？",
            title: "提示",
            btnok: "确定",
            btncl: "取消"
        }).on( function (e) {
            if(e){// 如果点击了确定，则进行订单的发货操作
                $.ajax({
                    async: false,
                    url: base + "order/modifyFHS",
                    data:{
                        orderId:$("#orderId").val(),
                        orderStatus:2,
                        logiId:$("#logiId").val(),
                        kdNo:$("#kdNo").val(),
                        addressId:$("#addressId").val(),
                        orderNo:$("#orderNoVal").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        if(data == 0){
                            Modal.alert({msg: "发货成功！", title: "提示", btnok: "确定"});
                            location.href=base+"order/orderIndex";
                        }else{
                            Modal.alert({msg: "发货失败！", title: "提示", btnok: "确定"});
                        }
                    }
                });
            }
        });
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"order/orderIndex";
    });

});