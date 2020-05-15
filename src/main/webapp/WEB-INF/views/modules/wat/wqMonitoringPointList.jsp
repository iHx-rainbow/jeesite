<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>point管理</title>
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
		<li class="active"><a href="${ctx}/wat/wqMonitoringPoint/">point列表</a></li>
		<shiro:hasPermission name="wat:wqMonitoringPoint:edit"><li><a href="${ctx}/wat/wqMonitoringPoint/form">point添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wqMonitoringPoint" action="${ctx}/wat/wqMonitoringPoint/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>监测点名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>监测点类型</th>
				<th>监测点名称</th>
				<th>描述</th>
				<th>所属街道/村id</th>
				<th>所在河流</th>
				<th>地理经度</th>
				<th>地理纬度</th>
				<th>站点图片存储路径</th>
				<shiro:hasPermission name="wat:wqMonitoringPoint:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wqMonitoringPoint">
			<tr>
				<td><a href="${ctx}/wat/wqMonitoringPoint/form?id=${wqMonitoringPoint.id}">
					${wqMonitoringPoint.wqMonitoringType.name}
				</a></td>
				<td>
					${wqMonitoringPoint.name}
				</td>
				<td>
					${wqMonitoringPoint.description}
				</td>
				<td>
					${wqMonitoringPoint.villageId}
				</td>
				<td>
					${wqMonitoringPoint.riverId}
				</td>
				<td>
					${wqMonitoringPoint.longitude}
				</td>
				<td>
					${wqMonitoringPoint.latitude}
				</td>
				<td>
					${wqMonitoringPoint.images}
				</td>
				<shiro:hasPermission name="wat:wqMonitoringPoint:edit"><td>
    				<a href="${ctx}/wat/wqMonitoringPoint/form?id=${wqMonitoringPoint.id}">修改</a>
					<a href="${ctx}/wat/wqMonitoringPoint/delete?id=${wqMonitoringPoint.id}" onclick="return confirmx('确认要删除该point吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>