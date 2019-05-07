<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>水质检测类型管理</title>
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
		<li class="active"><a href="${ctx}/wrd/wqRecordType/">水质检测类型列表</a></li>
		<shiro:hasPermission name="wrd:wqRecordType:edit"><li><a href="${ctx}/wrd/wqRecordType/form">水质检测类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wqRecordType" action="${ctx}/wrd/wqRecordType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label style="width:100px">记录类型名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="45" class="input-medium"/>
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
				<th>记录类型名称</th>
				<th>类型简述</th>
				<shiro:hasPermission name="wrd:wqRecordType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wqRecordType">
			<tr>
				<td><a href="${ctx}/wrd/wqRecordType/form?id=${wqRecordType.id}">
					${wqRecordType.id}
				</a></td>
				<td>
					${wqRecordType.name}
				</td>
				<td>
					${wqRecordType.description}
				</td>
				<shiro:hasPermission name="wrd:wqRecordType:edit"><td>
    				<a href="${ctx}/wrd/wqRecordType/form?id=${wqRecordType.id}">修改</a>
					<a href="${ctx}/wrd/wqRecordType/delete?id=${wqRecordType.id}" onclick="return confirmx('确认要删除该水质检测类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>