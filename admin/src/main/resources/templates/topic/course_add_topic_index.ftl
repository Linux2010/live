<#assign title="课程考题管理"/>
<#assign menuId="72"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style>
    .nav-search .nav-search-input {
        width: 180px;
    }
    .col-sm-2 {
        width: 10%;
        padding: 5px 2px 4px 6px;
    }
    .col-sm-2 input {
        width: 18px;
        height: 18px;
    }
    .col-sm-3 control-label no-padding-right{
        text-align: right;
    }
    .form-horizontal .form-group {
        margin-left: -20%;
        margin-right: -12px;
    }
    .col-sm-1 no-padding-left{
        text-align: right;
    }

</style
</style>

<#--add-start->
<!-- 添加考题-->
<div class="col-xs-12">
    <div class="modal-header">
         <h4 class="modal-title">课程考题添加</h4>
    </div>
    <form id="add-form-topic" class="form-horizontal">
        <input type="hidden" name ="courseId" id="courseId" value="${courseId}"/>
        <input type="hidden" name ="redirectUrl" id="redirectUrl" value="${redirectUrl}"/>
        <div class="form-group">
             <label class="col-sm-3 control-label no-padding-right" for="courseName"><small class="red">*</small> 课程名称:</label>
             <div class="col-sm-9">
                  <input type="text" id="courseName" name="courseName"  placeholder="课程名称" class="col-xs-12 col-sm-5" value="${courseName}" onblur=""  readonly="readonly"/>
             </div>
          </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 考卷名称:</label>
            <div class="col-sm-9">
                <input type="text" id="examinationName" name="examinationName" placeholder="考卷名称" class="col-xs-12 col-sm-5" value="" onblur=""/>
            </div>
        </div>
      <div class="hr hr-18 dotted"></div>
<!--<div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>课程类别:</label>
            <div class="col-sm-9">
                    <select id="courseTypeId" name="courseTypeId" class="col-xs-12 col-sm-12 easyui-combotree" style="width: 250px;height:34px; padding-right:2px;padding-right:2px;" >
                    </select>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 选择课程</label>
            <div class="col-sm-9">
                <select  class="form-control courseList col-xs-12 col-sm-12"  name="courseList"  id="courseList" style="width:42%">
                    <option value="">--课程名称--</option>
                </select>
                <input type="hidden" id="courseId" name="courseId" value=""/>
            </div>
       <div class="hr hr-18 dotted"></div>-->
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 考题类型 </label>
            <div class="col-sm-9" style="padding: 8px 19px 3px 14px;">
                <input name="isRegisterTopic" type="radio" class="ace" value="0" disabled="disabled">
                <span class="lbl">注册考题</span>
                <input name="isRegisterTopic" type="radio" class="ace" value="1"  checked = "true">
                <span class="lbl">课程考题</span>
            </div>
        </div>
        <input type="hidden" id="isRegisterTopic" name="isRegisterTopic" value="1"/>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 ">
            </label>
            <div class="col-sm-9">
                  <button type="button" class="btn btn-primary" id="addTopic">添加考题</button> <span class="lbl">(添加考题并选择答案)</span>
            </div>

        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" id="addTopics">保 存</button>
    <button type="button" class="btn btn-default" onclick="backCoursePage()">返回</button>
</div>
<#--add-end-->
<#--考题模板 start-->
<div id="topicTemplate" style="display: none">
    <input type="hidden" id="topicNo"name="topicNo" value=""/>
    <input type="hidden" id="course_topic_id"name="course_topic_id" value=""/>
    <input type="hidden" id="topic_id"name="topic_id" value=""/>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-left" for="recordName">题目<span class="topicIndex">1</span>： </label>
        <div class="col-sm-9">
            <input type="text" id="topicName" name="topicName" placeholder="题目名称"  class="col-xs-9 col-sm-9"  value="" onblur=""/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="recordName"> </label>
        <div class="col-sm-9">
            <div class="col-sm-2 no-padding-left" style=" text-align: right;">
                A.<input type="checkbox" id="topicAvalueCheck"  name="topicAvalueCheck" value="0">
            </div>
            <div class="col-sm-10">
                <input type="text" id="topicAvalue" name="topicAvalue" placeholder="A选项"  value=""  class="col-xs-5 col-sm-5" />
<!--   <select  class="form-control topicLabel"  name="topicAlabelName"  id="topicAlabelName">
                    <option value="">--学习标签--</option>
                </select>
                <input type="hidden"  value="" name="topicAlabelId" id="topicAlabelId"/>-->

            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="recordName"> </label>
        <div class="col-sm-9">
            <div class="col-sm-2 no-padding-left" style=" text-align: right;">
                B.<input type="checkbox"  id="topicBvalueCheck"  name="topicBvalueCheck" value="0">
            </div>
            <div class="col-sm-10">
                <input type="text" id="topicBvalue" name="topicBvalue" placeholder="B选项"  value=""  class="col-xs-5 col-sm-5" />
                <!-- <select  class="form-control topicLabel"  name="topicBlabelName"  id="topicBlabelName">
                        <option value="">--学习标签--</option>
                    </select>
                    <input type="hidden"  value="" name="topicBlabelId" id="topicBlabelId"/>-->
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="recordName"> </label>
        <div class="col-sm-9">
            <div class="col-sm-2 no-padding-left" style=" text-align: right;">
                C.<input type="checkbox" id="topicCvalueCheck"  name="topicCvalueCheck" value="0">
            </div>
            <div class="col-sm-10">
                <input type="text" id="topicCvalue" name="topicCvalue" placeholder="C选项"  value=""  class="col-xs-5 col-sm-5" />
<!--  <select  class="form-control topicLabel"  name="topicClabelName"  id="topicClabelName">
                    <option value="">--学习标签--</option>
                </select>
                <input type="hidden"  value="" name="topicClabelId" id="topicClabelId"/>-->
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="recordName"> </label>
        <div class="col-sm-9">
            <div class="col-sm-2 no-padding-left" style=" text-align: right;">
                D.<input type="checkbox" id="topicDvalueCheck"  name="topicDvalueCheck" value="0">
            </div>
            <div class="col-sm-10">
                <input type="text" id="topicDvalue" name="topicDvalue" placeholder="D选项"  value=""  class="col-xs-5 col-sm-5" />
          <!--  <select  class="form-control topicLabel"  name="topicDlabelName"  id="topicDlabelName">
                    <option value="">--学习标签--</option>-->
                </select>
                <input type="hidden"  value="" name="topicDlabelId" id="topicDlabelId"/>
            </div>
        </div>
        <div class="col-sm-9">
            <label class="col-sm-9 control-label no-padding-left" for="recordName">正确答案为(<small class="red" id="rightAnwer"></small>)</label>
        </div>
    </div>
</div>
<!-- 考题模板 end-->
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
<script src="${ctx}/js/course/course_add_topic_index.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>
