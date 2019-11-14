<!-- 详情显示Modal -->
<div class="modal fade" id="order_detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">详情信息</h4>
            </div>
            <div class="modal-body" style="max-height:600px; overflow:scroll; ">

                <div  class="form-group" style="margin-left: 15px">
                    <h3>订单信息</h3>
                    <hr>
                    <div class="row">
                        <div class="col-xs-4">订单号 : <span id="onum" ></span></div>
                        <div class="col-xs-4">下单时间 : <span id="odate"></span></div>
                        <div class="col-xs-4">订单状态 : <span id="ostatus" ></span></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">付款状态 : <span id="mstatus" ></span></div>
                        <div class="col-xs-4">支付类型 : <span id="pstatus" ></span></div>
                        <div class="col-xs-4">订单金额 : <span id="omoney" ></span></div>
                    </div>
                </div>
                <div class="form-group" style="margin-left: 15px">
                    <h3>课程信息</h3>
                    <hr>
                    <div class="row">
                        <div class="col-xs-12">课程标题: <span id="ctitle" ></span></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">课程封面: <img id="cover" style="height: 100px;"/></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">课程介绍: <span id="cintro" ></span></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">开始时间: <span id="cstart"></span></div>
                        <div class="col-xs-4"><div id="course_end" STYLE="display: block">结束时间 : <span id="cend"></span></div></div>
                        <div class="col-xs-4">普通价格: <span id="cpu"></span>/(银两)</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4">会员价格 : <span id="cvip"></span>/(银两)</div>
                        <div class="col-xs-4">课程积分: <span id="cscore" ></span>/(积分)</div>
                        <div class="col-xs-4">课程类型: <span id="ctype" ></span></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4"><div id="course_addredd">课程地址: <span id="caddress" ></span></div></div>
                        <div class="col-xs-4">课程讲师: <span id="cc"</b></div>
                        <div class="col-xs-4"></div>
                    </div>
                </div>
                <div class="hr hr-18 dotted"></div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->