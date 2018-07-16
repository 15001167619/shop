<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>意见反馈管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/feedback/shopFeedback/">意见反馈列表</a></li>
		<%--<shiro:hasPermission name="feedback:shopFeedback:edit"><li><a href="${ctx}/feedback/shopFeedback/form">意见反馈添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="shopFeedback" action="${ctx}/feedback/shopFeedback/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%--<ul class="ul-form">--%>
			<%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
			<%--<li class="clearfix"></li>--%>
		<%--</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户Id</th>
				<th>反馈详情</th>
				<th>提交反馈时间</th>
				<shiro:hasPermission name="feedback:shopFeedback:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopFeedback">
			<tr>
				<td>${shopFeedback.userId}</td>
				<td>${shopFeedback.feedbackDesc}</td>
				<td> <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${shopFeedback.addTime}" /></td>
				<shiro:hasPermission name="feedback:shopFeedback:edit"><td>
					<a href="${ctx}/feedback/shopFeedback/form?id=${shopFeedback.id}">查看</a>
					<a href="${ctx}/feedback/shopFeedback/delete?id=${shopFeedback.id}" onclick="return confirmx('确认要删除该意见反馈吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>