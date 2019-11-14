<#assign title="商品管理"/>
<#assign menuId="9cf02b462d6b47b9a38f4cf5f35ed7e9"/>
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
</style
</style>
<div class="nav-search col-xs-12" id="nav-search" >
    <form class="form-search" id="form-search" method="post">
        <div class="form-group">
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="recordName" style="text-align: right;"> 关键字:</label>
                <div class="col-sm-9">
                    <input type="text" placeholder="查找 商品名/商品编号/商品简介" class="col-xs-12" id="keyword" name="keyword"/>
                </div>
            </div>
        </div>
        <div class="col-sm-1">
            <input type="button" class="btn btn-info btn-sm" value="查找" id="find" />
        </div>

    </form>
</div>
<div class="col-xs-12" style="margin-top: 80px">
    <table id="grid-table"></table>
    <div id="grid-pager"></div>
</div><!-- /.col -->
<div id="dialog">
</div>
<div class="modal fade" id="goods-intro-Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
           <div class="modal-content" style="width: 100%;height:auto">
                  <div class="modal-header">
                       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       <h4 class="modal-title">商品详细</h4>
                 </div>
                 <div class="modal-body" style="max-height:768px; overflow:scroll; ">
                     <form id="goods-intro-form" class="form-horizontal"  enctype="multipart/form-data">
                          <input type="hidden" name="goodsIntro" id="goodsIntro" value=""/>
                          <div class="form-group">
                                 <div class="col-sm-12">
                                        <!-- 加载编辑器的容器 -->
                                        <script id="container" name="container" type="text/plain" style="width:100%;height:height:auto" zIndex="99999999">
                                              商品详细简介
                                        </script>
                                </div>
                          </div>
                          <div class="hr hr-18 dotted"></div>
                    </form>
                 </div>
                                                                                                                                                                                                                        <div class="modal-footer">
                       <button type="button" class="btn btn-primary" id="addGoodsIntro">保存修改</button>
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
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script src="${ctx}/js/goods/goods_index.js"></script>

<#include "../common/body_bottom.ftl"/>
