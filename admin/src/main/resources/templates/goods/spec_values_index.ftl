<#assign title="商品规格管理"/>
<#assign menuId="4859906ff80b41f0acf0fa0ed13b55af"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<style>
    .ace-spinner middle touch-spinner{
        width: 365px;
    }
</style>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<input type="hidden" id="specId" name="specId" value="${specId!}"/>
<div class="col-xs-12">
    <span style="font-weight: 800;">规格 》 ${specName!}</span>
</div>
<div class="col-xs-12" style="margin-top: 10px;">
    <form class="form-horizontal" id="searchForm" >
        <div class="form-group">
            <div class="col-sm-6">
                <input type="text" id="valuesName" placeholder="分类名称" class="col-xs-12">
            </div>
            <div class="col-sm-6">
                <button type="button" class="btn btn-info btn-sm" id="spec-values-search">查询</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="spec-values-add">添加</button>
                &nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-info btn-sm" id="spec-values-back" onclick="javascript:back()">返回</button>
                &nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </form>
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->

<div id="dialog">
</div>

<!-- 分类修改Modal -->
<div class="modal fade" id="updateModal-specValues" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改规格值</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-specValues" class="form-horizontal">
                    <input type="hidden" id="specId" name="specId" value="${specId!}"/>
                    <input type="hidden" id="goodsSpecValuesId" name="goodsSpecValuesId" value=""/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="valuesName"><small class="red">*</small>规格值名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="valuesName" name="valuesName" placeholder="规格值名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="status"><small class="red">*</small>是否显示:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="status" name="status" value="1"/>
                            是
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="status" name="status" value="0"/>
                            否
                        </div>
                    </div>
                    <div class="hr hr-18 dotted" ></div>
                    <div class="form-group" >
                        <label class="col-sm-3 control-label no-padding-right" for="type"><small class="red">*</small>规格值类型:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="type" name="type" value="1"/>
                            文字
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="type" name="type" value="2"/>
                            图片
                        </div>
                    </div>
                    <div class="hr hr-18 dotted sepecValueType"></div>
                    <div class="form-group sepecValueType">
                        <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>规格值图片:</label>
                        <div class="col-xs-9">
                            <input type="file" id="imageUrl" name="file" class="form-control"/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="remark">规格值备注:</label>
                        <div class="col-sm-9">
                            <input type="text" id="remark" name="remark" value=""  class="col-xs-12 col-sm-8"/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="seqno"><small class="red">*</small>规格值序号:</label>
                        <div class="col-sm-9">
                            <input type="text" id="seqno" name="seqno" placeholder="序号" class="col-xs-12 col-sm-5" />
                            <span class="help-inline col-xs-12 col-sm-7" style="width: 365px;" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateSpecValues">修 改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--add-end-->
<!-- 分类添加Modal -->
<div class="modal fade" id="addModal-specValues" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加规格值</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-specValues" class="form-horizontal">
                    <input type="hidden" id="specId" name="specId" value="${specId!}"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">规格:</label>
                        <div class="col-sm-9" style="padding-top: 9px;">
                            <span id="parentType">${specName!}</span>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="valuesName"><small class="red">*</small>规格值名称:</label>
                        <div class="col-sm-9">
                            <input type="text" id="valuesName" name="valuesName" placeholder="规格值名称" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>是否显示:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="status" name="status" value="1" checked="checked"/>
                            是
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" id="status" name="status" value="0"/>
                            否
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="type"><small class="red">*</small>规格值类型:</label>
                        <div class="col-sm-9" style="padding-top: 7px;">
                            <input type="radio" id="type" name="type" value="1" checked="true"/>
                            文字
                            <input type="radio" id="type" name="type" value="2"/>
                            图片
                        </div>
                    </div>
                    <div class="hr hr-18 dotted sepecValueType" style="display:none"></div>
                    <div class="form-group sepecValueType" style="display:none">
                        <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>规格值图片:</label>
                        <div class="col-xs-9">
                            <input type="file" id="imageUrl" name="file" class="form-control"/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="remark">规格值备注:</label>
                        <div class="col-xs-9">
                            <input type="text" id="remark" name="remark" class="form-control"/>
                        </div>
                    </div>

                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="seqno"><small class="red">*</small>规格值序号:</label>
                        <div class="col-sm-9" >
                            <input type="text" id="seqno" name="seqno" placeholder="序号" class="col-xs-12 col-sm-5"  />
                            <span class="help-inline col-xs-12 col-sm-7" style="width: 365px;" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addSpecValues">添 加</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<#--add-end-->
<!-- 图片展示Modal -->
<div class="modal fade" id="imgModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">规格图片</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <img src="" id="img"/>
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
<script src="${ctx}/assets/js/spinbox.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/js/goods/spec_values_index.js"></script>
<#include "../common/body_bottom.ftl"/>