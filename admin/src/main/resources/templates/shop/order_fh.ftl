<#assign title="订单发货"/>
<#assign menuId="b1f97d2b69124571a10dfd7a9314370f"/>
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
    .form-group{
        margin-bottom: 0px;
    }
    .orderC{
        margin-top: 10px;
    }
</style>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="addressId"/>
<input type="hidden" id="orderNoVal"/>
<div class="modal-header">
    <form class='form-horizontal'>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName">订单编号：</label>
            <div class="col-xs-5 orderC">
                <span id="orderNo"></span>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName">订单状态：</label>
            <div class="col-xs-5 orderC">
                <span id="status"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName">支付状态：</label>
            <div class="col-xs-5 orderC">
                <span id="payStatus"></span>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName">发货状态：</label>
            <div class="col-xs-5 orderC">
                <span id="orderStatus"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName">订单类型：</label>
            <div class="col-xs-5 orderC">
                <span id="orderTypeSpan"></span>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName">订单时间：</label>
            <div class="col-xs-5 orderC">
                <span id="orderTime"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName">订单总额：</label>
            <div class="col-xs-5 orderC">
                <span id="totalVal"></span>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName">优惠金额：</label>
            <div class="col-xs-5 orderC">
                <span id="orderYh"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName">应付总额：</label>
            <div class="col-xs-5 orderC">
                <span id="orderYf"></span>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName">实付金额：</label>
            <div class="col-xs-5 orderC">
                <span id="orderSf"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName">收货人：</label>
            <div class="col-xs-5 orderC">
                <span id="userName"></span>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName">手机号：</label>
            <div class="col-xs-5 orderC">
                <span id="userPhone"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName">收货地址：</label>
            <div class="col-xs-5 orderC">
                <span id="userAddress"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName"></label>
            <div class="col-xs-5 orderC"></div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName"></label>
            <div class="col-xs-5 orderC"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="recordName">快递公司：</label>
            <div class="col-xs-5">
                <select id="logiId" name="logiId" class="form-control">
                    <option value="">--请选择--</option>
                </select>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName">快递单号：</label>
            <div class="col-xs-5">
                <input id="kdNo" name="kdNo" class="form-control"/>
            </div>
        </div>
    </form>
</div>
<div class="modal-footer" style="text-align:center;">
    <button type="button" class="btn btn-default" id="fhBtn">发 货</button>
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
<script src="${ctx}/js/shop/orderFh.js"></script>
<#include "../common/body_bottom.ftl"/>