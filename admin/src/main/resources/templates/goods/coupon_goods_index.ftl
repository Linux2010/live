<#assign title="优惠劵商品列表"/>
<#assign menuId="012d1ad70f77458ca9c62f3c43329b71"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style>
</style
</style>
<div class="nav-search col-xs-12" id="nav-search" >
    <form class="form-search" id="form-search" method="post">
       <input type="hidden" name="couponId" id="couponId" value="${couponId!}"/>
        <div class="form-group">
            <div class="col-sm-12">
                <div class="col-sm-6">
                    <label class="col-sm-3 control-label no-padding-right" for="keyword" style="text-align: right;"> 关键字:</label>
                    <input type="text"  class="col-sm-9" placeholder="查找 商品名/商品编号/商品简介"  id="keyword" name="keyword"/>
                </div>
                <div class="col-sm-6">
                    <label class="col-sm-3 control-label no-padding-right" for="catId"><small class="red">*</small> 商品分类:</label>
                    <div class="col-sm-8">
                        <select id="catId" name="catId" class=" easyui-combotree" style="width: 250px;height:34px; /*padding-right:2px;padding-right:2px;*/" >
                        </select>
                    </div>
                    <div class="col-sm-1">
                         <input type="button" class="btn btn-info btn-sm" value="查找" id="find" />
                    </div>
                </div>
            </div>
        </div>

    </form>
</div>
<!-- 优惠商品-->
<div class="col-xs-12" style="margin-top: 80px;margin-bottom: 15px;">
     <div class="form-group">
        <div class="col-sm-12">
               优惠劵: <label class="control-label no-padding-right red">${coupon.couponName!}</h4></label>&nbsp;&nbsp;&nbsp;(默认选中为该优惠劵的优惠商品)
        </div>

     </div>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
</div><!-- /.col -->
<div id="dialog">
</div>
<div class="modal-footer" style="text-align: left;">
    <button type="button" class="btn btn-primary" id="addCouponGoods">设为优惠商品</button>
    <button type="button" class="btn btn-primary" id="cancelCouponGoods">取消商品优惠劵</button>
    <button type="button" class="btn btn-default" onclick="javascript:location.href=base+'coupon/coupon_index'">返回</button>
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
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script src="${ctx}/js/goods/coupon_goods_index.js"></script>

<#include "../common/body_bottom.ftl"/>
