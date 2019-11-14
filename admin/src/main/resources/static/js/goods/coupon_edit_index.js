jQuery(function ($) {
    init();
    //提交Form表单新增商品信息
    $("#addCoupon").click(function(){
        var $form = $("#add-form-coupon");
        var couponName = $form.find("input[name=couponName]").val();
        var couponDesc = $form.find("input[name=couponDesc]").val();
        var couponValue = $form.find("input[name=couponValue]").val();
        var limitMoney = $form.find("input[name=limitMoney]").val();
        var couponEndTimeStr = $form.find("input[name=couponEndTimeStr]").val();
        //var imageUrl = $form.find("input[name=file]").attr("value");
        var seqno = $form.find("input[name=seqno]").val();
        if(isEmpty(couponName)){
            Modal.alert({msg : "优惠劵名称必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(couponDesc)){
            Modal.alert({msg : "优惠劵描述必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(couponValue) ){
            Modal.alert({msg : "优惠劵价值必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(!isMoney(couponValue)){
            Modal.alert({msg : "优惠劵价值不正确", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(limitMoney)){
            Modal.alert({msg : "优惠劵限制金额必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(!isMoney(limitMoney)){
            Modal.alert({msg : "优惠劵限制金额不正确", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(couponEndTimeStr)){
            Modal.alert({msg : "截止日期必填", title : "提示", btnok : "确定"});
            return false;
        }
        //
        // if(isEmpty(imageUrl)){
        //     Modal.alert({msg : "优惠劵图片必填", title : "提示", btnok : "确定"});
        //     return false;
        // }

        if(isEmpty(seqno)){
            Modal.alert({msg : "优惠劵序号必填", title : "提示", btnok : "确定"});
            return false;
        }
       $("#addCoupon").attr("disabled",true);
       $("#addCoupon").text("正在提交信息....");
        $("#add-form-coupon").ajaxSubmit({
            type:"post",
            dataType:"json",
            url:base+"coupon/modifCoupon",
            success: function(data) {
                $("#addCoupon").attr("disabled",false);
                if(data == 1){
                    Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定"});
                    emptyForm('add-form-coupon');
                    location.href=base+'coupon/coupon_index';
                }else{
                    Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定"});
                    $("#addCoupon").attr("disabled",false);
                    $("#addCoupon").text("重新提交");
                }
            },
            error:function(data){
                $("#addCoupon").attr("disabled",false);
                $("#addCoupon").text("重新提交");
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });
    });
});

/**
 * 清空form表单
 * @param formId 表单Id
 */
var emptyForm = function(formId){
    $(':input','#'+formId).not(':button, :submit, :reset, :hidden,:radio').val('').removeAttr('checked').removeAttr('selected');
}
function init(){

    // 初始化下拉日历
    $(".date-picker").datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD HH:mm'
    });

    $('#seqno').ace_spinner({
        value: 1,
        min: 1,
        max: 10000,
        step: 1,
        touch_spinner: true,
        icon_up: 'ace-icon fa fa-caret-up bigger-110',
        icon_down: 'ace-icon fa fa-caret-down bigger-110'
    });


    // 初始化上传文件输入框
    $('#imageUrl').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });
}

//验证商品名称是否被占用和格式
function couponNameOnBlus(obj) {
    obj = $(obj);
    var total = checkGoodIsExist(obj.val(),"");
    if(total >= 1){
        Modal.alert({msg : "商品名称已被占用", title : "提示", btnok : "确定"});
        obj.val("");
        return false;
    }
}

//限制金额描述
function limitMoneyOnBlus(obj) {
    $("#limitMoneyDesc").val("满"+obj.value+"可使用");
}