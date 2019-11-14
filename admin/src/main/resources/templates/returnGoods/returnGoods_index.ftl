<#assign title="退货管理"/>
<#assign menuId="84"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="nav-search" id="nav-search">
    <form class="form-search" id="form-search" method="post">
		<span class="input-icon" style="margin-top: 12px">
           <select id="status_add">
               <option  value="">--请选择--</option>
               <option value="1">已打款</option>
               <option value="2">已拒绝</option>
               <option value="3">退款中</option>
           </select>
        </span>
        <input type="button" class="btn btn-blue btn-sm" value="查找" id="find"/>
    </form>
</div><!-- /.nav-search -->
<div class="col-xs-12" style="margin-top: 60px">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>

<#--拒绝退货model-->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 710px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">退货操作</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-returnGoods" class="form-horizontal">
                    <input type="hidden" id="returnGoods_id" name="returnGoodsId"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" id="title" style="width: 19%">拒绝原因：</label>
                        <div class="col-sm-9">
                            <textarea id="refuse_reason" name="refuseReason" cols="60" rows="6"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="sure">确 定</button>
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
<script src="${ctx}/js/returnGoods/returnGoods_index.js"></script>
<#include "../common/body_bottom.ftl"/>
