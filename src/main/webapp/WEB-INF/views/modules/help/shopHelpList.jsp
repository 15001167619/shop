<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>帮助中心管理</title>
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
		<li class="active"><a href="${ctx}/help/shopHelp/">帮助中心列表</a></li>
		<shiro:hasPermission name="help:shopHelp:edit"><li><a href="${ctx}/help/shopHelp/form">帮助中心添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shopHelp" action="${ctx}/help/shopHelp/" method="post" class="breadcrumb form-search">
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
				<th>帮助中心ID</th>
				<th>问题</th>
				<th>解决方案</th>
				<shiro:hasPermission name="help:shopHelp:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopHelp">
			<tr>
				<td>${shopHelp.id}</td>
				<td>${shopHelp.question}</td>
				<td>${shopHelp.solution}</td>
				<shiro:hasPermission name="help:shopHelp:edit"><td>
    				<a href="${ctx}/help/shopHelp/form?id=${shopHelp.id}">编辑</a>
					<a href="${ctx}/help/shopHelp/delete?id=${shopHelp.id}" onclick="return confirmx('确认要删除该帮助中心吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>