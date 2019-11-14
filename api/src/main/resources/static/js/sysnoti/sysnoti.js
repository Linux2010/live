$(function(){

    // 初始展示今日课程列表
    showmsgList(1,10,1);

    var curpage=1;
    var freshH=$(".fresh").height();
    var moreState=0;
    var freshState=0;
    var myscroll = new iScroll("wrapper",{
        topOffset:freshH,
        onScrollMove:function(){
            if(this.y >=-freshH){
                if(this.y>=0){
                    this.minScrollY=0;
                    freshState=1;
                    freshElement();
                }else if(0>this.y>-freshH){
                    freshState=0;
                    freshElement();
                }else{
                    console.log("没这种情况")
                }
            }else{
                if (this.y<(this.maxScrollY)) {
                    moreState=1;
                    moreElement();
                }else{
                    moreState=0;
                    moreElement();
                }
            }
        },
        onScrollEnd:function(){
            if(freshState===1){
                freshState=2;
                pullDownAction();
                freshElement();
            }else if (moreState===1) {
                moreState=2;
                moreElement();
                pullUpAction();
            }else{
                this.refresh();
            }

        },
        onRefresh:function(){
        }
    });

    function pullUpAction(){
        setTimeout(function(){
            curpage++;
            showmsgList(curpage,10,0);
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            curpage = 1;
            $("#msgDiv").html("");// 先清空原来加载的数据
            showmsgList(1,10,1);
        }, 1000)
    }

    function moreElement() {
        switch (moreState){
            case 0:
                $('.more .pull_icon').removeClass('flip loading');
                $('.more span').text('上拉加载...');
                break;
            case 1:
                $('.more .pull_icon').addClass('flip');
                $('.more .pull_icon').removeClass('loading');
                $('.more span').text('释放加载...');
                break;
            case 2:
                $('.more .pull_icon').addClass('loading');
                $('.more span').text('加载中...');
                break;
            case 3:
                $('.more .pull_icon').addClass('loading');
                $('.more span').text('加载成功');
                break;
            default:
                $('.more .pull_icon').addClass('loading');
                $('.more span').text('加载失败！！');
                break;
        }
    }

    function freshElement() {
        switch (freshState){
            case 0:
                $('.fresh .pull_icon').show();
                $('.fresh .pull_icon').removeClass('flip loading');
                $('.fresh span').text('下拉刷新...');
                break;
            case 1:
                $('.fresh .pull_icon').addClass('flip');
                $('.fresh .pull_icon').removeClass('loading');
                $('.fresh span').text('释放刷新...');
                break;
            case 2:
                $('.fresh .pull_icon').addClass('loading');
                $('.fresh span').text('刷新中...');
                break;
            case 3:
                $('.fresh .pull_icon').hide();
                $('.fresh span').text('刷新成功');
                break;
            default:
                $('.fresh .pull_icon').addClass('loading');
                $('.fresh span').text('刷新失败！！');
                break;
        }
    }

    // 展示今日课程列表
    function showmsgList(pageNum,pageSize,allIscrollState){
        $.ajax({
            async: false,
            type: "POST",
            url: $("#basePath").val()+"wap/message/list",
            data:{pageNum:pageNum,pageSize:pageSize},
            dataType: "json",
            success: function (data) {
                var a = data.data;
                var htmlVal = "";
                if(a != null && a.length>0){
                    for (var i =0; i<a.length; i++){
                        htmlVal += '<a href="'+ $("#basePath").val()+'api/message/getMessageDetails?messageId='+a[i].messageId+'">'///
                            +'<div class="line">'
                            +'<p>'+new Date(a[i].createTime).pattern("yyyy-MM-dd HH:mm")+'</p>'
                            +'<div class="con">';
                        if(a[i].messageStatus == 0){
                            htmlVal += '<div class="show'+i+'" style="width:0.1rem;height: 0.1rem;background-color: red;' +
                                'border-radius: 50%;position: relative;top: 0.1rem;"></div> ';
                        }
                        htmlVal += '<div class="n">'+check_(a[i].title)+'</div>'
                            +'<div class="say">'+check_(a[i].intro)+'' +
                            '<span style="float: right;font-size: 14px;color: red" onclick="del(\''+a[i].messageId+'\')">删除</span></div>'
                            +'</div>'
                            +'</div>'
                            +'</a>';
                    }
                    $("#msgDiv").append(htmlVal);
                    if($("#wrapper").height() > $(".scroller").height()){
                        $(".more").hide();
                    }else{
                        $(".more").show();
                    }
                    if(allIscrollState){
                        freshState = 3;
                        freshElement();
                    }else{
                        moreState=3;
                        moreElement();
                        myscroll.refresh();
                    }
                    setTimeout(function () {
                        myscroll.refresh();
                    }, 1000)
                }else{
                    if(allIscrollState){
                        freshState = 3;
                        freshElement();
                    }else{
                        moreState=3;
                        // moreElement();
                        $('.more .pull_icon').removeClass('flip loading');
                        $('.more span').text('没有更多数据');
                    }
                    setTimeout(function () {
                        myscroll.refresh();
                    }, 1000)
                }
            },
            error:function(data){
                if(allIscrollState){
                    freshState=4;
                    freshElement();
                    myscroll.refresh();
                }else {
                    moreState=4;
                    moreElement();
                    myscroll.refresh();
                }
            }
        });
    }

});
function check_(v) {
    if(v == null){
        return "";
    }else{
        return v;
    }
}

function del(v) {
    event.stopPropagation();
    event.preventDefault();
    var r = confirm("确定要删除此条信息吗？");
    if (r == true) {
        $.ajax({
            async: false,
            type: "POST",
            url: $("#basePath").val() + "wap/message/del",
            data: {'messageId': v},
            dataType: "json",
            success: function (data) {
                var result = data.message;
                if (result == "success") {
                    alert("删除成功!");
                    //window.location.href=$("#basePath").val() + "wap/message/list_page";
                    location.reload();//刷新页面
                } else {
                    alert("删除失败!");
                }
            },
            error: function (data) {
                alert("系统异常，请稍后重试");
            }
        })
    }else{
        alert("您取消了此次操作!");
    }
}