<#assign title="音频课程"/>
<#assign menuId="50"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<link href="//nos.netease.com/vod163/nep.min.css" rel="stylesheet">
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
                <input type="text" id="courseTitle" placeholder="课程名称" class="col-xs-12">
            </div>
            <div class="col-sm-3">
                <button type="button" class="btn btn-info btn-sm" id="course-search">查询</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="course-add">添加</button>
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div id="dialog"></div>

<div class="modal fade" id="uploadModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">上传课程内容</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="upload-form-courseYp" enctype="multipart/form-data" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>文件ID:</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="courseId" name="courseId"/>
                            <input type="text" id="videoId" name="videoId" placeholder="文件ID" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>文件名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="objName" name="objName" placeholder="文件名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
                <p style="color: red;">
                    温馨提示：请先到<a href="https://app.netease.im/login" target="_blank">网易云</a>上传音频，
                    <br/>
                    然后再把文件ID和地址中的文件名称拷贝到输入框中进行上传
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="uploadCourseYp">上 传</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div class="modal fade" id="showModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <video id="my-video" class="video-js" controls poster="//nos.netease.com/vod163/poster.png" preload="auto" width="597" height="300" data-setup='{}'></video>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="closeVideo()">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<input type="hidden" id="basePath" value="${ctx}"/>

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
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script src="${ctx}/js/course/courseYpIndex.js?v=1"></script>
<script src="//nos.netease.com/vod163/nep.min.js"></script>

<#include "../common/body_bottom.ftl"/>