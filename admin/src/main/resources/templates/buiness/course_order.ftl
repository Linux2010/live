<#assign title="课程订单管理"/>
<#assign menuId="40"/>
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
<div class="nav-search" id="nav-search" style="width:98%;top:30px;">
    <form class="form-search" id="form-search" method="post">
        <div class="row">
        <span class="input-icon col-sm-4">
									<i class="col-xs-2" style="text-align: right"></i><div class="col-xs-4" style="text-align: right;line-height: 33px">订单号 ：</div><input class="col-xs-6" type="text" placeholder="查找 订单号"  id="nav-search-input" autocomplete="off" name="keyword" style="padding-top: 4px;padding-bottom:4px;"/>

		</span>

        <div  style="" class="col-sm-4">
            <label class="col-xs-4 control-label no-padding-right" for="prizeNum" style="text-align: right;line-height: 33px;">订单状态:</label>
            <div class="col-xs-6">
                <select  name="status" id="status" class="form-control" id="form-field-select-1" >
                    <option value="">--请选择--</option>
                    <option value="0">待付款</option>
                    <option value="1">已付款</option>
                </select>
            </div>
        </div>
        <div class="col-sm-4"><input type="button" class="btn btn-blue btn-sm col-xs-2" value="查找" style="" id="find"/></div>
        </div>
    </form>
</div><!-- /.nav-search -->
<div class="col-xs-12" style="margin-top:80px">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>
<!-- 详情显示Modal -->
<div class="modal fade" id="order_detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title">详情信息</h3>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">

                    <div  class="form-group" style="margin-left: 15px">
                        <h4 style="color: black">订单信息</h4>
                        <hr>
                        <div class="row">
                            <div class="col-xs-4">订单号 : <span id="onum" ></span></div>
                            <div class="col-xs-4">下单时间 : <span id="odate"></span></div>
                            <div class="col-xs-4">订单状态 : <span id="ostatus" ></span></div>
                        </div><br>
                        <div class="row">
                            <div class="col-xs-4">付款状态 : <span id="mstatus" ></span></div>
                            <div class="col-xs-4">支付类型 : <span id="pstatus" ></span></div>
                            <div class="col-xs-4">订单金额 : <span id="omoney" ></span></div>
                        </div><hr>
                    </div>
                    <div class="form-group" style="margin-left: 15px">
                        <h4>课程信息</h4>
                        <hr>
                        <div class="row">
                            <div class="col-xs-4" style="top:40px;">课程标题: <span id="ctitle" ></span></div>
                            <div class="col-xs-4" style="top:40px;">课程讲师: <span id="cc"</b></div>
                            <div class="col-xs-4">课程封面: <img id="cover" style="height: 100px;"/></div>
                        </div><hr>
                        <div class="row">
                            <div class="col-xs-4">开始时间: <span id="cstart"></span></div>
                            <div class="col-xs-4"><div id="course_end" STYLE="display: block">结束时间 : <span id="cend"></span></div></div>
                            <div class="col-xs-4">普通价格: <span id="cpu"></span>/(银两)</div>
                        </div><hr>
                        <div class="row">
                            <div class="col-xs-4">会员价格 : <span id="cvip"></span>/(银两)</div>
                            <div class="col-xs-4">课程积分: <span id="cscore" ></span>/(积分)</div>
                            <div class="col-xs-4">课程类型: <span id="ctype" ></span></div>
                        </div><hr>
                        <div class="row">
                            <div class="col-xs-12"><div id="course_addredd">课程地址: <span id="caddress" ></span></div></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">课程介绍: <span id="cintro" ></span></div>
                        </div><hr>
                    </div>
                    <div class="hr hr-18 dotted"></div>
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
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/assets/js/laydate.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.js"></script>
<script src="${ctx}/js/base.js"></script>
<script src="${ctx}/js/corder/corder.js"></script>
<#include "../common/body_bottom.ftl"/>
