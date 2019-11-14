var regionId=[];
function city(d){
    var na = document.getElementById('adress');
    var nameEl = na.querySelector("span");
    function creatList(obj, list){
        obj.forEach(function(item, index, arr){
            var temp = new Object();
            temp.id=item.id;
            temp.regionId=item.regionId;
            temp.text=item.regionName;
            temp.value=index;
            list.push(temp);
        })
    }
    var data1=[];
    var data2=[];
    var data3=[];
    var data4=[];
    var picker1;
    var picker2;
    var picker3;
    var picker4;
    var text1;
    var text2;
    var text3;
    var text4;
    var id1;
    var id2;
    var id3;
    var id4;
    var regionId1;
    var regionId2;
    var regionId3;
    var regionId4;
    function init_data() {
        data2=[];data3=[];data4=[];picker2='';picker3='';picker4='';text1='';text2='';text3='';text4='';id1='';id2='';id3='';id4='';regionId1='';regionId2='';regionId3='';regionId4='';
    }
    init_data();
    creatList(d.data,data1);
    picker1 = new Picker({
        data: [data1],
        selectedIndex:[0],
        title: '地址选择'
    });

    picker1.on('picker.select', function (selectedVal, selectedIndex) {
        text1 = data1[selectedIndex[0]].text;
        id1 = selectedIndex[0];
        regionId1 = data1[selectedIndex[0]].regionId;
        regionId.push(regionId1);
        $.ajax({
            async: false,
            type: "POST",
            url:base+"region/get_address_list",
            data: {"pid":regionId1},
            dataType: "json",
            success: function (d1) {
                if(d1.data.length!==0){
                    creatList(d1.data,data2);
                    picker2 = new Picker({
                        data:[data2],
                        selectedIndex:[0],
                        title: '地址选择'
                    })
                    picker2.on('picker.select',function (selectedVal, selectedIndex) {
                        text2 = data2[selectedIndex[0]].text;
                        id2 = selectedIndex[0];
                        regionId2 = data2[selectedIndex[0]].regionId;
                        regionId.push(regionId2);
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: base + "region/get_address_list",
                            data: {"pid": regionId2},
                            dataType: "json",
                            success: function (d2) {
                                if(d2.data.length!==0){
                                    creatList(d2.data,data3);
                                    picker3 = new Picker({
                                        data:[data3],
                                        selectedIndex:[0],
                                        title: '地址选择'
                                    })
                                    picker3.on('picker.select',function (selectedVal, selectedIndex) {
                                        text3 = data3[selectedIndex[0]].text;
                                        id3 = selectedIndex[0];
                                        regionId3=data3[selectedIndex[0]].regionId;
                                        regionId.push(regionId3);
                                        $.ajax({
                                            async: false,
                                            type: "POST",
                                            url: base + "region/get_address_list",
                                            data: {"pid": regionId3},
                                            dataType: "json",
                                            success: function (d3) {
                                                if(d3.data.length!==0){
                                                    creatList(d3.data,data4);
                                                    picker4 = new Picker({
                                                        data:[data4],
                                                        selectedIndex:[0],
                                                        title: '地址选择'
                                                    })
                                                    picker4.on('picker.select',function (selectedVal, selectedIndex) {
                                                        text4 = data4[selectedIndex[0]].text;
                                                        id4 = selectedIndex[0];
                                                        regionId4=data4[selectedIndex[0]].regionId;
                                                        regionId.push(regionId4);
                                                        nameEl.innerHTML = text1 + ' ' + text2 + ' ' + text3+ ' ' +text4;init_data();
                                                    })
                                                    picker4.show();picker3='';
                                                }else{             //pick3
                                                    nameEl.innerHTML = text1 + ' ' + text2 + ' ' + text3;init_data();
                                                }
                                            },
                                            fail:function (data) {
                                                alert("sanjiaddress_error")
                                            }
                                        })
                                    })
                                    picker3.show();picker2='';
                                }else{                      //pick2
                                    nameEl.innerHTML = text1 + ' ' + text2;init_data();
                                }
                            },
                            fail:function (data) {
                                alert("erjiaddress_error")
                            }
                        })
                    })
                    picker2.show();
                }else{//pick1
                    nameEl.innerHTML = text1;init_data();
                }
            },
            error: function (data) {
                alert("yijiaddress_error")
            }
        })


    });

    nameEl.addEventListener('click', function () {
        picker1.show();
    });
}
/////////////////////////////////////////////////////////////
$.ajax({
    async: false,
    type: "POST",
    url:base+"region/get_address_list",
    data: {"pid":"0"},
    dataType: "json",
    success: function (d) {
        city(d);
    },
    error: function (data) {

    }
})

