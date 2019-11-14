var topicIndex=0;
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
jQuery(function ($) {
    var parent_column = $(grid_selector).closest('[class*="col-"]');
    //resize to fit page size
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', parent_column.width());
    });


    //resize on sidebar collapse/expand
    $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
        if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
            //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
            setTimeout(function () {
                $(grid_selector).jqGrid('setGridWidth', parent_column.width());
            }, 20);
        }
    });

    $("#find").on("click",function(){
        debugger;
        var keyword = $("input[name=keyword]").val();
      //  var isRegisterTopic = $('input[name="isRegisterTopic"]:checked ').val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword)},
        }).trigger('reloadGrid');
    });
    jQuery(grid_selector).jqGrid({
        url:base+ "/topic/list",
        subGrid: false,
        datatype: "json",
        postData:{isRegisterTopic:1},//is_register_topic 是否是注册考题试卷(0:是注册考题;1:课程考题)
        height: 'auto',
        colNames: ['id','考卷名称','课程名称','创建人','创建时间','修改人','修改时间','考题类型','操作'],
        colModel: [
            {name: 'courseTopicExaminationId', index: 'courseTopicExaminationId', width: 200, editable: true, hidden: true, key: true},
            {name: 'examinationName', index: 'examinationName', width: 90},
            {name: 'courseName', index: 'courseName', width: 90},
            {name: 'creater', index: 'creater', width: 90},
            {name: 'createTime', index: 'createTime', width: 90,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'updater', index: 'updater', width: 90},
            {name: 'updateTime', index: 'updateTime', width: 90,formatter:function (cellvalue, options, rowObject) {
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }
            },
            {name: 'isRegisterTopic', index: 'isRegisterTopic', width: 90,formatter:function (cellvalue, options, rowObject) {
                if(cellvalue==0)
                    return '<span class="label label-sm label-warning">注册考题</span> ';
                if(cellvalue==1)
                   return '<span class="label label-sm label-success">课程考题</span>'
            }
            },

            {name:'courseId',index:'courseId',width:20, formatter : operFuc}
        ],
        jsonReader: {
            root: "list",
            page: "pageNum",
            total: "pages",
            records: "total"
        },
        pgtext: "第 {0} 页，共 {1}页",
        recordtext: "第 {0} 到 {1} 共 {2} 条",
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,
        //toppager: true,
        multiselect: true,
        //multikey: "ctrlKey",
        multiboxonly: true,

        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },

        caption: "",
        autowidth: true,
        autohight:true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:0px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:0px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            editfunc : edit,
            edittext:"编辑",
            edittitle :"编辑考题",
            add: true,
            addtext: '添加',
            addtitle: "添加考题",
            addicon: 'ace-icon fa fa-plus-circle purple',
            addfunc : add,
            del: true,
            deltext :'删除',
            deltitle : '删除考题',
            delfunc : del,
            search: false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh yellow',
            alertcap:'提示',
            alerttext: '请选择一条记录'
        }
    )
    /**
     * 添加课程考题页面
     * @param courseId 课程ID
     * @param courseName 课程名称
     */
    function course_add(courseId,courseName){
        var url = base+"topic/course/add?courseId="+courseId+"&courseName="+courseName;
        window.open(url,"_self");
    }
    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
            {
                'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
                'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
                'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
                'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
            };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        });
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container: 'body'});
        $(table).find('.ui-pg-div').tooltip({container: 'body'});
    }
    $(document).one('ajaxloadstart.page', function (e) {
        $.jgrid.gridDestroy(grid_selector);
        $('.ui-jqdialog').remove();
    });

    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title = this.options.title || '&nbsp;'
            if (("title_html" in this.options) && this.options.title_html == true)
                title.html($title);
            else title.text($title);
        }
    }));

    // 操作
    function operFuc(cellvalue, options, rowObject) {
        return '<a title="查看考题" href="javascript:void(0);" ' +
            'onclick="detail(\'' + rowObject.courseTopicExaminationId + '\')" > ' +
            '<i class="ace-icon fa fa-search-plus bigger-200"></i></a>';
    }
    //添加考卷考题
    function add(){
        //重置form表单
        $("#add-form-topic")[0].reset();
        $("#addModal-type").modal("show");//打开模态窗口
        initCourseTypeCombotree("#add-form-topic");
        removeTopics("#add-form-topic");
        topicIndex=0;
        show("#add-form-topic");

    }

    function edit(id){
        var ids=$(grid_selector).jqGrid('getGridParam', "selarrrow");
        if(ids){
            if(ids.length>1){
                Modal.alert({msg: "请选择一条数据进行编辑", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }
        }
        var rowData =   $(grid_selector).jqGrid('getRowData', id);
        //重置form表单
        $("#edit-form-topic")[0].reset();
        $("#editModal-type").modal("show");//打开模态窗口
        removeTopics("#edit-form-topic");
        topicIndex=0;
        show("#add-form-topic");

        //根据courseTopicExaminationId查询考卷与课程信息
        var courseTopicExaminationId = rowData.courseTopicExaminationId;
        var examDetailJson = getExamDetailCourseTopicExamId(courseTopicExaminationId);
        initEditData(rowData,examDetailJson);
        initEditFormCombotree(examDetailJson);

    }
    function hide(idTag){
        $(idTag).find("#courseList").parents(".form-group").hide();//attr("disabled",false);
        $(idTag).find("#courseList").parents(".form-group").next().hide();
        $(idTag).find('#courseTypeId').parents(".form-group").hide();//combotree({ disabled: false });
        $(idTag).find('#courseTypeId').parents(".form-group").next().hide();
    }
    function show(idTag){

        $(idTag).find("#courseList").parents(".form-group").show();//attr("disabled",false);
        $(idTag).find("#courseList").parents(".form-group").next().show();
        $(idTag).find('#courseTypeId').parents(".form-group").show();//combotree({ disabled: false });
        $(idTag).find('#courseTypeId').parents(".form-group").next().show();
    }
    function initEditFormCombotree(examDetailJson){
        $("#editModal-type").find('#courseTypeId').combotree ({
            url: base + 'courseType/searchCtByParentId?parentId=-1',
            onBeforeExpand:function(node) {
                $("#editModal-type").find('#courseTypeId').combotree("tree").tree("options").url = base + 'courseType/searchCtByParentId?parentId=' + node.id;
            },
            onClick:function(node){
                $("#editModal-type").find("#courseId").val("");
                // 查询该节点分类下的课程列表
                initCourseListByTypeId("#editModal-type",node.id);
                var isRegisterTopic = $("#editModal-type").find('input[name="isRegisterTopic"]:checked ').val();
                var courseTypeId = $("#editModal-type").find("input[name=courseTypeId]").val();
                var courseId =$("#editModal-type").find("#courseId").val();
                if(!courseId  && (isRegisterTopic ==1)){
                    if(courseTypeId){//如果课程类别找不到课程
                          alert("该课程类别下无课程，请重新选择类别");
                        return false;

                    }
                }
            },
            onLoadSuccess:function(node,data){
                // 初始化默认值
                setDefaultDbSet("#editModal-type",examDetailJson.courseTypeId,examDetailJson.courseTypeName);
                //初始化课程下拉框
                initCourseListByTypeId("#editModal-type",examDetailJson.courseTypeId);
            }, //选择树节点触发事件
            onSelect : function(node) {
                //返回树对象
                var tree = $(this).tree;
                //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
                var isLeaf = tree('isLeaf', node.target);
                if (!isLeaf) {
                    Modal.alert({msg: "请选择二级分类", title: "提示", btnok: "确定", btncl: "取消"});
                    //清除选中
                    $("#editModal-type").find('#courseTypeId').combotree('clear');
                    return false;
                }
            }
        });
    }

    function initEditData(rowData,examDetailJson){
        var courseTopicExaminationId = rowData.courseTopicExaminationId;
        $("#edit-form-topic").find("#examinationName").val(rowData.examinationName);
        $("#edit-form-topic").find("input[name=course_topic_examination_id]").val(courseTopicExaminationId);
        var courseId=examDetailJson.courseId;
        var isRegisterTopic  =examDetailJson.isRegisterTopic;
        if((isRegisterTopic == 0) || !courseId){
            $("#edit-form-topic").find('input[name="isRegisterTopic"][type="radio"]').first().attr("checked","checked");
            $("#edit-form-topic").find('input[name="isRegisterTopic"][type="radio"]').last().attr("disabled","disabled");
            hide("#edit-form-topic");
        }else{
            $("#edit-form-topic").find('input[name="isRegisterTopic"][type="radio"]').first().attr("disabled","disabled");
            $("#edit-form-topic").find('input[name="isRegisterTopic"][type="radio"]').last().attr("checked","checked");
            show("#edit-form-topic");
            var courseTypeId =examDetailJson.courseTypId;
            $("#edit-form-topic").find("select[name=courseList] option[value="+courseId+"]").attr("selected",true);
        }
        var topicMaps = examDetailJson.topicMaps;
        $.each(topicMaps,function (i,val) {
            topicIndex=val.topic_no;
            var $topic =$("#topicTemplate").clone(true).css({"display":"block"}).prop("id","topic").addClass("topic topicIndex" + val.topic_no)
            $topic.find("#topicNo").val(val.topic_no);
            $topic.find(".topicIndex").text(val.topic_no);
            $("#edit-form-topic").append($topic );
            //初始化学习标签下拉框
            intTopicLabelSelect($topic);
            $topic.find("#course_topic_id").val(val.course_topic_id);
            $topic.find("#topic_id").val(val.id);
            $topic.find("input[name=topicName]").val(val.topic_name);
            $topic.find("input[name=topicAvalue]").val(val.topic_a_value);
            $topic.find("input[name=topicBvalue]").val(val.topic_b_value);
            $topic.find("input[name=topicCvalue]").val(val.topic_c_value);
            $topic.find("input[name=topicDvalue]").val(val.topic_d_value);
            $topic.find("#rightAnwer").text(val.right_answer);

            // var topic_a_label_id = val.topic_a_label_id;
            // var topic_b_label_id = val.topic_b_label_id
            // var topic_c_label_id = val.topic_c_label_id;
            // var topic_d_label_id = val.topic_d_label_id;
            // $($topic).find("select[name=topicAlabelName] option[value="+topic_a_label_id+"]").attr("selected",true);
            // $($topic).find("select[name=topicBlabelName] option[value="+topic_b_label_id+"]").attr("selected",true);
            // $($topic).find("select[name=topicClabelName] option[value="+topic_c_label_id+"]").attr("selected",true);
            // $($topic).find("select[name=topicDlabelName] option[value="+topic_d_label_id+"]").attr("selected",true);
            // $($topic).find("input[name=topicAlabelId]").val(topic_a_label_id);
            // $($topic).find("input[name=topicBlabelId]").val(topic_b_label_id);
            // $($topic).find("input[name=topicClabelId]").val(topic_c_label_id);
            // $($topic).find("input[name=topicDlabelId]").val(topic_d_label_id);
            $("select[name='xxxx'] option[value='xxx']").attr("selected","selected");
            var rightAnswer = val.right_answer;
            if(rightAnswer){
                rightAnswer = rightAnswer.split(",");
                $.each(rightAnswer,function(i,val){
                    if(val){
                        switch(val){
                            case 'A':
                                $topic.find("input[name=topicAvalueCheck]").attr("checked","checked").val(1);
                                break;
                            case 'B':
                                $topic.find("input[name=topicBvalueCheck]").attr("checked","checked").val(1);
                                break;
                            case 'C':
                                $topic.find("input[name=topicCvalueCheck]").attr("checked","checked").val(1);
                                break;
                            default :
                                $topic.find("input[name=topicDvalueCheck]").attr("checked","checked").val(1);
                                break;
                        }
                    }
                });
            }



        });

    }



// 设置下拉树的默认值
    function setDefaultDbSet(idTag,course_type_id, type_name) {
        // 设置默认值
        var t = $(idTag).find('#courseTypeId').combotree('tree');
        var defNode = t.tree("find", course_type_id);
        if (!defNode) {
            t.tree('append', {
                data: [{
                    id: course_type_id,
                    text: type_name
                }]
            });
            defNode = t.tree("find", course_type_id);
            t.tree('select', defNode.target);
            defNode.target.style.display = 'none';
        }
        $(idTag).find('#courseTypeId').combotree('setValue', course_type_id);
    }
    function del(ids){ //删除考卷
        Modal.confirm({msg: "确认删除吗？",
            title: "提示",
            btnok: "确定",
            btncl: "取消"
        }).on( function (e) {
            if(e){// 如果点击了确定，执行删除操作
                if(ids){
                    ids = ids.toString().split(",");
                    $.each(ids,function(i,id){
                        console.log("id：" + id);
                        if(id){
                            $.ajax({
                                async: false,
                                url: base + "topic/delCourseTopicExam?courseTopicExaminationId=" + id,
                                dataType: "json",
                                success: function (data) {
                                    if(data==1){
                                        Modal.alert({msg: "删除成功！", title: "提示", btnok: "确定", btncl: "取消"});
                                    }else{
                                        Modal.alert({msg: "删除失败！", title: "提示", btnok: "确定", btncl: "取消"});
                                    }
                                }
                            });
                        }
                    });

                }else{
                    Modal.alert({msg: "至少选择一个考题！", title: "提示", btnok: "确定", btncl: "取消"});
                }
                jQuery(grid_selector).trigger("reloadGrid");
            }
        });
    }

    //添加考题
    $("#add-form-topic #addTopic").click(function () {
        ++topicIndex;
        //记录考题题号
        $("#topicTemplate").find("#topicNo").val(topicIndex);
        $("#topicTemplate").find(".topicIndex").text(topicIndex);
        var $topic = $($("#topicTemplate").prop("outerHTML")); //$("#topicTemplate").clone(true);
        $("#add-form-topic").append( $topic.css({"display":"block"}).prop("id","topic").addClass("topic topicIndex" + topicIndex));
        //初始化学习标签下拉框
        intTopicLabelSelect($(".topicIndex" + topicIndex));
    });
    //添加考题
    $("#edit-form-topic #addTopic").click(function () {
        ++topicIndex;
        //记录考题题号
        $("#topicTemplate").find("#topicNo").val(topicIndex);
        $("#topicTemplate").find(".topicIndex").text(topicIndex);
        var $topic = $($("#topicTemplate").prop("outerHTML")); //$("#topicTemplate").clone(true);
        $("#edit-form-topic").append( $topic.css({"display":"block"}).prop("id","topic").addClass("topic topicIndex" + topicIndex));
        //初始化学习标签下拉框
        intTopicLabelSelect($(".topicIndex" + topicIndex));
    });
    //选择考题类型
   $("#add-form-topic input[name=isRegisterTopic]").change(function () {
        if($(this).is(":checked") && $(this).val() == 0){
            //如果是注册考题，课程分类与课程下拉框失效
            $("#add-form-topic").find("#courseList").attr("disabled",true);
            $("#add-form-topic").find("#courseTypeId").combotree({ disabled: true });
            hide("#add-form-topic");
        }else{
            $("#courseList").attr("disabled",false);
            $("#courseTypeId").combotree({ disabled: false });
            show("#add-form-topic");
        }
    });
    //选择考题类型
    $("#ediot-form-topic input[name=isRegisterTopic]").change(function () {
        if($(this).is(":checked") && $(this).val() == 0){
            //如果是注册考题，课程分类与课程下拉框失效
            //如果是注册考题，课程分类与课程下拉框失效
            $("#ediot-form-topic").find("#courseList").attr("disabled",true);
            $("#ediot-form-topic").find("#courseTypeId").combotree({ disabled: true });
            hide("#ediot-form-topic");
        }else{
            $("#ediot-form-topic").find("#courseList").attr("disabled",false);
            $("#ediot-form-topic").find("#courseTypeId").combotree({ disabled: false });
            show("#ediot-form-topic");
        }
    });
    //选择考题答案
    $(document).on("change",".topic input[type=checkbox]",function(){
        if($(this).is(":checked")){
            $(this).val(1);
        }else{
            $(this).val(0);
        }
        //展示正确答案
        $(this).parents(".topic").find("#rightAnwer").text(showRightAnswer($(this).parents(".topic")));
    });

    //选择学习标签
    $(document).on("change",".topic .topicLabel",function(){
       var studyLabelId =  $(this).children('option:selected').val();
       $(this).next().val(studyLabelId);
    });
    //选择课程
    $(document).on("change","#add-form-topic .courseList",function(){
        var courseId =  $(this).children('option:selected').val();
        $(this).next().val(courseId);
    });

    //添加考卷
    $("#addTopics").bind("click",function () {
        var course_topic_examination_id =  $("#add-form-topic").find("input[name=course_topic_examination_id]").val();
        var examinationName =  $("#add-form-topic").find("#examinationName").val();
        if(!examinationName){
            alert("考卷名称必填!");
            return false;
        }
        var isRegisterTopic = $("#add-form-topic").find('input[name="isRegisterTopic"]:checked ').val();
        var courseId =$("#add-form-topic").find("#courseId").val();
        var courseTypeId = $("#add-form-topic").find("input[name=courseTypeId]").val();
        if( isRegisterTopic == 1){
            if(!courseTypeId){
                Modal.alert({msg: "该课程类别下无课程，请重新选择类别", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }
            if(!courseId){
                Modal.alert({msg: "课程必填!", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }


        }
        if($("#add-form-topic").find(".topic").length == 0){
            Modal.alert({msg: "请添加考题", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }

        //考卷内容
        var exam={};
        var topics=[];
        var flag=false;
       $("#add-form-topic").find(".topic").each(function() {//作废只能拿到一道题
            var course_topic_id =$(this).find("input[name=course_topic_id]").val();
            var  $obj = $(this); //$obj替换$(this)
            var topic = {};
            var topicNo = $obj.find("#topicNo").val();
            var topicName = $obj.find("#topicName").val();
            if (!topicName) {
                alert("考题名称必填");
                flag = true;
                return false;
            }
            var topicAvalue = $obj.find("#topicAvalue").val();
            var topicBvalue = $obj.find("#topicBvalue").val();
            var topicCvalue = $obj.find("#topicCvalue").val();
            var topicDvalue = $obj.find("#topicDvalue").val();
            // var topicAlabelId = $obj.find("input[name=topicAlabelId]").val();
            // var topicBlabelId = $obj.find("input[name=topicBlabelId]").val();
            // var topicClabelId = $obj.find("input[name=topicClabelId]").val();
            // var topicDlabelId = $obj.find("input[name=topicDlabelId]").val();
            if (!topicAvalue) {
                alert("第" + topicIndex + "题A选项值必填");
                flag = true;
                return false;
            }
            // if (!topicAlabelId) {
            //     flag = true;
            //     alert("第" + topicIndex + "题A选项学习标签必选");
            //     return false;
            // }
            if (!topicBvalue) {
                alert("第" + topicIndex + "题B选项值必填");
                flag = true;
                return false;
            }
            // if (!topicBlabelId) {
            //     flag = true;
            //     alert("第" + topicIndex + "题B选项学习标签必选");
            //     return false;
            // }
            if (!topicCvalue) {
                alert("第" + topicIndex + "题C选项值必填");
                flag = true;
                return false;
            }
            // if (!topicClabelId) {
            //     flag = true;
            //     alert("第" + topicIndex + "题C选项学习标签必选");
            //     return false;
            // }
            if (!topicDvalue) {
                alert("第" + topicIndex + "题D选项值必填");
                flag = true;
                return false;
            }
            // if (!topicDlabelId) {
            //     flag = true;
            //     alert("第" + topicIndex + "题D选项学习标签必选");
            //     return false;
            // }
            var rightAnwer = $(this).find("#rightAnwer").text();
            if (!rightAnwer) {
                flag = true;
                alert("第" + topicIndex + "题,至少设置一个答案");
                return false;
            }
            topic["topicName"] = topicName;
            topic["topicAvalue"] = topicAvalue;
            topic["topicBvalue"] = topicBvalue;
            topic["topicCvalue"] = topicCvalue;
            topic["topicDvalue"] = topicDvalue;
            // topic["topicAlabelId"] = topicAlabelId;
            // topic["topicBlabelId"] = topicBlabelId;
            // topic["topicClabelId"] = topicClabelId;
            // topic["topicDlabelId"] = topicDlabelId;
            topic["rightAnwer"] = rightAnwer;
            topic["topicNo"] = topicNo;
            if(course_topic_id){topic["course_topic_id"] = course_topic_id;}
            topics.push(topic);
        });
        if(flag){
            return false;
        }
        exam["examinationName"] = examinationName;
        exam["isRegisterTopic"] = isRegisterTopic;
        exam["courseId"] = courseId;
        exam["topics"] = topics;
        if(course_topic_examination_id){exam["course_topic_examination_id"] = course_topic_examination_id;}
        submitAddTopics(exam);
    });
    //提交考卷与考题数据
    function submitAddTopics(exam){
        var url =base + "topic/insertTopics";
        exam = JSON.stringify(exam);
        $.ajax({
            async: false,
            type: "POST",
            url:url,
            data: {"data":exam},
            dataType: "json",
            success: function (data) {
                if(data==1){
                        $("#addModal-type").modal("hide");
                        Modal.alert({msg: "添加成功！", title: "提示", btnok: "确定", btncl: "取消"});
                }else{
                        $("#addModal-type").modal("hide");
                        Modal.alert({msg: "添加失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
                jQuery(grid_selector).trigger("reloadGrid");


            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
            }
        });
    }

    //修改考卷
    $("#editTopics").bind("click",function () {
        var course_topic_examination_id =  $("#edit-form-topic").find("input[name=course_topic_examination_id]").val();
        var examinationName =  $("#edit-form-topic").find("#examinationName").val();
        if(!examinationName){
            alert("考卷名称必填!");
            return false;
        }
        var isRegisterTopic = $("#edit-form-topic").find('input[name="isRegisterTopic"]:checked ').val();
        var courseId =$("#edit-form-topic").find("#courseId").val();
        var courseTypeId = $("#edit-form-topic").find("input[name=courseTypeId]").val();
        if( isRegisterTopic == 1){
            if(!courseTypeId){
                Modal.alert({msg: "该课程类别下无课程，请重新选择类别", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }
            if(!courseId){
                Modal.alert({msg: "课程必填!", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }


        }
        if($("#edit-form-topic").find(".topic").length == 0){
            Modal.alert({msg: "请添加考题", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }

        //考卷内容
        var exam={};
        var topics=[];
        var flag=false;
        $("#edit-form-topic").find(".topic").each(function() {//作废只能拿到一道题
            var course_topic_id =$(this).find("input[name=course_topic_id]").val();
            var id =$(this).find("input[name=topic_id]").val();
            var  $obj = $(this); //$obj替换$(this)
            var topic = {};
            var topicNo = $obj.find("#topicNo").val();
            var topicName = $obj.find("#topicName").val();
            if (!topicName) {
                alert("考题名称必填");
                flag = true;
                return false;
            }
            var topicAvalue = $obj.find("#topicAvalue").val();
            var topicBvalue = $obj.find("#topicBvalue").val();
            var topicCvalue = $obj.find("#topicCvalue").val();
            var topicDvalue = $obj.find("#topicDvalue").val();
            // var topicAlabelId = $obj.find("input[name=topicAlabelId]").val();
            // var topicBlabelId = $obj.find("input[name=topicBlabelId]").val();
            // var topicClabelId = $obj.find("input[name=topicClabelId]").val();
            // var topicDlabelId = $obj.find("input[name=topicDlabelId]").val();
            if (!topicAvalue) {
                alert("第" + topicIndex + "题A选项值必填");
                flag = true;
                return false;
            }
            // if (!topicAlabelId) {
            //     flag = true;
            //     alert("第" + topicIndex + "题A选项学习标签必选");
            //     return false;
            // }
            if (!topicBvalue) {
                alert("第" + topicIndex + "题B选项值必填");
                flag = true;
                return false;
            }
            // if (!topicBlabelId) {
            //     flag = true;
            //     alert("第" + topicIndex + "题B选项学习标签必选");
            //     return false;
            // }
            if (!topicCvalue) {
                alert("第" + topicIndex + "题C选项值必填");
                flag = true;
                return false;
            }
            // if (!topicClabelId) {
            //     flag = true;
            //     alert("第" + topicIndex + "题C选项学习标签必选");
            //     return false;
            // }
            if (!topicDvalue) {
                alert("第" + topicIndex + "题D选项值必填");
                flag = true;
                return false;
            }
            // if (!topicDlabelId) {
            //     flag = true;
            //     alert("第" + topicIndex + "题D选项学习标签必选");
            //     return false;
            // }
            var rightAnwer = $(this).find("#rightAnwer").text();
            if (!rightAnwer) {
                flag = true;
                alert("第" + topicIndex + "题,至少设置一个答案");
                return false;
            }
            // var topicAvalueCheck =  $("this").find("input[name=topicAvalueCheck]").val();
            // var topicBvalueCheck =  $("this").find("input[name=topicBvalueCheck]").val();
            // var topicCvalueCheck =  $("this").find("input[name=topicCvalueCheck]").val();
            // var topicDvalueCheck =  $("this").find("input[name=topicDvalueCheck]").val();
            topic["topicName"] = topicName;
            topic["topicAvalue"] = topicAvalue;
            topic["topicBvalue"] = topicBvalue;
            topic["topicCvalue"] = topicCvalue;
            topic["topicDvalue"] = topicDvalue;
            // topic["topicAlabelId"] = topicAlabelId;
            // topic["topicBlabelId"] = topicBlabelId;
            // topic["topicClabelId"] = topicClabelId;
            // topic["topicDlabelId"] = topicDlabelId;
            topic["rightAnwer"] = rightAnwer;
            topic["topicNo"] = topicNo;
            topic["id"] =id;
            if(course_topic_id){topic["course_topic_id"] = course_topic_id;}
            topics.push(topic);
        });
        if(flag){
            return false;
        }
        exam["examinationName"] = examinationName;
        exam["isRegisterTopic"] = isRegisterTopic;
        exam["courseId"] = courseId;
        exam["topics"] = topics;
        if(course_topic_examination_id){exam["course_topic_examination_id"] = course_topic_examination_id;}
        submitUpdateTopics(exam);
    });
    //提交考卷与考题数据
    function submitUpdateTopics(exam){
        var url =base + "topic/updateTopics";
        exam = JSON.stringify(exam);
        $.ajax({
            async: false,
            type: "POST",
            url:url,
            data: {"data":exam},
            dataType: "json",
            success: function (data) {
                if(data==1){
                       $("#editModal-type").modal("hide");
                        Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                }else{
                    $("#editModal-type").modal("hide");
                    Modal.alert({msg: "修改失败！", title: "提示", btnok: "确定", btncl: "取消"});

                }
                jQuery(grid_selector).trigger("reloadGrid");
            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
            }
        });
    }

    //正确答案
    function showRightAnswer($obj){
        var rightAnswer="";
        $obj.find('input[type=checkbox]:checked ').each(function(){
            var key = $(this).prop("id");
            switch(key){
                case "topicAvalueCheck":
                    rightAnswer += 'A,';
                    break;
                case "topicBvalueCheck":
                    rightAnswer += 'B,';
                    break;
                case "topicCvalueCheck":
                    rightAnswer += 'C,';
                    break;
                default:
                    rightAnswer += 'D,';
                    break;
            }
        });
        return rightAnswer;
    }

    //初始化课程下拉框
    function initCourseListByTypeId(idTag,courseTypeId){
        $(idTag).find("#courseList").empty();
        $(idTag).find("#courseList").append( '<option value="">--课程名称--</option>');
        $.ajax({
            async: false,
            type: "POST",
            url: base + "topic/getCourseListByCourseTypeId",
            data: {'courseTypeId': courseTypeId},
            dataType: "json",
            success: function (data) {
                if(data){
                    $.each(data, function(i,val){
                        $(idTag).find("#courseList").append('<option value="'+val.course_id+'">'+val.course_title+'</option>');
                        if(val.course_type_id == courseTypeId){
                            $(idTag).find("select[name=courseList] option[value="+val.course_id+"]").attr("selected",true);
                        }
                        $(idTag).find("input[name=courseId]").val(val.course_id);
                    });

                }
            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
            }
        });
    }

    //初始化课程类别easyui树
    function initCourseTypeCombotree(idTag){
        $(idTag).find('#courseTypeId').combotree ({
            url: base + 'courseType/searchCtByParentId?parentId=-1',
            onBeforeExpand:function(node) {
                $(idTag).find('#courseTypeId').combotree("tree").tree("options").url = base + 'courseType/searchCtByParentId?parentId=' + node.id;
            },
            onClick:function(node){
                // 查询该节点分类下的课程列表
                initCourseListByTypeId(idTag,node.id);
                var isRegisterTopic = $("#add-form-topic").find('input[name="isRegisterTopic"]:checked ').val();
                var courseTypeId = $("#add-form-topic").find("input[name=courseTypeId]").val();
                var courseId =$("#courseId").val();
                if(!courseId  && (isRegisterTopic ==1)){
                    if(courseTypeId){//如果课程类别找不到课程
                      //  alert("该课程类别下无课程，请重新选择类别");
                       // $(".panel combo-p").css({"display":"block"})
                       // $(this).tree('expandAll',node.target);//展开所有节点
                       // $("#add-form-topic").find('#courseTypeId').combotree('tree').tree("expandAll");
                        return false;

                    }
                }
            }, //选择树节点触发事件
            onSelect : function(node) {
                //返回树对象
                var tree = $(this).tree;
                //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
                var isLeaf = tree('isLeaf', node.target);
                if (!isLeaf) {
                    Modal.alert({msg: "请选择二级分类", title: "提示", btnok: "确定", btncl: "取消"});
                    //清除选中
                    $(idTag).find('#courseTypeId').combotree('clear');
                    return false;
                }
            }
        });
    }


});

function detail(id){
    //根据courseTopicExaminationId查询考卷与课程信息
    var examDetailJson = getExamDetailCourseTopicExamId(id);
    $("#detailModal-type").modal("show");//打开模态窗口
    $("#detailModal-type").find("#examinationName").val(examDetailJson.examinationName);
    removeTopics("#detailModal-type");
    var isRegisterTopic  =examDetailJson.isRegisterTopic;
    if(isRegisterTopic == 0){
        $("#detailModal-type").find('input[name="isRegisterTopic"][type="radio"]').first().attr("checked",true);
        $("#detailModal-type").find('input[name="isRegisterTopic"][type="radio"]').last().attr("disabled","disabled");
        $("#detailModal-type").find("#courseName").parents(".form-group").hide();
        $("#detailModal-type").find("#courseName").parents(".form-group").next().hide();
    }else{
        $("#detailModal-form-topic").find('input[name="isRegisterTopic"][type="radio"]').last().attr("checked",true);
        $("#detailModal-type").find('input[name="isRegisterTopic"][type="radio"]').first().attr("disabled","disabled");
        $("#detailModal-type").find("#courseName").parents(".form-group").show();
        $("#detailModal-type").find("#courseName").parents(".form-group").next().show();
        $("#detailModal-type").find("#courseName").val(examDetailJson.courseName);
    }
    var topicMaps = examDetailJson.topicMaps;
    $.each(topicMaps,function (i,val) {
        var $topic =$("#topicTemplate").clone(true).css({"display":"block"}).prop("id","topic").addClass("topic topicIndex" + val.topic_no);
        $topic.find("#topicNo").val(val.topic_no);
        $("#detail-form-topic").append($topic );
        $topic.find(".topicIndex").text(val.topic_no);
        //初始化学习标签下拉框
        intTopicLabelSelect($topic);
        $topic.find("input[name=topicName]").attr("readonly","readonly").val(val.topic_name);
        $topic.find("input[name=topicAvalue]").attr("readonly","readonly").val(val.topic_a_value);
        $topic.find("input[name=topicBvalue]").attr("readonly","readonly").val(val.topic_b_value);
        $topic.find("input[name=topicCvalue]").attr("readonly","readonly").val(val.topic_c_value);
        $topic.find("input[name=topicDvalue]").attr("readonly","readonly").val(val.topic_d_value);
        $topic.find("#rightAnwer").text(val.right_answer);

        // var topic_a_label_id = val.topic_a_label_id;
        // var topic_b_label_id = val.topic_b_label_id
        // var topic_c_label_id = val.topic_c_label_id;
        // var topic_d_label_id = val.topic_d_label_id;
        // $($topic).find("select[name=topicAlabelName] option[value="+topic_a_label_id+"]").attr("selected",true);
        // $($topic).find("select[name=topicBlabelName] option[value="+topic_b_label_id+"]").attr("selected",true);
        // $($topic).find("select[name=topicClabelName] option[value="+topic_c_label_id+"]").attr("selected",true);
        // $($topic).find("select[name=topicDlabelName] option[value="+topic_d_label_id+"]").attr("selected",true);
        var rightAnswer = val.right_answer;
        if(rightAnswer){
            rightAnswer = rightAnswer.split(",");
            $.each(rightAnswer,function(i,val){
                if(val){
                    switch(val){
                        case 'A':
                            $topic.find("input[name=topicAvalueCheck]").attr("checked","checked").val(1);
                            break;
                        case 'B':
                            $topic.find("input[name=topicBvalueCheck]").attr("checked","checked").val(1);
                            break;
                        case 'C':
                            $topic.find("input[name=topicCvalueCheck]").attr("checked","checked").val(1);
                            break;
                        default :
                            $topic.find("input[name=topicDvalueCheck]").attr("checked","checked").val(1);
                            break;
                    }
                }
            });

            $topic.find("input[type=checkbox]").not("input:checked").attr("disabled","disabled");
        }



    });
}


function getExamDetailCourseTopicExamId(courseTopicExaminationId){
    var dataJson ;
    $.ajax({
        async: false,
        type: "POST",
        url: base + "topic/getExamDetailCourseTopicExamId",
        data: {"courseTopicExaminationId":courseTopicExaminationId},
        dataType: "json",
        success: function (data) {
            dataJson =data;
        },
        error: function (data) {
            dataJson =data;
            Modal.alert({msg: "系统异常，请稍后再试!", title: "提示", btnok: "确定", btncl: "取消"});
            jQuery(grid_selector).trigger("reloadGrid");
        }
    });
    return dataJson;
}



//初始化考题学习标签下拉框
function intTopicLabelSelect($obj){
    $obj.find(".topicLabel").empty();
    $obj.find(".topicLabel").append( '<option value="">--学习标签--</option>');
    $.ajax({
        async: false,
        type: "POST",
        url: base + "topic/getStudyLabels",
        data: {},
        dataType: "json",
        success: function (data) {
            if(data){
                $.each(data, function(i,val){
                    $obj.find(".topicLabel").append('<option value="'+val.label_id+'">'+val.labelname+'</option>');
                });
            }
        },
        error: function (data) {
            alert("系统异常，请稍后再试!");
        }
    });
}

//删除历史考题div
function removeTopics(idTag){
    $(idTag).find(".topic").each(function (index,element) {
        $(this).remove();
    });
}