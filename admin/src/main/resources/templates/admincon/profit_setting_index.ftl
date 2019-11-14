<#assign title="分润设置"/>
<#assign menuId="5cc410e0ad3b45b689d04a672b2e49d0"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<div class="col-xs-12">
    <form class="form-horizontal hide">
        <div class="form-group">
            <div class="col-sm-8">
                <input type="text" id="user-keyword-1" placeholder=" " class="col-xs-2 ">
                 &nbsp;&nbsp;<button type="button" class="btn btn-info btn-sm" id="searchBtn">查询</button>
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div id="dialog">
</div>

<!-- 修改Modal -->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改分润</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll;">
                <form id="form_update" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">分润类型 : </label>
                        <div class="col-sm-9">
                            <select class="col-xs-12 col-sm-5" id="type_update" name="type">
                                <option value=""></option>
                                <option value="1">课程分润</option>
                                <option value="2">商品分润</option>
                                <option value="3">会员分润</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">一级分润规则 (%) :</label>
                        <div class="col-sm-9">
                            <input type="text" id="primaryRule_update" name="primaryRule" placeholder="0.00%" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">二级分润规则 (%) :</label>
                        <div class="col-sm-9">
                            <input type="text" id="secondaryRule_update" name="secondaryRule" placeholder="0.00%" class="col-xs-12 col-sm-5" value=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2">是否开启: </label>
                        <div class="col-sm-9">
                            <div class="radio" id="isOpen_radio_update">
                                <label>
                                    <input name="isOpen" type="radio" class="ace" value="0">
                                    <span class="lbl"> 不开启 </span>
                                </label>
                                <label>
                                    <input name="isOpen" type="radio" class="ace" value="1">
                                    <span class="lbl"> 开启 </span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <input id="setId_update" name="setId" type="hidden">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateBtn">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 添加Modal -->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加规则</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll;">
                <form id="form_add" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 分润类型:</label>
                        <div class="col-sm-9">
                            <select class="col-xs-12 col-sm-5" id="type_add" name="type">
                                <option value=""></option>
                                <option value="1">课程分润</option>
                                <option value="2">商品分润</option>
                                <option value="3">会员分润</option>
                            </select>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 一级分润规则:</label>
                        <div class="col-sm-9">
                            <input type="text" id="primaryRule_add" name="primaryRule" placeholder="0.00%" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small> 二级分润规则:</label>
                        <div class="col-sm-9">
                            <input type="text" id="secondaryRule_add" name="secondaryRule" placeholder="0.00%" class="col-xs-12 col-sm-5" value=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-2"><small class="red">*</small> 是否开启: </label>
                        <div class="col-sm-9">
                            <div class="radio">
                                <label>
                                    <input name="isOpen" type="radio" class="ace" value="0">
                                    <span class="lbl"> 不开启 </span>
                                </label>
                                <label>
                                    <input name="isOpen" type="radio" class="ace" value="1" checked>
                                    <span class="lbl"> 开启 </span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addBtn">保 存</button>
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
<script src="${ctx}/js/admincon/profit_setting.js"></script>
<#include "../common/body_bottom.ftl"/>
