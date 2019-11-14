<#assign title="商品详情"/>
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

    .priceStock{
        width: 80px;
        height: 34px;
    }

</style
</style>

<#--add-start->
<!-- 商品详情-->
<div class="col-xs-12">
    <div class="modal-header">
         <h4 class="modal-title">商品详情</h4>
    </div>
    <form id="update-form-goods" class="form-horizontal">
        <input type="hidden" name="goodsId" id="goodsId" value="${goods.goodsId!}"/>
        <div class="form-group">
             <label class="col-sm-3 control-label no-padding-right" for="goodsName"><small class="red">*</small> 商品名称:</label>
             <div class="col-sm-9">
                  <input type="text" id="goodsName" name="goodsName"  placeholder="商品名称" class="col-xs-12 col-sm-5" value="${goods.goodsName!}" readonly="true"/>
             </div>
          </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="goodsNumber"><small class="red">*</small> 商品编号:</label>
            <div class="col-sm-9">
                 <input type="text" id="goodsNumber" name="goodsNumber"  placeholder="商品编号" class="col-xs-12 col-sm-5" value="${goods.goodsNumber!}" readonly="true"/>
            </div>
          </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="goodsType"><small class="red">*</small> 商品类别:</label>
                <div class="col-sm-9" style="padding: 8px 19px 3px 14px;">
                    <input name="goodsType" type="radio" class="ace" value="0" <#if goods.goodsType ==0> checked = "true"</#if>  disabled="disabled" >
                    <span class="lbl">普通商品</span>
                    <input name="goodsType" type="radio" class="ace" value="1"  <#if goods.goodsType ==1> checked = "true"</#if> disabled="disabled">
                    <span class="lbl">银两商品</span>
              </div>
         </div>
        <#--<div class="hr hr-18 dotted"></div>-->
         <#--<div class="form-group">-->
               <#--<label class="col-sm-3 control-label no-padding-right" for="costPrice"><small class="red">*</small> 成本价格:</label>-->
               <#--<div class="col-sm-9">-->
                    <#--<input type="text" id="costPrice" name="costPrice"  placeholder="商品成本价" class="col-xs-12 col-sm-5" value="${goods.costPrice?c!}" readonly="true"/>-->
                <#--</div>-->
        <#--</div>-->
        <#--<div class="hr hr-18 dotted"></div>-->
        <#--<div class="form-group">-->
             <#--<label class="col-sm-3 control-label no-padding-right" for="mktPrice"><small class="red">*</small> 市场价格:</label>-->
             <#--<div class="col-sm-9">-->
                   <#--<input type="text" id="mktPrice" name="mktPrice"  placeholder="商品成本价" class="col-xs-12 col-sm-5" value="${goods.mktPrice?c!}" readonly="true"/>-->
              <#--</div>-->
        <#--</div>-->
        <#--<div class="hr hr-18 dotted"></div>-->
        <#--<div class="form-group">-->
              <#--<label class="col-sm-3 control-label no-padding-right" for="price"><small class="red">*</small> 会员价格:</label>-->
              <#--<div class="col-sm-9">-->
                   <#--<input type="text" id="price" name="price"  placeholder="会员价格" class="col-xs-12 col-sm-5" value="${goods.price?c!}" readonly="true"/>-->
              <#--</div>-->
        <#--</div>-->
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
             <label class="col-sm-3 control-label no-padding-right" for="unit"><small class="red">*</small> 商品单位:</label>
             <div class="col-sm-9">
                  <input type="text" id="unit" name="unit"  placeholder="商品单位" class="col-xs-12 col-sm-5" value="${goods.unit!}" readonly="true"/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="expreeFee"><small class="red">*</small> 商品运费:</label>
              <div class="col-sm-9">
                    <input type="text" id="expreeFee" name="expreeFee"  placeholder="商品运费" class="col-xs-12 col-sm-5" value="${goods.expreeFee!}" readonly="true"/>
              </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
              <label class="col-sm-3 control-label no-padding-right" for="catId"><small class="red">*</small> 商品分类:</label>
              <div class="col-sm-9">
                    <select id="catId" name="catId" class="col-xs-12 col-sm-12 easyui-combotree" style="width: 250px;height:34px; padding-right:2px;padding-right:2px;" >
                    </select>
                    <input type="hidden" id="catIdStr" name="catIdStr" value="${goods.catId!}"/>
                    <input type="hidden" id="catNameStr" name="catNameStr" value="${goods.catName!}"/>
              </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
               <label class="col-sm-3 control-label no-padding-right" for="isSoldOut"><small class="red">*</small>是否下架:</label>
               <div class="col-sm-9" style="padding: 8px 19px 3px 14px;">
                    <div class="col-sm-3" style="padding: 8px 0px 3px 14px;">
                        <input name="isSoldOut" type="radio" class="ace" value="0" <#if goods.isSoldOut ==0> checked = "true"</#if> disabled="disabled" />
                        <span class="lbl">下架</span>
                         <input name="isSoldOut" type="radio" class="ace" value="1"  <#if goods.isSoldOut ==1> checked = "true"</#if>  disabled="disabled" />
                        <span class="lbl">上架</span>
                     </div>
                    <div class="col-sm-4" >
                        <label class="control-label no-padding-right" for="seqno"><small class="red">*</small> 商品序号:</label>
                        <input type="text" id="seqno" name="seqno" placeholder="序号" value="${goods.seqno}" readonly="true"/>
                    </div>
              </div>
        </div>
        <#if goods.imgs[0] ??>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                   <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片1:</label>
                   <input type="hidden" name="goodsImgId1" id="goodsImgId1"  value="<#if goods.imgs[0] ??>${goods.imgs[0].goodsImgId !}</#if>" />
                   <div class="col-xs-9">
                        <div class="col-xs-3" style="text-align: left;padding-top: 7px;">
                              <input name="first1" type="radio" class="ace" value="1"
                                        <#if goods.imgs ??>
                                            <#list goods.imgs as item>
                                                <#if item_index == 0>
                                                    <#if item.first == 1>
                                                       checked = "true"
                                                    </#if>
                                                <#else>
                                                checked = "true"
                                                </#if>
                                            </#list>
                                        <#else>
                                            checked = "true"
                                        </#if>
        disabled="disabled">
                              <span class="lbl">主图</span>
                              <input name="first1" type="radio" class="ace" value="0"
                                            <#if goods.imgs ??>
                                                <#list goods.imgs as item>
                                                    <#if item_index == 0>
                                                        <#if item.first == 0>
                                                        checked = "true"
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
        disabled="disabled">
                              <span class="lbl">非主图</span>
                        </div>
                        <div class="col-xs-3">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                              <input type="text" id="seqno1" name="seqno1" placeholder="序号"  value="<#if goods.imgs[0] ??>${goods.imgs[0].seqno !} <#else>1</#if>" readonly="true"/>
                        </div>
                        <div class="col-xs-1" style="margin-top: 4px;">
                            <#if goods.imgs ??>
                                <#list goods.imgs as item>
                                    <#if item_index == 0>
                                        <a href="${item.url}" target="_blank" ><img src="${item.url}" id="img1" width="35" height="34"/></a>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                   </div>
            </div>
        </#if>

        <#if goods.imgs[1] ??>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片2:</label>
                  <input type="hidden" name="goodsImgId2" id="goodsImgId2"  value="<#if goods.imgs[1] ??>${goods.imgs[1].goodsImgId !}</#if>"/>
                  <div class="col-xs-9">
                        <div class="col-xs-3" style="text-align: left;padding-top: 7px;">
                              <input name="first2" type="radio" class="ace" value="1"
                                 <#if goods.imgs ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 1>
                                                <#if item.first == 1>
                                                checked = "true"
                                                </#if>
                                            <#else>
                                            checked = "true"
                                            </#if>
                                        </#list>
                                     <#else>
                                     checked = "true"
                                    </#if>
        disabled="disabled">
                              <span class="lbl">主图</span>
                              <input name="first2" type="radio" class="ace" value="0"
                                   <#if goods.imgs ??>
                                    <#list goods.imgs as item>
                                        <#if item_index == 1>
                                            <#if item.first == 0>
                                            checked = "true"
                                            </#if>
                                        </#if>
                                    </#list>
                                    </#if>
        disabled="disabled" >
                              <span class="lbl">非主图</span>
                        </div>
                        <div class="col-xs-3">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                              <input type="text" id="seqno2" name="seqno2" placeholder="序号"   value="<#if goods.imgs[1] ??>${goods.imgs[1].seqno !} <#else>1</#if>"  readonly="true"/>
                        </div>

                        <div class="col-xs-1" style="margin-top: 4px;">
                            <#if goods.imgs ??>
                                <#list goods.imgs as item>
                                    <#if item_index == 1>
                                        <a href="${item.url}" target="_blank" ><img src="${item.url}" id="img2" width="35" height="34"/></a>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                   </div>
            </div>
        </#if>
        <#if goods.imgs[2] ??>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片3:</label>
                 <input type="hidden" name="goodsImgId3" id="goodsImgId3"   value="<#if goods.imgs[2] ??>${goods.imgs[2].goodsImgId !}</#if>"/>
                 <div class="col-xs-9">
                        <div class="col-xs-3" style="text-align: left;padding-top: 7px;">
                              <input name="first3" type="radio" class="ace" value="1"
                                        <#if goods.imgs ??>
                                            <#list goods.imgs as item>
                                                <#if item_index == 2>
                                                    <#if item.first == 1>
                                                    checked = "true"
                                                    </#if>
                                                <#else>
                                                checked = "true"
                                                </#if>
                                            </#list>
                                        <#else>
                                        checked = "true"
                                        </#if>
        disabled="disabled"     >
                              <span class="lbl">主图</span>
                              <input name="first3" type="radio" class="ace" value="0"
                                    <#if goods.imgs ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 2>
                                                <#if item.first ==0>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    </#if>
        disabled="disabled">
                              <span class="lbl">非主图</span>
                        </div>
                        <div class="col-xs-3">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                              <input type="text" id="seqno3" name="seqno3" placeholder="序号"  value="<#if goods.imgs[2] ??>${goods.imgs[2].seqno !} <#else>1</#if>" readonly="true" />
                        </div>

                        <div class="col-xs-1" style="margin-top: 4px;">
                            <#if goods.imgs ??>
                                <#list goods.imgs as item>
                                    <#if item_index == 2>
                                         <a href="${item.url}" target="_blank" ><img src="${item.url}" id="img3" width="35" height="34"/></a>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                 </div>
            </div>
        </#if>
        <#if goods.imgs[3] ??>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片4:</label>
                 <input type="hidden" name="goodsImgId4" id="goodsImgId4" value="<#if goods.imgs[3] ??>${goods.imgs[3].goodsImgId !}</#if>"/>
                 <div class="col-xs-9">
                       <div class="col-xs-3" style="text-align: left;padding-top: 7px;">
                             <input name="first4" type="radio" class="ace" value="1"
                                    <#if goods.imgs ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 3>
                                                <#if item.first ==1>
                                                checked = "true"
                                                </#if>
                                            <#else>
                                            checked = "true"
                                            </#if>
                                        </#list>
                                    <#else>
                                    checked = "true"
                                    </#if>
        disabled="disabled">
                             <span class="lbl">主图</span>
                             <input name="first4" type="radio" class="ace" value="0"
                                    <#if goods.imgs ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 3>
                                                <#if item.first ==0>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    </#if>
        disabled="disabled"  >
                             <span class="lbl">非主图</span>
                       </div>
                       <div class="col-xs-3">
                            <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                            <input type="text" id="seqno4" name="seqno4" placeholder="序号"  value="<#if goods.imgs[3] ??>${goods.imgs[3].seqno !} <#else>1</#if>"  readonly="true"  />
                       </div>
                       <div class="col-xs-1" style="margin-top: 4px;">
                            <#if goods.imgs ??>
                                <#list goods.imgs as item>
                                    <#if item_index == 3>
                                        <a href="${item.url}" target="_blank" ><img src="${item.url}" id="img4" width="35" height="34"/></a>
                                    </#if>
                                </#list>
                            </#if>
                       </div>
                  </div>
            </div>
        </#if>
        <#if goods.imgs[4] ??>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片5:</label>
                  <input type="hidden" name="goodsImgId5" id="goodsImgId5"   value="<#if goods.imgs[4] ??>${goods.imgs[4].goodsImgId !}</#if>"/>
                  <div class="col-xs-9">
                       <div class="col-xs-3" style="text-align: left;padding-top: 7px;">
                            <input name="first5" type="radio" class="ace" value="1"
                                    <#if goods.imgs ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 4>
                                                <#if item.first ==1>
                                                checked = "true"
                                                </#if>
                                            <#else>
                                            checked = "true"
                                            </#if>
                                        </#list>
                                    <#else>
                                    checked = "true"
                                    </#if>
        disabled="disabled">
                            <span class="lbl">主图</span>
                            <input name="first5" type="radio" class="ace" value="0"
                                    <#if goods.imgs ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 4>
                                                <#if item.first ==0>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    </#if>
        disabled="disabled">
                            <span class="lbl">非主图</span>
                       </div>
                       <div class="col-xs-3">
                             <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                             <input type="text" id="seqno5" name="seqno5" placeholder="序号"   value="<#if goods.imgs[4] ??>${goods.imgs[4].seqno !} <#else>1</#if>" readonly="true"/>
                       </div>

                       <div class="col-xs-1" style="margin-top: 4px;">
                            <#if goods.imgs ??>
                                <#list goods.imgs as item>
                                    <#if item_index == 4>
                                        <a href="${item.url}" target="_blank" ><img src="${item.url}" id="img5" width="35" height="34"/></a>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                  </div>
            </div>
        </#if>
        <div class="hr hr-18 dotted"></div>
        <div id="goodsSpecDiv">
            <#if goodsSkus ??>
                <#list goodsSkus as skus>
                <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品规格：</label>
                                                                                                                         <div class="col-sm-9">
                <select id="specId" name="specId" disabled="disabled" class="col-xs-12 col-sm-12" style="width: 250px;height:34px; padding-right:2px;padding-right:2px;" index="0">
                    <#list specs as spec>
                    <option value="${spec.specId!}" <#if skus.specId = spec.specId> selected="selected"</#if>>${spec.specName!}</option>
                    </#list>
                </select>
                  </div>
                    </div>
                      <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right" for="imageUrl"></label>
                                                                                        <div class="col-xs-9" id="specValues">
                    <#list specs as spec>
                        <#if skus.specId = spec.specId>
                            <#list spec.specValuesList as specValue>
                            <div class="col-sm-2">
                            <input   type="checkbox"  id="goodsSpecValuesId"
                            value="${specValue.goodsSpecValuesId}"
                            name="goodsSpecValuesId"
                            onclick="onClickHander(this)" specid="${spec.specId}"
                                <#list goodsSpecs as goodsSpec>
                                    <#if skus.specId = goodsSpec.specId && (goodsSpec.goodsSpecValuesId =specValue.goodsSpecValuesId) >
                                    checked="checked"
                                    <#elseif goodsSpec.specId1?? &&(skus.specId = goodsSpec.specId1 && (goodsSpec.goodsSpecValuesId1 =specValue.goodsSpecValuesId))  >
                                    checked="checked"
                                    <#elseif  goodsSpec.specId2 ?? &&(skus.specId = goodsSpec.specId2 && goodsSpec.goodsSpecValuesId2 =specValue.goodsSpecValuesId) >
                                    checked="checked"
                                    <#elseif goodsSpec.specId3?? &&(skus.specId = goodsSpec.specId3 && goodsSpec.goodsSpecValuesId3 =specValue.goodsSpecValuesId)  >
                                    checked="checked"
                                    <#elseif goodsSpec.specId4?? &&(skus.specId = goodsSpec.specId4 && goodsSpec.goodsSpecValuesId4 =specValue.goodsSpecValuesId)  >
                                    checked="checked"
                                    <#else>
                                    </#if>
                                </#list>
                            disabled="disabled">
                            <span id="valuesName" style="border-color: #393939;color: #393939!important;border: 1px solid;">${specValue.valuesName}</span>
                                                                                                                                                     </div>
                            </#list>
                        </#if>
                    </#list>
                <#--<div class="col-sm-2">
                    <a href="http://192.168.1.162:8888/admin/spec/spec_values_index?specId=7c444c90a50d4909a944f314b9981c40">+添加</a>
                </div>-->
                </div>
                  </div>
                </#list>
            </#if>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品规格库存:</label>
            <div class="col-sm-9">
                <#if  goods.goodsSpecs?? && ( goods.goodsSpecs?size > 0) >
                    <#list goods.goodsSpecs as item>
                            <div id="goodsSpecPriceStock" style="display: block;" class="col-sm-12">
                                    <div class="col-sm-2" style="padding-top: 10px;" id="goodsSpecValueSpan">
                                        <#if item.valuesName??>
                                             <span id="goodsSpecValuesId" style="border-color: #393939;color: #393939!important;border: 1px solid;">${item.valuesName!}</span>&nbsp;&nbsp;;
                                        <#elseif item.valuesName1??>
                                             <span id="goodsSpecValuesId" style="border-color: #393939;color: #393939!important;border: 1px solid;">${item.valuesName1!}</span>&nbsp;&nbsp;;
                                        <#elseif item.valuesName2??>
                                            <span id="goodsSpecValuesId" style="border-color: #393939;color: #393939!important;border: 1px solid;">${item.valuesName2!}</span>&nbsp;&nbsp;;
                                        <#elseif item.valuesName3??>
                                            <span id="goodsSpecValuesId" style="border-color: #393939;color: #393939!important;border: 1px solid;">${item.valuesName3!}</span>&nbsp;&nbsp;;
                                       <#elseif item.valuesName4??>
                                           <span id="goodsSpecValuesId" style="border-color: #393939;color: #393939!important;border: 1px solid;">${item.valuesName4!}</span>&nbsp;&nbsp;;    <#else>无规格商品</#if>
                                    </div>
                                    <div class="col-sm-10">
                                        <label class="control-label no-padding-right" for="weight">重量(克)：</label>
                                        <input type="text" id="weight" name="weight" value="${item.weight?c!}"  readonly="true"  class="priceStock">
                                        <label class="control-label no-padding-right" for="costPrice"><small class="red">*</small>成本价格：</label>
                                        <input type="text" id="costPrice" name="costPrice" value="${item.userPrice?c!}"  readonly="true"  class="priceStock">
                                        <label class="control-label no-padding-right" for="userPrice"><small class="red">*</small>用户价格：</label>
                                        <input type="text" id="userPrice" name="userPrice" value="${item.userPrice?c!}"  readonly="true"  class="priceStock"/>
                                        <label class="control-label no-padding-right" for="vipPrice"><small class="red">*</small>会员价格：</label>
                                        <input type="text" id="vipPrice" name="vipPrice" value="${item.vipPrice?c!}"  readonly="true"  class="priceStock"/>
                                        <label class=" control-label no-padding-right" for="num"><small class="red">*</small>总库存：</label>
                                        <input type="text" id="num" name="num" value="${item.stockNum?c!}"  readonly="true"  class="priceStock"/>
                                    </div>
                           </div>
                    </#list>
                </#if>
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
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
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>                                                                                                    <script src="${ctx}/js/goods/goods_detail_index.js"></script>
<#include "../common/body_bottom.ftl"/>
