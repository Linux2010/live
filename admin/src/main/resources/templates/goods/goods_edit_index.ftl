<#assign title="修改商品"/>
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
<!-- 添加考题-->
<div id="goodsDiv">
    <div class="col-xs-12">
        <div class="modal-header">
             <h4 class="modal-title">修改商品</h4>
        </div>
        <form id="update-form-goods" class="form-horizontal" enctype="multipart/form-data">
            <input type="hidden" name="goodsId" id="goodsId" value="${goods.goodsId!}"/>
            <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="goodsName"><small class="red">*</small> 商品名称:</label>
                 <div class="col-sm-9">
                      <input type="text" id="goodsName" name="goodsName"  placeholder="商品名称" class="col-xs-12 col-sm-5" value="${goods.goodsName!}"/>
                 </div>
              </div>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="goodsNumber"><small class="red">*</small> 商品编号:</label>
                <div class="col-sm-9">
                     <input type="text" id="goodsNumber" name="goodsNumber"  placeholder="商品编号" class="col-xs-12 col-sm-5" value="${goods.goodsNumber!}"/>
                </div>
              </div>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="goodsType"><small class="red">*</small> 商品类别:</label>
                    <div class="col-sm-9" style="padding: 8px 19px 3px 14px;">
                        <input name="goodsType" type="radio" class="ace" value="0" <#if goods.goodsType ==0> checked = "true"</#if> >
                        <span class="lbl">普通商品</span>
                        <input name="goodsType" type="radio" class="ace" value="1"  <#if goods.goodsType ==1> checked = "true"</#if> >
                        <span class="lbl">银两商品</span>
                  </div>
             </div>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="unit"><small class="red">*</small> 商品单位:</label>
                 <div class="col-sm-9">
                      <input type="text" id="unit" name="unit"  placeholder="商品单位" class="col-xs-12 col-sm-5" value="${goods.unit!}"/>
                </div>
            </div>
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right" for="expreeFee"><small class="red">*</small> 商品运费:</label>
                  <div class="col-sm-9">
                        <input type="text" id="expreeFee" name="expreeFee"  placeholder="商品运费" class="col-xs-12 col-sm-5" value="${goods.expreeFee!}"/>
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
                        <input name="isSoldOut" type="radio" class="ace" value="0" <#if goods.isSoldOut ==0> checked = "true"</#if> />
                        <span class="lbl">下架</span>
                        <input name="isSoldOut" type="radio" class="ace" value="1"  <#if goods.isSoldOut ==1> checked = "true"</#if> >
                        <span class="lbl">上架</span>
                  </div>
            </div>

            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="seqno"><small class="red">*</small> 商品序号:</label>
                    <div class="col-sm-9" >
                         <input type="text" id="seqno" name="seqno" placeholder="序号" value="${goods.seqno}" />
                         <span class="help-inline " style="width:50%;" >
                    </div>
            </div>

            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                   <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片1:</label>
                   <input type="hidden" name="imgs[0].goodsImgId" id="goodsImgId1"  value="<#if goods.imgs[0] ??>${goods.imgs[0].goodsImgId !}</#if>"/>
                   <div class="col-xs-9">
                        <div class="col-xs-5">
                               <input type="file" id="imageUrl1" name="file1" class="form-control"/>
                        </div>
                        <div class="col-xs-3" style="text-align: center;">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                              <input name="imgs[0].first" type="radio" class="ace" value="1"
                                        <#if goods.imgs ?? && (goods.imgs?size>0) >
                                            <#list goods.imgs as item>
                                                <#if item_index == 0>
                                                    <#if item.first == 1>
                                                       checked = "true"
                                                    </#if>
                                                </#if>
                                            </#list>
                                        <#else>
                                            checked = "true"
                                        </#if>
                                       >
                              <span class="lbl">主图</span>
                              <input name="imgs[0].first" type="radio" class="ace" value="0"
                                            <#if goods.imgs ??>
                                                <#list goods.imgs as item>
                                                    <#if item_index == 0>
                                                        <#if item.first == 0>
                                                        checked = "true"
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                            >
                              <span class="lbl">非主图</span>
                        </div>
                        <div class="col-xs-3">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                              <input type="text" id="seqno1" name="imgs[0].seqno" placeholder="序号"  value="<#if goods.imgs[0] ??>${goods.imgs[0].seqno !}<#else>1</#if>" />
                              <span class="help-inline " style="width:50%;" >
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
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片2:</label>
                  <input type="hidden" name="imgs[1].goodsImgId" id="goodsImgId2"  value="<#if goods.imgs[1] ??>${goods.imgs[1].goodsImgId !}</#if>"/>
                  <div class="col-xs-9">
                        <div class="col-xs-5">
                              <input type="file" id="imageUrl2" name="file2" class="form-control"/>
                        </div>
                        <div class="col-xs-3" style="text-align: center;">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                              <input name="imgs[1].first" type="radio" class="ace" value="1"
                                 <#if goods.imgs ?? && (goods.imgs?size>0) &&  goods.imgs[1] ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 1>
                                                <#if item.first == 1>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    </#if>
                                    >
                              <span class="lbl">主图</span>
                              <input name="imgs[1].first" type="radio" class="ace" value="0"
                                   <#if goods.imgs ?? && (goods.imgs?size>0) &&  goods.imgs[1] ??>
                                    <#list goods.imgs as item>
                                        <#if item_index == 1>
                                            <#if item.first == 0>
                                            checked = "true"
                                            </#if>
                                        </#if>
                                    </#list>
                                   <#else>
                                   checked = "true"
                                    </#if>
                                    >
                              <span class="lbl">非主图</span>
                        </div>
                        <div class="col-xs-3">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                              <input type="text" id="seqno2" name="imgs[1].seqno" placeholder="序号"   value="<#if goods.imgs[1] ??>${goods.imgs[1].seqno !}<#else>2</#if>" />
                              <span class="help-inline " style="width:50%;" >
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
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片3:</label>
                 <input type="hidden" name="imgs[2].goodsImgId" id="goodsImgId3"   value="<#if goods.imgs[2] ??>${goods.imgs[2].goodsImgId !}</#if>"/>
                 <div class="col-xs-9">
                        <div class="col-xs-5">
                              <input type="file" id="imageUrl3" name="file3" class="form-control"/>
                        </div>
                        <div class="col-xs-3" style="text-align: center;">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                              <input name="imgs[2].first" type="radio" class="ace" value="1"
                                        <#if goods.imgs ?? && (goods.imgs?size>0) &&  goods.imgs[2] ??>
                                            <#list goods.imgs as item>
                                                <#if item_index == 2>
                                                    <#if item.first == 1>
                                                    checked = "true"
                                                    </#if>
                                                </#if>
                                            </#list>
                                        </#if>
                                        >
                              <span class="lbl">主图</span>
                              <input name="imgs[2].first" type="radio" class="ace" value="0"
                                    <#if goods.imgs ?? && (goods.imgs?size>0) &&  goods.imgs[2] ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 2>
                                                <#if item.first ==0>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    <#else>
                                    checked = "true"
                                    </#if>
                                    >
                              <span class="lbl">非主图</span>
                        </div>
                        <div class="col-xs-3">
                              <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                              <input type="text" id="seqno3" name="imgs[2].seqno" placeholder="序号"  value="<#if goods.imgs[2] ??>${goods.imgs[2].seqno !}<#else>3</#if>"  />
                              <span class="help-inline " style="width:50%;" >
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
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                 <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片4:</label>
                 <input type="hidden" name="imgs[3].goodsImgId" id="goodsImgId4" value="<#if goods.imgs[3] ??>${goods.imgs[3].goodsImgId !}</#if>"/>
                 <div class="col-xs-9">
                       <div class="col-xs-5">
                             <input type="file" id="imageUrl4" name="file4" class="form-control"/>
                       </div>
                       <div class="col-xs-3" style="text-align: center;">
                             <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                             <input name="imgs[3].first" type="radio" class="ace" value="1"
                                    <#if goods.imgs ?? && (goods.imgs?size>0) &&  goods.imgs[3] ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 3>
                                                <#if item.first ==1>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    </#if>
                                    >
                             <span class="lbl">主图</span>
                             <input name="imgs[3].first" type="radio" class="ace" value="0"
                                    <#if goods.imgs ??  && (goods.imgs?size>0) &&  goods.imgs[3] ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 3>
                                                <#if item.first ==0>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    <#else>
                                       checked = "true"
                                    </#if>
                                    >
                             <span class="lbl">非主图</span>
                       </div>
                       <div class="col-xs-3">
                            <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                            <input type="text" id="seqno4" name="imgs[3].seqno" placeholder="序号"  value="<#if goods.imgs[3] ??>${goods.imgs[3].seqno !}<#else>4</#if>"  />
                            <span class="help-inline " style="width:50%;" >
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
            <div class="hr hr-18 dotted"></div>
            <div class="form-group">
                  <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品图片5:</label>
                  <input type="hidden" name="imgs[4].goodsImgId" id="goodsImgId5"   value="<#if goods.imgs[4] ??>${goods.imgs[4].goodsImgId !}</#if>"/>
                  <div class="col-xs-9">
                       <div class="col-xs-5">
                             <input type="file" id="imageUrl5" name="file5" class="form-control"/>
                       </div>
                       <div class="col-xs-3" style="text-align: center;">
                            <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>是否主图:</label>
                            <input name="imgs[4].first" type="radio" class="ace" value="1"
                                    <#if goods.imgs ?? && (goods.imgs?size>0) &&  goods.imgs[4] ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 4>
                                                <#if item.first ==1>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    </#if>
                                    >
                            <span class="lbl">主图</span>
                            <input name="imgs[4].first" type="radio" class="ace" value="0"
                                    <#if goods.imgs ?? && (goods.imgs?size>0) &&  goods.imgs[4] ??>
                                        <#list goods.imgs as item>
                                            <#if item_index == 4>
                                                <#if item.first ==0>
                                                checked = "true"
                                                </#if>
                                            </#if>
                                        </#list>
                                    <#else>
                                       checked = "true"
                                    </#if>
                                    >
                            <span class="lbl">非主图</span>
                       </div>
                       <div class="col-xs-3">
                             <label class="control-label no-padding-right" for="imageUrl"><small class="red">*</small>图片序号:</label>
                             <input type="text" id="seqno5" name="imgs[4].seqno" placeholder="序号" value="<#if goods.imgs[4] ??>${goods.imgs[4].seqno !}<#else>5</#if>" />
                             <span class="help-inline " style="width:50%;" >
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
        <div id="goodsSpecDiv">
                      <div id="specs">
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
       <#-- <div class="form-group">
              <label class="col-sm-3 "></label>
              <div class="col-sm-9">
                   <a href="javascript:(0)"  class="btn btn-white btn-sm btn-primary" id="addSpecs">添加规格项目</a>(<small class="red">如果商品没有规格不用点此操作</small>)
              </div>
        </div>-->
                     <div class="form-group kucun">
                            <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><small class="red">*</small>商品库存：</label>
                            <div class="col-sm-9" id="goodsStock">
                                <#-- 无规格商品-->
                                <#if  goodsSpecs ?? && (goodsSpecs?size==1) && (goodsSkus ??) && (goodsSkus?size ==0 ) >
                                     <div id="goodsSpecPriceStock" style="display: block;" class="col-sm-9">
                                          <input type="hidden" value="${goodsSpecs[0].goodsSpecId!}" name="goodsSpecs[0].goodsSpecId" id="goodsSpecId"/>
                                          <input type="hidden" value="${goodsSpecs[0].specId!}" name="goodsSpecs[0].specId" id="specId"/>
                                          <div class="col-sm-2" style="padding-top: 10px;" id="goodsSpecValueSpan">
                                               <span id="goodsSpecValuesId" goodsSpecId=${goodsSpecs[0].goodsSpecId!} style="border-color: #393939;color: #393939!important;border: 1px solid;">无规格商品</span>&nbsp;&nbsp;
                                          </div>
                                          <div class="col-sm-10">
                                              <label class="control-label no-padding-right" for="weight">重量(克)：</label>
                                              <input type="text" id="weight" name="goodsSpecs[0].weight" value="${goodsSpecs[0].weight?c!}" class="priceStock"/>
                                              <label class="control-label no-padding-right" for="goodsSpecs[0].costPrice"><small class="red">*</small>成本价格：</label>
                                              <input type="text" id="costPrice" name="goodsSpecs[0].costPrice" value="${goodsSpecs[0].costPrice?c!}"  class="priceStock"/>
                                              <label class="control-label no-padding-right" for="userPrice"><small class="red">*</small>用户价格：</label>
                                              <input type="text" id="userPrice" name="goodsSpecs[0].userPrice" value="${goodsSpecs[0].userPrice?c!}"  class="priceStock"/>
                                              <label class="control-label no-padding-right" for="vipPrice"><small class="red">*</small>会员价格：</label>
                                              <input type="text" id="vipPrice" name="goodsSpecs[0].vipPrice" value="${goodsSpecs[0].vipPrice?c!}"   class="priceStock" />
                                              <label class=" control-label no-padding-right" for="num"><small class="red">*</small>总库存：</label>
                                              <input type="text" id="num" name="goodsSpecs[0].stockNum" value="${goodsSpecs[0].stockNum?c!}"  class="priceStock" />
                                          </div>
                                      </div>
                                <#else>
                                     <#list goodsSpecs as goodsSpec>
                                         <div id="goodsSpecPriceStock" style="display: block;" class="col-sm-9">
                                              <input type="hidden" value="${goodsSpec.goodsSpecId!}" name="goodsSpecs[${goodsSpec_index}].goodsSpecId" id="goodsSpecId"/>
                                               <input type="hidden" value="${goodsSpecs[0].specId!}" name="goodsSpecs[${goodsSpec_index}].specId" id="specId"/>
                                              <div class="col-sm-2" style="padding-top: 10px;" id="goodsSpecValueSpan">
                                             <#if goodsSpec.goodsSpecValuesId??>
                                                 <input type="hidden" value="${goodsSpec.goodsSpecValuesId!}" name="goodsSpecs[${goodsSpec_index}].goodsSpecValuesId"/>
                                                 <span id="goodsSpecValuesId" goodsSpecValuesId="${goodsSpec.goodsSpecValuesId}" specid="${goodsSpec.specId}" style="border-color: #393939;color: #393939!important;border: 1px solid;">${goodsSpec.valuesName}</span>&nbsp;&nbsp;;
                                             </#if>
                                         <#if goodsSpec.goodsSpecValuesId1??>
                                             <input type="hidden" value="${goodsSpec.goodsSpecValuesId1!}" name="goodsSpecs[${goodsSpec_index}].goodsSpecValuesId1"/>
                                             <span id="goodsSpecValuesId" goodsSpecValuesId1="${goodsSpec.goodsSpecValuesId1}" specid="${goodsSpec.specId1}" style="border-color: #393939;color: #393939!important;border: 1px solid;">${goodsSpec.valuesName1}</span>&nbsp;&nbsp;;
                                         </#if>
                                         <#if goodsSpec.goodsSpecValuesId2??>
                                             <input type="hidden" value="${goodsSpec.goodsSpecValuesId2!}" name="goodsSpecs[${goodsSpec_index}].goodsSpecValuesId2"/>
                                             <span id="goodsSpecValuesId" goodsSpecValuesId2="${goodsSpec.goodsSpecValuesId2}" specid="${goodsSpec.specId2}" style="border-color: #393939;color: #393939!important;border: 1px solid;">${goodsSpec.valuesName2}</span>&nbsp;&nbsp;;
                                         </#if>
                                         <#if goodsSpec.goodsSpecValuesId3??>
                                             <input type="hidden" value="${goodsSpec.goodsSpecValuesId3!}" name="goodsSpecs[${goodsSpec_index}].goodsSpecValuesId3"/>
                                             <span id="goodsSpecValuesId" goodsSpecValuesId3="${goodsSpec.goodsSpecValuesId3}" specid="${goodsSpec.specId3}" style="border-color: #393939;color: #393939!important;border: 1px solid;">${goodsSpec.valuesName3}</span>&nbsp;&nbsp;;
                                         </#if>
                                         <#if goodsSpec.goodsSpecValuesId4??>
                                             <input type="hidden" value="${goodsSpec.goodsSpecValuesId4!}" name="goodsSpecs[${goodsSpec_index}].goodsSpecValuesId4"/>
                                             <span id="goodsSpecValuesId" goodsSpecValuesId4="${goodsSpec.goodsSpecValuesId4}" specid="${goodsSpec.specId4}" style="border-color: #393939;color: #393939!important;border: 1px solid;">${goodsSpec.valuesName4}</span>&nbsp;&nbsp;;
                                         </#if>
                                              </div>
                                              <div class="col-sm-10">
                                                    <label class="control-label no-padding-right" for="weight">重量(克)：</label>
                                                    <input type="text" id="weight" name="goodsSpecs[${goodsSpec_index}].weight" value="${goodsSpec.weight?c!}" class="priceStock">
                                                    <label class="control-label no-padding-right" for="costPrice"><small class="red">*</small>成本价格：</label>
                                                    <input type="text" id="costPrice" name="goodsSpecs[${goodsSpec_index}].costPrice" value="${goodsSpec.costPrice?c!}" class="priceStock">
                                                    <label class="control-label no-padding-right" for="userPrice"><small class="red">*</small>用户价格：</label>
                                                    <input type="text" id="userPrice" name="goodsSpecs[${goodsSpec_index}].userPrice" value="${goodsSpec.userPrice?c!}" class="priceStock">
                                                    <label class="control-label no-padding-right" for="vipPrice"><small class="red">*</small>会员价格：</label>
                                                    <input type="text" id="vipPrice" name="goodsSpecs[${goodsSpec_index}].vipPrice" value="${goodsSpec.vipPrice?c!}" class="priceStock">
                                                    <label class=" control-label no-padding-right" for="stockNum"><small class="red">*</small>总库存：</label>
                                                    <input type="text" id="num" name="goodsSpecs[${goodsSpec_index}].stockNum" value="${goodsSpec.stockNum?c!}" class="priceStock">
                                              </div>
                                       </div>
                                     </#list>
                                </#if>
                            </div>
                     </div>
            </div>
       </form>
    </div>
    <div class="modal-footer" style="text-align:center">
         <button type="button" class="btn btn-primary" id="addGoods">修改</button>
         <button type="button" class="btn btn-default" onclick="javascript:location.href=base+'goods/goods_index'">返回商品列表</button>
    </div>
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
<script src="${ctx}/js/goods/goods_edit_index.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>
