<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>意见反馈管理</title>
	<meta name="decorator" content="default"/>

	<script src="${ctxStatic}/ueditor/ueditor.config.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ueditor/ueditor.all.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/feedback/shopFeedback/">意见反馈列表</a></li>
		<li class="active"><a href="${ctx}/feedback/shopFeedback/form?id=${shopFeedback.id}">意见反馈<shiro:hasPermission name="feedback:shopFeedback:edit">${not empty shopFeedback.id?'详情':'添加'}</shiro:hasPermission><shiro:lacksPermission name="feedback:shopFeedback:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shopFeedback" action="${ctx}/feedback/shopFeedback/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label font_weight">意见反馈详情</label>
			<div class="controls">
				<script id="feedbackDescEditor" type="text/plain" style="width:1024px;height:500px;"></script>
                <form:hidden path="feedbackDesc" id="feedbackDescValue"/>
                <script type="text/javascript">
                var feedbackDescUE = UE.getEditor('feedbackDescEditor', {
                    toolbars: [[
                        'fullscreen', 'source', '|', 'undo', 'redo', '|',
                        'bold','pasteplain'
                    ]]
                });
                var feedbackDescHtmlValue = $("#feedbackDescValue").val();
                feedbackDescUE.ready(function() {
                    feedbackDescUE.setContent(feedbackDescHtmlValue, false);
                    feedbackDescUE.setHeight(250);
                });
				</script>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">常识列表图</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="picUrl" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<%--<shiro:hasPermission name="feedback:shopFeedback:edit">--%>
				<%--<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;--%>
			<%--</shiro:hasPermission>--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>