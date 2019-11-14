<#assign title="讲师分类管理"/>
<#assign menuId="5ab7f0c67de848e299ef85aec4a77f05"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<input type="hidden" id="typeVal"  name="typeVal" value="${typeVal}"/>
<input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
<div class="col-xs-12">
    <span style="font-weight: 800;">上级讲师分类 》 ${typeName}</span>
</div>
<div class="col-xs-12" style="margin-top: 10px;">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-6">
                <input type="text" id="typeName" placeholder="分类名称" class="col-xs-12">
            </div>
            <div class="col-sm-6">
                <button type="button" class="btn btn-info btn-sm" id="user-type-search">查询</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="user-type-add">添加</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="user-type-back">返回</button>
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

<!-- 分类修改Modal -->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改讲师分类</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-userType" class="form-horizontal">

                    <input type="hidden" id="typeVal"  name="typeVal" value="${typeVal}"/>
                    <input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
                    <input type="hidden" id="userTypeIdVal" name="userTypeId" value="">
                    <input type="hidden" id="typeLevel"  name="typeLevel" value="2"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>分类名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="typename_update" name="typeName" placeholder="分类名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>是否显示:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="status_1_update" name="status" value="1"/>
                            是
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="status_0_update" name="status" value="0"/>
                            否
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>分类图片:</label>
                        <div class="col-xs-9">
                            <input type="file" id="userTypePicture" name="file" class="form-control"/>
                        </div>
                    </div>
                   <#-- <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <div class="col-xs-9" style="float: right;">
                        <img src="" id="img" style="width: 300px;
    height: 200px;"/>
                        </div>
                    </div>-->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateUserType">修 改</button>
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
                <h4 class="modal-title">添加讲师分类</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-userType" class="form-horizontal">

                    <input type="hidden" id="typeVal"  name="typeVal" value="${typeVal}"/>
                    <input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
                    <input type="hidden" id="typeLevel"  name="typeLevel" value="2"/>
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
                            <input type="text" id="typename_add" name="typeName" placeholder="分类名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>是否显示:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="status_1_add" name="status" value="1" checked="checked"/>
                            是
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="status_0_add" name="status" value="0"/>
                            否
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>教头分类图片:</label>
                        <div class="col-xs-9">
                            <input type="file" id="userTypePicture" name="file" class="form-control"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addUserType">添 加</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--add-end-->
<!-- 图片展示Modal -->
<div class="modal fade" id="imgModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">讲师分类图片</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <img src="" id="img"/>
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
<script src="${ctx}/js/user/teacher_type_children.js"></script>
<#include "../common/body_bottom.ftl"/>