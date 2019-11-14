var tele_num = [
    {
        "name": "中国",
        "num":"+86"
    },
    {
        "name": "台湾",
        "num":"+886"
    },
    {
        "name": "香港",
        "num":"+852"
    },
    {
        "name": "澳门",
        "num":"+853"
    },
    {
        "name": "马来西亚",
        "num":"+60"
    },
    {
        "name": "泰国",
        "num":"+66"
    },
    {
        "name": "新加坡",
        "num":"+65"
    },
    {
        "name": "美国",
        "num":"+1"
    },
    {
        "name": "加拿大",
        "num":"+1"
    },
    {
        "name": "印度尼西亚",
        "num":"+62"
    },
    {
        "name": "缅甸",
        "num":"+95"
    },
    {
        "name": "柬埔寨",
        "num":"+855"
    }
]



var nameEl = document.getElementById('sel_city');
function creatList(obj, list){
    obj.forEach(function(item, index, arr){
        var temp = new Object();
        temp.text = item.name;
        temp.num = item.num;
        temp.value = index;
        list.push(temp);
    })
}
var data = [];
var picker;
var text;
creatList(tele_num, data);
picker = new Picker({
    data: [data],
    selectedIndex:[0],
    title: '地址选择'
});
picker.on('picker.select', function (selectedVal, selectedIndex) {
    text = data[selectedIndex[0]].num;
    nameEl.innerText = text;
});
nameEl.addEventListener('click', function () {
    picker.show();
});