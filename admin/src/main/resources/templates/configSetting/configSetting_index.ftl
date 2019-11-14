<#assign title="系统设置"/>
<#assign menuId="19"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="nav-search" id="nav-search">
    <form class="form-search" id="form-search" method="post">
								<span class="input-icon">
									<input type="text" placeholder="根据名称查询" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keyword"/>
									<i class="ace-icon fa fa-search nav-search-icon"></i>
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

<!--添加设置内容-->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加设置</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-configSetting" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">内容：</label>
                        <div class="col-sm-9">
                            <input type="text" id="config_name_add" name="config_name" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">数值：</label>
                        <div class="col-sm-9">
                            <input type="text" id="config_value_add" name="config_value" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">标识：</label>
                        <div class="col-sm-9">
                            <input type="text" id="config_sign_add" name="config_sign" class="col-xs-12 col-sm-5" value=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">备注：</label>
                        <div class="col-sm-9">
                            <input type="text" id="remark_add" name="remark" class="col-xs-12 col-sm-5" />
                            <span class="help-inline col-xs-12 col-sm-7">
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_configSetting">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!--修改设置内容-->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改设置</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-configSetting" class="form-horizontal">
                    <input type="hidden" id="config_id_update" name="config_id"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">内容：</label>
                        <div class="col-sm-9">
                            <input type="text" id="config_name_update" name="config_name" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">数值：</label>
                        <div class="col-sm-9">
                            <input type="text" id="config_value_update" name="config_value" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">备注：</label>
                        <div class="col-sm-9">
                            <input type="text" id="remark_update" name="remark" class="col-xs-12 col-sm-5" />
                            <span class="help-inline col-xs-12 col-sm-7">
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update_configSetting">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
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
<script src="${ctx}/js/configSetting/configSetting.js"></script>
<script type="text/javascript">

</script>
<#include "../common/body_bottom.ftl"/>
