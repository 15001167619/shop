<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/order/shopOrder/">订单列表</a></li>
		<%--<shiro:hasPermission name="order:shopOrder:edit"><li><a href="${ctx}/order/shopOrder/form">订单添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="shopOrder" action="${ctx}/order/shopOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户Id</label>
				<form:input path="userId" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>商品Id</label>
				<form:input path="commodityId" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li><label>订单状态</label>
				<form:select path="orderStatus" class="input-medium">
					<option value="" selected="selected">全部类型</option>
					<form:options items="${fns:getDictList('orderStatus')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>手机号</label>
				<form:input path="mobile" htmlEscape="false" maxlength="45" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单Id</th>
				<th>用户Id</th>
				<th>手机号</th>
				<th>商品Id</th>
				<th>订单状态</th>
				<th>订单费用</th>
				<th>实际费用</th>
				<shiro:hasPermission name="order:shopOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopOrder">
			<tr>
				<td>${shopOrder.id}</td>
				<td>${shopOrder.userId}</td>
				<td>${shopOrder.mobile}</td>
				<td>${shopOrder.commodityId}</td>
				<td>${fns:getDictLabel(shopOrder.orderStatus, 'orderStatus', '')}</td>
				<td>${shopOrder.orderPrice}</td>
				<td>${shopOrder.actualPrice}</td>
				<shiro:hasPermission name="order:shopOrder:edit"><td>
					<a href="${ctx}/order/shopOrder/delete?id=${shopOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>