<#assign title="发送私信"/>
<#assign menuId="71"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="col-xs-12">
</div>
<div class="col-xs-12">
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div class="col-xs-12 hide">
    <div class="clearfix" style="margin-top:10px;">
        <div class="pull-left tableTools-container">
            <div class="btn-overlap">
                <a type="button" class="dt-button buttons-copy buttons-html5 btn btn-white btn-primary" value="发送短信" id="find">
                    批量发
                    <span class="badge badge-success">23</span>
                </a>
            </div>
        </div>
    </div>
</div>

<!--message -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">发送私信</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll;">
                <form id="sendMessage-form" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>编辑私信内容:</label>
                        <div class="col-sm-9">
                            <textarea name="content" id= "content" class="form-control limited autosize-transition form-control" style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 208px;" id="form-field-9" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <input type="hidden" name="sendUserId" value="-1">
                    <input type="hidden" name="receiveUserId" value="" id="receiveUserId">
                    <input type="hidden" name="relationId" value="" id="relationId">
                    <input type="hidden" name="relationType" value="3">
                    <input type="hidden" name="messageType" value="1">
                    <input type="hidden" name="userIds" value="" id="userIds">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="sendMessageBtn">发 送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--add-end-->
<#--add-end-->
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>

<script src="${ctx}/assets/js/jquery.inputlimiter.min.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/assets/js/laydate.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.js"></script>
<script src="${ctx}/js/base.js"></script>
<script src="${ctx}/js/customer/user_letter_index.js"></script>
<#include "../common/body_bottom.ftl"/>
