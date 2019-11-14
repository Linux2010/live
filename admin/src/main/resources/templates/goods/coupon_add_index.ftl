<#assign title="添加优惠劵"/>
<#assign menuId="012d1ad70f77458ca9c62f3c43329b71"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/daterangepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style>
    .nav-search .nav-search-input {
        width: 180px;
    }
    .col-sm-2 {
        width: 10%;
        padding: 5px 2px 4px 6px;
    }
    .col-sm-2 input {
        width: 18px;
        height: 18px;
    }
    .col-sm-3 control-label no-padding-right{
        text-align: right;
    }
    .form-horizontal .form-group {
        margin-left: -20%;
        margin-right: -12px;
    }
    .col-sm-1 no-padding-left{
        text-align: right;
    }
</style>

<#--add-start->
<!-- 添加优惠劵-->
<div class="col-xs-12">
    <div class="modal-header">
         <h4 class="modal-title">添加优惠劵</h4>
    </div>
    <form id="add-form-coupon" class="form-horizontal">
        <div class="form-group">
             <label class="col-sm-3 control-label no-padding-right" for="couponName"><small class="red">*</small>优惠劵名称:</label>
             <div class="col-sm-9">
                  <input type="text" id="couponName" name="couponName"  placeholder="优惠劵名称" class="col-xs-12 col-sm-5" value="" />
             </div>
          </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="couponDesc">优惠劵描述:</label>
            <div class="col-sm-9">
                 <input type="text" id="couponDesc" name="couponDesc"  placeholder="优惠劵描述" class="col-xs-12 col-sm-5" value="仅限教头学院使用" />
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="couponType"><small class="red">*</small>优惠劵类别:</label>
            <div class="col-xs-9">
                    <input name="couponType" type="radio" class="ace" value="0" checked = "true">
                    <span class="lbl">商品</span>
                    <#--<input name="couponType" type="radio" class="ace" value="1"  >-->
                    <#--<span class="lbl">课程</span>-->
                    <input name="couponType" type="radio" class="ace" value="2"  >
                    <span class="lbl">全场优惠劵</span>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="couponValue"><small class="red">*</small>面值金额(N元):</label>
            <div class="col-sm-9">
                <input type="text" id="couponValue" name="couponValue"  placeholder="面值金额" class="col-xs-12 col-sm-5" value="" />
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="limitMoney"><small class="red">*</small>限制金额(N元):</label>
            <div class="col-sm-9">
                <input type="text" id="limitMoney" name="limitMoney"  placeholder="限制金额" class="col-xs-12 col-sm-5" value="" onblur="limitMoneyOnBlus(this)" />
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="limitMoney"><small class="red">*</small>使用条件:</label>
            <div class="col-sm-9">
                <input type="text" id="limitMoneyDesc" name="limitMoneyDesc"  placeholder="使用条件" class="col-xs-12 col-sm-5" value=""/>
            </div>
        </div>

        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="couponEndTimeStr"><small class="red">*</small>使用开始日期:</label>
            <div class="col-sm-9">
                <input type="text" id="couponBeginTimeStr" name="couponBeginTimeStr" class="form-control date-picker"   style="width: 41.9%;"/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="couponEndTimeStr"><small class="red">*</small>使用截止日期:</label>
            <div class="col-sm-9">
                <input type="text" id="couponEndTimeStr" name="couponEndTimeStr" class="form-control date-picker"   style="width: 41.9%;"/>
            </div>
        </div>

       <#-- <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>优惠劵图片:</label>
            <div class="col-sm-4">
                  <input type="file" id="imageUrl" name="file" class="form-control" />
            </div>
        </div>-->
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>优惠劵序号:</label>
            <div class="col-sm-9">
                <input type="text" id="seqno" name="seqno" placeholder="序号" />
                <span class="help-inline " style="width:50%;" >
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" id="addCoupon">保 存</button>
    <button type="button" class="btn btn-default" onclick="javascript:location.href=base+'coupon/coupon_index'">返回</button>
</div>

<!--商品分类下的商品列表-->
<div class="modal fade" id="goodsModal-cat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 100%;height:auto">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">商品列表</h4>
            </div>
            <div class="modal-body" style="max-height:768px; overflow:scroll; ">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>
<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>

<!--ace datepicker moment-->
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/js/ace.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-timepicker.min.js"></script>
<script src="${ctx}/assets/js/moment.js"></script>
<script src="${ctx}/assets/js/daterangepicker.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-datetimepicker.min.js"></script>

<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/assets/js/laydate.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.js"></script>
<script src="${ctx}/js/base.js"></script>
<script src="${ctx}/assets/js/spinbox.min.js"></script>
<script src="${ctx}/js/goods/coupon_add_index.js"></script>
<#include "../common/body_bottom.ftl"/>
