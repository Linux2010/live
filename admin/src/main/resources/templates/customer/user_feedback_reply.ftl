<#assign title="用户反馈"/>
<#assign menuId="13cec1e18c014c65b99f2beca4f45fb1"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" xmlns="http://www.w3.org/1999/html"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/jquery.treegrid.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="modal-content">
    <div class="modal-header">
        <button type="button" onclick="back()"><i class="fa fa-arrow-left"></i></button>
    </div>
    <div class="modal-body" style="max-height:600px; overflow:scroll; ">
        <form id="form-reply" class="form-horizontal">
            <input id="userId" name="userId" type="hidden" value="${record.userId}">
            <input id="feedbackId" name="feedbackId" type="hidden" value="${record.feedbackId}">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="loginName">用户名</label>
                <div class="col-sm-9">
                    <input type="text" id="" name="" placeholder="用户名" class="col-xs-10 col-sm-5"  value="${record.userName!''}" readonly/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="loginName">姓名</label>
                <div class="col-sm-9">
                    <input type="text" id="" name=""  class="col-xs-10 col-sm-5"  value="${record.name!''}" readonly/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="loginName">手机号</label>
                <div class="col-sm-9">
                    <input type="text" id="" name="" class="col-xs-10 col-sm-5"  value="${record.phone!''}" readonly/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="loginName">反馈内容</label>
                <div class="col-sm-9">
                    <textarea  class="col-xs-10 col-sm-5"  readonly>${record.content!''}</textarea>
                </div>
            </div>

            <#if messageRecordList??>
                <#list messageRecordList as messageRecord>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">回复用户</label>
                        <div class="col-sm-9">
                            <textarea  class="col-xs-10 col-sm-5" readonly>${messageRecord.content}</textarea>
                        </div>
                    </div>
                </#list>
            </#if>


            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="loginName">回复用户</label>
                <div class="col-sm-9">
                    <textarea class="col-xs-10 col-sm-5" name="replayContent" id="replayContent"></textarea>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="addBtn">回 复</button>
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="back()">返 回</button>
    </div>
</div><!-- /.modal-content -->
<#--add-end-->
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">

<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>

<script src="${ctx}/assets/js/grid.locale-en.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script src="${ctx}/assets/js/jquery.treegrid.min.js"></script>
<#include "../common/body_bottom.ftl"/>
<script type="text/javascript">
    $("#addBtn").click(function () {
        if(isEmpty($("#replayContent").val())){
            Modal.alert({msg: "回复内容不能为空！", title: "提示", btnok: "确定", btncl: "取消"});
            return;
        }
        $.ajax({
            async: true,
            url: base + "userfeedback/addRecord",
            type: "POST",
            data: $("#form-reply").serialize(),
            dataType: "json",
            success: function (data) {
                if ("success" == data.status){
                    Modal.alert({msg: "回复成功！", title: "提示", btnok: "确定", btncl: "取消"});
                    location.reload();
                }else{
                    Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        });
    });
    var back = function(){
        location.href = base + 'userfeedback/';
    }
</script>
