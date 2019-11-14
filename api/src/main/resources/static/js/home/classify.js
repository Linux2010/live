$(function () {

    //一级教头分类
    var  a = teacherTypeListByUserLevel(1,-1,1);
    if (a != null && a.length > 0) {
        var classifysHtml = "";
        var classifyImgHtml = "";
        for (var i = 0; i < a.length; i++) {
            if (i == 0) {
                classifysHtml += '<li class="active" userTypeId="' + a[i].userTypeId + '" userTypePicture="' + a[i].userTypePicture + '">'
                    + '<div class="item"><span>' + a[i].typeName + '</span></div>'
                    + '</li>';
                //二级下的教头分类列表
                var  a1 = teacherTypeListByUserLevel(1,a[i].userTypeId,2);
                if (a1 != null && a1.length > 0) {
                    for (var j = 0; j < a1.length; j++) {
                        classifyImgHtml += '<li><a href="'+base+'search/home/wellTeacherList"><img class="img1" src="' + a1[j].userTypePicture + '"><div style="width:100%;height: 0.5rem;text-align: center;color:white;background-color: black;opacity: 0.5;margin-top: -0.54rem;">' + a1[j].typeName + '</div></a> </li>';
                    }
                }
            }
            if(i>=1 ) {
                if(a[i]){
                    classifysHtml += '<li  userTypeId="' + a[i].userTypeId + '" userTypePicture="' + a[i].userTypePicture + '">'
                        + '<div class="item"><span>' + a[i].typeName + '</span></div>'
                        + '</li>';
                }
            }
        }
        $("#classifys-ul").append(classifysHtml);
        $("#classify-img-ul").append(classifyImgHtml);
    }

    var myscroll1 = new iScroll("wrapper1");
    var myscroll2 = new iScroll("wrapper2");
    var ulli1 = $(".listL #wrapper1 ul li");
    ulli1.click(function () {
        ulli1.removeClass("active");
        $(this).addClass("active");
        $("#classify-img-ul").empty();
        var userTypeId= $(this).attr("userTypeId");
        //二级下的教头分类列表
        var classifyImgHtml = "";
        var  a1 = teacherTypeListByUserLevel(1,userTypeId,2);
        if (a1 != null && a1.length > 0) {
            for (var j = 0; j < a1.length; j++) {
                classifyImgHtml += '<li><a href="'+base+'search/home/wellTeacherList"><img class="img1" src="' + a1[j].userTypePicture + '"><div style="width:100%;height: 0.5rem;text-align: center;color:white;background-color: black;opacity: 0.5;margin-top: -0.54rem;">' + a1[j].typeName + ' </div></a></li>';
            }
        }
        $("#classify-img-ul").append(classifyImgHtml);
    })

});

//教头分类列表
function teacherTypeListByUserLevel(typeVal,parentId,level){
     var userTypeList= null;
    //加载全部教头分类
    $.ajax({
        async: false,
        url: base + "search/teacherTypeListByUserLevel",
        data: {typeVal: typeVal, parentId: parentId, level: level},
        type: "post",
        dataType: "json",
        success: function (data) {
            userTypeList=data.data;
        },
        error: function (data) {
            userTypeList=data.data;
        },
    });
    return userTypeList;
}