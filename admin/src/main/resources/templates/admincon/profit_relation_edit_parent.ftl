<#assign title="修改上级分润关系"/>
<#assign menuId="5a5d244650e24e69b273aecc88368dba"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" xmlns="http://www.w3.org/1999/html"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/bootstrap-select.css">
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="modal-content">
    <div class="modal-header">
        <button type="button" onclick="back()"><i class="fa fa-arrow-left"></i></button>
    </div>
    <#if relationIndex??>
        <div class="modal-body" style="max-height:900px; ">
            <form id="form-data" class="form-horizontal">
                <input type="hidden" name="userId" id="userId" value="${relationIndex.userId!''}"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="loginName">用户级别：</label>
                    <div class="col-sm-9">
                    <input type="text" class="col-xs-10 col-sm-3"
                        <#if level??>
                            <#if level =="1" >   value="一级分润用户" </#if>
                            <#if level =="2" >   value="二级分润用户" </#if>
                            <#if level =="3" >   value="三级分润用户" </#if>
                        </#if>
                           readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="loginName">用户名称：</label>
                    <div class="col-sm-9">
                        <input type="text" class="col-xs-10 col-sm-3"  value="${relationIndex.userName!''}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="loginName">选择上级分润用户手机号:</label>
                    <div class="col-sm-9">
                        <select  class="form-control courseList col-xs-2 col-sm-2"  name="tele_num"  id="tele_num" style="width:8.5%">
                           <option value="">--选择国家--</option>
                        </select>
                        <input type="text" id="phone" name="phone" placeholder="上级分润用户手机号" class="col-xs-12 col-sm-4"  value="" onblur="" />
                        <input type="hidden" id="countryCode" name="countryCode" value=""/>
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
<script type="text/javascript" src="${ctx}/assets/js/bootstrap-select.js"></script>

<#include "../common/body_bottom.ftl"/>
<script type="text/javascript">
    $(function () {
        tele_num();//国际手机区号
        $("#addBtn").click(function () {
            var countryCode =$("#countryCode").val();
            var phone = $("input[name=phone]").val();
            if(!countryCode){
                Modal.alert({msg: "国际区号必选", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }
            if(!phone){
                Modal.alert({msg: "讲师电话必填", title: "提示", btnok: "确定", btncl: "取消"});
                return false;
            }
            $.ajax({
                async: false,
                url: base + "profitshare/relation/updateParentRaltion",
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


    });


    function tele_num(){
        var codeList;
        $.ajax({
            async: false,
            url: base + "user/get_countries_code_list",
            dataType: "json",
            type:"GET",
            success: function (data) {
                codeList = data;
            },
            error: function (data) {
                codeList = data;
            }
        });
        $.each(codeList,function(i,val){
            var selected = "";
            if(val.regionName === '中国'){
                selected ="selected=\"selected\"";
                $("#countryCode").val(val.international);
            }
            $("#tele_num").append('<option value="'+val.international.replace("+","")+'" '+ selected+' >'+val.regionName+'</option>');
        });
    }
</script>
