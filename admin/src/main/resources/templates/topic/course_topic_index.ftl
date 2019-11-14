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
        margin-left: -10%;
        margin-right: -12px;
    }
</style
</style>
<div class="nav-search col-xs-12" id="nav-search" >
    <form class="form-search" id="form-search" method="post">
        <div class="form-group">
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="recordName" style="text-align: right;"> 关键字:</label>
                <div class="col-sm-9">
                    <input type="text" placeholder="查找 课程名/考题名称" class="nav-search-input input-xxlarge" style="width:30%" id="nav-search-input" autocomplete="off" name="keyword"/>
                </div>
            </div>
        </div>
        <div class="col-sm-1">
            <input type="button" class="btn btn-info btn-sm" value="查找" id="find" />
        </div>

    </form>
</div><!-- /.nav-search -->
<div class="col-xs-12" style="margin-top: 80px">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">
</div>

<#--add-start->
<!-- 添加考题-->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 100%;height:auto">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加考题</h4>
            </div>
            <div class="modal-body" style="max-height:768px; overflow:scroll; ">
                <form id="add-form-topic" class="form-horizontal">
                    <div class="form-group">
                        <input id="course_topic_examination_id" name="course_topic_examination_id" type="hidden"/>
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 考卷名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="examinationName" name="examinationName" placeholder="考卷名称" class="col-xs-12 col-sm-12" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>课程类别:</label>
                        <div class="col-sm-9">
                                <select id="courseTypeId" name="courseTypeId" class="col-xs-12 col-sm-5 easyui-combotree" style="width: 250px;height:34px; padding-right:2px;padding-right:2px;" >
                                </select>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 选择课程</label>
                        <div class="col-sm-9">
                            <select  class="form-control courseList"  name="courseList"  id="courseList">
                                <option value="">--课程名称--</option>
                            </select>
                            <input type="hidden" id="courseId" name="courseId" value=""/>
                        </div>
                    </div>
                   <div class="hr hr-18 dotted"></div>
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
                        <label class="col-sm-6 control-label no-padding-left"  style="text-align: left;margin-left: 13.8%;" for="recordName">
                            <button type="button" class="btn btn-primary" id="addTopic">添加考题</button> <span class="lbl">(添加考题并选择答案)</span>
                        </label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addTopics">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--add-end-->
<#--edit-start->
<!--编辑考题-->
<div class="modal fade" id="editModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 100%;height:auto">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑考题</h4>
            </div>
            <div class="modal-body" style="max-height:768px; overflow:scroll; ">
                <form id="edit-form-topic" class="form-horizontal">
                    <div class="form-group">
                        <input id="course_topic_examination_id" name="course_topic_examination_id" type="hidden"/>
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 考卷名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="examinationName" name="examinationName" placeholder="考卷名称" class="col-xs-12 col-sm-12" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>课程类别:</label>
                        <div class="col-sm-9">
                            <select id="courseTypeId" name="courseTypeId" class="col-xs-12 col-sm-5 easyui-combotree" style="width: 200px;height:34px; padding-right:2px;padding-right:2px;" >
                            </select>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 选择课程</label>
                        <div class="col-sm-9">
                            <select  class="form-control courseList"  name="courseList"  id="courseList">
                                <option value="">--课程名称--</option>
                            </select>
                            <input type="hidden" id="courseId" name="courseId" value=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
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
                        <label class="col-sm-6 control-label no-padding-left"  style="text-align: left;margin-left: 13.8%;" for="recordName">
                            <button type="button" class="btn btn-primary" id="addTopic">添加考题</button> <span class="lbl">(添加考题并选择答案)</span>
                        </label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="editTopics">更新</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--edit-end-->
<!-- 考题详情-->
<div class="modal fade" id="detailModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 100%;height:auto">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">考题详情</h4>
            </div>
            <div class="modal-body" style="max-height:768px; overflow:scroll; ">
                <form id="detail-form-topic" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 考卷名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="examinationName" name="examinationName" placeholder="考卷名称" class="col-xs-12 col-sm-12"   readonly="readonly"  value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>课程名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="courseName" name="courseName" placeholder="课程名称" class="col-xs-12 col-sm-12"   readonly="readonly"  value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 考题类型 </label>
                        <div class="col-sm-9" style="padding: 8px 19px 3px 14px;">
                            <input name="isRegisterTopic" type="radio" class="ace" value="0">
                            <span class="lbl">注册考题</span>
                            <input name="isRegisterTopic" type="radio" class="ace" value="1"  checked = "true">
                            <span class="lbl">课程考题</span>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--detail -end-->
<#--考题模板 start-->
<div id="topicTemplate" style="display: none">
    <input type="hidden" id="topicNo" name="topicNo" value=""/>
    <input type="hidden" id="course_topic_id" name="course_topic_id" value=""/>
    <input type="hidden" id="topic_id" name="topic_id" value=""/>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-left" for="recordName">题目<span class="topicIndex">1</span>： </label>
        <div class="col-sm-9">
            <input type="text" id="topicName" name="topicName" placeholder="题目名称"  class="col-xs-12 col-sm-12"  value="" onblur=""/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="recordName"> </label>
        <div class="col-sm-9">
            <div class="col-sm-2 no-padding-left">
                A.<input type="checkbox" id="topicAvalueCheck"  name="topicAvalueCheck" value="0">
            </div>
            <div class="col-sm-10">
                <input type="text" id="topicAvalue" name="topicAvalue" placeholder="A选项"  value=""  class="col-xs-12 col-sm-12" />
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
            <div class="col-sm-2 no-padding-left">
                B.<input type="checkbox"  id="topicBvalueCheck"  name="topicBvalueCheck" value="0">
            </div>
            <div class="col-sm-10">
                <input type="text" id="topicBvalue" name="topicBvalue" placeholder="B选项"  value=""  class="col-xs-12 col-sm-12" />
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
            <div class="col-sm-2 no-padding-left">
                C.<input type="checkbox" id="topicCvalueCheck"  name="topicCvalueCheck" value="0">
            </div>
            <div class="col-sm-10">
                <input type="text" id="topicCvalue" name="topicCvalue" placeholder="C选项"  value=""  class="col-xs-12 col-sm-12" />
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
            <div class="col-sm-2 no-padding-left">
                D.<input type="checkbox" id="topicDvalueCheck"  name="topicDvalueCheck" value="0">
            </div>
            <div class="col-sm-10">
                <input type="text" id="topicDvalue" name="topicDvalue" placeholder="D选项"  value=""  class="col-xs-12 col-sm-12" />
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
<script src="${ctx}/js/course/course_topic_index.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>
