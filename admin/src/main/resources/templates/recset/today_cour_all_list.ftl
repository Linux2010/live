<#assign title="今日课程推荐  >  课程列表"/>
<#assign menuId="68"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<a  id ="return_page" href="javascript:void(0);" style="margin-left: 20px;top:20px;"><i class="glyphicon glyphicon-arrow-left bigger-130"></i></a>
<input type="hidden" id="pass_keyword" name="pass_keyword" value="${pass_keyword}"/>
<div class="col-xs-12">
<#--  标签搜索  -->
    <form class="form-horizontal">
        <div class="form-group" style="margin-bottom: -30px">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="prizeNum">学习标签:</label>
                <div class="col-sm-9">
                    <select  name="keyword" id="keyword" class="form-control" id="form-field-select-1" style="width: 520px;margin-bottom: 30px">
                        <option value="">--请选择--</option>
                    <#list study_tag as st>
                        <option value="${st.labelname}" <#if '${st.labelname}' == '${pass_keyword}'>selected</#if>  >${st.labelname}</option>
                    </#list>
                    </select>
                </div>
            </div>
        </div>
    </form>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog"></div>
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
<script src="${ctx}/js/recset/today_cour_all_list.js"></script>
<#include "../common/body_bottom.ftl"/>
