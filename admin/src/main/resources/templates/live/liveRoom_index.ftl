<#assign title="直播间管理"/>
<#assign menuId="26"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="col-xs-12">
    <form id ="form-search" class="form-horizontal" style="margin-top: 30px;margin-bottom: -15px;">
        <div class="form-group">
            <div class="col-sm-6">
                    <span class="input-icon">
                        <input type="text" placeholder="查找 直播间名称/聊天室名称/讲师名称/讲师账号/讲师昵称/讲师手机号" class="nav-search-input " id="nav-search-input" autocomplete="off" name="keyword" style="width:253%;"/>
                        <i class="ace-icon fa fa-search nav-search-icon"></i>
                    </span>
            </div>
            <label class="col-sm-2 control-label no-padding-right" for="recordName"  style="text-align: right;">账号状态:</label>
            <div class="col-sm-3">
                <span class="input-icon" style="margin-top: 6px;">
                    <label>
                       <input  type="radio" class="nav-search-input ace " name="status" value="0"/>
                       <span class="lbl" id="status1">已停用</span>
                   </label>
                   <label style="margin-bottom:50px;display: inline-block;">
                       <input  type="radio" class="nav-search-input ace "  name="status" value="1" checked = "true"/>
                       <span class="lbl" id="status2" >已开启</span>
                   </label>
                </span>
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
        <div class="modal-content" style="width: 800px;height:90%">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改直播间信息</h4>
            </div>
            <div class="col-xs-12 modal-body" style="max-height:600px; overflow:scroll; " >
                <form id="update-form-user" class="form-horizontal"  enctype="multipart/form-data">
                    <input type="hidden" name="roomId" id="roomId" value=""/>
                    <input type="hidden" name="cid" id="cid" value=""/>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="userName"><small class="red">*</small>直播间名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="roomName" name="roomName" placeholder="直播间名称" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="phone"><small class="red">*</small>在线人数:</label>
                        <div class="col-sm-9">
                            <input type="text" id="onlineusercount" name="onlineusercount" placeholder="在线人数" class="col-xs-12 col-sm-5" value="" onblur=""/>
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
                                   <input type="radio" class="ace col-xs-12 col-sm-5"  name="status" value="1" />
                                   <span class="lbl" id="status" >开启账号</span>
                                </label>
                             </span>
                        </div>
                    </div>
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
<!-- 直播间添加Modal -->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" >
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px;height:90%">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加直播间</h4>
            </div>
            <div class="col-xs-12 modal-body" style="max-height:600px; overflow:scroll; " >
                <form id="add-form-user" class="form-horizontal"  enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="userName"><small class="red">*</small>直播间名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="roomName" name="roomName" placeholder="直播间名称" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="userTypeId"><small class="red">*</small>讲师分类:</label>
                        <div class="col-sm-9">
                            <select id="userTypeId" name="userTypeId" class="col-xs-12 col-sm-12 easyui-combotree" style="width: 200px;height:34px; padding-right:2px;padding-right:2px;" >
                            </select>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="teacherList"><small class="red">*</small>选择讲师:</label>
                        <div class="col-sm-9">
                            <select  class="form-control col-xs-12 col-sm-12 name="teacherList" id="teacherList" style="width:41%">
                            <option value="">--请选择--</option>
                            </select>
                            <input type="hidden" name="userId" id="userId"/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="onlineusercount"><small class="red">*</small>在线人数:</label>
                        <div class="col-sm-9">
                            <input type="text" id="onlineusercount" name="onlineusercount" placeholder="在线人数" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
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
<script src="${ctx}/js/live/liveRoom_index.js?v=1"></script>
  <script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>
