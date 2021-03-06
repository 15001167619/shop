<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收货地址管理</title>
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
		<li class="active"><a href="${ctx}/address/shopAddress/">收货地址列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="shopAddress" action="${ctx}/address/shopAddress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>收货人</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>收货人</th>
				<th>联系电话</th>
				<th>收货地址</th>
				<th>是否默认地址</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopAddress">
			<tr>
				<td>${shopAddress.name}</td>
				<td>${shopAddress.mobile}</td>
				<td>${shopAddress.provinceName} ${shopAddress.cityName} ${shopAddress.areaName} ${shopAddress.address}</td>
				<td>${shopAddress.isDefault==0?'普通地址':'默认地址'}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>