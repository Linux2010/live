$(function(){

    // 回显课程信息
    $.ajax({
        async: false,
        type: "POST",
        data: {courseId:$("#courseId").val()},
        url: base + "course/searchCourseById",
        dataType: "json",
        success: function (courseData) {
            if(courseData != null){
                // 显示课程分类树
                $('#courseTypeId').combotree ({
                    url: base + 'courseType/searchCtByParentId?parentId=-1&typeVal=wzkc',
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
                    },
                    onLoadSuccess:function(node,data){
                        // 初始化默认值
                        setDefaultDbSet(courseData.courseTypeId,courseData.ctName);
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
                                if(data[i].courseTagId == courseData.courseTagId){
                                    htmlVal += "<option selected='selected' value='"+data[i].courseTagId+"'>"+data[i].name+"</option>";
                                }else{
                                    htmlVal += "<option value='"+data[i].courseTagId+"'>"+data[i].name+"</option>";
                                }
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
                                if(data[i].userId == courseData.teacherId){
                                    htmlVal += "<option selected='selected' value='"+data[i].userId+"'>"+data[i].userName+"</option>";
                                }else{
                                    htmlVal += "<option value='"+data[i].userId+"'>"+data[i].userName+"</option>";
                                }
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

                // 赋值
                $("#courseTitle").val(courseData.courseTitle);
                $("#courseBeginTime").val(new Date(courseData.courseBeginTime).pattern("yyyy-MM-dd HH:mm"));
                $("#costPrice").val(courseData.costPrice);
                $("#vipPrice").val(courseData.vipPrice);
                $("#seqno").val(courseData.seqno);
                $("#courseIntegral").val(courseData.courseIntegral);
                $("#courseIntro").val(courseData.courseIntro);
                $("#course_cover_link").attr("href",courseData.courseCover);
                $("#course_img_link").attr("href",courseData.courseImg);

            }
        },
        error:function(data){
            Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
        }
    });

    // 点击返回按钮
    $("#backBtn").click(function(){
        location.href=base+"courseWz/courseWzIndex";
    });

    // 点击发布按钮进行ajax提交form表单
    $("#updateCourse").click(function(){
        // 先验证非空项
        if(!verifyValue($("#course_type_id").val(),$("#courseTagId").val(),$("#courseTitle").val(),
                $("#courseBeginTime").val(),$("#teacherId").val(),
                $("#costPrice").val(),$("#vipPrice").val(),$("#seqno").val(),$("#courseIntegral").val(),
                $("#courseIntro").val())){
            return false;
        }else{
            // 设置发布按钮不可用
            $("#updateCourse").attr("disabled",true);
            $("#updateCourse").text("修改中...");
            // 如果验证通过，则提交表单
            $("#update-form-course").ajaxSubmit({
                type:"post",
                dataType:"json",
                url:base+"course/modifyC",
                success: function(data) {
                    // 设置发布按钮可用
                    $("#updateCourse").attr("disabled",false);
                    $("#updateCourse").text("修 改");
                    if(data == 0){
                        location.href=base+"courseWz/courseWzIndex";
                        // Modal.alert({msg : "修改成功！", title : "提示", btnok : "确定"});
                    }else{
                        Modal.alert({msg : "修改失败！", title : "提示", btnok : "确定"});
                    }
                },
                error:function(data){
                    // 设置发布按钮可用
                    $("#updateCourse").attr("disabled",false);
                    $("#updateCourse").text("修 改");
                    Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
                }
            });
        }
    });

    // 校验对应数据
    function verifyValue(courseTypeId,courseTagId,courseTitle,
                         courseBeginTime,teacherId,
                         costPrice,vipPrice,seqno,courseIntegral,courseIntro){
        var result = false;
        if(isEmpty(courseTypeId)){
            Modal.alert({msg: "课程分类不能为空！",title: "提示", btnok: "确定"});
            return result;
        }
        if(isEmpty(courseTagId)){
            Modal.alert({msg: "课程标签不能为空！",title: "提示", btnok: "确定"});
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

// 设置下拉树的默认值
function setDefaultDbSet(dbSetId, setName) {
    // 设置默认值
    var t = $('#courseTypeId').combotree('tree');
    var defNode = t.tree("find", dbSetId);
    if (!defNode) {
        t.tree('append', {
            data: [{
                id: dbSetId,
                text: setName
            }]
        });
        defNode = t.tree("find", dbSetId);
        t.tree('select', defNode.target);
        defNode.target.style.display = 'none';
    }
    $('#courseTypeId').combotree('setValue', dbSetId);
    $('#course_type_id').val(dbSetId);
}