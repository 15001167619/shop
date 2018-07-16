<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客服热线管理</title>
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
		<li class="active"><a href="${ctx}/hotline/shopHotline/">客服热线列表</a></li>
		<shiro:hasPermission name="hotline:shopHotline:edit"><li><a href="${ctx}/hotline/shopHotline/form">客服热线添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shopHotline" action="${ctx}/hotline/shopHotline/" method="post" class="breadcrumb form-search">
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
				<th>客服电话</th>
				<th>工作时间</th>
				<shiro:hasPermission name="hotline:shopHotline:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopHotline">
			<tr>
				<td>${shopHotline.telephone}</td>
				<td> <fmt:formatDate pattern="HH:mm:ss" value="${shopHotline.beginTime}" />-<fmt:formatDate pattern="HH:mm:ss" value="${shopHotline.endTime}" /></td>
				<shiro:hasPermission name="hotline:shopHotline:edit"><td>
    				<a href="${ctx}/hotline/shopHotline/form?id=${shopHotline.id}">编辑</a>
					<a href="${ctx}/hotline/shopHotline/delete?id=${shopHotline.id}" onclick="return confirmx('确认要删除该客服热线吗？', this.href)">删除</a>
					<c:if test="${shopHotline.status==0}">
						<a href="${ctx}/hotline/shopHotline/forbidden?id=${shopHotline.id}" onclick="return confirmx('确认要禁用客服热线吗？', this.href)">禁用</a>
					</c:if>
					<c:if test="${shopHotline.status==1}">
						<a href="${ctx}/hotline/shopHotline/enable?id=${shopHotline.id}" onclick="return confirmx('确认要启用客服热线吗？', this.href)">启用</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>