<#assign title="用户团购卡管理"/>
<#assign menuId="15"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style type="text/css">
    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height:auto;
        vertical-align:text-top;
        padding-top:2px;
    }
</style>
<div class="col-xs-12">
    <input type="hidden" id="userId" value="${userId}"/>
    <span style="font-weight: 800;">当前用户： ${userName}</span>
</div>
<div class="col-xs-12" style="margin-top: 10px;">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-3">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">卡号：</label>
                <div class="col-xs-8">
                    <input type="text" id="cardNo" placeholder="卡号" class="col-xs-12">
                </div>
            </div>
            <div class="col-sm-3">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">类型：</label>
                <div class="col-xs-8">
                    <select id="cardType" class="col-xs-12" style="height: 34px;">
                        <option value="0">--请选择--</option>
                        <option value="1">年卡</option>
                        <option value="2">月卡</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-3">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">状态：</label>
                <div class="col-xs-8">
                    <select id="status" class="col-xs-12" style="height: 34px;">
                        <option value="-1">--请选择--</option>
                        <option value="0">未激活</option>
                        <option value="1">已激活</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-3">
                <button type="button" class="btn btn-info btn-sm" id="user-group-card-search">查询</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="user-group-card-export">导出</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="user-group-card-back">返回</button>
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div id="dialog">
</div>

<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加团购卡</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-courseTag" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>添加数量:</label>
                        <div class="col-sm-9">
                            <input type="text" id="cardNum" name="cardNum" placeholder="添加数量" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>团购卡类型:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="cardType1" name="status_update" value="1" checked="checked"/>
                            年卡
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="cardType2" name="status_update" value="2"/>
                            月卡
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addUserGroupCard">添 加</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
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
<script src="${ctx}/js/user/user_group_card.js?v=1"></script>
<#include "../common/body_bottom.ftl"/>