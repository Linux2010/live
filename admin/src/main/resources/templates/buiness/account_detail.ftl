<#assign title="用户管理 > 用户详情"/>
<#assign menuId="15"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<style>
    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height:auto;
        vertical-align:text-top;
        padding-top:2px;
    }
</style>
<input type="hidden" id="userId" name="userId" value="${userId}"/>
<div class="col-xs-12" style="margin-top: 150px">

    <table id="grid-table">

    </table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

    <div class="table-detail">
        <div class="row">
            <div class="col-xs-12 col-sm-2">
                <div class="text-center">
                    <img height="150" class="thumbnail inline no-margin-bottom" alt="Domain Owner's Avatar" id="photo" src="" />
                    <br />
                    <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                        <div class="inline position-relative">
                            <a class="user-title-label" href="#">
                                <i class="ace-icon fa fa-circle light-green"></i>
                                &nbsp;
                                <span class="white" id="nick_name"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 col-sm-7">
                <div class="space visible-xs"></div>

                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name"> 用户名 </div>

                        <div class="profile-info-value">
                            <span id="user_name"></span>
                        </div>
                    </div>


                    <div class="profile-info-row">
                        <div class="profile-info-name">  真实姓名 </div>

                        <div class="profile-info-value">
                            <span id="real_name"></span>
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name"> 地址 </div>

                        <div class="profile-info-value">
                            <i class="fa fa-map-marker light-orange bigger-110"></i>
                            <span id="address"></span>
                        </div>
                    </div>


                    <div class="profile-info-row">
                        <div class="profile-info-name"> 用户身份 </div>

                        <div class="profile-info-value">
                            <span id="user_identity"></span>
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name">会员到期时间</div>

                        <div class="profile-info-value">
                            <span id="e_date"></span>
                        </div>
                    </div>

                    <div class="profile-info-row">
                        <div class="profile-info-name"> 我的学习标签 </div>

                        <div class="profile-info-value">
                            <span id="label_name"></span>
                        </div>
                    </div>
                </div>
            </div>
</div>
<div style="text-align: center;margin-top: 120px" align="center" >
    <button class="btn btn-info" id="return_index">返回</button>
</div>
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
<script src="${ctx}/js/user/account_detail.js"></script>
<#include "../common/body_bottom.ftl"/>
