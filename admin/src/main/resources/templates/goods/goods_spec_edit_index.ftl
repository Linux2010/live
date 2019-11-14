<#assign title="修改商品库存/规格"/>
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
    .nav-search .nav-search-input {
        width: 180px;
    }
    .col-sm-2 {
        width: 10%;
        padding: 5px 2px 4px 6px;
    }
    .col-sm-3 control-label no-padding-right{
        text-align: right;
    }
    .form-horizontal .form-group {
        margin-left: -20%;
        margin-right: -12px;
    }
    .col-sm-1 no-padding-left{
        text-align: right;
    }
    .priceStock{
        width: 50px;
        height: 34px;
    }

</style>

<#--add-start->
<!-- 修改商品规格-->
<div class="col-xs-12">
    <div class="modal-header">
         <h4 class="modal-title">商品:${goods.goodsName!}</h4>
    </div>
    <form id="goods-spec-form" class="form-horizontal">
        <input type="hidden" name="goodsId" id="goodsId" value="${goods.goodsId!}"/>
        <div id="specs">
        </div>
        <div class="form-group">
                <label class="col-sm-3 ">
                </label>
                <div class="col-sm-9">
                    <#-- <a href="javascript:(0)"  class="btn btn-white btn-sm btn-primary" id="addSpecs">添加规格项目</a>-->
                </div>
       </div>
       <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品库存：</label>
              <div class="col-sm-9" id="goodsStock">
              </div>
       </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" id="updateGoodsSpecs">保 存</button>
    <button type="button" class="btn btn-default" onclick="javascript:location.href=base+'goods/goods_index'">返回</button>
</div>
<#--add-end-->
<div id="specTemplements" style="display:none;">
    <div class="form-group">
          <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品规格：</label>
          <div class="col-sm-9">
                <select id="specId" name="specId" class="col-xs-12 col-sm-12" style="width: 250px;height:34px; padding-right:2px;padding-right:2px;" >
                </select>
          </div>
    </div>
    <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"></label>
            <div class="col-xs-9" id="specValues">
            </div>
    </div>
</div>
<div id="priceStock" style="display:none;" class="col-sm-9">
    <div class="col-sm-2" style="padding-top: 10px;" id="goodsSpecValueSpan">
    </div>
    <div class="col-sm-10">
        <label class="control-label no-padding-right" for="weight">重量(克)：</label>
        <input type="text" id="weight" name="weight" value="0" class="priceStock"/>
        <label class="control-label no-padding-right" for="costPrice"><small class="red">*</small>成本价格：</label>
        <input type="text" id="costPrice" name="costPrice" value=""  class="priceStock"/>
        <label class="control-label no-padding-right" for="userPrice"><small class="red">*</small>用户价格：</label>
        <input type="text" id="userPrice" name="userPrice" value="${goods.mktPrice!}"  class="priceStock"/>
        <label class="control-label no-padding-right" for="vipPrice"><small class="red">*</small>会员价格：</label>
        <input type="text" id="vipPrice" name="vipPrice" value="${goods.price!}"   class="priceStock" />
        <label class=" control-label no-padding-right" for="num"><small class="red">*</small>总库存：</label>
        <input type="text" id="num" name="num" value="100"  class="priceStock" />
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
<script src="${ctx}/assets/js/spinbox.min.js"></script>
<script src="${ctx}/js/goods/cartesian.js"></script>
<script src="${ctx}/js/goods/goods_spec_edit_index.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>
