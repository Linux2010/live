$(function(){
    // 展示江湖大课地图定位
    var mapArr = [117.000923,36.675807];
    mapArr[0] = $("#xVal").val();
    mapArr[1] = $("#yVal").val();
    var map = new AMap.Map('mapDiv', {
        center:mapArr,
        zoom:11,
        resizeEnable: true
    });
    map.plugin(["AMap.ToolBar"], function() {
        map.addControl(new AMap.ToolBar());
    });
    if(location.href.indexOf('&guide=1')!==-1){
        map.setStatus({scrollWheel:false})
    }
    var marker = new AMap.Marker({  //加点
        map: map,
        position: mapArr
    });
    map.setFitView();
});