<#assign title="好文推荐"/>
<#assign menuId="66"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>


<div class="" id="nav-search" style="position: absolute;width:80%; margin-left: 200px" >
    <div class="row" style="margin-top: 10px;">
        <div  style="" class="col-sm-4">
            <label class="col-xs-4 control-label no-padding-right" for="prizeNum" style="text-align: right;line-height: 33px;">推荐状态:</label>
            <div class="col-xs-6">
                <select  name="status" id="status_goodArticle" class="form-control" id="form-field-select-1" >
                    <option value="">--请选择--</option>
                    <option value="1">已推荐</option>
                    <option value="2">未推荐</option>
                </select>
            </div>
        </div>
    </div>
</div>
<div class="col-xs-12" style="margin-top: 60px">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>

<!--添加好文推荐-->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 710px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加文章</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-goodArticle" class="form-horizontal" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" id="title" style="width: 19%">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" id="title_add" name="title" maxlength="10"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="width: 19%">封面：</label>
                        <div class="col-sm-9">
                            <input type="file" id="cover_add" name="file"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="width: 19%">是否推荐：</label>
                        <div class="col-sm-9" style="padding-top: 8px">
                            <input type="radio" name="status_add" class="col-xs-12 col-sm-1" value="1"/><div style="float: left">是</div>
                            <input type="radio" name="status_add" class="col-xs-12 col-sm-1" value="2"/><div style="float: left">否</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="width: 19%">序列值：</label>
                        <div class="col-sm-9">
                            <input type="text" id="seqno_add" name="seqno"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_goodArticle">确 定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div>
    </div>
</div>


<!--修改好文推荐-->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 710px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改文章</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-goodArticle" class="form-horizontal" enctype="multipart/form-data">
                    <input type="hidden" name="goodArticleId" id="goodArticleId_update"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" id="title" style="width: 19%">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" id="goodArticleTitle_update" name="title" maxlength="10"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="width: 19%">封面：</label>
                        <div class="col-sm-9">
                            <input type="file" id="goodArticlCover_update" name="file"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="width: 19%">序列值：</label>
                        <div class="col-sm-9">
                            <input type="text" id="goodArticlSeqno_update" name="seqno"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update_goodArticle">确 定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div>
    </div>
</div>

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
<script src="${ctx}/js/goodArticle/goodArticle.js"></script>
<#include "../common/body_bottom.ftl"/>
