var grid_selector = "#grid-table";
$(function () {
    var adverId = $("#adver_id").val();
    if (isEmpty(adverId)){
        alert("系统异常，请稍后再试！");
    }
    $.ajax({
        async: false,
        url: base+"advertise/selectAdverById",
        type: "post",
        data: {"adverId": adverId},
        dataType: "json",
        success: function (data) {
            advertise = data;
            $("#about_adv").val(advertise.name);
            if (null != advertise){
                $("#adver_name_update").val(advertise.adverName);
                //$("#adver_img_update").val(data.adverImg);
                $("#name_update").val(advertise.name);
                $("#detail_id").val(advertise.detailId);
                //$("input[name='linkType]']").val(advertise.linkType);
                $("input[name=status]:eq("+[advertise.status-1]+")").attr("checked",true);
                $("input[name=type]:eq("+[advertise.type-1]+")").attr("checked",true);
                $("input[name=linkType]:eq("+[advertise.linkType-2]+")").attr("checked",true);
                selectList(advertise.linkType,advertise.name);
            }else {
                Modal.alert({msg : "加载失败！", title : "提示", btnok : "确定", btncl : "取消"});
            }
        },
        error: function (data) {
            Modal.alert({msg : "系统异常！", title : "提示", btnok : "确定", btncl : "取消"});
        }
    });
})

$("input[name='type']").on("click",function () {
    var value = $(this).val();
    var link_type = $("#link_type .col-sm-6 span");
    link_type.show();
    link_type.children("input").prop("checked",false);
    if(value==1){
    }else if(value==2){
        link_type.eq(1).hide();
    }else {
        link_type.hide();
        link_type.eq(1).show();
    }
})
$("input[name='linkType']").on("click",function () {
    $("#add_link_type .col-sm-6").html("");
    var value = $(this).val();
    var link_type = $("#add_link_type .col-sm-6 span");
    if (isEmpty(value)){
        alert("请先选择链接地址！");
        return false;
    }
    selectList(parseInt(value,""));
})

function selectList(value,nameVal){
    switch (value){
        case 2:
            $.ajax({
                async: false,
                url: base+"advertise/selectByUserType",
                type: "post",
                dataType: "json",
                success: function (data) {
                    if(data != null && data.length > 0){
                        var htmlVal = "<option value=''>--请选择--</option>";
                        for(var i = 0;i < data.length;i++){
                            if(data[i].realName == nameVal){
                                htmlVal += "<option selected='selected' id='"+ data[i].userId +"' value='"+data[i].realName+"'>"+data[i].realName+"</option>";
                            }else{
                                htmlVal += "<option id='"+ data[i].userId +"' value='"+data[i].realName+"'>"+data[i].realName+"</option>";
                            }
                        }
                        $("#about_adv").html(htmlVal);
                    }
                    /*for (var i = 0; i < data.length; i++) {
                     $("#add_link_type .col-sm-6").append("<span onclick='red(this)' id='"+data[i].userId+"' style='width: 150px;display: inline-block;margin-right:10px;cursor:pointer;text-align: left;overflow: hidden;'>"+data[i].realName+"</span>")
                     };*/
                }
            });
            break;
        case 3:
            $.ajax({
                async: false,
                url: base+"advertise/goodsList",
                data: {"type":3},
                type: "post",
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if(data != null && data.length > 0){
                        var htmlVal = "<option value=''>--请选择--</option>";
                        for(var i = 0;i < data.length;i++){
                            if(data[i].goodsName == nameVal){
                                htmlVal += "<option selected='selected' id='"+ data[i].goodsId +"' value='"+data[i].goodsName+"'>"+data[i].goodsName+"</option>";
                            }else{
                                htmlVal += "<option id='"+ data[i].goodsId +"' value='"+data[i].goodsName+"'>"+data[i].goodsName+"</option>";
                            }
                        }
                        $("#about_adv").html(htmlVal);
                    }
                }
            });
            break;
        case 4:
            $.ajax({
                async: false,
                url: base+"advertise/selectCategory",
                data: {"type":4},
                type: "post",
                dataType: "json",
                success: function (data) {
                    if(data != null && data.length > 0){
                        var htmlVal = "<option value=''>--请选择--</option>";
                        for(var i = 0;i < data.length;i++){
                            if(data[i].courseTitle == nameVal){
                                htmlVal += "<option selected='selected' id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }else{
                                htmlVal += "<option id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }
                        }
                        $("#about_adv").html(htmlVal);
                    }
                    /*for (var i = 0; i < data.length; i++) {
                     $("#add_link_type .col-sm-6").append("<span onclick='red(this)' id='"+data[i].courseId+"'  style='width: 150px;display: inline-block;margin-right:10px;cursor:pointer;text-align: left;overflow: hidden;'>"+data[i].courseTitle+"</span>")
                     };*/
                }
            });
            break;
        case 5:
            $.ajax({
                async: false,
                url: base+"advertise/selectCategory",
                type: "post",
                data: {"type":5},
                dataType: "json",
                success: function (data) {
                    if(data != null && data.length > 0){
                        var htmlVal = "<option value=''>--请选择--</option>";
                        for(var i = 0;i < data.length;i++){
                            if(data[i].courseTitle == nameVal){
                                htmlVal += "<option selected='selected' id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }else{
                                htmlVal += "<option id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }
                        }
                        $("#about_adv").html(htmlVal);
                    }
                    /*for (var i = 0; i < data.length; i++) {
                     $("#add_link_type .col-sm-6").append("<span onclick='red(this)' id='"+data[i].courseId+"'  style='width: 150px;display: inline-block;margin-right:10px;cursor:pointer;text-align: left;overflow: hidden;'>"+data[i].courseTitle+"</span>")
                     };*/
                }
            });
            break;
        case 6:
            $.ajax({
                async: false,
                url: base+"advertise/selectCategory",
                type: "post",
                dataType: "json",
                data: {"type":6},
                success: function (data) {
                    if(data != null && data.length > 0){
                        var htmlVal = "<option value=''>--请选择--</option>";
                        for(var i = 0;i < data.length;i++){
                            if(data[i].courseTitle == nameVal){
                                htmlVal += "<option selected='selected' id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }else{
                                htmlVal += "<option id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }
                        }
                        $("#about_adv").html(htmlVal);
                    }
                    /*for (var i = 0; i < data.length; i++) {
                     $("#add_link_type .col-sm-6").append("<span onclick='red(this)' id='"+data[i].courseId+"'  style='width: 150px;display: inline-block;margin-right:10px;cursor:pointer;text-align: left;overflow: hidden;'>"+data[i].courseTitle+"</span>")
                     };*/
                }
            });
            break;
        case 7:
            $.ajax({
                async: false,
                url: base+"advertise/selectCategory",
                type: "post",
                dataType: "json",
                data: {"type":7},
                success: function (data) {
                    if(data != null && data.length > 0){
                        var htmlVal = "<option value=''>--请选择--</option>";
                        for(var i = 0;i < data.length;i++){
                            if(data[i].courseTitle == nameVal){
                                htmlVal += "<option selected='selected' id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }else{
                                htmlVal += "<option id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }
                        }
                        $("#about_adv").html(htmlVal);
                    }
                    /*for (var i = 0; i < data.length; i++) {
                     $("#add_link_type .col-sm-6").append("<span onclick='red(this)' id='"+data[i].courseId+"' style='width: 150px;display: inline-block;margin-right:10px;cursor:pointer;text-align: left;overflow: hidden;'>"+data[i].courseTitle+"</span>")
                     };*/
                }
            });
            break;
        case 8:
            $.ajax({
                async: false,
                url: base+"advertise/selectCategory",
                type: "post",
                dataType: "json",
                data: {"type":8},
                success: function (data) {
                    if(data != null && data.length > 0){
                        var htmlVal = "<option value=''>--请选择--</option>";
                        for(var i = 0;i < data.length;i++){
                            if(data[i].courseTitle == nameVal){
                                htmlVal += "<option selected='selected' id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }else{
                                htmlVal += "<option id='"+ data[i].courseId +"' value='"+data[i].courseTitle+"'>"+data[i].courseTitle+"</option>";
                            }
                        }
                        $("#about_adv").html(htmlVal);
                    }
                    /*for (var i = 0; i < data.length; i++) {
                     $("#add_link_type .col-sm-6").append("<span onclick='red(this)' id='"+data[i].courseId+"' style='width: 150px;display: inline-block;margin-right:10px;cursor:pointer;text-align: left;overflow: hidden;'>"+data[i].courseTitle+"</span>")
                     };*/
                }
            });
            break;
    }
}

$("#update_advertise").click(function () {
    $("#update-form-advertise").ajaxSubmit({
        url: base+"advertise/updateAdver",
        type: "post",
        dataType: "json",
        success: function (data) {
            if ("success" == data.status) {
                Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                emptyForm("update-form-advertise");
                jQuery(grid_selector).trigger("reloadGrid");
                setTimeout(function () {
                    window.location.href = base+"advertise/mallAdvertise/";
                },1800);
            } else {
                Modal.alert({msg: "修改失败！", title: "提示", btnok: "确定", btncl: "取消"});
            }
        },
        error: function (data) {
            Modal.alert({msg : "系统异常，请稍后重试！", title : "提示", btnok : "确定", btncl : "取消"});
        }
    });
})

//获取默认选中的值
function choose(obj) {

    var detailId= $("select option:checked").attr("id");
    $("#detail_id").val(detailId);
    var name = $("#about_adv").val();
    $("#name_update").val(name);
}

//返回
$("#backBtn").click(function () {
    location.href = base+"advertise/mallAdvertise/";
})
$('#adver_img_update').ace_file_input({
    no_file:'选择文件...',
    btn_choose:'选择',
    btn_change:'修改',
    droppable:false,
    onchange:null,
    thumbnail:false
});
