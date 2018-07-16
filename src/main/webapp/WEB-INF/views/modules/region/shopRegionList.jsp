]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行政区域管理</title>
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
		<li class="active"><a href="${ctx}/region/region/shopRegion/">行政区域列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="shopRegion" action="${ctx}/region/shopRegion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>区域名称</label>
				<form:input path="name" htmlEscape="false" maxlength="120" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>区域id</th>
				<th>区域父id</th>
				<th>区域名称</th>
				<th>区域类型</th>
				<th>区域编码</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopRegion">
			<tr>
				<td>${shopRegion.id}</td>
				<td>${shopRegion.pid}</td>
				<td>${shopRegion.name}</td>
				<td>${fns:getTypeName(shopRegion.type, '')}</td>
				<td>${shopRegion.code}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>