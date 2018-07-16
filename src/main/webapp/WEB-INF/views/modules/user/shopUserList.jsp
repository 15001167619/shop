<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
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
		<li class="active"><a href="${ctx}/user/shopUser/">会员列表</a></li>
		<shiro:hasPermission name="user:shopUser:edit"><li><a href="${ctx}/user/shopUser/form">会员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shopUser" action="${ctx}/user/shopUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名</label>
				<form:input path="userName" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>昵称</label>
				<form:input path="nickName" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>手机号</label>
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户Id</th>
				<th>用户名</th>
				<th>昵称</th>
				<th>手机号</th>
				<th>性别</th>
				<th>生日</th>
				<th>级别</th>
				<shiro:hasPermission name="user:shopUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopUser">
			<tr>
				<td>${shopUser.id}</td>
				<td>${shopUser.userName}</td>
				<td>${shopUser.nickName}</td>
				<td>${shopUser.mobile}</td>
				<td>${fns:getDictLabel(shopUser.gender, 'sex', '')}</td>
				<td> <fmt:formatDate pattern="yyyy-MM-dd" value="${shopUser.birthday}" /></td>
				<td>${fns:getDictLabel(shopUser.userLevel, 'userLevel', '')}</td>
				<shiro:hasPermission name="user:shopUser:edit"><td>
    				<a href="${ctx}/user/shopUser/form?id=${shopUser.id}">编辑</a>
					<a href="${ctx}/user/shopUser/delete?id=${shopUser.id}" onclick="return confirmx('确认要删除该会员吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>