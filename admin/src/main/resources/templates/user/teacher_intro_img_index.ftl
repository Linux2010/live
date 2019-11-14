<#assign title="讲师简介图片管理"/>
<#assign menuId="feb6ac8a64e244b0958f5db4cf3dad7a"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<div class="col-xs-12">
    <span style="font-weight: 800;">讲师名称 》 ${teacher.userName!}</span>
</div>
<div class="col-xs-12" style="margin-top: 10px;">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-8"></div>
            <div class="col-sm-4">
                <button type="button" class="btn btn-info btn-sm" id="teacher-introImg-add">添加</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="teacher-introImg-back">返回</button>
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
<!-- 分类添加Modal -->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加讲师简介图片</h4>(<small class="red">每次可以添加5张</small>)
            </div>
            <div class="modal-body" style="max-height:800px;overflow:scroll; ">
                <form id="add-form-introImg" class="form-horizontal">
                    <input type="hidden" id="teacherId" name="teacherId" value="${teacher.userId!}"/>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>图片1:</label>
                            <div class="col-xs-9">
                                <input type="file" id="imageUrl" name="file" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>序号1:</label>
                            <div class="col-xs-9">
                                <input type="text" id="seqno" name="seqno" value="" placeholder="序号" />
                                <span class="help-inline " style="width:50%;" >
                            </div>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>图片2:</label>
                            <div class="col-xs-9">
                                <input type="file" id="imageUrl" name="file" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>序号3:</label>
                            <div class="col-xs-9">
                                <input type="text" id="seqno" name="seqno" value="" placeholder="序号" />
                                <span class="help-inline " style="width:50%;" >
                            </div>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>图片3:</label>
                            <div class="col-xs-9">
                                <input type="file" id="imageUrl" name="file" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>序号3:</label>
                            <div class="col-xs-9">
                                <input type="text" id="seqno" name="seqno" value="" placeholder="序号" />
                                <span class="help-inline " style="width:50%;" >
                            </div>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>图片4:</label>
                            <div class="col-xs-9">
                                <input type="file" id="imageUrl" name="file" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>序号4:</label>
                            <div class="col-xs-9">
                                <input type="text" id="seqno" name="seqno" value="" placeholder="序号" />
                                <span class="help-inline " style="width:50%;" >
                            </div>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>图片5:</label>
                            <div class="col-xs-9">
                                <input type="file" id="imageUrl" name="file" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>序号5:</label>
                            <div class="col-xs-9">
                                <input type="text" id="seqno" name="seqno" value="" placeholder="序号" />
                                <span class="help-inline " style="width:50%;" >
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addTeacherIntroImg">添 加</button>
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
                <h4 class="modal-title">商品图片</h4>
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
<script src="${ctx}/assets/js/spinbox.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/user/teacher_intro_img_index.js"></script>
<#include "../common/body_bottom.ftl"/>