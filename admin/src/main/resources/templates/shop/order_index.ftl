<#assign title="订单管理"/>
<#assign menuId="b1f97d2b69124571a10dfd7a9314370f"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/daterangepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datetimepicker.min.css" />
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
<div class="col-xs-12" style="margin-top: 10px;">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-4">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">订单编号：</label>
                <div class="col-xs-8">
                    <input type="text" id="orderNo" placeholder="订单编号" class="col-xs-12">
                </div>
            </div>
            <div class="col-sm-4">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">订单类型：</label>
                <div class="col-xs-8">
                    <select id="orderType" class="col-xs-12" style="height: 34px;">
                        <option value="0">--请选择--</option>
                        <option value="1">金额商品订单</option>
                        <option value="2">银两商品订单</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">订单状态：</label>
                <div class="col-xs-8">
                    <select id="status" class="col-xs-12" style="height: 34px;">
                        <option value="0">--请选择--</option>
                        <option value="1">正常</option>
                        <option value="2">已取消</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">发货状态：</label>
                <div class="col-xs-8">
                    <select id="orderStatus" class="col-xs-12" style="height: 34px;">
                        <option value="0">--请选择--</option>
                        <option value="1">待发货</option>
                        <option value="2">待收货</option>
                        <option value="3">待评价</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">支付状态：</label>
                <div class="col-xs-8">
                    <select id="payStatus" class="col-xs-12" style="height: 34px;">
                        <option value="0">--请选择--</option>
                        <option value="1">待付款</option>
                        <option value="2">已付款</option>
                        <option value="3">待评价</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">用户名：</label>
                <div class="col-xs-8">
                    <input type="text" id="userName" placeholder="用户名" class="col-xs-12">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">订单时间：</label>
                <div class="col-xs-8">
                    <input type="text" id="startTime" class="form-control date-picker col-xs-12"/>
                </div>
            </div>
            <div class="col-sm-4">
                <label class="col-xs-4" style="padding-top: 5px;padding-left: 40px;">至</label>
                <div class="col-xs-8">
                    <input type="text" id="endTime" class="form-control date-picker col-xs-12"/>
                </div>
            </div>
            <div class="col-sm-4">
                <button type="button" class="btn btn-info btn-sm" id="course-tag-search">查询</button>
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div id="dialog"></div>

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

<!--ace datepicker moment-->
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/js/ace.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-timepicker.min.js"></script>
<script src="${ctx}/assets/js/moment.js"></script>
<script src="${ctx}/assets/js/daterangepicker.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-datetimepicker.min.js"></script>

<script src="${ctx}/js/shop/orderIndex.js"></script>
<#include "../common/body_bottom.ftl"/>