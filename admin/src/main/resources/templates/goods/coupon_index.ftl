<#assign title="优惠劵管理"/>
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
        <div class="form-group">
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="recordName" style="text-align: right;"> 关键字:</label>
                <div class="col-sm-9">
                    <input type="text" placeholder="查找 优惠劵名称" class="col-xs-12" id="couponName" name="couponName"/>
                </div>
            </div>
        </div>
        <div class="col-sm-1">
            <input type="button" class="btn btn-info btn-sm" value="查找" id="find" />
        </div>

    </form>
</div>
<div class="col-xs-12" style="margin-top: 80px">
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
</div><!-- /.col -->
<div id="dialog">
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
<script src="${ctx}/js/goods/coupon_index.js"></script>

<#include "../common/body_bottom.ftl"/>
