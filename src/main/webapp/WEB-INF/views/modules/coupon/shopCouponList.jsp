<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠券管理</title>
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
		<li class="active"><a href="${ctx}/coupon/shopCoupon/">优惠券列表</a></li>
		<shiro:hasPermission name="coupon:shopCoupon:edit"><li><a href="${ctx}/coupon/shopCoupon/form">优惠券添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shopCoupon" action="${ctx}/coupon/shopCoupon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称</label>
				<form:input path="name" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>

			<li><label>类型</label>
				<form:select path="couponType" class="input-medium">
					<option value="" selected="selected">全部类型</option>
					<form:options items="${fns:getDictList('couponType')}" itemLabel="label" itemValue="value" htmlEscape="false" />
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
				<th>id</th>
				<th>名称</th>
				<th>类型</th>
				<th>金额</th>
				<th>状态</th>
				<shiro:hasPermission name="coupon:shopCoupon:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopCoupon">
			<tr>
				<td>
					${shopCoupon.id}
				</td>
				<td>
					${shopCoupon.name}
				</td>
				<td>${fns:getDictLabel(shopCoupon.couponType, 'couponType', '')}</td>
				<td>
					${shopCoupon.money}
				</td>
				<td>${fns:getDictLabel(shopCoupon.status,'dataStatus', '')}</td>
				<shiro:hasPermission name="coupon:shopCoupon:edit"><td>
    				<a href="${ctx}/coupon/shopCoupon/form?id=${shopCoupon.id}">编辑</a>
					<a href="${ctx}/coupon/shopCoupon/delete?id=${shopCoupon.id}" onclick="return confirmx('确认要删除该优惠券吗？', this.href)">删除</a>
					<c:if test="${shopCoupon.status==0}">
						<a href="${ctx}/coupon/shopCoupon/forbidden?id=${shopCoupon.id}" onclick="return confirmx('确认要禁用该优惠券吗？', this.href)">禁用</a>
					</c:if>
					<c:if test="${shopCoupon.status==1}">
						<a href="${ctx}/coupon/shopCoupon/enable?id=${shopCoupon.id}" onclick="return confirmx('确认要启用该优惠券吗？', this.href)">启用</a>
					</c:if>

				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>