<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>帮助中心管理</title>
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
		<li><a href="${ctx}/help/shopHelp/">帮助中心列表</a></li>
		<li class="active"><a href="${ctx}/help/shopHelp/form?id=${shopHelp.id}">帮助中心<shiro:hasPermission name="help:shopHelp:edit">${not empty shopHelp.id?'编辑':'添加'}</shiro:hasPermission><shiro:lacksPermission name="help:shopHelp:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" name="shopHelpForm" modelAttribute="shopHelp" action="${ctx}/help/shopHelp/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label font_weight">问题</label>
			<div class="controls">
				<script id="questionEditor" type="text/plain" style="width:1024px;height:500px;"></script>
                <form:hidden path="question" id="questionValue"/>
                <script type="text/javascript">
                var questionUE = UE.getEditor('questionEditor', {
                    toolbars: [[
                        'fullscreen', 'source', '|', 'undo', 'redo', '|',
                        'bold','pasteplain'
                    ]]
                });
                var htmlStrValue = $("#questionValue").val();
                questionUE.ready(function() {
                    questionUE.setContent(htmlStrValue, false);
                    questionUE.setHeight(150);
                });
				</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label font_weight">解决方案</label>
			<div class="controls">
				<script id="solutionEditor" type="text/plain" style="width:1024px;height:500px;"></script>
                <form:hidden path="solution" id="solutionValue"/>
                <script type="text/javascript">
                var solutionUE = UE.getEditor('solutionEditor', {
                    toolbars: [[
                        'fullscreen', 'source', '|', 'undo', 'redo', '|',
                        'bold','pasteplain'
                    ]]
                });
                var solutionHtmlValue = $("#solutionValue").val();
                solutionUE.ready(function() {
                    solutionUE.setContent(solutionHtmlValue, false);
                    solutionUE.setHeight(250);
                });
				</script>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="help:shopHelp:edit">
				<input id="btnSubmit" onclick="saveShopHelp()" class="btn btn-primary" type="button" value="保存"/>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

	<script type="text/javascript">
        function saveShopHelp(){
            $("#questionValue").val(UE.getEditor('questionEditor').getContent());
            $("#solutionValue").val(UE.getEditor('solutionEditor').getContent());
            document.shopHelpForm.action = "<c:url value='/a/help/shopHelp/save'/>";
            document.shopHelpForm.submit();
        }

	</script>

</body>
</html>