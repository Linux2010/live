<#assign title="邀请好友二维码生成器"/>
<#assign menuId="6584b89c970d44eeafd4409c60d43213"/>
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
        <div class="modal-body" style="min-height:500px;max-height:900px; ">
            <form id="form-data" class="form-horizontal">
                  <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="loginName">海报模板1：</label>
                            <div class="col-sm-9">
                                <div class="col-sm-12">
                                    <#--<div class="col-sm-6">-->
                                       <#--<input type="file" id="photo" name="file1" class="form-control"/>-->
                                    <#--</div>-->
                                    <div class="col-sm-6">
                                        <input type="hidden" id="share0" name="share0" value="${share0}"/>
                                        <a href="${share0}" target="_blank"><img src="${share0}"  alt="默认海报图片" width="50px" height="89.6px"/></a>(<small class="red">默认海报图片</small>)
                                    </div>
                                </div>
                          </div>
                   </div>
                  <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">海报模板2：</label>
                        <div class="col-sm-9">
                            <div class="col-sm-12">
                                <#--<div class="col-sm-6">-->
                                    <#--<input type="file" id="photo" name="file2" class="form-control"/>-->
                                <#--</div>-->
                                <div class="col-sm-6">
                                    <input type="hidden" id="share1" name="share1" value="${share1}"/>
                                    <a href="${share1}" target="_blank"><img src="${share1}"  alt="默认海报图片" width="50px" height="89.6px"/></a>(<small class="red">默认海报图片</small>)
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">海报模板3：</label>
                        <div class="col-sm-9">
                            <div class="col-sm-12">
                                <#--<div class="col-sm-6">-->
                                    <#--<input type="file" id="photo" name="file3" class="form-control"/>-->
                                <#--</div>-->
                                <div class="col-sm-6">
                                    <input type="hidden" id="share2" name="share2" value="${share2}"/>
                                    <a href="${share2}" target="_blank"><img src="${share2}"  alt="默认海报图片" width="50px" height="89.6px"/></a>(<small class="red">默认海报图片</small>)
                                </div>
                            </div>
                        </div>
                    </div>
                   <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">选择要分享的用户:</label>
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
    <div class="modal-footer" >
        <button type="button" class="btn btn-primary" style="margin-right: 4rem;" id="addBtn">重新二维码分享图片</button>
        <#--<button type="button" class="btn btn-primary" style="background-color: #ff0000!important;border-color: #ff0000;" id="batchQrCodes" onclick="batchQrCodes()">更新系统全部用户二维码</button>(<small class="red">警示！谨慎操作</small>)-->
        <#--<button type="button" class="btn btn-primary"  style="background-color: #ff0000!important;border-color: #ff0000;" id="batchShareQrCodeImgs"  onclick="batchShareQrCodeImgs()">批量合成系统全部用户二维码分享图片</button>(<small class="red">警示！谨慎操作</small>)-->
    </div>
</div><!-- /.modal-content -->

<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">

<#include "../common/body_bottom.ftl"/>
<script type="text/javascript">
    $(function () {
        tele_num();//国际手机区号


        $("#addBtn").click(function () {
            $("#addBtn").attr("disabled",true);
            $.ajax({
                async: true,
                url: base + "configSetting/batchQrCode",
                type: "POST",
                data: $("#form-data").serialize(),
                dataType: "json",
                success: function (data) {
                    $("#addBtn").attr("disabled",false);
                    if ("success" == data.status){
                        Modal.alert({msg:"操作成功", title: "提示", btnok: "确定", btncl: "取消"});
                    }else{
                        Modal.alert({msg: data.message, title: "提示", btnok: "确定", btncl: "取消"});
                    }
                },
                error: function (data) {
                    Modal.alert({msg:"系统异常，请稍后重试", title: "提示", btnok: "确定", btncl: "取消"});
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
    /**
     * 批量合成用户二维码
     * @return
     */
    function batchQrCodes(){
        $("#batchQrCodes").attr("disabled",true);
        $.ajax({
            async: false,
            url: base + "configSetting/batchAllUserQrCodes",
            dataType: "json",
            type:"GET",
            success: function (data) {
                if(data ==1){
                    Modal.alert({msg:"操作成功", title: "提示", btnok: "确定", btncl: "取消"});
                }else{
                    $("#batchQrCodes").attr("disabled",false);
                    Modal.alert({msg:"操作失败", title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error: function (data) {
                $("#batchQrCodes").attr("disabled",false);
                Modal.alert({msg:"系统异常，请稍后重试", title: "提示", btnok: "确定", btncl: "取消"});
            }
        });
    }
    /**
     * 批量合成用户海报分享二维码
     * @return
     */
    function batchShareQrCodeImgs() {
        $("#batchShareQrCodeImgs").attr("disabled",true);
        $.ajax({
            async: false,
            url: base + "configSetting/batchAllUserShareQrCodeImgs",
            dataType: "json",
            data: $("#form-data").serialize(),
            type:"GET",
            success: function (data) {
               if(data ==1){
                   Modal.alert({msg:"操作成功", title: "提示", btnok: "确定", btncl: "取消"});
               }else{
                   Modal.alert({msg:"操作失败", title: "提示", btnok: "确定", btncl: "取消"});
                   $("#batchQrCodes").attr("disabled",false);
               }
            },
            error: function (data) {
                $("#batchQrCodes").attr("disabled",false);
                    Modal.alert({msg:"系统异常，请稍后重试", title: "提示", btnok: "确定", btncl: "取消"});
            }
        });
    }
</script>

<#--add-end-->

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
