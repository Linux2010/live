<#assign title="私信列表"/>
<#assign menuId="71"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<link rel="stylesheet" href="${ctx}/css/admin/customer/user.letter.send.css"/>

<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>
<div class="modal-header">
    <button type="button" onclick="back()"><i class="fa fa-arrow-left"></i></button>
</div>
<#if pageInfo??>
<div class="col-xs-12">
    <div class="widget-box">
        <div class="widget-header">
            <h4 class="widget-title lighter smaller">
                <i class="ace-icon fa fa-comment blue"></i>
                私信内容
            </h4>
        </div>
        <div class="widget-body">
            <div class="widget-main no-padding">
                <div class="scroll" style="overflow:auto; max-height:630px;border: 1px solid #999;">
                    <div class="scroll-content" style="max-height: 630px;"></div></div>
                <form id="sendMessage-form" class="form-horizontal">
                    <input type="hidden" name="sendUserId" value="-1">
                    <input type="hidden" name="receiveUserId" value="${userId}">
                    <input type="hidden" name="relationId" value="${userId}">
                    <input type="hidden" name="relationType" value="4">
                    <input type="hidden" name="messageType" value="1">
                    <input type="hidden" name="classify" value="1">
                    <input type="hidden" name="userIds" value="" id="userIds">
                    <div class="form-actions">
                        <div class="input-group">
                            <input placeholder="请输入内容" type="text" class="form-control" name="content" id="content" onkeydown="if(event.keyCode==13){return false;}">
                            <span class="input-group-btn">
                                <button class="btn btn-sm btn-info no-radius" type="button" id="sendMessageBtn">
                                    <i class="ace-icon fa fa-share"></i>
                                     发送
                                </button>
                            </span>
                        </div>
                    </div>
                </form>
            </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->
    </div>
</div>
</#if>
<#--add-end-->
<#--add-end-->
<#include "../common/body_middle.ftl"/>
<#include "../common/bootstrap_modal.ftl">
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/grid.locale-en.js"></script>

<script src="${ctx}/assets/js/jquery.inputlimiter.min.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-ui.min.js"></script>
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/js/jquery.form.js"></script>
<script src="${ctx}/assets/js/bootstrap.modal.js"></script>
<script src="${ctx}/assets/js/laydate.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.js"></script>
<script src="${ctx}/js/base.js"></script>
<script>
    $('.scroll').scrollTop( $('.scroll')[0].scrollHeight );
    <#if pageInfo??>
    var page = 1;
    var userId = '${userId}';
    $(".scroll").scroll(function(){
        var h = $(this).height();//div可视区域的高度
        var sh = $(this)[0].scrollHeight;//滚动的高度，$(this)指代jQuery对象，而$(this)[0]指代的是dom节点
        var st =$(this)[0].scrollTop;//滚动条的高度，即滚动条的当前位置到div顶部的距离
        if(h+st>=sh){}
        if(st == 0){
           if(page != 0 && page !=-1){
               loadPage();
           }else if(page == 0){
               $(".scroll-content").after('<div class="alert alert-block alert-success gray center">--The End--</div>');
               page = -1;
           }
        }
    })
    var loadPage = function(){
        var html='';
        $.get(base + "letter/letterListPage",{
            rows:${pageInfo.pageSize!'10'},
            page:page,
            userId:userId
        },function(data){
            if("success" == data.status){
                if(true == data.data.hasNextPage){
                    page+=1;
                }else{
                    page = 0;
                }
                $.each(data.data.list, function(k, message) {
                    if(message.sendUserId == "-1"){
                        html += '<div class="itemdiv dialogdiv">'+
                                '   <div class="userright">'+
                                '       <img alt="平台系统" src="../assets/images/avatars/user.jpg">'+
                                '    </div>'+
                                '    <div class="dialogue clearfix fr">'+
                                '       <span class="triangle right"></span>'+
                                '       <div class="article">'+message.content+'</div>'+
                                '     </div>'+
                                '</div>';
                    }else{
                        html += '<div class="itemdiv dialogdiv">'+
                                '   <div class="user">'+
                                '       <img alt="'+message.userName+'" src="../assets/images/avatars/avatar2.png">'+
                                '    </div>'+
                                '    <div class="dialogue clearfix">'+
                                '       <span class="triangle right"></span>'+
                                '       <div class="article">'+message.content+'</div>'+
                                '     </div>'+
                                '</div>';
                    }
                });
                $(".scroll-content").after(html);
            }
        },"json");
    }
    loadPage();
    </#if>
    $("#sendMessageBtn").bind("click",function(){
        if(isEmpty($("#content").val())){
            Modal.alert({msg : "内容为空", title : "提示", btnok : "确定", btncl : "取消"});
            return;
        }
        $.ajax({
            url:base+"mail/addRecord",
            data:$("#sendMessage-form").serialize(),
            type:"POST",
            dateType:"json",
            success:function(data){
                if("success" == data.status){
                    Modal.alert({msg : "发送成功！", title : "提示", btnok : "确定", btncl : "取消"});
                    $("#content").val("");
                    $("#userIds").val("");
                    location.reload();
                }else{
                    Modal.alert({msg : data.message, title : "提示", btnok : "确定", btncl : "取消"});
                }
            },
            error:function(data){
                Modal.alert("系统异常，请稍后重试！");
            }
        })
    })

    var back = function(){
        location.href = base + "letter/";
    }
    
    var add = function () {
        alert("ddddddddd");
    }


</script>
<#include "../common/body_bottom.ftl"/>
