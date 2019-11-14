<#assign title="新闻咨询管理 > 商业资讯 >商业模式的添加"/>
<#assign menuId="45"/>
<#include "../common/head_top.ftl"/>

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/easy-ui/themes/icon.css"/>
<#include "../common/head_bottom.ftl"/>
<#include "../common/body_top.ftl"/>

<div id="dialog"></div>

<input type="hidden" id="basePath" value="${ctx}"/>
<input type="hidden" id="businessId" value="${businessId}"/>

<div class="modal-header">
    <h4 class="modal-title"><span id="title"></span></h4>
</div>
<div class="modal-body" id="ctDetail">
    <div id="container" name="content" type="text/plain" style="height:90%; width:95%"></div>
</div>
<div class="modal-footer" style="text-align:center;">
    <button type="button" class="btn btn-default" id="uploadBtn">上 传</button>
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
<script src="${ctx}/js/easy-ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/UEditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/UEditor/ueditor.all.js"></script>
<#include "../common/body_bottom.ftl"/>
<script>
    $(function(){

        //展示课程内容
        $.ajax({
            async: true,
            type: "POST",
            url: base + "news/selectOperation",
            data: {businessId : $("#businessId").val()},
            dataType: "json",
            success: function (data) {
                if(data != null){
                    ue.ready(function() {// 编辑器初始化完成再赋值
                        if(data.content == null || data.content == ""){
                            ue.setContent("暂无内容");  // 赋值给UEditor
                        }else{
                            ue.setContent(data.content);  // 赋值给UEditor
                        }
                    });
                }
            },
            error:function(data){
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });

        //上传文章内容
        $("#uploadBtn").click(function(){
            // 先验证非空项

            if(!check($("#businessId").val(),ue.getContent())){
                return false;
            }else{
                // 设置上传按钮不可用
                $("#uploadBtn").attr("disabled",true);
                $("#uploadBtn").text("上传中...");
                // 如果验证通过，则提交表单
                var showContent = ue.getContentTxt().substr(0,60);//截取文章内容
                $.ajax({
                    type:"post",
                    data:{businessId:$("#businessId").val(),content:ue.getContent()},
                    dataType:"json",
                    url:base+"news/updateOperation",
                    success: function(data) {
                        // 设置上传按钮可用
                        $("#uploadBtn").attr("disabled",false);
                        $("#uploadBtn").text("上 传");
                        if("1" == data){
                            Modal.alert({msg : "上传成功！", title : "提示", btnok : "确定"});
                            setTimeout(function () {
                                window.location.href=base+"news/";
                            },1800)
                        }else{
                            Modal.alert({msg : "上传失败！", title : "提示", btnok : "确定"});
                        }
                    },
                    error:function(data){
                        // 设置上传按钮可用
                        $("#uploadBtn").attr("disabled",false);
                        $("#uploadBtn").text("上 传");
                        Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
                    }
                });
            }
        });

        function check(articleId, content) {
            var result = false;
            if(isEmpty(articleId)){
                Modal.alert({msg: "文章编号不能为空！",title: "提示", btnok: "确定"});
                return result;
            }
            if(isEmpty(content)){
                Modal.alert({msg: "文章内容不能为空！",title: "提示", btnok: "确定"});
                return result;
            }
            result = true;
            return result;
        }
    });

   // var ue = UE.getEditor('container');
    var  ue = UE.getEditor('container', {
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
            return $("#basePath").val()+'/UEditor/uploadImg';
        } else if (action == 'uploadvideo') {
            return 'http://a.b.com/video.php';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>