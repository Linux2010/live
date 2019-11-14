<#assign title="优惠劵详情"/>
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

</style
</style>

<#--add-start->
<!-- 添加优惠劵-->
<div class="col-xs-12">
    <div class="modal-header">
    <h4 class="modal-title">优惠劵详情</h4>
    </div>
    <form id="add-form-coupon" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="couponName"><small class="red">*</small>优惠劵名称:</label>
                <div class="col-sm-9">
                     <input type="text" id="couponName" name="couponName"  placeholder="优惠劵名称" class="col-xs-12 col-sm-5" value="${coupon.couponName!}" onblur="javascript:couponNameOnBlus(this)" readonly="true"/>
                </div>
            </div>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="couponDesc">优惠劵描述:</label>
                <div class="col-sm-9">
                       <input type="text" id="couponDesc" name="couponDesc"  placeholder="优惠劵描述" class="col-xs-12 col-sm-5" value="${coupon.couponDesc!}" readonly="true"/>
                </div>
            </div>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="couponValue"><small class="red">*</small>面值金额:</label>
                <div class="col-sm-9">
                     <input type="text" id="couponValue" name="couponValue"  placeholder="面值金额" class="col-xs-12 col-sm-5" value="${coupon.couponValue!?c}" readonly="true"/>
                </div>
            </div>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="limitMoney"><small class="red">*</small>限制金额:</label>
                 <div class="col-sm-9">
                       <input type="text" id="limitMoney" name="limitMoney"  placeholder="限制金额" class="col-xs-12 col-sm-5" value="${coupon.limitMoney!?c}" readonly="true"/>
                 </div>
           </div>
           <div class="hr hr-18 dotted"></div>
           <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="limitMoney"><small class="red">*</small>使用条件:</label>
                 <div class="col-sm-9">
                      <input type="text" id="limitMoneyDesc" name="limitMoneyDesc"  placeholder="使用条件" class="col-xs-12 col-sm-5" value="${coupon.limitMoneyDesc!}" readonly="true"/>
                 </div>
           </div>
           <div class="hr hr-18 dotted"></div>
           <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="couponEndTimeStr"><small class="red">*</small>截止日期:</label>
                 <div class="col-sm-9">
                     <input type="text" id="couponEndTimeStr" name="couponEndTimeStr" class="form-control date-picker" value="${couponEndTimeStr!}" readonly="true"/>
                 </div>
           </div>
         <#--  <div class="hr hr-18 dotted"></div>
           <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>优惠劵图片:</label>
                 <div class="col-sm-9">
                       <a href="${coupon.couponImgUrl!}" target="_blank" ><img src="${coupon.couponImgUrl!}" id="img2" width="100" height="34"/></a>
                </div>
           </div>
           <div class="hr hr-18 dotted"></div> -->
           <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>优惠劵序号:</label>
                 <div class="col-sm-9">
                       <input type="text" id="seqno" name="seqno" placeholder="序号" value="${coupon.seqno!}" readonly="true"/>
                       <span class="help-inline " style="width:50%;" >
                 </div>
           </div>
           <div class="hr hr-18 dotted"></div>
           <div class="form-group">
               <label class="col-sm-3 control-label no-padding-right" for="imageUrl">优惠商品列表:</label>
               <div class="col-sm-9">
                </div>
           </div>

            <#if goodsList?? && (goodsList?size>0) && (coupon.couponType !=2)>
                <#list  goodsList as goods >
                     <div class="form-group">
                          <div class="col-sm-3"></div>
                                 <div class="col-sm-9">
                                        <div class="col-sm-3">
                                              <label class="col-sm-3 control-label no-padding-left" for="imageUrl"><small class="red">*</small>商品编号:</label>
                                              <input type="text"  class="col-sm-9" value="${goods.goodsNumber!}" readonly="true"/>
                                        </div>
                                        <div class="col-sm-3">
                                             <label class="col-sm-3 control-label no-padding-left" for="imageUrl"><small class="red">*</small>商品类别:</label>
                                             <#if goods.goodsType == 0>
                                                <input type="text"  class="col-sm-9" value="普通商品" readonly="true"/>
                                             <#else>
                                                <input type="text"  class="col-sm-9" value="银两商品" readonly="true"/>
                                            </#if>
                                        </div>
                                        <div class="col-sm-6">
                                              <label class="col-sm-3 control-label no-padding-left" for="imageUrl"><small class="red">*</small>商品名称:</label>
                                              <input type="text"  class="col-sm-9"  value="${goods.goodsName!}" readonly="true"/>
                                       </div>
                                  </div>

                            </div>
                </#list>
            <#else>
                <div class="form-group">
                   <div class="col-sm-3"></div>
                  <#if coupon.couponType !=2>
                     <div class="col-sm-9"> 暂无优惠商品</div>
                  <#else>
                     <div class="col-sm-9"> 全部商品</div>
                  </#if>
                </div>
            </#if>
         </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" onclick="javascript:location.href=base+'coupon/coupon_index'">返回</button>
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
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>                                                                                                    <script src="${ctx}/js/goods/goods_detail_index.js"></script>
<#include "../common/body_bottom.ftl"/>
