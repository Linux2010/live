<#assign title="用户管理"/>
<#assign menuId="7"/>
<#include "common/head_top.ftl"/>
<!-- page specific plugin styles -->

<#include "common/head_bottom.ftl"/>
<#include "common/body_top.ftl"/>
<div class="col-xs-12">
    <!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain">
        这里写你的初始化内容
    </script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="${ctx}/UEditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctx}/UEditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function(action) {
            if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'catchimage') {
                return '${ctx}/UEditor/uploadImg';
            } else if (action == 'uploadvideo') {
                return 'http://a.b.com/video.php';
            } else {
                return this._bkGetActionUrl.call(this, action);
            }
        }
    </script>
</div><!-- /.col -->
<#include "common/body_middle.ftl"/>

<!-- page specific plugin scripts -->


<#include "common/body_bottom.ftl"/>
