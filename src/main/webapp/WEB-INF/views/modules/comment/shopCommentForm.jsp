<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
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
		<li><a href="${ctx}/comment/shopComment/">评论列表</a></li>
		<li class="active"><a href="${ctx}/comment/shopComment/form?id=${shopComment.id}">评论<shiro:hasPermission name="comment:shopComment:edit">${not empty shopComment.id?'详情':'添加'}</shiro:hasPermission><shiro:lacksPermission name="comment:shopComment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shopComment" action="${ctx}/comment/shopComment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">业务类型 0 商品</label>
			<div class="controls">
				<form:input path="businessType" htmlEscape="false" maxlength="3" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务Id</label>
			<div class="controls">
				<form:input path="businessId" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label font_weight">评论内容</label>
			<div class="controls">
				<script id="contentEditor" type="text/plain" style="width:1024px;height:150px;"></script>
                <form:hidden path="content" id="contentValue"/>
                <script type="text/javascript">
                var contentUE = UE.getEditor('contentEditor', {
                    toolbars: [[
                        'fullscreen', 'source', '|', 'undo', 'redo', '|',
                        'bold','pasteplain'
                    ]]
                });
                var contentHtmlValue = $("#contentValue").val();
                contentUE.ready(function() {
                    contentUE.setContent(contentHtmlValue, false);
                    contentUE.setHeight(150);
                });
				</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">userId</label>
			<div class="controls">
				<form:input path="userId" htmlEscape="false" rows="4" maxlength="1024" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">has_picture</label>
			<div class="controls">
				<form:input path="hasPicture" htmlEscape="false" maxlength="1" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">pic_urls</label>
			<div class="controls">
				<form:input path="picUrls" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">评分</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="star" htmlEscape="false" maxlength="6" class="input-xlarge  digits"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">评论时间</label>
			<div class="controls">
				<input name="addTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shopComment.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<%--<shiro:hasPermission name="comment:shopComment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>