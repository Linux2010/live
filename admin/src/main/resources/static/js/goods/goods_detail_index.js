jQuery(function ($) {

    initGoodsCatComboTree($("#catIdStr").val(),$("#catNameStr").val());
});


//初始化商品分类comboTree
function initGoodsCatComboTree(catId,catName){
    $('#catId').combotree ({
        url: base + 'goodsCat/searchGoodsCateByParentId?parentId=-1',
        onBeforeExpand:function(node) {
            $('#catId').combotree("tree").tree("options").url = base + 'goodsCat/searchGoodsCateByParentId?parentId=' + node.id;
        },
        onClick:function(node){
        },
        onLoadSuccess:function(node,data){
            // 初始化默认值
            setDefaultDbSet(catId,catName);
        }, //选择树节点触发事件
        onSelect : function(node) {
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                Modal.alert({msg: "请选择二级分类", title: "提示", btnok: "确定", btncl: "取消"});
                //清除选中
                $('#catId').combotree('clear');
                return false;
            }
        }
    });
}


// 设置下拉树的默认值
function setDefaultDbSet(catId, catName) {
    // 设置默认值
    var t = $('#catId').combotree('tree');
    var defNode = t.tree("find", catId);
    if (!defNode) {
        t.tree('append', {
            data: [{
                id: catId,
                text: catName
            }]
        });
        defNode = t.tree("find", catId);
        t.tree('select', defNode.target);
        defNode.target.style.display = 'none';
    }
    $('#catId').combotree('setValue', catId);
}