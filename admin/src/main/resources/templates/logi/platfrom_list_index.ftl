<#assign title="物流平台设置"/>
<#assign menuId="f44042e90b2a498aa8cb26c0b1f65665"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<div class="col-xs-12">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog"></div>
<!-- 添加物流平台Modal -->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">设置物流平台</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="plat-form-add" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">名称 : </label>
                        <div class="col-sm-9">
                            <input type="text" id="name_id" name="platName" placeholder="名称" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">代码 : </label>
                        <div class="col-sm-9">
                            <input type="text" id="code_id" name="code" placeholder="代码" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">key : </label>
                        <div class="col-sm-9">
                            <input type="text" id="key" name="key" placeholder="key" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="margin-top: -9px;margin-left: 57px;" class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>类型 : </label>
                        <div class="col-sm-6">
                            <input type="radio" name="type" class="col-xs-12 col-sm-1" value="1" checked/><div style="float: left">公司</div>
                            <input type="radio" name="type" class="col-xs-12 col-sm-1" value="0"/><div style="float: left">个人</div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">customer : </label>
                        <div class="col-sm-9">
                            <input type="text" id="customer" name="customer" placeholder="customer" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="margin-top: -9px;margin-left: 57px;" class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>状态 : </label>
                        <div class="col-sm-6">
                            <input type="radio" name="status" class="col-xs-12 col-sm-1" value="1" checked/><div style="float: left">启用</div>
                            <input type="radio" name="status" class="col-xs-12 col-sm-1" value="0"/><div style="float: left">禁用</div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addplat">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div>
    </div>
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
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/assets/js/laydate.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.js"></script>
<script src="${ctx}/js/base.js"></script>
<script src="${ctx}/js/logi/plat_list.js"></script>
<#include "../common/body_bottom.ftl"/>
