<!DOCTYPE html>
<html lang="en" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0, user-scalable=no" />
    <title>教头学院</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <script src="http://webapi.amap.com/maps?v=1.4.1&key=33d8f16649ead4f016bfd720d36a7395&plugin=AMap.Geocode"></script>
    <script src="${ctx}js/jquery.min.js" type="text/javascript" ></script>
    <script src="${ctx}js/course/gaodeMap.js" type="text/javascript"></script>
</head>
<body>
<input type="hidden" id="xVal" value="${xVal!}"/>
<input type="hidden" id="yVal" value="${yVal!}"/>
    <div id="mapDiv" style="display: block;width:100%;height:100%;"></div>
</body>
</html>