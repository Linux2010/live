jQuery(function ($) {

    init();

    initGoodsCatComboTree();

    //下一步
    $("#goodsDiv").find("#next").click(function () {
        var $form = $("#add-form-goods");
        var goodsName = $form.find("input[name=goodsName]").val();
        var goodsNumber = $form.find("input[name=goodsNumber]").val();
        var unit = $form.find("input[name=unit]").val();
        var catId = $form.find("input[name=catId]").val();
        var imageUrl1 = $form.find("input[name=file1]").val();
        var seqno1 = $form.find("input[name=seqno1]").val();
        var imageUrl2 = $form.find("input[name=file2]").val();
        var seqno2 = $form.find("input[name=seqno2]").val();
        var imageUrl3 = $form.find("input[name=file3]").val();
        var seqno3 = $form.find("input[name=seqno3]").val();
        var imageUrl4 = $form.find("input[name=file4]").val();
        var seqno4 = $form.find("input[name=seqno4]").val();
        var imageUrl5 = $form.find("input[name=file5]").val();
        var seqno5 = $form.find("input[name=seqno5]").val();
        if(isEmpty(goodsName)){
            Modal.alert({msg : "商品名称必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(goodsNumber)){
            Modal.alert({msg : "商品编号必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(unit)){
            Modal.alert({msg : "商品单位必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(catId)){
            Modal.alert({msg : "商品分类必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(imageUrl1)){
            Modal.alert({msg : "商品图片1必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(seqno1)){
            Modal.alert({msg : "商品图片1序号必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(imageUrl2)){
            Modal.alert({msg : "商品图片2必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(seqno2)){
            Modal.alert({msg : "商品图片2序号必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(imageUrl3)){
            Modal.alert({msg : "商品图片3必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(seqno3)){
            Modal.alert({msg : "商品图片3序号必填", title : "提示", btnok : "确定"});
            return false;
        }

        if(isEmpty(imageUrl4)){
            Modal.alert({msg : "商品图片4必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(seqno4)){
            Modal.alert({msg : "商品图片4序号必填", title : "提示", btnok : "确定"});
            return false;
        }

        if(isEmpty(imageUrl5)){
            Modal.alert({msg : "商品图片5必填", title : "提示", btnok : "确定"});
            return false;
        }
        if(isEmpty(seqno5)){
            Modal.alert({msg : "商品图片5序号必填", title : "提示", btnok : "确定"});
            return false;
        }

        $form.hide();
        $("#goodsSpecDiv").show();
        $(this).hide();
        $("#addGoods").show();
        $("#prev").show();
    });
    $("#goodsDiv").find("#prev").click(function () {
        var $form = $("#add-form-goods");
        $form.show();
        $("#goodsSpecDiv").hide();
        $(this).hide();
        $("#next").show();
        $("#addGoods").hide();
    });

    //提交Form表单新增商品信息
    $("#addGoods").click(function(){
        var $form = $("#add-form-goods");
        $("#addGoods").attr("disabled",true);
        $("#addGoods").text("商品发布中....");
        $("#add-form-goods").ajaxSubmit({
            type:"post",
            dataType:"text",
            url:base+"goods/addGoods",
            success: function(goodsId) {
                $("#addGoods").attr("disabled",false);
                if(goodsId){
                   var map = addGoodsSpecStock(goodsId);
                   if(map.status == 1){
                       Modal.alert({msg : "添加成功！", title : "提示", btnok : "确定"});
                       emptyForm('add-form-goods');
                       location.href=base+'goods/goods_index';
                   }else{
                       Modal.alert({msg : map.msg, title : "提示", btnok : "确定"});
                   }
                }else{
                    Modal.alert({msg : "添加失败！", title : "提示", btnok : "确定"});
                    $("#addGoods").attr("disabled",false);
                    $("#addGoods").text("重新发布");
                }
            },
            error:function(data){
                $("#addGoods").attr("disabled",false);
                $("#addGoods").text("重新发布");
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });
    });
});


function addGoodsSpecStock(goodsId){
    var resultMap={};
    var specStockVO = {};
    specStockVO["goodsId"] =goodsId;
    var goodsSpecs = [];
    if($(".xnGoods")){
        specStockVO["weight"] =  $(".xnGoods").find("input[name=weight]").val();
        specStockVO["costPrice"] =  $(".xnGoods").find("input[name=costPrice]").val();
        specStockVO["userPrice"] =  $(".xnGoods").find("input[name=userPrice]").val();
        specStockVO["vipPrice"] =$(".xnGoods").find("input[name=vipPrice]").val();
        specStockVO["stockNum"] =$(".xnGoods").find("input[name=num]").val();
    }
    $("#goodsStock #goodsSpecPriceStock").each(function(i, spec){
        var obj = $(this);
        var goodsSpec = {};
        obj.children("#goodsSpecValueSpan").find("span").each(function (index, element) {
            var span = $(this);
            if(index==0){
                goodsSpec["specId"] =span.attr("specid");
            }else{
                goodsSpec["specId"+index] = span.attr("specid");
            }
            if(index == 0){
                goodsSpec["goodsSpecValuesId"] = span.attr("goodsspecvaluesid");
            }else{
                goodsSpec["goodsSpecValuesId"+index] = span.attr("goodsspecvaluesid");
            }
            goodsSpec["weight"] =span.parent().next().find("input[name=weight]").val();
            goodsSpec["costPrice"] =span.parent().next().find("input[name=costPrice]").val();
            goodsSpec["userPrice"] =span.parent().next().find("input[name=userPrice]").val();
            goodsSpec["vipPrice"] =span.parent().next().find("input[name=vipPrice]").val();
            goodsSpec["stockNum"] =span.parent().next().find("input[name=num]").val();

        });
        goodsSpecs.push(goodsSpec);
    });
    specStockVO["goodsSpecs"]=goodsSpecs;
    $.ajax({
        async: false,
        url: base + "goods/addGoodsSpecAndStock",
        dataType: "json",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(specStockVO),
        success: function (data) {
            if(data > 0){
                resultMap = {"status":1,"msg":"添加商品规格库存成功！"};
            }else{
                rresultMap = {"status":0,"msg":"添加品规格库存失败！"};
            }
        },
        error: function (data) {
            resultMap = {"status":0,"msg":"系统异常，请稍后重试"};
        }
    });
  return resultMap;
}

//初始化商品分类comboTree
function initGoodsCatComboTree(){
   $('#catId').combotree ({
        url: base + 'goodsCat/searchGoodsCateByParentId?parentId=-1',
        onBeforeExpand:function(node) {
            $('#catId').combotree("tree").tree("options").url = base + 'goodsCat/searchGoodsCateByParentId?parentId=' + node.id;
        },
        onClick:function(node){
        }, //选择树节点触发事件
        onSelect : function(node) {
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                Modal.alert({msg: "请选择二级分类", title: "提示", btnok: "确定", btncl: "取消"});
                //清除选中
                $('#catId').combotree('clear');
                return false;
            }
        }
    });
}

/**
 * 清空form表单
 * @param formId 表单Id
 */
var emptyForm = function(formId){
    $(':input','#'+formId).not(':button, :submit, :reset, :hidden,:radio').val('').removeAttr('checked').removeAttr('selected');
}
function init(){

    $('#seqno').ace_spinner({
        value: 1,
        min: 1,
        max: 10000,
        step: 1,
        touch_spinner: true,
        icon_up: 'ace-icon fa fa-caret-up bigger-110',
        icon_down: 'ace-icon fa fa-caret-down bigger-110'
    });

    $('#seqno1').ace_spinner({
        value: 1,
        min: 1,
        max: 10000,
        step: 1,
        touch_spinner: true,
        icon_up: 'ace-icon fa fa-caret-up bigger-110',
        icon_down: 'ace-icon fa fa-caret-down bigger-110'
    });

    $('#seqno2').ace_spinner({
        value: 1,
        min: 1,
        max: 10000,
        step: 1,
        touch_spinner: true,
        icon_up: 'ace-icon fa fa-caret-up bigger-110',
        icon_down: 'ace-icon fa fa-caret-down bigger-110'
    });

    $('#seqno3').ace_spinner({
        value: 1,
        min: 1,
        max: 10000,
        step: 1,
        touch_spinner: true,
        icon_up: 'ace-icon fa fa-caret-up bigger-110',
        icon_down: 'ace-icon fa fa-caret-down bigger-110'
    });

    $('#seqno4').ace_spinner({
        value: 1,
        min: 1,
        max: 10000,
        step: 1,
        touch_spinner: true,
        icon_up: 'ace-icon fa fa-caret-up bigger-110',
        icon_down: 'ace-icon fa fa-caret-down bigger-110'
    });

    $('#seqno5').ace_spinner({
        value: 1,
        min: 1,
        max: 10000,
        step: 1,
        touch_spinner: true,
        icon_up: 'ace-icon fa fa-caret-up bigger-110',
        icon_down: 'ace-icon fa fa-caret-down bigger-110'
    });
    // 初始化上传文件输入框
    $('#imageUrl1').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

    // 初始化上传文件输入框
    $('#imageUrl2').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

    // 初始化上传文件输入框
    $('#imageUrl3').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

    // 初始化上传文件输入框
    $('#imageUrl4').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

    // 初始化上传文件输入框
    $('#imageUrl5').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });
}




//验证商品名称是否被占用和格式
function goodsNameOnBlus(obj) {
    obj = $(obj);
    var total = checkGoodIsExist(obj.val(),"");
    if(total >= 1){
        Modal.alert({msg : "商品名称已被占用", title : "提示", btnok : "确定"});
        obj.val("");
        return false;
    }
}
//验证商品编号是否被占用 和格式
function goodsNumberOnBlus(obj) {
    obj = $(obj);
    if(obj.val() == ''){
        return true;
    }
    if(! /^[0-9a-zA-Z]+$/.test(obj.val()) || obj.val().length >11){
        Modal.alert({msg : "商品编号不合法", title : "提示", btnok : "确定"});
        obj.val("");
        return false;
    }
    var total = checkGoodIsExist("",obj.val());
    if(total >= 1){
        Modal.alert({msg : "商品编号已被占用", title : "提示", btnok : "确定"});
        obj.val("");
        return false;
    }
}

function checkGoodIsExist(goodsName,goodsNumber){
    var total=0;
    $.ajax({
        async: false,
        url: base + "goods/checkGoodsIsExist",
        dataType: "json",
        type:"POST",
        data: {"goodsName":goodsName,"goodsNumber":goodsNumber},
        success: function (data) {
            total = data;
        },
        error: function (data) {
            total = data;
        }
    });
    return total;
}