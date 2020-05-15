<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>水压站点管理</title>
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
		<li class="active"><a href="${ctx}/wq/wqWaterPressure/">水压站点列表</a></li>
		<shiro:hasPermission name="wq:wqWaterPressure:edit"><li><a href="${ctx}/wq/wqWaterPressure/form">水压站点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wqWaterPressure" action="${ctx}/wq/wqWaterPressure/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属水厂：</label>
				<form:select path="scId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>序号</th>
				<th>站点名称</th>
				<th>所属水厂</th>
				<shiro:hasPermission name="wq:wqWaterPressure:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wqWaterPressure">
			<tr>
				<td><a href="${ctx}/wq/wqWaterPressure/form?id=${wqWaterPressure.id}">
					${wqWaterPressure.id}
				</a></td>
				<td>
					${wqWaterPressure.zdName}
				</td>
				<td>
					${fns:getDictLabel(wqWaterPressure.scId, '', '')}
				</td>
				<shiro:hasPermission name="wq:wqWaterPressure:edit"><td>
    				<a href="${ctx}/wq/wqWaterPressure/form?id=${wqWaterPressure.id}">修改</a>
					<a href="${ctx}/wq/wqWaterPressure/delete?id=${wqWaterPressure.id}" onclick="return confirmx('确认要删除该水压站点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>