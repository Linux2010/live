<#assign title="广告管理"/>
<#assign menuId="92"/>
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
									<input type="text" placeholder="根据名称查询" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keyword"/>
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

<!--上传APP开启视频-->
<div class="modal fade" id="addModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加视频文件内容</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="add-form-shortVideo" class="form-horizontal" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName">视频名称：</label>
                        <div class="col-sm-9">
                            <input type="text" id="add_videoName" name="videoName" class="col-xs-12 col-sm-5" value="" onblur=""/>
                        </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label style="margin-top: -9px" class="col-sm-3 control-label no-padding-right" for="recordName">启动视频：</label>
                        <div class="col-sm-9">
                            <input type="radio" name="type" class="col-xs-12 col-sm-1" value="1" /><div style="float: left">ios</div>
                            <input type="radio" name="type" class="col-xs-12 col-sm-1" value="2" /><div style="float: left">Android</div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addShortVideo">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="updateModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加视频文件信息</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">
                <form id="update-form-shortVideo" class="form-horizontal" enctype="multipart/form-data">
                    <input type="hidden" id="video_id" name="videoId"/>
                    <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>文件ID:</label>
                    <div class="col-sm-9">
                        <input type="text" id="file_id" name="fileId" placeholder="文件ID" class="col-xs-12 col-sm-8" value="" onblur=""/>
                    </div>
                    </div>
                    <div class="hr hr-18 dotted"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>文件地址:</label>
                        <div class="col-sm-9">
                            <input type="text" id="video_file" name="videoFile" placeholder="文件地址" class="col-xs-12 col-sm-8" value="" onblur=""/>
                        </div>
                    </div>
                </form>
                <p style="color: red;">
                    温馨提示：请先到<a href="https://app.netease.im/login" target="_blank">网易云</a>上传视频，
                    <br/>
                    然后再把文件ID和地址拷贝到输入框中进行上传
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateVideo">保 存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
            </div>
        </div>
    </div>
</div>

<#--查看启动视频-->
<div class="modal fade" id="showModal-type" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <video id="my-video" class="video-js" controls poster="//nos.netease.com/vod163/poster.png" preload="auto" width="597" height="300" data-setup='{}'></video>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="closeVideo()">关 闭</button>
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
<script src="${ctx}/js/shortVideo/shortVideo.js?v=1"></script>
<script src="//nos.netease.com/vod163/nep.min.js"></script>
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script type="text/javascript"></script>
<#include "../common/body_bottom.ftl"/>
