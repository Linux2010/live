<#assign title="课程内容"/>
<#assign menuId="49"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<div id="dialog"></div>

<input type="hidden" id="basePath" value="${ctx}"/>
<input type="hidden" id="courseId" value="${courseId}"/>

<div class="modal-header">
    <h4 class="modal-title"><span id="courseTitle"></span></h4>
</div>
<div class="modal-body" id="ctDetail">
    <div id="container" name="courseContent" type="text/plain" style="height:300px;"></div>
</div>
<div class="modal-footer" style="text-align:center;">
    <button type="button" class="btn btn-default" id="uploadBtn">上 传</button>
    &nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-default" id="backBtn">返 回</button>
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
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/UEditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/UEditor/ueditor.all.js"></script>
<script src="${ctx}/js/course/courseWzContentDetailUpload.js"></script>
<#include "../common/body_bottom.ftl"/>