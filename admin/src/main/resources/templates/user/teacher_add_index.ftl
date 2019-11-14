<#assign title="添加讲师信息"/>
<#assign menuId="feb6ac8a64e244b0958f5db4cf3dad7a"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<style>
    .combo  .combo-text validatebox-text{
        width:447px;
    }
    .form-horizontal .form-group {
        margin-left: -10%;
        margin-right: -12px;
    }
    .ace-file-input{
        width: 41.9%;
    }
</style>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
 <#--add-start-->
<!-- 用户添加Modal -->
<div id="addModal-type"  >
    <form id="add-form-user" class="form-horizontal"  enctype="multipart/form-data">
        <input type="hidden" name="userType" id="userType" value="1"/> <#--类型 1：教头，2：用户-->
        <input type="hidden" name="data" id="data" value=""/>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>讲师分类:</label>
            <div class="col-sm-9">
                <select id="userTypeId" name="userTypeId" class="easyui-combotree" style=" width: 465.667px;height:34px;" >
                </select>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userName"><small class="red">*</small>真实姓名:</label>
            <div class="col-sm-9">
                <input type="text" id="realName" name="realName" placeholder="真实姓名" class="col-xs-12 col-sm-5" value="" onblur=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userName"><small class="red">*</small>讲师姓名:</label>
            <div class="col-sm-9">
                <input type="text" id="userName" name="userName" placeholder="讲师姓名" class="col-xs-12 col-sm-5" value="" onblur=""/>
            </div>
        </div>

        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userName"><small class="red">*</small>讲师昵称:</label>
            <div class="col-sm-9">
                <input type="text" id="nickName" name="nickName" placeholder="讲师昵称" class="col-xs-12 col-sm-5" value="" onblur=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="phone"><small class="red">*</small>讲师电话:</label>
            <div class="col-sm-9">
                <select  class="form-control courseList col-xs-2 col-sm-2"  name="tele_num"  id="tele_num" style="width:8.5%">
                    <option value="">--选择国家--</option>
                </select>
                <input type="text" id="phone" name="phone" placeholder="讲师电话" class="col-xs-12 col-sm-4"  value="" onblur="" />
                <input type="hidden" id="countryCode" name="countryCode" value=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="loginName"><small class="red">*</small>讲师账号:</label>
            <div class="col-sm-9">
                <input type="text" id="loginName" name="loginName" placeholder="讲师账号" class="col-xs-12 col-sm-5" value="" onblur=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="password"><small class="red">*</small>讲师密码:</label>
            <div class="col-sm-9">
                <input type="text" id="password" name="password" placeholder="讲师密码" class="col-xs-12 col-sm-5" value="" onblur=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="seqno"><small class="red">*</small>序列号:</label>
            <div class="col-sm-9">
                <input type="text" id="seqno" name="seqno" placeholder="序列号" class="col-xs-12 col-sm-5" value="" onblur=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"  style="text-align: right;"><small class="red">*</small>账号状态:</label>
            <div class="col-sm-9">
                 <span class="input-icon">
                    <label>
                       <input  type="radio" class="ace col-xs-12 col-sm-5" name="status" value="0"/>
                       <span class="lbl" id="status">停用账号</span>
                    </label>
                    <label style="margin-top:5px;display: inline-block;">
                       <input type="radio" class="ace col-xs-12 col-sm-5"  name="status" value="1" checked = "true"/>
                       <span class="lbl" id="status" >开启账号</span>
                    </label>
                 </span>
            </div>
        </div>
      <#--  <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"  style="text-align: right;"><small class="red">*</small>用户身份:</label>
            <div class="col-sm-9">
                <select  class="form-control col-xs-12 col-sm-4"  name="identity" id="identity" style="width: 41.8%;">
                <option value="1">普通用户</option>
                <option value="2">会员用户</option>
                </select>
            </div>
        </div>-->
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="photo"><small class="red">*</small>用户头像:</label>
            <div class="col-xs-9">
                <input type="file" id="photo" name="file" class="form-control"/>(<small class="red">建议正方形图片长宽比例为1:1建议200px*200px</small>)
            </div>
        </div>
       <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>名优教头图片:</label>
            <div class="col-xs-9">
                <input type="file" id="rectanglePhoto" name="file1" class="form-control"/>(<small class="red">建议与头像内容相同,长方形图片长宽比例为1.725:1建议345*200px</small>)
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userIntro"><small class="red">*</small>讲师简介:</label>
            <div class="col-sm-9">
                <textarea id="userIntroText" name="userIntroText"  style="width: 41.9%;" rows="7" onkeyup="javascript:checkWordNum(this)"></textarea>
                (限500字以内,已输入<span id="word_count">0</span>字,还能输入<span id="word">500</span>字)
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userIntro"><small class="red">*</small>讲师详细简介:</label>
            <div class="col-sm-9">
                <!-- 加载编辑器的容器 -->
                <script id="container" name="container" type="text/plain" style="width:95%;height:height:auto" zIndex="99999999">
                    讲师详细简介
                </script>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" id="adduser">保 存</button>
    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:location.href = base + 'user/teacher/'">返回</button>
</div>

<#--add-end-->
<!-- 实例化编辑器 -->
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
<script src="${ctx}/js/user/teacher_add_index.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="${ctx}/UEditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctx}/UEditor/ueditor.all.js"></script>
  <script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>