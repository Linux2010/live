<#assign title="系统文案"/>
<#assign menuId="84ef1a0c86fc49b399b5af8928dc1ece"/>
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
<div class="col-xs-12" style="margin-top: 10px;">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-6">
                <label class="col-xs-4" style="text-align: right;padding-top: 5px;">文案类型：</label>
                <div class="col-xs-8">
                    <select id="docType" class="col-xs-12" style="height: 34px;">
                        <option value="0">--请选择--</option>
                        <option value="1">学分制度</option>
                        <option value="2">激活码制度</option>
                        <option value="3">邀请好友规则</option>
                        <option value="4">积分制度</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-6">
                <button type="button" class="btn btn-info btn-sm" id="user-group-card-search">查询</button>
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">

<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/user/sys_doc_index.js"></script>
<#include "../common/body_bottom.ftl"/>