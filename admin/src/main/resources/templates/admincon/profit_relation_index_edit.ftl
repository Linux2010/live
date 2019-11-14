<#assign title="修改一级分润"/>
<#assign menuId="5a5d244650e24e69b273aecc88368dba"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" xmlns="http://www.w3.org/1999/html"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="modal-content">
    <div class="modal-header">
        <button type="button" onclick="back()"><i class="fa fa-arrow-left"></i></button>
    </div>
    <#if relationIndex??>
        <div class="modal-body" style="max-height:600px; overflow:scroll;">
            <form id="form-data" class="form-horizontal">
                <input type="hidden" name="relationId" value="${relationIndex.relationId!''}"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="loginName">用户名</label>
                    <div class="col-sm-9">
                        <input type="text" class="col-xs-10 col-sm-3"  value="${relationIndex.userName!''}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="loginName">序号</label>
                    <div class="col-sm-9">
                        <div class="ace-spinner middle touch-spinner" style="width: 140px;">
                            <div class="input-group">
                                <input type="text" class="input-sm spinbox-input form-control text-center" id="spinner2" name="no" value="${relationIndex.no!''}">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </#if>
    <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="addBtn">保  存</button>
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
<script src="${ctx}/assets/js/spinbox.min.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>

<#include "../common/body_bottom.ftl"/>
<script type="text/javascript">

    $("#addBtn").click(function () {
        $.ajax({
            async: true,
            url: base + "profitshare/relation/updateRelation",
            type: "POST",
            data: $("#form-data").serialize(),
            dataType: "json",
            success: function (data) {
                if ("success" == data.status){
                    Modal.alert({msg:"操作成功", title: "提示", btnok: "确定", btncl: "取消"});
                    location.href = base + 'profitshare/relation/';
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
        location.href = base + 'profitshare/relation/';
    }

    $('#spinner2').ace_spinner({value:1,min:1,max:10000,step:1, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up bigger-110', icon_down:'ace-icon fa fa-caret-down bigger-110'});
</script>
