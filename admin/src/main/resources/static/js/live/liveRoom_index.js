
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
        var keyword=$("#form-search").find("input[name=keyword]").val();
        var phone=$("#form-search").find("#phone").val();
        var status  = $("#form-search").find("input[type='radio'][name=status]:checked").val();
        $("#grid-table").jqGrid('setGridParam',{
            postData:{"keyword":encodeURI(keyword),"phone":phone,"status":status},
        }).trigger('reloadGrid');
    });

    jQuery(grid_selector).jqGrid({
        url:base+ "liveRoom/getLiveRoomsPage",
        subGrid: false,
        datatype: "json",
        postData:{status:1},//0：已关闭;1:已开启 status:"1"
        height: 'auto',
        colNames: ['id', '直播间名称','聊天室名称','讲师名称','注册时间','操作'],
        colModel:[
            {name: 'room_id', index: 'room_id', width: 50, editable: true, hidden: true, key: true},
            {name: 'room_name', index: 'room_name', width: 100},
            {name: 'chat_room_name', index: 'room_name', width: 100},
            {name: 'user_name', index: 'user_name', width: 100},
            {name: 'ctime', index: 'ctime', width: 150,formatter:function (cellvalue, options, rowObject){
                if(typeof(cellvalue) != "undefined"){
                    return new Date(cellvalue).pattern("yyyy-MM-dd HH:mm:ss");
                }else{
                    return '';
                }
            }},
            {name:'id',index:'id', width:90, fixed:true, sortable:false, resize:false,
                formatter: function(cellvalue, options, rowObject) {
                   return '<a title="编辑" href="javascript:void(0);" ' +
                    'onclick="edit(\'' + rowObject.room_id + '\')" > ' +
                    '<i class="ace-icon fa fa-pencil-square-o bigger-200"></i></a>';
                }
            }
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
        autowidth: true
    });

    $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:0px solid #A6C9E2; padding: 0.5em;' mce_style='margin:0.3em; background:#FFFFFF; border:0px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";
    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: true,
            edittext:'编辑',
            editfunc : edit,
            edititle:"编辑直播间",
            add: true,
            addtext:'添加',
            addtitle: "添加直播间",
            addicon: 'ace-icon fa fa-plus-circle purple',
            addfunc : add,
            del: false,
            deltext:'删除',
            delfunc : del,
            search: false,
            refresh: true,
            refreshicon: 'ace-icon fa fa-refresh yellow',
            alertcap:'提示',
            alerttext : alertText
        }
    )
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

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

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
    //选择讲师记录讲师ID
    $("#add-form-user").find("#teacherList").change(function(){
        $("#add-form-user").find("#userId").val($(this).find("option:checked").val());
    });
    $("#adduser").click(function(){
        var data={};
        var $obj = $("#add-form-user");
        var userId = $obj.find("input[name=userId]").val();
        //此处判断用户是否有直播间账号
        var liveRoomJson =selectTeacherLiveRoom(userId);
        if(liveRoomJson && liveRoomJson.roomId ){
            Modal.alert({msg: "该教头已开通直播间,请选择其他教头", title: "提示", btnok: "确定", btncl: "取消"});
            //清除选中
            $(idTag).find('#userTypeId').combotree('clear');
            return false;
        }
        var roomName = $obj.find("input[name=roomName]").val();
        var onlineusercount = $obj.find("input[name=onlineusercount]").val();
       if(!userId){
           Modal.alert({msg: "讲师必选", title: "提示", btnok: "确定", btncl: "取消"});
           return false;
       }
       if(!roomName){
           Modal.alert({msg: "直播间名称必填", title: "提示", btnok: "确定", btncl: "取消"});
           return false;
       }
       if(!onlineusercount){
            Modal.alert({msg: "在线人数必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
       }
       data["userId"]=userId;
       data["roomName"]=roomName;
       data["onlineusercount"]=onlineusercount;
       data = JSON.stringify(data);
       $.ajax({
            async: false,
            url: base + "liveRoom/insertLiveRoom",
            dataType: "json",
           type:"POST",
            data: {"data":data},
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

    });

    $("#updateUser").click(function(){
        var data={};
        var $obj = $("#update-form-user");
        var roomName = $obj.find("input[name=roomName]").val();
        var onlineusercount = $obj.find("input[name=onlineusercount]").val();
        var roomId = $obj.find("input[name=roomId][type=hidden]").val();
        var status =$obj.find("input[name=status]:checked").val();
        var cid = $obj.find("input[name=cid][type=hidden]").val();
        if(!roomName){
            Modal.alert({msg: "直播间名称必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        if(!onlineusercount){
            Modal.alert({msg: "在线人数必填", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
        data["status"]=status;
        data["roomId"]=roomId;
        data["roomName"]=roomName;
        data["onlineusercount"]=onlineusercount;
        data["cid"]=cid;

        data = JSON.stringify(data);
        $.ajax({
            async: false,
            url: base + "liveRoom/updateLiveRoom",
            dataType: "json",
            type:"POST",
            data: {"data":data},
            success: function (data) {
                if(data==1){
                    $("#updateModal-type").modal("hide");
                    Modal.alert({msg: "修改成功！", title: "提示", btnok: "确定", btncl: "取消"});
                }else{
                    $("#updateModal-type").modal("hide");
                    Modal.alert({msg: "修改失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
                jQuery(grid_selector).trigger("reloadGrid");
            },
            error: function (data) {
                alert("系统异常，请稍后再试!");
            }
        });

    });
});

function selectTeacherLiveRoom(userId){
    var liveRoomJson;
    $.ajax({
        async: false,
        url: base + "liveRoom/selectTeacherLiveRoom",
        dataType: "json",
        type:"POST",
        data: {"userId":userId},
        success: function (data) {
            liveRoomJson = data;
        },
        error: function (data) {
            liveRoomJson = data;
        }
    });
    return liveRoomJson;
}

//初始化教师分类comboTree
function initTeacherTypeComboTree(idTag){
    $(idTag).find('#userTypeId').combotree ({
        url: base + 'userType/searchUserTypeByParentId?parentId=-1',
        onBeforeExpand:function(node) {
            $('#userTypeId').combotree("tree").tree("options").url = base + 'userType/searchUserTypeByParentId?parentId=' + node.id;
        },
        onClick:function(node){
            var jsonArray = selectTeacherUserByUserTypeId( node.id);
            $("#teacherList").empty();
            if(jsonArray && jsonArray.length > 0){
                $.each(jsonArray,function(i,val){
                    if(i == 0){
                        $("#userId").val(val.user_id);
                    }
                    $("#teacherList").append(' <option value="'+val.user_id+'">'+val.user_name+'</option>');
                });
            }
        },//选择树节点触发事件
        onSelect : function(node) {
            var jsonArray = selectTeacherUserByUserTypeId( node.id);
            $("#teacherList").empty();
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                Modal.alert({msg: "请选择二级分类", title: "提示", btnok: "确定", btncl: "取消"});
                //清除选中
                $(idTag).find('#userTypeId').combotree('clear');
                return false;
            }else{
                if(!jsonArray || jsonArray.length == 0){
                    //清除选中
                    $(idTag).find('#userTypeId').combotree('clear');
                    Modal.alert({msg: "该类别没有讲师！", title: "提示", btnok: "确定", btncl: "取消"});
                }
            }
        }
    });
}

function selectTeacherUserByUserTypeId(userTypeId){
    var jsonArray;
    $.ajax({
        async: false,
        url: base + "user/selectTeacherUserByUserTypeId",
        dataType: "json",
        type:"POST",
        data: {"userTypeId":userTypeId},
        success: function (data) {
            jsonArray = data;
        },
        error: function (data) {
            jsonArray = data;
            alert("系统异常，请稍后再试!");
        }
    });
    return jsonArray;
}

function add(){
    //重置form表单
    $("#addModal-type").find("form")[0].reset();
    $("#addModal-type").modal("show");//打开模态窗口
    initTeacherTypeComboTree( "#addModal-type");
    $("#teacherList").empty();
}

function edit(id){
    var ids=$(grid_selector).jqGrid('getGridParam', "selarrrow");
    if(ids){
        if(ids.length>1){
            Modal.alert({msg: "请选择一条数据进行编辑", title: "提示", btnok: "确定", btncl: "取消"});
            return false;
        }
    }
    //重置form表单
    $("#updateModal-type").find("form")[0].reset();

    var rowData =   $(grid_selector).jqGrid('getRowData', id);

    $("#updateModal-type").modal("show");
    initUpdateForm(rowData);
}
function initUpdateForm(data){
    var $obj =$("#updateModal-type");
    //教师分类combotree默认选中
    $obj.find("input[name=roomName]").val(data.room_name);
    $obj.find("input[name=onlineusercount]").val(data.onlineusercount);
    $obj.find("input[name=roomId][type=hidden]").val(data.room_id);
    var liveRoomJson = selectLiveRoomByRoomId(data.room_id);
    $obj.find("input[name=cid][type=hidden]").val(liveRoomJson.cid);
    var status =liveRoomJson.status;
    if(status == 0){
        $obj.find("input[name=status][type=radio]").first().attr("checked","checked");
        $obj.find("input[name=status][type=radio]").last().attr("checked",false);
    }else{
        $obj.find("input[name=status][type=radio]").first().attr("checked",false);
        $obj.find("input[name=status][type=radio]").last().attr("checked","checked");
    }
}
function selectLiveRoomByRoomId(id){
    var liveRoomJson;
    $.ajax({
        async: false,
        type: "POST",
        url: base + "liveRoom/selectLiveRoomByRoomId",
        data:{roomId:id},
        dataType: "json",
        success: function (data) {
            liveRoomJson = data;
        },
        error:function(data){
            liveRoomJson = data;
            Modal.alert({msg: "系统异常，请稍后再试!", title: "提示", btnok: "确定", btncl: "取消"});
        }
    });
    return liveRoomJson;
}

function del(ids){
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
                            url: base + "liveRoom/delLiveRoomByRoomId",
                            dataType: "json",
                            data: {"roomId":id},
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
                Modal.alert({msg: "至少选择一个直播间！", title: "提示", btnok: "确定", btncl: "取消"});
            }
            jQuery(grid_selector).trigger("reloadGrid");
        }
    });
}




var userstatus = function(v){
    var sel_id = $(grid_selector).jqGrid('getGridParam', 'selrow');  //status	int	频道状态（0：空闲； 1：直播； 2：禁用； 3：直播录制）
    if (v == 1) {
        return "直播";
    } else if (v == 0) {
        return "空闲";
    }else if (v == 2) {
        return "禁播";
    } else if (v == 3) {
        return "直播录制";
    }
}
