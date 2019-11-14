<#assign title="优惠劵使用对象"/>
<#assign menuId="012d1ad70f77458ca9c62f3c43329b71"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style>
    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height:auto;
        vertical-align:text-top;
        padding-top:2px;
    }
</style>
<div class="nav-search col-sm-12" id="nav-search" style="border:1px solid #cccccc;width:97.9%;height:120px;padding-top:20px;left:1.1%">
    <form class="form-search" id="form-search" method="post">
        <input type="hidden" name="couponId" id="couponId" value="${couponId!}"/>
        <div class="row">
            <div class="col-xs-6">
                <div class="row">
                    <div class="col-xs-3 no-padding-right" style="text-align: right">关键字: </div>
                    <div class="col-xs-3">
                        <span class="input-icon"><input type="text" placeholder="查找 用户名/昵称/标签名/真实姓名" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keyword"/><i class="ace-icon fa fa-search nav-search-icon"></i></span>
                    </div>
                    <div class="col-xs-3 no-padding-right" style="text-align: right">手机号:</div>
                    <div class="col-xs-3">
                        <span class="input-icon"><input type="text" placeholder="查找 手机号" class="nav-search-input" id="nav-search-input1" autocomplete="off" name="phone"/><i class="ace-icon fa fa-search nav-search-icon"></i></span>
                    </div>
                </div>
            </div>
            <div class="col-xs-1"></div>
            <div class="col-xs-5">
                <span>
                   <label>账号状态 : </label>
                   <div class="radio" style="display: inline-block;margin-top: 0;">
                       <label>
                           <input name="form-field-radio" type="radio" class="ace" name="gender" id="g1" value="0"/>
                           <span class="lbl" id="status1">已停用账号</span>
                       </label>
                       <label >
                           <input name="form-field-radio" type="radio" class="ace"  name="gender" id="g2" value="1" checked = "true"/>
                           <span class="lbl" id="status2" >已开启账号</span>
                       </label>
                   </div>
                </span>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6">
                <div class="row">
                    <div class="form-group" style="margin-bottom: 0;">
                        <label class="col-xs-3 control-label no-padding-right" for="prizeNum" style="line-height: 34px;text-align: right">用户身份:</label>
                        <div class="col-xs-3">
                            <select  class="form-control" name="identity" id="identity">
                                <option value="">--请选择--</option>
                                <option value="1">普通用户</option>
                                <option value="2">会员用户</option>
                            </select>
                        </div>
                        <label class="col-xs-3 control-label no-padding-right" for="prizeNum" style="line-height: 34px;text-align: right">用户类型:</label>
                        <div class="col-xs-3" >
                            <select class="form-control"  name="type" id="type">
                                <option value="">--请选择--</option>
                                <option value="1">教头</option>
                                <option value="2">用户</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4"></div>
            <div class="col-xs-2">
                <input type="button" class="btn btn-blue btn-sm" value="查找" id="find" />
            </div>
        </div>
    </form>
</div><!-- /.nav-search -->
<div class="col-xs-12" style="margin-top: 150px">
    <div class="form-group">
        <div class="col-sm-12">
            优惠劵: <label class="control-label no-padding-right red">${coupon.couponName!}</h4></label>&nbsp;&nbsp;&nbsp;(默认选中为该优惠劵的优惠商品)
        </div>

    </div>
    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>

<div class="modal-footer" style="text-align: left;">
    <button type="button" class="btn btn-primary" id="addUserCoupon">发放优惠劵</button>
    <button type="button" class="btn btn-primary" id="cancelUserCoupon">取消发放的优惠劵</button>
    <button type="button" class="btn btn-default" onclick="javascript:location.href=base+'coupon/coupon_index'">返回</button>
</div>
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
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/assets/js/laydate.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.js"></script>
<script src="${ctx}/js/base.js"></script>
<script src="${ctx}/js/goods/coupon_user_index.js"></script>
<#include "../common/body_bottom.ftl"/>
