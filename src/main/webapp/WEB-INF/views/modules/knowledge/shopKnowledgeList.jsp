<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>常识管理</title>
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
		<li class="active"><a href="${ctx}/knowledge/shopKnowledge/">常识列表</a></li>
		<shiro:hasPermission name="knowledge:shopKnowledge:edit"><li><a href="${ctx}/knowledge/shopKnowledge/form">常识添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shopKnowledge" action="${ctx}/knowledge/shopKnowledge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%--<ul class="ul-form">--%>
			<%--<li><label>标题：</label>--%>
				<%--<form:input path="name" htmlEscape="false" maxlength="120" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
			<%--<li class="clearfix"></li>--%>
		<%--</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>浏览量</th>
				<th>添加时间</th>
				<shiro:hasPermission name="knowledge:shopKnowledge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopKnowledge">
			<tr>
				<td>${shopKnowledge.name}</td>
				<td>${shopKnowledge.browseCount}</td>
				<td> <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${shopKnowledge.addTime}" /></td>
				<shiro:hasPermission name="knowledge:shopKnowledge:edit"><td>
    				<a href="${ctx}/knowledge/shopKnowledge/form?id=${shopKnowledge.id}">编辑</a>
					<a href="${ctx}/knowledge/shopKnowledge/delete?id=${shopKnowledge.id}" onclick="return confirmx('确认要删除该常识吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>