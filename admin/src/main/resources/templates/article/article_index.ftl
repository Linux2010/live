<#assign title="文章海报管理"/>
<#assign menuId="16"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="nav-search" id="nav-search">
    <form class="form-search" id="form-search" method="post">
								<span class="input-icon">
									<input type="text" placeholder="根据标题查询" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keyword"/>
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
        <input type="button" class="btn btn-blue btn-sm" value="查找" id="find"/>
    </form>
</div><!-- /.nav-search -->
<div class="col-xs-12" style="margin-top: 60px">

    <table id="grid-table"></table>

    <div id="grid-pager"></div>
    <!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
<div id="dialog">

</div>

<!--添加文章海报-->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 710px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加文章</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-article" class="form-horizontal" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" id="title" style="width: 19%">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" id="article_title_add" name="title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="width: 19%">图片：</label>
                        <div class="col-sm-9">
                            <input type="file" id="article_img_add" name="file"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_article">确 定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div>
    </div>
</div>



<!--修改文章海报-->
<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 710px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改文章</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-article" class="form-horizontal" enctype="multipart/form-data">
                    <input type="hidden" id="articleId" name="articleId_update"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" id="title" style="width: 19%">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" id="article_title_update" name="title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="width: 19%">原来的图片路径：</label>
                        <div class="col-sm-9">
                            <input type="text" id="article_img_url" name="imgUrl"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="width: 19%">图片：</label>
                        <div class="col-sm-9">
                            <input type="file" id="article_img_update" name="file"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update_article">确 定</button>
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
<script src="${ctx}/js/article/article.js"></script>
<script type="text/javascript">


</script>
<#include "../common/body_bottom.ftl"/>
