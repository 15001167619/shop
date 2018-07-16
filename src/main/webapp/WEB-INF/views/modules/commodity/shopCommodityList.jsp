<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
		<li class="active"><a href="${ctx}/commodity/shopCommodity/">商品列表</a></li>
		<shiro:hasPermission name="commodity:shopCommodity:edit"><li><a href="${ctx}/commodity/shopCommodity/form">商品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shopCommodity" action="${ctx}/commodity/shopCommodity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品名称</label>
				<form:input path="name" htmlEscape="false" maxlength="120" class="input-medium"/>
			</li>
			<li><label>是否推荐</label>
				<form:select path="isOnSale" class="input-medium">
					<option value="" selected="selected">全部类型</option>
					<form:options items="${fns:getDictList('is_on_sale')}" itemLabel="label" itemValue="value" htmlEscape="false" />
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
				<th>商品名称</th>
				<th>是否推荐</th>
				<th>专柜价格</th>
				<th>零售价格</th>
				<shiro:hasPermission name="commodity:shopCommodity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopCommodity">
			<tr>
				<td>${shopCommodity.name}</td>
				<td>${fns:getDictLabel(shopCommodity.isOnSale, 'is_on_sale', '')}</td>
				<td>${shopCommodity.counterPrice}</td>
				<td>${shopCommodity.retailPrice}</td>
				<shiro:hasPermission name="commodity:shopCommodity:edit"><td>
    				<a href="${ctx}/commodity/shopCommodity/form?id=${shopCommodity.id}">编辑</a>
					<a href="${ctx}/commodity/shopCommodity/delete?id=${shopCommodity.id}" onclick="return confirmx('确认要删除该商品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>