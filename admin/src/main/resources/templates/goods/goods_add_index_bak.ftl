<#assign title="发布商品"/>
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
    .col-sm-2 input {
        width: 18px;
        height: 18px;
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
</style>

<#--add-start->
<!-- 发布商品-->
<div class="col-xs-12">
    <div class="modal-header">
         <h4 class="modal-title">发布商品</h4>
    </div>
    <form id="add-form-goods" class="form-horizontal">
        <div class="form-group">
             <label class="col-sm-3 control-label no-padding-right" for="goodsName"><small class="red">*</small> 商品名称:</label>
             <div class="col-sm-9">
                  <input type="text" id="goodsName" name="goodsName"  placeholder="商品名称" class="col-xs-12 col-sm-5" value="" onblur="javascript:goodsNameOnBlus(this)"/>
             </div>
          </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="goodsNumber"><small class="red">*</small> 商品编号:</label>
            <div class="col-sm-9">
                 <input type="text" id="goodsNumber" name="goodsNumber"  placeholder="商品编号" class="col-xs-12 col-sm-5" value="" onblur="javascript:goodsNumberOnBlus(this)"/>(<small class="red">11位以下英文或数字组成</small>)
            </div>
          </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="goodsType"><small class="red">*</small> 商品类别:</label>
                <div class="col-sm-9" style="padding: 8px 19px 3px 14px;">
                    <input name="goodsType" type="radio" class="ace" value="0"  checked = "true">
                    <span class="lbl">普通商品</span>
                    <input name="goodsType" type="radio" class="ace" value="1" >
                    <span class="lbl">银两商品</span>
              </div>
         </div>
        <div class="hr hr-18 dotted"></div>
         <div class="form-group">
               <label class="col-sm-3 control-label no-padding-right" for="costPrice"><small class="red">*</small> 成本价格:</label>
               <div class="col-sm-9">
                   <input type="text" id="costPrice" name="costPrice"  placeholder="商品成本价" class="col-xs-12 col-sm-5" value=""/>(<small class="red">保留两位小数</small>)
                </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
             <label class="col-sm-3 control-label no-padding-right" for="mktPrice"><small class="red">*</small> 市场价格:</label>
             <div class="col-sm-9">
                 <input type="text" id="mktPrice" name="mktPrice"  placeholder="商品成本价" class="col-xs-12 col-sm-5" value=""/>(<small class="red">保留两位小数</small>)
              </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="price"><small class="red">*</small> 会员价格:</label>
              <div class="col-sm-9">
                  <input type="text" id="price" name="price"  placeholder="会员价格" class="col-xs-12 col-sm-5" value=""/>(<small class="red">保留两位小数</small>)
              </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
             <label class="col-sm-3 control-label no-padding-right" for="unit"><small class="red">*</small> 商品单位:</label>
             <div class="col-sm-9">
                  <input type="text" id="unit" name="unit"  placeholder="商品单位" class="col-xs-12 col-sm-5" value=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="expreeFee"><small class="red">*</small> 商品运费:</label>
              <div class="col-sm-9">
                    <input type="text" id="expreeFee" name="expreeFee"  placeholder="商品运费" class="col-xs-12 col-sm-5" value="0"/>
              </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="catId"><small class="red">*</small> 商品分类:</label>
              <div class="col-sm-9">
                    <select id="catId" name="catId" class="col-xs-12 col-sm-12 easyui-combotree" style="width: 250px;height:34px; padding-right:2px;padding-right:2px;" >
                    </select>
              </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
               <label class="col-sm-3 control-label no-padding-right" for="isSoldOut"><small class="red">*</small>是否下架:</label>
                <div class="col-xs-9">
                    <div class="col-sm-8" style="padding: 8px 0px 3px 14px;">
                        <input name="isSoldOut" type="radio" class="ace" value="0" >
                        <span class="lbl">下架</span>
                        <input name="isSoldOut" type="radio" class="ace" value="1"  checked = "true">
                        <span class="lbl">上架</span>
                    </div>
                    <div class="col-sm-4" >
                        <label class="control-label no-padding-right" for="seqno"><small class="red">*</small> 商品序号:</label>
                        <input type="text" id="seqno" name="seqno" placeholder="序号" />
                        <span class="help-inline " style="width:50%;" >
                    </div>
                </div>

        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片1:</label>
            <div class="col-xs-9">
                <div class="col-xs-5">
                    <input type="file" id="imageUrl1" name="file1" class="form-control"/>
                </div>
                <div class="col-xs-3" style="text-align: center;">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                    <input name="first1" type="radio" class="ace" value="1"  checked = "true">
                    <span class="lbl">主图</span>
                    <input name="first1" type="radio" class="ace" value="0" >
                    <span class="lbl">非主图</span>
                </div>
                <div class="col-xs-4">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                    <input type="text" id="seqno1" name="seqno1" placeholder="序号" value="1"/>
                    <span class="help-inline " style="width:50%;" >
                </div>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片2:</label>
            <div class="col-xs-9">
                <div class="col-xs-5">
                    <input type="file" id="imageUrl2" name="file2" class="form-control"/>
                </div>
                <div class="col-xs-3" style="text-align: center;">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                    <input name="first2" type="radio" class="ace" value="1"  >
                    <span class="lbl">主图</span>
                    <input name="first2" type="radio" class="ace" value="0"  checked = "true">
                    <span class="lbl">非主图</span>
                </div>
                <div class="col-xs-4">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                    <input type="text" id="seqno2" name="seqno2" placeholder="序号" value="2"/>
                    <span class="help-inline " style="width:50%;" >
                </div>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片3:</label>
            <div class="col-xs-9">
                <div class="col-xs-5">
                    <input type="file" id="imageUrl3" name="file3" class="form-control"/>
                </div>
                <div class="col-xs-3" style="text-align: center;">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                    <input name="first3" type="radio" class="ace" value="1" >
                    <span class="lbl">主图</span>
                    <input name="first3" type="radio" class="ace" value="0"  checked = "true">
                    <span class="lbl">非主图</span>
                </div>
                <div class="col-xs-3">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                    <input type="text" id="seqno3" name="seqno3" placeholder="序号" value="3"/>
                    <span class="help-inline " style="width:50%;" >
                </div>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片4:</label>
            <div class="col-xs-9">
                <div class="col-xs-5">
                    <input type="file" id="imageUrl4" name="file4" class="form-control"/>
                </div>
                <div class="col-xs-3" style="text-align: center;">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                    <input name="first4" type="radio" class="ace" value="1"  >
                    <span class="lbl">主图</span>
                    <input name="first4" type="radio" class="ace" value="0" checked = "true" >
                    <span class="lbl">非主图</span>
                </div>
                <div class="col-xs-4">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                    <input type="text" id="seqno4" name="seqno4" placeholder="序号" value="4" />
                    <span class="help-inline " style="width:50%;" >
                </div>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片5:</label>
            <div class="col-xs-9">
                <div class="col-xs-5">
                    <input type="file" id="imageUrl5" name="file5" class="form-control"/>
                </div>
                <div class="col-xs-3" style="text-align: center;">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                    <input name="first5" type="radio" class="ace" value="1" >
                    <span class="lbl">主图</span>
                    <input name="first5" type="radio" class="ace" value="0"  checked = "true" >
                    <span class="lbl">非主图</span>
                </div>
                <div class="col-xs-4">
                    <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                    <input type="text" id="seqno5" name="seqno5" placeholder="序号" value="5"/>
                    <span class="help-inline " style="width:50%;" >
                </div>
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" id="addGoods">保 存</button>
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
<script src="${ctx}/js/goods/goods_add_index.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>
