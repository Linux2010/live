<#assign title="课程标签管理"/>
<#assign menuId="41"/>
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
                <input type="text" id="name" placeholder="标签名称" class="col-xs-12">
            </div>
            <div class="col-sm-6">
                <button type="button" class="btn btn-info btn-sm" id="course-tag-search">查询</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="course-tag-add">添加</button>
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div id="dialog">
</div>

<!-- 分类修改Modal -->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改课程标签</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-courseTag" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>标签名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="name_update" name="name_update" placeholder="标签名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateCourseTag">修 改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--add-end-->
<!-- 分类添加Modal -->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加课程标签</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-courseTag" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>标签名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="name_add" name="name_add" placeholder="标签名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addCourseTag">添 加</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
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
<script src="${ctx}/js/course/courseTag.js"></script>
<#include "../common/body_bottom.ftl"/>