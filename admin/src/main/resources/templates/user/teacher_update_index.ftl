<#assign title="修改讲师信息"/>
<#assign menuId="feb6ac8a64e244b0958f5db4cf3dad7a"/>
<#include "../common/head_top.ftl"/>
<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<style>

    .form-horizontal .form-group {
        margin-left: -10%;
        margin-right: -12px;
    }
    .combo{
        width: 465.667px;
    }
    .combo-text validatebox-text{
        width: 441px;
    }
    .ace-file-input{
        width: 41.9%;
    }
</style>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<#--update-start-->
<!-- 用户修改Modal -->
<div id="updateModal-type"  >
    <form id="update-form-user" class="form-horizontal"  enctype="multipart/form-data">
        <input type="hidden" name="userId" id="userId" value="${data["user_id"]!}"/>
        <input type="hidden" name="userType" id="userType" value="1"/> <#--类型 1：教头，2：用户-->
        <input type="hidden" name="data" id="data" value=""/>
        <input type="hidden" name="photo" id="photoUrl" value="${data.photo!}"/>
        <input type="hidden" name="rectanglephoto" id="rectanglePhotoUrl" value="${data.rectangle_photo!}"/>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>讲师分类:</label>
            <div class="col-sm-9">
                <select id="userTypeId" name="userTypeId" class="col-xs-12 col-sm-12 easyui-combotree" style="width: 200px;height:34px; padding-right:2px;padding-right:2px;" >
                </select>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userName"><small class="red">*</small>真实姓名:</label>
            <div class="col-sm-9">
                <input type="text" id="realName" name="realName" placeholder="真实姓名"  class="col-xs-12 col-sm-5" value="${data["real_name"]!}" onblur=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userName"><small class="red">*</small>讲师姓名:</label>
            <div class="col-sm-9">
                <input type="text" id="userName" name="userName" placeholder="讲师姓名" class="col-xs-12 col-sm-5" value="${data["user_name"]!}" onblur=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userName"><small class="red">*</small>讲师昵称:</label>
            <div class="col-sm-9">
                <input type="text" id="nickName" name="nickName" placeholder="讲师昵称" class="col-xs-12 col-sm-5" value="${data["nick_name"]!}" onblur=""/>
            </div>
        </div>
      <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="phone"><small class="red">*</small>讲师电话:</label>
            <div class="col-sm-9">
                <select  class="form-control courseList col-xs-2 col-sm-2"  name="tele_num"  id="tele_num" style="width:8.5%">
                    <option value="">--选择国家--</option>
                </select>
                <input type="text" id="phone" name="phone" placeholder="讲师电话" class="col-xs-12 col-sm-4" value="" onblur="" value="${data["phone"]!}" />
                <input type="hidden" id="countryCode" name="countryCode" value="${data["countryCode"]!}"/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="loginName"><small class="red">*</small>讲师账号:</label>
            <div class="col-sm-9">
                <input type="text" id="loginName" name="loginName" placeholder="讲师账号" class="col-xs-12 col-sm-5" value="${data["login_name"]!}" onblur=""/>
            </div>
        </div>
     <#--   <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="password"><small class="red">*</small>讲师密码:</label>
            <div class="col-sm-9">
                <input type="text" id="password" name="password" placeholder="讲师密码" class="col-xs-12 col-sm-5" value="${data["password"]}" onblur=""/>
            </div>
        </div>-->
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="seqno"><small class="red">*</small>序列号:</label>
            <div class="col-sm-9">
                <input type="text" id="seqno" name="seqno" placeholder="序列号" class="col-xs-12 col-sm-5" value="${data["seqno"]!}" onblur=""/>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"  style="text-align: right;"><small class="red">*</small>账号状态:</label>
            <div class="col-sm-9">
                 <span class="input-icon">
                    <label>
                       <input  type="radio" class="ace col-xs-12 col-sm-5" name="status" value="0" />
                       <span class="lbl" id="status">已停用账号</span>
                    </label>
                    <label style="margin-top:5px;display: inline-block;">
                       <input type="radio" class="ace col-xs-12 col-sm-5"  name="status" value="1" />
                       <span class="lbl" id="status" >已开启账号</span>
                    </label>
                 </span>
            </div>
        </div>
     <#--   <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"  style="text-align: right;"><small class="red">*</small>用户身份:</label>
            <div class="col-sm-9">
                <select  class="form-control col-xs-12 col-sm-4" name="identity" id="identity" style="width: 41.9%;">
                <option value="1">普通用户</option>
                <option value="2">会员用户</option>
                </select>
            </div>
        </div>-->

        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="photoFile"><small class="red">*</small>用户头像:</label>
            <div class="col-xs-6">
                <input type="file" id="photoFile" name="file" class="form-control"  value="${data.photo!}"/>(<small class="red">建议正方形图片长宽比例为1:1建议200px*200px</small>)
            </div>
            <div class="col-sm-3" style="margin-top: 4px;">
            <#if data.photo ??>
                <a href="${data.photo!}" target="_blank" ><img src="${data.photo!}" id="img3" width="34" height="34"/></a>
            <#else >
                无头像
            </#if>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="recordName"><small class="red">*</small>名优教头图片:</label>
            <div class="col-xs-6">
                <input type="file" id="rectanglePhoto" name="file1" class="form-control" value="${data.rectangle_photo!}"/>(<small class="red">建议与头像内容相同,长方形图片长宽比例为1.725:1建议345*200px</small>)
            </div>
            <div class="col-sm-3" style="margin-top: 4px;">
            <#if data.rectangle_photo ??>
                <a href="${data.rectangle_photo!}" target="_blank" ><img src="${data.rectangle_photo!}" id="img3" width="58.65" height="34"/></a>
            <#else >
                名优教头图片
            </#if>
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userIntro"><small class="red">*</small>讲师简介:</label>
            <div class="col-sm-9">
                <textarea id="userIntroText" name="userIntroText"  style="width: 41.9%;" rows="7" onkeyup="javascript:checkWordNum(this)">${data["user_intro_text"]!}</textarea>(限500字以内,已输入<span id="word_count">0</span>字,还能输入<span id="word">500</span>字)
            </div>
        </div>
        <div class="hr hr-18 dotted"></div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="userIntro"><small class="red">*</small>讲师详细简介:</label>
            <div class="col-sm-9">
            <!-- 加载编辑器的容器 -->
            <script id="updateContainer" name="updateContainer" type="text/plain" style="width:95%;height:height:90%" >
                讲师详细简介
            </script>
            </div>
            </div>
         <div class="hr hr-18 dotted"></div>
     </form>
 </div>
<div class="modal-footer">
    <button type="button" class="btn btn-primary" id="updateUser">保 存</button>
    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:location.href = base + 'user/teacher/'">返回</button>
</div>
 <#--update-end-->

<!-- 配置文件 -->
 <script type="text/javascript" src="${ctx}/UEditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctx}/UEditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script>
    var  updateContainer = UE.getEditor('updateContainer', {
        toolbars: [
            [

                'anchor', //锚点
                'undo', //撤销
                'redo', //重做
                'bold', //加粗
                'indent', //首行缩进
                //  'snapscreen', //截图
                'italic', //斜体
                'underline', //下划线
                'strikethrough', //删除线
                'subscript', //下标
                'fontborder', //字符边框
                'superscript', //上标
                'formatmatch', //格式刷
                'source', //源代码
                'blockquote', //引用
                'pasteplain', //纯文本粘贴模式
                'selectall', //全选
                'print', //打印
                'preview', //预览
                'horizontal', //分隔线
                'removeformat', //清除格式
                'time', //时间
                'date', //日期
                // 'unlink', //取消链接
                // 'insertrow', //前插入行
                // 'insertcol', //前插入列
                // 'mergeright', //右合并单元格
                // 'mergedown', //下合并单元格
                // 'deleterow', //删除行
                // 'deletecol', //删除列
                // 'splittorows', //拆分成行
                // 'splittocols', //拆分成列
                // 'splittocells', //完全拆分单元格
                // 'deletecaption', //删除表格标题
                // 'inserttitle', //插入标题
                // 'mergecells', //合并多个单元格
                // 'deletetable', //删除表格
                // 'cleardoc', //清空文档
                // 'insertparagraphbeforetable', //"表格前插入行"
                'insertcode', //代码语言
                'fontfamily', //字体
                'fontsize', //字号
                'paragraph', //段落格式
                'simpleupload', //单图上传
                // 'edittable', //表格属性
                // 'edittd', //单元格属性
                // 'link', //超链接
                // 'emotion', //表情
                // 'spechars', //特殊字符
                // 'searchreplace', //查询替换
                // 'insertvideo', //视频
                // 'help', //帮助
                'justifyleft', //居左对齐
                'justifyright', //居右对齐
                'justifycenter', //居中对齐
                'justifyjustify', //两端对齐
                'forecolor', //字体颜色
                'backcolor', //背景色
                'insertorderedlist', //有序列表
                'insertunorderedlist', //无序列表
                'fullscreen', //全屏
                'directionalityltr', //从左向右输入
                'directionalityrtl', //从右向左输入
                'rowspacingtop', //段前距
                'rowspacingbottom', //段后距
                'pagebreak' //分页
                // // 'insertframe', //插入Iframe
                // // 'imagenone', //默认
                // // 'imageleft', //左浮动
                // // 'imageright', //右浮动
                // // 'attachment', //附件
                // // 'imagecenter', //居中
                // // 'wordimage', //图片转存
                // 'lineheight', //行间距
                // 'edittip ', //编辑提示
                // 'customstyle', //自定义标题
                // 'autotypeset', //自动排版
                // 'touppercase', //字母大写
                // 'tolowercase', //字母小写
                // 'background', //背景
                // 'template', //模板
                // 'scrawl', //涂鸦
                // 'music', //音乐
                // 'inserttable', //插入表格
                // 'drafts', // 从草稿箱加载
                // 'charts', // 图表
            ]
        ],
        autoHeightEnabled: true,
        autoFloatEnabled: false, //focus时自动清空初始化时的内容
        autoClearinitialContent:true,
        //关闭字数统计
        wordCount:false,
        //关闭elementPath
        elementPathEnabled:false,
        //默认的编辑区域高度
        initialFrameHeight:900});

    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'catchimage') {
            return base+'UEditor/uploadImg';
        } else if (action == 'uploadvideo') {
            return 'http://a.b.com/video.php';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>
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
<script src="${ctx}/js/user/teacher_update_index.js"></script>
 <script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<#include "../common/body_bottom.ftl"/>