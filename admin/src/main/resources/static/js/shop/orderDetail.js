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
                    $("#fhBtn").hide();// 隐藏发货按钮
                }else{
                    $("#status").text("正常");
                }
                if(data.payStatus == 1){
                    $("#payStatus").text("待付款");
                    $("#fhBtn").hide();// 隐藏发货按钮
                }else if(data.payStatus == 2){
                    $("#payStatus").text("已付款");
                }else if(data.payStatus == 3){
                    $("#payStatus").text("退款");
                    $("#fhBtn").hide();// 隐藏发货按钮
                }else{
                    $("#payStatus").text("待付款");
                    $("#fhBtn").hide();// 隐藏发货按钮
                }
                if(data.orderStatus == 1){
                    $("#orderStatus").text("待发货");
                }else if(data.orderStatus == 2){
                    $("#orderStatus").text("待收货");
                    $("#fhBtn").hide();// 隐藏发货按钮
                    $("#coBtn").hide();// 隐藏取消订单按钮
                }else if(data.orderStatus == 3){
                    $("#orderStatus").text("待评价");
                    $("#fhBtn").hide();// 隐藏发货按钮
                    $("#coBtn").hide();// 隐藏取消订单按钮
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
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 显示订单商品详情
    $.ajax({
        async: true,
        type: "POST",
        url: base + "order/searchODList",
        data: {
            orderId:$("#orderId").val(),
            orderType:$("#orderType").val()
        },
        dataType: "json",
        success: function (data) {
            if(data != null){
                if(data.length > 0){
                    var goodsJzVal = "商品金额值";
                    if($("#orderType").val() == 2){
                        goodsJzVal = "商品银两值";
                    }
                    var htmlVal = "<form class='form-horizontal'>" +
                        "<div class='form-group' style='padding-bottom: 15px;'>" +
                        "<div class='col-sm-3'>商品名称</div>" +
                        "<div class='col-sm-2'>商品图片</div>" +
                        "<div class='col-sm-3'>商品规格</div>" +
                        "<div class='col-sm-2'>"+goodsJzVal+"</div>" +
                        "<div class='col-sm-2'>商品数量</div>" +
                        "</div>";
                    var goodsNameVal = "";
                    var goodsImgSrc = "";
                    // 循环遍历订单商品详情
                    for(var i = 0;i < data.length;i++){
                        if(data[i].goodsName != null && data[i].goodsName != ""){
                            goodsNameVal = data[i].goodsName;
                        }
                        if(data[i].goodsImg != null && data[i].goodsImg != ""){
                            goodsImgSrc = data[i].goodsImg;
                        }
                        htmlVal += "<div class='form-group' style='border-top:1px solid #e3e8ee;padding:20px 0;'>" +
                            "<div class='col-sm-3'>"+goodsNameVal+"</div>"+
                            "<div class='col-sm-2'><img style='width: 100px;' src='"+goodsImgSrc+"'></div>"+
                            "<div class='col-sm-3'>"+data[i].goodsSpecVal+"</div>" +
                            "<div class='col-sm-2'>"+data[i].goodsJz+"</div>" +
                            "<div class='col-sm-2'>"+data[i].goodsNum+"</div></div>";
                    }
                    htmlVal += "</form>";
                    $("#orderDetail").html(htmlVal);
                }
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 取消订单
    $("#coBtn").click(function(){
        Modal.confirm({msg: "确认取消吗？",
            title: "提示",
            btnok: "确定",
            btncl: "取消"
        }).on( function (e) {
            if(e){// 如果点击了确定，则进行订单的取消操作
                $.ajax({
                    async: false,
                    url: base + "order/modifyOs",
                    data:{orderId:$("#orderId").val(),status:2},
                    dataType: "json",
                    success: function (data) {
                        if(data == 0){
                            location.href=base+"order/orderIndex";
                        }else{
                            Modal.alert({msg: "取消失败！", title: "提示", btnok: "确定"});
                        }
                    }
                })
            }
        });
    });

    // 发货
    $("#fhBtn").click(function(){
        location.href = base + "order/orderFh?orderId="+$("#orderId").val();
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"order/orderIndex";
    });

});