$(function(){

    // 显示课程分类树
    $('#courseTypeId').combotree ({
        url: base + 'courseType/searchCtByParentId?parentId=-1&typeVal=spkc',
        onBeforeExpand:function(node) {
            $('#courseTypeId').combotree("tree").tree("options").url = base + 'courseType/searchCtByParentId?parentId=' + node.id;
        },
        onBeforeSelect: function(node) {
            if (!$(this).tree('isLeaf', node.target)) {
                return false;
            }
        },
        onClick:function(node){
            // 把课程分类ID放到隐藏域里面
            $("#course_type_id").val(node.id);
        }
    });

    // 显示课程标签下拉列表
    $.ajax({
        async: true,
        type: "POST",
        url: base + "courseTag/searchCourseTagList",
        dataType: "json",
        success: function (data) {
            if(data != null && data.length > 0){
                var htmlVal = "<option value=''>--请选择--</option>";
                for(var i = 0;i < data.length;i++){
                    htmlVal += "<option value='"+data[i].courseTagId+"'>"+data[i].name+"</option>";
                }
                $("#courseTagId").append(htmlVal);
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 显示讲师下拉列表
    $.ajax({
        async: true,
        type: "POST",
        url: base + "course/searchTeaList",
        dataType: "json",
        success: function (data) {
            if(data != null && data.length > 0){
                var htmlVal = "<option value=''>--请选择--</option>";
                for(var i = 0;i < data.length;i++){
                    htmlVal += "<option value='"+data[i].userId+"'>"+data[i].userName+"</option>";
                }
                $("#teacherId").append(htmlVal);
            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 初始化下拉日历
    $(".date-picker").datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD HH:mm'
    });

    // 初始化上传文件输入框
    $('#courseCover').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });
    $('#courseImg').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"courseSp/courseSpIndex";
    });

    // 点击发布按钮进行ajax提交form表单
    $("#addCourse").click(function(){
        // 先验证非空项
        if(!verifyValue($("#course_type_id").val(),$("#courseTagId").val(),$("#courseCover").val(),$("#courseTitle").val(),
                $("#courseBeginTime").val(),$("#teacherId").val(),
                $("#costPrice").val(),$("#vipPrice").val(),$("#seqno").val(),$("#courseIntegral").val(),
                $("#courseIntro").val(),$("#courseImg").val())){
            return false;
        }else{
            // 设置发布按钮不可用
            $("#addCourse").attr("disabled",true);
            $("#addCourse").text("发布中...");
            // 如果验证通过，则提交表单
            $("#add-form-course").ajaxSubmit({
                type:"post",
                dataType:"json",
                url:base+"course/addC",
                success: function(data) {
                    // 设置发布按钮可用
                    $("#addCourse").attr("disabled",false);
                    $("#addCourse").text("发 布");
                    if(data == 0){
                        location.href=base+"courseSp/courseSpIndex";
                        // Modal.alert({msg : "发布成功！", title : "提示", btnok : "确定"});
                    }else{
                        Modal.alert({msg : "发布失败！", title : "提示", btnok : "确定"});
                    }
                },
                error:function(data){
                    // 设置发布按钮可用
                    $("#addCourse").attr("disabled",false);
                    $("#addCourse").text("发 布");
                    Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
                }
            });
        }
    });

    // 校验对应数据
    function verifyValue(courseTypeId,courseTagId,courseCover,courseTitle,
                         courseBeginTime,teacherId,
                         costPrice,vipPrice,seqno,courseIntegral,courseIntro,courseImg){
        var result = false;
        if(isEmpty(courseTypeId)){
            Modal.alert({msg: "课程分类不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(courseTagId)){
            Modal.alert({msg: "课程标签不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(courseCover)){
            Modal.alert({msg: "课程封面不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(courseImg)){
            Modal.alert({msg: "课程列表图片不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(courseTitle)){
            Modal.alert({msg: "课程名称不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(courseBeginTime)){
            Modal.alert({msg: "课程时间不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(teacherId)){
            Modal.alert({msg: "讲师不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(costPrice)){
            Modal.alert({msg: "课程价格不能为空！",title: "提示", btnok: "确定"});
            return result;
        }else{
            if(isNaN(costPrice)){
                Modal.alert({msg: "请以数字的形式输入课程价格！",title: "提示", btnok: "确定"});
                return result;
            }
        }
        if(isEmpty(vipPrice)){
            Modal.alert({msg: "会员价格不能为空！",title: "提示", btnok: "确定"});
            return result;
        }else{
            if(isNaN(vipPrice)){
                Modal.alert({msg: "请以数字的形式输入会员价格！",title: "提示", btnok: "确定"});
                return result;
            }
        }
        if(isEmpty(seqno)){
            Modal.alert({msg: "序列值不能为空！",title: "提示", btnok: "确定"});
            return result;
        }else{
            if(isNaN(seqno)){
                Modal.alert({msg: "请以数字的形式输入序列值！",title: "提示", btnok: "确定"});
                return result;
            }
        }
        if(isEmpty(courseIntegral)){
            Modal.alert({msg: "积分数不能为空！",title: "提示", btnok: "确定"});
            return result;
        }else{
            if(isNaN(courseIntegral)){
                Modal.alert({msg: "请以数字的形式输入积分数！",title: "提示", btnok: "确定"});
                return result;
            }
        }
        if(isEmpty(courseIntro)){
            Modal.alert({msg: "课程简介不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        result = true;
        return result;
    }

});

// 用户价格是否免费事件
function changeCost(obj){
    if($("#costPrice").val() != 0 || $("#costPrice").attr("disabled") == false || $("#costPrice").attr("disabled") == null){
        $("#costPrice").val(0);
        $("#costPrice").attr("disabled",true);
        $(obj).attr("checked","checked");
    }else{
        $("#costPrice").attr("disabled",false);
        $(obj).removeAttr("checked");
    }
}

// 会员价格是否免费事件
function changeVip(obj){
    if($("#vipPrice").val() != 0 || $("#vipPrice").attr("disabled") == false || $("#vipPrice").attr("disabled") == null){
        $("#vipPrice").val(0);
        $("#vipPrice").attr("disabled",true);
        $(obj).attr("checked","checked");
    }else{
        $("#vipPrice").attr("disabled",false);
        $(obj).removeAttr("checked");
    }
}