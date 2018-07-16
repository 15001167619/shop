<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>微信配置管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(
            function() {
                $('#scene_str_Id').hide();
                $('#expire_seconds').val(30);
            });</script>
</head>
<body>
<ul class="nav nav-tabs">
</ul>
<form:form  class="form-horizontal">
    <div class="control-group">
        <h1>生成带参数的二维码</h1>
    </div>
    <div class="control-group">
        <label class="control-label font_weight">二维码类型</label>
        <div class="controls">
            <select id="qrCodeType" style="width: 130px" onChange="qrCodeTypeChange(this)" >
                <option value="1">临时二维码</option>
                <option value="2">永久二维码</option>
            </select>
        </div>
    </div>

    <div class="control-group" id="expire_seconds_ID">
        <label class="control-label">expire_seconds</label>
        <div class="controls">
            <input type="text" id="expire_seconds" />
            <span class="help-inline">expire_seconds	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。</span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label font_weight">二维码ticket</label>
        <div class="controls">
            <select id="qrCodeTicket" style="width: 130px" onChange="ticketChange(this)" >
                <option value="1">整型</option>
                <option value="2">字符串类型</option>
            </select>
        </div>
    </div>
    <div class="control-group" id="scene_id_Id">
        <label class="control-label font_weight">scene_id</label>
        <div class="controls">
            <textarea id="scene_id" class="input-xxlarge " style="margin: 0px; width: 600px; height: 92px;" ></textarea>
            <span class="help-inline">scene_id	场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）</span>
        </div>
    </div>

    <div class="control-group" id="scene_str_Id">
        <label class="control-label font_weight">scene_str</label>
        <div class="controls">
            <textarea id="scene_str" class="input-xxlarge " style="margin: 0px; width: 600px; height: 92px;" ></textarea>
            <span class="help-inline">scene_str	场景值ID（字符串形式的ID），字符串类型，长度限制为1到64</span>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="button" value="生成二维码" onclick="setGenerateQRCode()"/>
    </div>
</form:form>


<script language="javascript" type="text/javascript">

    var qrCodeTicket = $('#qrCodeTicket').val();
    function ticketChange(obj) {
        qrCodeTicket = obj.options[obj.selectedIndex].value;
        if(qrCodeTicket==2){
            $('#scene_str_Id').show();
            $('#scene_id_Id').hide();
        }else if(qrCodeTicket==1){
            $('#scene_str_Id').hide();
            $('#scene_id_Id').show();
        }
    }

    function qrCodeTypeChange(obj) {
        var qrCodeTypeValue = obj.options[obj.selectedIndex].value;
        if(qrCodeTypeValue==2){
            $('#expire_seconds_ID').hide();
        }else{
            $('#expire_seconds_ID').show();
        }
    }

    function  setGenerateQRCode(){
        var qrCodeType = $('#qrCodeType').val();
        //二维码ticket
        var qrCodeTicket = $('#qrCodeTicket').val();
        var scene_id = $('#scene_id').val();
        var scene_str = $('#scene_str').val();
        var expire_seconds = $('#expire_seconds').val();
        var POST_JSON_Str;
        var temporaryObj = new Object();
        var actionInfoObj = new Object();
        var sceneObj = new Object();
        if(qrCodeType == 1){//临时二维码
            //秒校验
            if(expire_seconds==""){
                swal("二维码有效时间为空", "二维码有效时间为空，请重新输入", "error");
                return false;
            }
            temporaryObj.expire_seconds = parseInt(expire_seconds);
            if(qrCodeTicket==1){//整型校验
                if(scene_id==""){
                    swal("场景值ID为空", "场景值ID为空，请重新输入", "error");
                    return false;
                }
                temporaryObj.action_name = 'QR_SCENE';
                sceneObj.scene_id = parseInt(scene_id);
            } else if(qrCodeTicket==2){//字符串校验
                if(scene_str==""){
                    swal("场景值ID为空", "场景值ID为空，请重新输入", "error");
                    return false;
                }
                temporaryObj.action_name = 'QR_STR_SCENE';
                sceneObj.scene_str = scene_str;
            }
            actionInfoObj.scene = sceneObj;
            temporaryObj.action_info = actionInfoObj;
            POST_JSON_Str = JSON.stringify(temporaryObj);

        }else if(qrCodeType == 2){//永久二维码
            var permanentObj =  new Object();
            if(qrCodeTicket==1){//整型校验
                if(scene_id==""){
                    swal("场景值ID为空", "场景值ID为空，请重新输入", "error");
                    return false;
                }
                permanentObj.action_name = 'QR_LIMIT_SCENE';
                sceneObj.scene_id = parseInt(scene_id);
            } else if(qrCodeTicket==2){//字符串校验
                if(scene_str==""){
                    swal("场景值ID为空", "场景值ID为空，请重新输入", "error");
                    return false;
                }
                permanentObj.action_name = 'QR_LIMIT_STR_SCENE';
                sceneObj.scene_str = scene_str;
            }
            actionInfoObj.scene = sceneObj;
            permanentObj.action_info = actionInfoObj;
            POST_JSON_Str = JSON.stringify(permanentObj);
        }
        $.ajax({
            url : "<c:url value='/a/wechat/function/setGenerateQRCode'/>",
            type : "POST",
            async : false,
            data : {param : POST_JSON_Str},
            success : function(data) {
                window.open("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+data);
            }
        });
    }

</script>
</body>
</html>
