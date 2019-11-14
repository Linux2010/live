var topicIndex=0;
var ue;
var goodsId = $("#goodsId").val();
jQuery(function ($) {
    initUEditor();
    var intro ="";
    $.ajax({
        async: false,
        url: base + "goods/goodsDetail?goodsId=" + goodsId,
        dataType: "json",
        success: function (data) {
            intro=data.intro;
        },
        error:function (data) {
            intro=data.intro;
        }
    });
    ue.ready(function() {
        if(intro)  ue.setContent(intro);
        else ue.setContent("暂无商品简介,请在次编辑");
    });

    $("#addGoodsIntro").click(function(){
        var introHtml =ue.getContent();
        //$("#introText").html(introHtml);
       // $("#intro").val(introHtml);

        $.ajax({
            async: true,
            type: "POST",
            url: base + "goods/updateGoodsIntro?=" + goodsId,
            data:{"goodsId":goodsId,"intro":introHtml},
            dataType: "json",
            success: function (data) {
                $("#addGoodsIntro").attr("disabled",false);
                $("#addGoodsIntro").text("信息上传中...");
                if(data==1){
                    location.href = base + 'goods/goods_index/';
                }else{
                    $("#addGoodsIntro").attr("disabled",false);
                    $("#addGoodsIntro").text("重新上传");
                    Modal.alert({msg: "修改失败！", title: "提示", btnok: "确定", btncl: "取消"});
                }
            },
            error:function (data) {
                $("#addGoodsIntro").attr("disabled",false);
                $("#addGoodsIntro").text("重新上传");
                Modal.alert({msg : "系统异常，请稍后重试", title : "提示", btnok : "确定"});
            }
        });
    });
});


function initUEditor(){
    ue = UE.getEditor('container', {
        toolbars: [
            [
                'anchor', //锚点
                'undo', //撤销
                'redo', //重做
                'bold', //加粗
                'indent', //首行缩进
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
                'insertcode', //代码语言
                'fontfamily', //字体
                'fontsize', //字号
                'paragraph', //段落格式
                'simpleupload', //单图上传
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
            ]
        ],
        autoHeightEnabled: true,
        autoFloatEnabled: false,
        //focus时自动清空初始化时的内容
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
    };
}

