<#assign title="江湖大课"/>
<#assign menuId="52"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/daterangepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style type="text/css">
    .combo-arrow{
        background: #fff !important;
    }
    input.combo-text.validatebox-text{
        background: #fff !important;
        border: #fff;
    }
</style>
<div id="dialog"></div>

<input type="hidden" id="basePath" value="${ctx}"/>

<div class="modal-header">
    <h4 class="modal-title">发布课程</h4>
</div>
<div class="modal-body">
    <form id="add-form-course" class="form-horizontal" enctype="multipart/form-data">
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>选择分类:</label>
            <div class="col-xs-6">
                <select id="courseTypeId" style="width:500px;height:35px;"></select>
                <input type="hidden" id="course_type_id" name="courseTypeId"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>选择标签:</label>
            <div class="col-xs-6">
                <select id="courseTagId" name="courseTagId" class="form-control"></select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>课程封面:</label>
            <div class="col-xs-6">
                <input type="file" id="courseCover" name="file" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>课程列表图片:</label>
            <div class="col-xs-6">
                <input type="file" id="courseImg" name="file1" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>课程名称:</label>
            <div class="col-xs-6">
                <input type="text" id="courseTitle" name="courseTitle" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>开始时间:</label>
            <div class="col-xs-6">
                <input type="text" id="courseBeginTime" name="courseBeginTimeVal" class="form-control date-picker"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>结束时间:</label>
            <div class="col-xs-6">
                <input type="text" id="courseEndTime" name="courseEndTimeVal" class="form-control date-picker"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>讲师名称:</label>
            <div class="col-xs-6">
                <select id="teacherId" name="teacherId" class="form-control"></select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>用户价格:</label>
            <div class="col-xs-5">
                <input type="text" id="costPrice" name="costPrice" class="form-control"/>
            </div>
            <div class="col-xs-2" style="padding-top:5px;">
                <input type="checkbox" id="costFree" onclick="changeCost(this)" style="zoom:150%;vertical-align:middle;"/>
                <span style="vertical-align: bottom;">免费</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>会员价格:</label>
            <div class="col-xs-5">
                <input type="text" id="vipPrice" name="vipPrice" class="form-control"/>
            </div>
            <div class="col-xs-2" style="padding-top:5px;">
                <input type="checkbox" id="costFree" onclick="changeVip(this)" style="zoom:150%;vertical-align:middle;"/>
                <span style="vertical-align: bottom;">免费</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>地址:</label>
            <div class="col-xs-6">
                <input type="text" id="courseAddress" name="courseAddress" placeholder="请输入经纬度，以逗号隔开" class="form-control"/>
                <a href="http://www.gpsspg.com/maps.htm" target="_blank">获取经纬度</a>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>详细地址:</label>
            <div class="col-xs-6">
                <input type="text" id="courseAddressDetail" name="courseAddressDetail" placeholder="请输入详细地址" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>序列值:</label>
            <div class="col-xs-6">
                <input type="text" id="seqno" name="seqno" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-right" for="recordName"><small class="red">*</small>课程简介:</label>
            <div class="col-xs-6">
                <textarea id="courseIntro" name="courseIntro" maxlength="150" style="height:120px;" placeholder="最多可输入150字......" class="form-control"></textarea>
            </div>
        </div>
    </form>
</div>
<div class="modal-footer" style="text-align:center;">
    <button type="button" class="btn btn-primary" id="addCourse">发 布</button>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-default" id="backBtn">返 回</button>
</div>

<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">

<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>

<!--ace datepicker moment-->
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/js/ace.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-timepicker.min.js"></script>
<script src="${ctx}/assets/js/moment.js"></script>
<script src="${ctx}/assets/js/daterangepicker.min.js"></script>
<script src="${ctx}/assets/js/bootstrap-datetimepicker.min.js"></script>

<script src="${ctx}/js/course/courseJhdkAdd.js"></script>
<#include "../common/body_bottom.ftl"/>