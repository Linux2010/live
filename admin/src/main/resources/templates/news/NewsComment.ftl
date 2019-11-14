<#assign title="资讯评论"/>
<#assign menuId="69"/>
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
                <h4 class="modal-title">详情信息</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="reply-form" class="form-horizontal">
                    <div  class="form-group" style="margin-left: 15px" id="con">
                        用户名 : <b id="newsname" style="margin-left: 30px;margin-top: 15px"></b>&nbsp;&nbsp;&nbsp;<b id="createTime"></b><br>
                        评论内容 :<b id="newsContent"  style="margin-left: 30px;margin-top: 15px"></b><br>
                        <div id="huifu">管理员回复 :</span><b id="hcontent"  style="margin-left: 30px;margin-top: 15px"></b>&nbsp;&nbsp;&nbsp;<b id="htime"></b></div><br>

                    </div>
                    <input id="id" name="id" type="hidden">
                    <div class="form-group" style="top:20px;">
                        <div class="col-sm-9">
                            <input type="text" id="replayContent" name="replyContent" placeholder="你想说的话..."
                                   class="col-xs-12 col-sm-5" value="" style="width: 340px;margin-left: 15px;margin-top: 30px" maxlength="15"/> &nbsp;&nbsp;&nbsp; <button type="button" style="margin-top: 15px;margin-left: 10px" class="response" id="resp"">回复</button>
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
<script src="${ctx}/js/news/newscomment.js"></script>
<#include "../common/body_bottom.ftl"/>
