<#assign title="课程评论管理"/>
<#assign menuId="27"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<div class="col-xs-12">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>
<!-- 详情显示Modal -->
<div class="modal fade" id="comm_find" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">课程评论详情信息</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="reply-form" class="form-horizontal">

                    <div class="row">
                        <div class="col-xs-12"><b> 用户名 : </b><h7 id="ctitle" style="margin-left: 30px;margin-top: 15px"></h7>&nbsp;&nbsp;&nbsp; <h7 id="cdate"></h7></div>
                    </div><hr>
                    <div class="row">
                        <div class="col-xs-12"><b> 评论内容 :</b><h7 id="cwenben"  style="margin-left: 30px;margin-top: 15px"></h7></div>
                    </div><hr>
                    <div class="row">
                        <div class="col-xs-12">  <div id="huifu"><b>管理员回复 :</b></span><h7 id="hcontent"  style="margin-left: 30px;margin-top: 15px"></h7>&nbsp;&nbsp;&nbsp;<h7 id="htime"></h7></div</div>
                    </div>

                    <input id="courseCommentId" name="courseCommentId" type="hidden">
                    <div class="form-group" style="top:20px;" id="res_context">
                        <div class="col-sm-9" style="width: 90%">
                            <input type="text" id="replayContent" name="replayContent" placeholder="你想说的话..."
                                   class="col-xs-12 col-sm-5" value="" style="width: 420px;margin-left: 15px;margin-top: 30px" maxlength="15"/> &nbsp;&nbsp;&nbsp; <button type="button"  class="btn btn-white btn-primary" class="response"style="top:11px;left: 40px" id="response"">回复</button>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
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
<script src="${ctx}/js/ccomment/coursecomment.js"></script>
<#include "../common/body_bottom.ftl"/>
