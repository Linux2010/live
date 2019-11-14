<#assign title="讲师管理"/>
<#assign menuId="feb6ac8a64e244b0958f5db4cf3dad7a"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<style>
   .modal .form-horizontal .form-group {
        margin-left: -10%;
        margin-right: -12px;
    }
</style>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="col-xs-12" >
    <form id ="form-search" class="form-horizontal">
        <div class="form-group" style="margin-top: 30px;margin-bottom: 0px;">
            <label class="col-sm-1 control-label no-padding-right" for="recordName" style="text-align: right;"> 关键字:</label>
            <div class="col-sm-3">
                    <span class="input-icon">
                        <input type="text" placeholder="用户名/昵称/标签名/真实姓名/手机号" class="nav-search-input " id="nav-search-input" autocomplete="off" name="keyword" style="width: 146%;"/>
                        <i class="ace-icon fa fa-search nav-search-icon"></i>
                    </span>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName"  style="text-align: right;">账号状态:</label>
            <div class="col-sm-3">
                <span class="input-icon" style="margin-top: 6px;">
                    <label>
                       <input type="radio" class="ace" name="status" value="0"/>
                       <span class="lbl" id="status1">已停用账号</span>
                   </label>
                   <label style="margin-bottom:50px;display: inline-block;">
                       <input  type="radio" class="ace"  name="status" value="1" checked = "true"/>
                       <span class="lbl" id="status2" >已开启账号</span>
                   </label>
                </span>
            </div>
            <label class="col-sm-1 control-label no-padding-right" for="recordName"  style="text-align: right;">用户身份:</label>
            <div class="col-sm-2" style="text-align: left;">
                <select  class="form-control nav-search-input text-left " name="identity" id="identity" style="width: 55.7%;">
                    <option value="1">普通用户</option>
                    <option value="2" selected="selected">会员用户</option>
                </select>
            </div>
            <div class="col-sm-1">
                <input type="button" class="btn btn-info btn-sm" value="查找" id="find" />
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>


<#--update-start-->
<!-- 用户修改Modal -->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" >
    <div class="modal-dialog">
        <div class="modal-content" style="width: 180%;height:auto">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改讲师信息</h4>
            </div>
            <div class="col-xs-12 modal-body" style="max-height:600px; overflow:scroll; " >
                <form id="update-form-user" class="form-horizontal"  enctype="multipart/form-data">
                    <input type="hidden" name="userId" id="userId" value=""/>
                    <input type="hidden" name="userType" id="userType" value="1"/> <#--类型 1：教头，2：用户-->
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>讲师分类:</label>
                        <div class="col-sm-9">
                            <select id="userTypeId" name="userTypeId" class="col-xs-12 col-sm-12 easyui-combotree" style="width: 200px;height:34px; padding-right:2px;padding-right:2px;" >
                            </select>
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
                        <label class="col-sm-3 control-label no-padding-right" for="phone"><small class="red">*</small>讲师电话:</label>
                        <div class="col-sm-9">
                            <input type="text" id="phone" name="phone" placeholder="讲师电话" class="col-xs-12 col-sm-5" value="" onblur=""/>
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
                                   <span class="lbl" id="status">已停用账号</span>
                                </label>
                                <label style="margin-top:5px;display: inline-block;">
                                   <input type="radio" class="ace col-xs-12 col-sm-5"  name="status" value="1" checked = "true"/>
                                   <span class="lbl" id="status" >已开启账号</span>
                                </label>
                             </span>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"  style="text-align: right;"><small class="red">*</small>用户身份:</label>
                        <div class="col-sm-9">
                            <select  class="form-control col-xs-12 col-sm-4 name="identity" id="identity" style="width: 42.9%;">
                            <option value="1">普通用户</option>
                            <option value="2">会员用户</option>
                            </select>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="userIntro"><small class="red">*</small>讲师简介:</label>
                        <div class="col-sm-9">
                        <!-- 加载编辑器的容器 -->
                        <script id="updateContainer" name="updateContainer" type="text/plain" style="width:95%;height:height:90%"  zIndex="99999999">
                            讲师简介
                        </script>
                        </div>
                        </div>
                     <div class="hr hr-18 dotted"></div>
                 </form>
             </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateUser">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
         </div><!-- /.modal-content -->
     </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
 <#--update-end-->


 <#--add-start-->
<!-- 用户添加Modal -->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" >
    <div class="modal-dialog"  style="z-index:8;">
        <div class="modal-content" style="width: 180%;height:auto">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加讲师信息</h4>
            </div>
            <div class="col-xs-12 modal-body" style="max-height:600px; overflow:scroll; " >
                <form id="add-form-user" class="form-horizontal"  enctype="multipart/form-data">
                    <input type="hidden" name="userType" id="userType" value="1"/> <#--类型 1：教头，2：用户-->
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>讲师分类:</label>
                        <div class="col-sm-9">
                            <select id="userTypeId" name="userTypeId" class="col-xs-12 col-sm-12 easyui-combotree" style="width: 200px;height:34px; padding-right:2px;padding-right:2px;" >
                            </select>
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
                        <label class="col-sm-3 control-label no-padding-right" for="phone"><small class="red">*</small>讲师电话:</label>
                        <div class="col-sm-9">
                            <input type="text" id="phone" name="phone" placeholder="讲师电话" class="col-xs-12 col-sm-5" value="" onblur=""/>
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
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"  style="text-align: right;"><small class="red">*</small>用户身份:</label>
                        <div class="col-sm-9">
                            <select  class="form-control col-xs-12 col-sm-4 name="identity" id="identity" style="width: 42.9%;">
                            <option value="1">普通用户</option>
                            <option value="2">会员用户</option>
                            </select>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="userIntro"><small class="red">*</small>讲师简介:</label>
                        <div class="col-sm-9">
                            <!-- 加载编辑器的容器 -->
                            <script id="container" name="container" type="text/plain" style="width:95%;height:height:auto" zIndex="99999999">
                                讲师简介
                            </script>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="adduser">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--add-end-->
<#-- 是讲师简介 -->
<div class="modal fade" id="introModal-type" tabindex="1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" >
    <div class="modal-dialog">
        <div class="modal-content" style="width: 100%;height:auto">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">讲师简介</h4>
            </div>
            <div class="modal-body" style="max-height:768px; overflow:scroll; " >
                <form id="intro-form" class="form-horizontal"  enctype="multipart/form-data">
                    <div class="form-group" id="introId" style="margin-left:1%;">
                        <!-- 加载编辑器的容器
                        <script id="userIntroContainer" name="userIntro" type="text/plain"  zIndex="99999999">
                            讲师简介
                        </script>-->
                     </div>
                </form>
            </div>
            <div class="modal-footer">
                  <!--<button type="button" class="btn btn-primary" id="adduser">保 存</button>-->
                  <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
 </div><!-- /.modal -->
 <#-- 是讲师简介 -->

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
<script src="${ctx}/js/user/teacher_index.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="${ctx}/UEditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctx}/UEditor/ueditor.all.js"></script>
  <script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>