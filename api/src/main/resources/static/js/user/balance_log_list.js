$(function(){

    // 初始精英教头列表
    showbalancelogList(1,10,1);

    var curpage=1;
    var freshH=$(".fresh").height();
    var moreState=0;
    var freshState=0;
    var myscroll = new iScroll("wrapper",{
        topOffset:freshH,
        onScrollMove:function(){
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
            if (this.y<(this.maxScrollY)) {
                moreState=1;
                moreElement();
            }else{
                moreState=0;
                moreElement();
            }
        },
        onScrollEnd:function(){
            if(freshState===1){
                freshState=2;
                pullDownAction();
                freshElement();
            }
            if (moreState===1) {
                moreState=2;
                moreElement();
                pullUpAction();
            }
        },
        onRefresh:function(){}
    });

    function pullUpAction(){
        setTimeout(function(){
            curpage++;
            showbalancelogList(curpage,10,0);
        }, 1000)
    }

    function pullDownAction(){
        setTimeout(function(){
            $("#balanceDetail").html("");// 先清空原来加载的数据
            showbalancelogList(1,10,1);
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

    // 展示精英教头列表
    function showbalancelogList(pageNum,pageSize,allIscrollState){
        $.ajax({
            async: false,
            type: "POST",
            url: $("#basePath").val()+"wap/bal_log/all_list",
            data:{pageNum:pageNum,pageSize:pageSize},
            dataType: "json",
            success: function (data) {
                var a = data.data;
                var htmlVal = "";
                if(a != null && a.length > 0){
                    for (var i =0; i<a.length; i++){
                        htmlVal+= '<div class="line">'
                            +'<div class="tit">'
                            +'<div >'+a[i].des_message+'</div>'
                            +'<div >'+a[i].fcreateTime+'</div>'
                            +'</div>'
                                if(a[i].operateType == -1){
                                   htmlVal += '<div class="pay color"><span>-</span>'+a[i].account+'学分</div>'
                                }else{
                                    htmlVal += '<div class="pay" style="color: #ffa42f"><span style="color: #ffa42f">+</span>'+a[i].account+'学分</div>'
                                }

                           htmlVal += ' </div>';
                    }
                    $("#balanceDetail").append(htmlVal);
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
                    $('.more .pull_icon').removeClass('flip loading');
                    $('.more span').text('没有更多数据');
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
    if(-1 == v){
        return "-";
    }else {
        console.log($(".pay.color"))
        $(".pay.color").css("color","#ffa42f");
        return "+";
    }
}