<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
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
		<li class="active"><a href="${ctx}/comment/shopComment/">评论列表</a></li>
		<%--<shiro:hasPermission name="comment:shopComment:edit"><li><a href="${ctx}/comment/shopComment/form">评论添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="shopComment" action="${ctx}/comment/shopComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户Id</label>
				<form:input path="userId" htmlEscape="false" maxlength="3" class="input-medium"/>
			</li>
			<li><label>评论是否可见</label>
				<form:select path="visible" class="input-medium">
					<option value="" selected="selected">全部</option>
					<form:options items="${fns:getDictList('visible')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品ID</th>
				<th>用户ID</th>
				<th>评论内容</th>
				<th>评论时间</th>
				<th>是否配图</th>
				<th>是否可见</th>
				<shiro:hasPermission name="comment:shopComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopComment">
			<tr>
				<td>${shopComment.businessId}</td>
				<td>${shopComment.userId}</td>
				<td>${shopComment.content}</td>
				<td> <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${shopComment.addTime}" /></td>
				<td>${shopComment.hasPicture==0?'无图':'有图'}</td>
				<td>${fns:getDictLabel(shopComment.visible, 'visible', '')}</td>
				<shiro:hasPermission name="comment:shopComment:edit"><td>
    				<a href="${ctx}/comment/shopComment/form?id=${shopComment.id}">查看</a>
					<a href="${ctx}/comment/shopComment/delete?id=${shopComment.id}" onclick="return confirmx('确认要删除该评论吗？', this.href)">删除</a>
					<c:if test="${shopComment.visible==0}">
						<a href="${ctx}/comment/shopComment/forbidden?id=${shopComment.id}" onclick="return confirmx('确认要设置评论不可见？', this.href)">设置不可见</a>
					</c:if>
					<c:if test="${shopComment.visible==1}">
						<a href="${ctx}/comment/shopComment/enable?id=${shopComment.id}" onclick="return confirmx('确认要开启评论可见？', this.href)">设置可见</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>