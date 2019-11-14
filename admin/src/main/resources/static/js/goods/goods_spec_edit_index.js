//记录用户选择的规格与规格项列表
var specs =[];

//全部规格列表
var specList = null ;

//记录规格select序号
var index=0;

var specsIndexMapList=[]; //存放规格不重复

jQuery(function ($) {
    //初始化商品规格模板
    initSepecList();

    //读取商品已有的规格
    initGoodsSpecs();

    //添加规格项目
    $("#addSpecs").click(function(){
        ++index;
        if(index > 4){
            Modal.alert({msg: "规格限制5种", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        var sepcObject = $("#specTemplements").children().clone(true);
        sepcObject.find("#specId").attr("index",index);
        $("#specs").append(sepcObject);
    });

    //选择商品规格
    $(document).on("change","#specs #specId",function(){
        var $specId = $(this);
        updateSpecValues($specId);
    });

    //#提交商品规格库存添加
    $("#updateGoodsSpecs").click(function () {
        $("#updateGoodsSpecs").attr("disabled",true);
        $("#updateGoodsSpecs").text("数据修改中....");
        var specStockVO = {};
        specStockVO["goodsId"] = $("#goodsId").val();
        var goodsSpecs = [];
      $("#goodsStock #goodsSpecPriceStock").each(function(i, spec){
             var obj = $(this);
          var goodsSpec = {};
             obj.children("#goodsSpecValueSpan").find("span").each(function (index, element) {
                 var span = $(this);
                if(span.attr("goodsSpecId")){
                    goodsSpec["goodsSpecId"] =span.attr("goodsSpecId");
                }
                if(index==0 && span.attr("specid")){
                    goodsSpec["specId"] =span.attr("specid");
                }else if( span.attr("specid")){
                    goodsSpec["specId"+index] = span.attr("specid");
                }
                if(index == 0 && span.attr("goodsspecvaluesid")){
                    goodsSpec["goodsSpecValuesId"] = span.attr("goodsspecvaluesid");
                }else if(span.attr("goodsspecvaluesid")){
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
            url: base + "goods/updateGoodsSpecAndStock",
            dataType: "json",
            type:"POST",
            contentType:"application/json",
            data:JSON.stringify(specStockVO),
            success: function (data) {
                if(data > 0){
                    Modal.alert({msg : "添加商品规格库存成功！", title : "提示", btnok : "确定"});
                    location.href=base+"goods/goods_index";
                }else{
                    Modal.alert({msg : "添加品规格库存失败！", title : "提示", btnok : "确定"});
                }
            },
            error: function (data) {
                $("#updateGoodsSpecs").attr("disabled",false);
                $("#updateGoodsSpecs").text("重新添加");
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });

    });

});
//展示商品specs列表
function selectSpecsByGoodsId(){
    var  specArray =null;
    $.ajax({
        async: false,
        url: base + "goods/selectSpecsByGoodsId",
        dataType: "json",
        type:"POST",
        data: {"goodsId":$("#goodsId").val()},
        success: function (data) {
            specArray = data;
        },
        error: function (data) {
            specArray = data;
        }
    });
    return specArray;

}
//读取商品已有的规格与库存
function initGoodsSpecs(){
    var specArray =selectSpecsByGoodsId();
    var  goodsSpecArray = selectGoodsSpecsByGoodsId();
    //规格组
    if(specArray && specArray.length >0){ //有规格商品
        $.each(specArray,function(i,item){
            var sepcObject = $("#specTemplements").children().clone(true);
            sepcObject.find("#specId").attr("index",i);
            sepcObject.find("select[name=specId] option[value="+item.specId+"]").attr("selected",true);
            $("#specs").append(sepcObject);
            updateSpecValues( sepcObject.find("#specId"));
            sepcObject.find("#specValues input[type=checkbox]").attr("specId",item.specId);
            sepcObject.find("#specValues input[type=checkbox]").each(function(i,spec){  //如果规格值ID在goodsSpecArray中则选中该规格值
                for(var k =0;k< goodsSpecArray.length;k++){
                    if(goodsSpecArray[k]){
                        if( (goodsSpecArray[k].goodsSpecValuesId == $(this).val() && item.specId == goodsSpecArray[k].specId)
                            || (goodsSpecArray[k].goodsSpecValuesId1 == $(this).val() && item.specId == goodsSpecArray[k].specId1)
                            || (goodsSpecArray[k].goodsSpecValuesId2 == $(this).val() &&  item.specId == goodsSpecArray[k].specId2)
                            || (goodsSpecArray[k].goodsSpecValuesId3 == $(this).val()&&  item.specId == goodsSpecArray[k].specId3)
                            || (goodsSpecArray[k].goodsSpecValuesId4 == $(this).val() &&  item.specId == goodsSpecArray[k].specId3)){
                            $(this).attr("checked","checked");
                            $(this).attr("disabled","disabled");
                        }
                    }

                }
          });
        //记录选择的规格值到specs中
         $("#specs").find("input[type=checkbox]").each(function (p, o) {
            if($(this).attr("checked") =="checked" && $(this).attr("specid") == item.specId){
                var spec={};
                var specid = $(this).attr("specid");
                var goodsSpecValuesId = $(this).val();
                spec["index"] =i;
                spec["specId"] =item.specId;
                spec["goodsSpecValuesId"] = $(this).val();
                if(isNotRepetSpecValue(specs,$(this).val(),item.specId,i)){
                    specs.push(spec);
                }
            }
        });
        });
        //排列组合各种规格的组合情况
        combination();
    }else{
       // 无规格商品
            for (var k = 0; k < goodsSpecArray.length; k++) {
                $("#goodsStock").empty();
                var sepcObject = $("#priceStock").clone(true).css({"display": "block"}).prop("id","goodsSpecPriceStock");
                var specStr ='<span id="goodsSpecValuesId" goodsSpecId="'+goodsSpecArray[k].goodsSpecId+'"  style="border-color: #393939;color: #393939!important;border: 1px solid;">无规格商品</span>&nbsp;&nbsp;';
                sepcObject.find("#goodsSpecValueSpan").html(specStr)
                $("#goodsStock").append(sepcObject);

                //更新所有规格库存与价格
                var obj= $("#goodsStock #goodsSpecPriceStock");
                    obj.find("input[name=weight]").val(goodsSpecArray[k].weight);
                    obj.find("input[name=costPrice]").val(goodsSpecArray[k].costPrice);
                    obj.find("input[name=userPrice]").val(goodsSpecArray[k].userPrice);
                    obj.find("input[name=vipPrice]").val(goodsSpecArray[k].vipPrice);
                    obj.find("input[name=num]").val(goodsSpecArray[k].stockNum);

            }

    }
}
//展示商品规格库存列表
function selectGoodsSpecsByGoodsId(){
    var  goodsSpecArray =null;
    $.ajax({
        async: false,
        url: base + "goods/selectGoodsSpecsByGoodsId",
        dataType: "json",
        type:"POST",
        data: {"goodsId":$("#goodsId").val()},
        success: function (data) {
            goodsSpecArray = data;
        },
        error: function (data) {
            goodsSpecArray = data;
        }
    });
    return goodsSpecArray;
}
//初始化商品规格select框
function initSepecList(){
    $.ajax({
        async: false,
        url: base + "spec/getSpecList",
        dataType: "json",
        type:"POST",
        data: {},
        success: function (data) {
            specList = data;
        },
        error: function (data) {
            specList = data;
        }
    });
    if(specList){
        $.each(specList,function(i,val){
           $("#specTemplements").find("#specId").append('<option value="'+val.specId+'">'+val.specName+'</option>');
            var specValuesList =val.specValuesList;
            $.each(specValuesList,function(j,specValue){
                if(i==0){
                    $("#specTemplements").find("#specValues").append('<div class="col-sm-2">'+
                        '<input type="checkbox" id="goodsSpecValuesId" value="'+specValue.goodsSpecValuesId+'" name="goodsSpecValuesId" onclick="onClickHander(this)"/>'+
                        '<span id="valuesName" style="border-color: #393939;color: #393939!important;border: 1px solid;">'+specValue.valuesName+'</span></div>');

                    if(specValuesList.length-1 == j){
                        $("#specTemplements").find("#specValues").append( '<div class="col-sm-2"><a  href="'+base+'spec/spec_values_index?specId='+val.specId+'" >+添加</a></div>');
                    }
                }
            });
        });
    }
}
//监听规格值是否被选择
function onClickHander(obj){
    //当前规格项的规格
    var specId =  $(obj).parents(".form-group").prev(".form-group").find("#specId").children("option:selected").val();
    var index =$(obj).parents(".form-group").prev(".form-group").find("#specId").attr("index");
    if(obj.checked){

        //如果用户重复选择了同规格的规格值提示用户重新选择
        var flag= true;
        $.each(specs,function (i,val) {
            if(val.specId == specId ){
                if(val.goodsSpecValuesId ==  $(obj).val() && val.index != index ){
                    Modal.alert({msg: "请重新选择规格和规格项", title: "提示", btnok: "确定", btncl: "取消"});
                    obj.checked =false;
                    flag=false;
                    return;
                }
            }
        });
        if(flag){
            var spec={};
            spec["index"] =index;
            spec["specId"] = specId;
            spec["goodsSpecValuesId"] = $(obj).val();
            if(isNotRepetSpecValue(specs,$(obj).val(),specId,index)){
                specs.push(spec);
            }
            //排列组合各种规格的组合情况
            combination();
        }
    }else{
        //清除specs中的规格
        removeSpecValuesNoChecked(specId,index,$(obj).val());
        //排列组合各种规格的组合情况
        combination();
    }
}

function removeSpecValuesNoChecked(specId,index,goodsSpecValuesId){
    $.each(specs,function(i,spec){
        if(spec){
            if(spec.specId == specId && (spec.index == index || spec.index+"" == index )&& spec.goodsSpecValuesId == goodsSpecValuesId){
                specs.splice($.inArray(spec, specs), 1);
            }
        }
    });
}
//判断同规格同规格值是否在specs数组中存在
function isNotRepetSpecValue(specs,goodsSpecValuesId,specId,index){
    var flag = true;
    $.each(specs,function (i,item) {
        if(item.specId == specId && item.goodsSpecValuesId  ==goodsSpecValuesId  && item.index == index){
            flag = false;
        }
    });
    return flag;
}
//排列组合各种规格的组合情况
function combination(){
        $.each(specs,function(i,spec){
            //存放规格不重复
            var specsIndexMap=getSpecMapFromSpecsIndexMapList(spec.specId,specsIndexMapList);
            var values=[];
            if(getLength(specsIndexMap) >0){
                specsIndexMap["values"]= getSpecValuesBySpecId(spec.specId);
            }else{
                specsIndexMap={};
                specsIndexMap["index"] = spec.index;
                specsIndexMap["specId"] = spec.specId;
                values.push(getSpecValuesBySpecId(spec.specId));
                specsIndexMap["values"] =values;
                specsIndexMapList.push(specsIndexMap);
            }
        });
        $("#goodsStock").empty();
        var specValuesList=[];//笛卡尔积数组
        $.each(specsIndexMapList,function (i,item) {
            specValuesList.push(item.values);
        });
        var r = multiCartesian(specValuesList); //笛卡尔积算法算出结果组合
        for(var i=0;i<r.length;i++) {
            var sepcObject = $("#priceStock").clone(true).css({"display": "block"}).prop("id","goodsSpecPriceStock");
            var specStr ="";
            if (r[i] instanceof Array) {
                for(var s=0;s<r[i].length;s++){
                    var span = r[i][s];
                    if (span instanceof Array) {
                        for(var ss=0; ss< span.length;ss++){
                            var sspan = span[ss];
                            if (sspan instanceof Array) {
                                for(var sss=0; sss< sspan.length;sss++){
                                    var ssspan =sspan[sss];
                                    if(ssspan instanceof Array){
                                        for(var ssss=0; ssss< ssspan.length;sss++){
                                            var sssspan =ssspan[sss];
                                            if(sssspan instanceof Array){
                                                for(var sssss=0; sssss< sssspan.length;sssss++){
                                                    var ssssspan =sssspan[sssss];
                                                    specStr +='<span id="goodsSpecValuesId" goodsSpecValuesId="'+ssssspan.goodsSpecValuesId+'" specId="'+ssssspan.specId+'" style="border-color: #393939;color: #393939!important;border: 1px solid;">' +ssssspan.valuesName + '</span>&nbsp;&nbsp;;';
                                                }
                                            }else{
                                                specStr +='<span id="goodsSpecValuesId" goodsSpecValuesId="'+sssspan.goodsSpecValuesId+'" specId="'+sssspan.specId+'" style="border-color: #393939;color: #393939!important;border: 1px solid;">' +sssspan.valuesName + '</span>&nbsp;&nbsp;;';
                                            }
                                        }
                                    }else{
                                        specStr +='<span id="goodsSpecValuesId" goodsSpecValuesId="'+ssspan.goodsSpecValuesId+'" specId="'+ssspan.specId+'" style="border-color: #393939;color: #393939!important;border: 1px solid;">' +ssspan.valuesName + '</span>&nbsp;&nbsp;;';
                                    }
                                }
                            }else{
                                specStr +='<span id="goodsSpecValuesId" goodsSpecValuesId="'+sspan.goodsSpecValuesId+'" specId="'+sspan.specId+'" style="border-color: #393939;color: #393939!important;border: 1px solid;">' +sspan.valuesName + '</span>&nbsp;&nbsp;;';
                            }
                        }
                    }else{
                        specStr +='<span id="goodsSpecValuesId" goodsSpecValuesId="'+span.goodsSpecValuesId+'" specId="'+span.specId+'" style="border-color: #393939;color: #393939!important;border: 1px solid;">' +span.valuesName + '</span>&nbsp;&nbsp;;';
                    }
                }
            }else{
                specStr ='<span id="goodsSpecValuesId" goodsSpecValuesId="'+r[i].goodsSpecValuesId+'" specId="'+r[i].specId+'" style="border-color: #393939;color: #393939!important;border: 1px solid;">' +r[i].valuesName + '</span>&nbsp;&nbsp;;';
            }
            sepcObject.find("#goodsSpecValueSpan").html(specStr)
            $("#goodsStock").append(sepcObject);
        }

        //更新所有规格库存与价格
        $("#goodsStock #goodsSpecPriceStock").each(function(i, spec){
            var obj = $(this);
            var goodsSpec = {};
            goodsSpec["goodsId"] = $("#goodsId").val();
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

            });
            $.ajax({
                async: false,
                url: base + "goods/getGoodsSpecDetail",
                dataType: "json",
                data:goodsSpec,
                success: function (data) {
                    if(data){
                        obj.find("input[name=weight]").val(data.weight);
                        obj.find("input[name=costPrice]").val(data.costPrice);
                        obj.find("input[name=userPrice]").val(data.userPrice);
                        obj.find("input[name=vipPrice]").val(data.vipPrice);
                        obj.find("input[name=num]").val(data.stockNum);
                    }

                },
                error: function (data) {

                }
            });
        });
}
function getSpecMapFromSpecsIndexMapList(specId,specsIndexMapList){
    var specsIndexMap = null;
    $.each(specsIndexMapList,function(i,spec){
        if(specId == spec.specId){
            specsIndexMap = spec;
        }
    });
    return specsIndexMap;
}

function getLength(obj){
    var count = 0;
    for(var i in obj){
        if(obj.hasOwnProperty(i)){
            count++;
        }
    }
    return count;
}
//根据规格ID获取规格值列表
function getSpecValuesBySpecId(specId){
    var specsValueIds =[];
    $.each(specs,function(i,spec){
        if(specId == spec.specId){
            $.each(specList,function(j,item){
                $.each(item.specValuesList,function(k,item2){
                    if(specId == item.specId && (spec.goodsSpecValuesId == item2.goodsSpecValuesId)){
                        specsValueIds.push(item2);
                    }
                });
            });
        }
    });
    return specsValueIds;
}



function updateSpecValues($specId){
    var specId =  $specId.children('option:selected').val();
    if(specId){

        var specValuesList = null;
        //找到该规格下面的规格值列表
        $.each(specList,function (i,val) {
            if(val.specId == specId){
                specValuesList = val.specValuesList;
            }
        });
        $specId.parents(".form-group").next(".form-group").find("#specValues").empty();
        //更新规格值
        $.each(specValuesList,function(j,specValue){
            $specId.parents(".form-group").next(".form-group").find("#specValues").append('<div class="col-sm-2">'+
                '<input type="checkbox" id="goodsSpecValuesId" value="'+specValue.goodsSpecValuesId+'" name="goodsSpecValuesId" onclick="onClickHander(this)"/>'+
                '<span id="valuesName" style="border-color: #393939;color: #393939!important;border: 1px solid;">'+specValue.valuesName+'</span></div>');
            if(specValuesList.length-1 == j){
                $specId.parents(".form-group").next(".form-group").find("#specValues").append( '<div class="col-sm-2"> <a  href="'+base+'spec/spec_values_index?specId='+specId+'" >+添加</a></div>');
            }
        });
    }
}