<#assign title="商品简介"/>
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

<#--add-start->
<!-- 添加或修改商品简介-->
<div class="col-xs-12" style="width:98%;">
    <div class="modal-header">
         <h4 class="modal-title">商品简介</h4>
    </div>
    <form id="goods-intro-form" class="form-horizontal">
        <input type="hidden" name="goodsId" id="goodsId" value="${goodsId!}"/>

        <div class="form-group">
            <div class="col-sm-12">
                <!-- 加载编辑器的容器 -->
                <div  id="container" name="container" type="text/plain" style="width:98%;height:height:auto" zIndex="99999999">
                </div >
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" id="addGoodsIntro">保 存</button>
    <button type="button" class="btn btn-default" onclick="javascript:location.href=base+'goods/goods_index'">返回</button>
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
<script src="${ctx}/assets/js/spinbox.min.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="${ctx}/UEditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctx}/UEditor/ueditor.all.js"></script>
<script src="${ctx}/js/goods/goods_intro_index.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>
