<#assign title="广告管理"/>
<#assign menuId="29"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style>
    input[type="radio"]{width: 20px;margin-left: 20px;}
</style>

<div id="dialog"></div>

<div class="modal-header">
    <h4 class="modal-title">修改广告</h4>
</div>
<div class="modal-body" style="height: 380px">
    <form id="update-form-advertise" class="form-horizontal" enctype="multipart/form-data">
        <input type="hidden" id="adver_id" name="adver_id" value="${adverId}"/>
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>广告名称：</label>
            <div class="col-xs-6">
                <input type="text" id="adver_name_update" name="adverName" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>广告图片：</label>
            <div class="col-xs-6">
                <input type="file" id="adver_img_update" name="file" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label style="margin-top: -9px" class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>是否显示：</label>
            <div class="col-sm-6">
                <input type="radio" name="status" class="col-xs-12 col-sm-1" value="1"/><div style="float: left">显示</div>
                <input type="radio" name="status" class="col-xs-12 col-sm-1" value="2"/><div style="float: left">不显示</div>
            </div>
        </div>
        <div class="form-group">
            <label style="margin-top: -9px" class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>对映地方：</label>
            <div class="col-sm-6">
                <input type="radio" name="type" id="advertise_type_add" class="col-xs-12 col-sm-1" value="1" /><div style="float: left">首页</div>
                <input type="radio" name="type" class="col-xs-12 col-sm-1" value="2" /><div style="float: left">学习</div>
                <input type="radio" name="type" class="col-xs-12 col-sm-1" value="3" /><div style="float: left">商城</div>
            </div>
        </div>
        <div class="form-group" id="link_type">
            <label style="margin-top: -9px" class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>链接地址：</label>
            <div class="col-sm-6">
                <span><input type="radio" name="linkType" class="col-xs-12 col-sm-1" value="2" /><div style="float: left">教头</div></span>
                <span><input type="radio" name="linkType" class="col-xs-12 col-sm-1" value="3" /><div style="float: left">商品</div></span>
                <span><input type="radio" name="linkType" class="col-xs-12 col-sm-1" value="4" /><div style="float: left">音频</div></span>
                <span><input type="radio" name="linkType" class="col-xs-12 col-sm-1" value="5" /><div style="float: left">视频</div></span>
                <span><input type="radio" name="linkType" class="col-xs-12 col-sm-1" value="6" /><div style="float: left">直播</div></span>
                <span><input type="radio" name="linkType" class="col-xs-12 col-sm-1" value="7" /><div style="float: left">文字</div></span>
                <span><input type="radio" name="linkType" class="col-xs-12 col-sm-1" value="8" /><div style="float: left">江湖大课</div></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>相关广告：</label>
            <div class="col-xs-6">
                <select onchange="choose(this)" id="about_adv" name="about_adv" class="form-control"></select>
            </div>
        </div>
        <input type="hidden" id="name_update" name="name" /><#--名称-->
        <input type="hidden" id="detail_id" name = "detailId"><#--详情Id-->
    </form>
</div>
<div class="modal-footer" style="text-align:center;">
    <button type="button" class="btn btn-primary" id="update_advertise">保 存</button>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-default" id="backBtn">返 回</button>
</div>



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
<script type="text/javascript" src="${ctx}/UEditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/UEditor/ueditor.all.js"></script>
<script src="${ctx}/js/advertise/updateIndexAdvertise.js"></script>
<#include "../common/body_bottom.ftl"/>
