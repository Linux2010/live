<#assign title="课程分类管理"/>
<#assign menuId="32"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="col-xs-12">
    <span style="font-weight: 800;">上级课程分类 》 ${typeName}</span>
</div>
<div class="col-xs-12" style="margin-top: 10px;">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-6">
                <input type="text" id="typeName" placeholder="分类名称" class="col-xs-12">
            </div>
            <div class="col-sm-6">
                <button type="button" class="btn btn-info btn-sm" id="course-type-search">查询</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="course-type-add">添加</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="course-type-back">返回</button>
                &nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div id="dialog">
</div>

<input type="hidden" id="parentId" value="${parentId}"/>

<!-- 分类修改Modal -->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改课程分类</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-courseType" class="form-horizontal" enctype="multipart/form-data">
                    <input type="hidden" id="courseTypeId" name="courseTypeId"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>分类名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="typename_update" name="typename_update" placeholder="分类名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>课程分类图片:</label>
                        <div class="col-xs-9">
                            <input type="file" id="picture_update" name="file" class="fileUpload col-xs-12 col-sm-8"/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>是否显示:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="status_1_update" name="status_update" value="1"/>
                            是
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="status_0_update" name="status_update" value="0"/>
                            否
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateCourseType">修 改</button>
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
                <h4 class="modal-title">添加课程分类</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-courseType" class="form-horizontal" enctype="multipart/form-data">
                    <input type="hidden" name="parentId" value="${parentId}"/>
                    <input type="hidden" name="typeLevel" value="2"/>
                    <input type="hidden" id="typeVal" name="typeVal" value="${typeVal}"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">上级分类:</label>
                        <div class="col-sm-9" style="padding-top: 9px;">
                            <span id="parentType">${typeName}</span>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>分类名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="typename_add" name="typename_add" placeholder="分类名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>课程分类图片:</label>
                        <div class="col-xs-9">
                            <input type="file" id="picture_add" name="file" class="fileUpload col-xs-12 col-sm-8"/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>是否显示:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="status_1_add" name="status_add" value="1" checked="checked"/>
                            是
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="status_0_add" name="status_add" value="0"/>
                            否
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addCourseType">添 加</button>
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
<script src="${ctx}/js/course/courseType_children.js"></script>
<script>
    // 初始化上传文件输入框
    $('.fileUpload').ace_file_input({
        no_file:'选择文件...',
        btn_choose:'选择',
        btn_change:'修改',
        droppable:false,
        onchange:null,
        thumbnail:false
    });
</script>
<#include "../common/body_bottom.ftl"/>