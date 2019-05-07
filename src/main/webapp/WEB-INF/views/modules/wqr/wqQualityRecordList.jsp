<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>水质检测记录管理</title>
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
		<li class="active"><a href="${ctx}/wqr/wqQualityRecord/">水质检测记录列表</a></li>
		<shiro:hasPermission name="wqr:wqQualityRecord:edit"><li><a href="${ctx}/wqr/wqQualityRecord/form">水质检测记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wqQualityRecord" action="${ctx}/wqr/wqQualityRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label style="width:90px">所属监测站id：</label>
				<form:input path="pointId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>记录的类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${typelist}" itemLabel="name" itemValue="id" htmlEscape="false"/>
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
				<th>所属监测站id</th>
				<th>记录采集时间</th>
				<th>检测项目COD</th>
				<th>检测项目DO</th>
				<th>检测项目ph值</th>
				<th>检测项目BOD值</th>
				<th>检测项目水温</th>
				<th>检测项目nh3</th>
				<th>记录的类型</th>
				<shiro:hasPermission name="wqr:wqQualityRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wqQualityRecord">
			<tr>
				<td><a href="${ctx}/wqr/wqQualityRecord/form?id=${wqQualityRecord.id}">
					${wqQualityRecord.id}
				</a></td>
				<td>
					${wqQualityRecord.pointId}
				</td>
				<td>
					<fmt:formatDate value="${wqQualityRecord.collectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${wqQualityRecord.cod}
				</td>
				<td>
					${wqQualityRecord.doo}
				</td>
				<td>
					${wqQualityRecord.ph}
				</td>
				<td>
					${wqQualityRecord.bod}
				</td>
				<td>
					${wqQualityRecord.temperature}
				</td>
				<td>
					${wqQualityRecord.nh3}
				</td>
				<td>
					<c:forEach var="type" items="${typelist}">
					<c:if test="${type.id.equals(wqQualityRecord.type)}">
					<c:out value="${type.name}" />
					</c:if>
					</c:forEach>
					<!--${wqQualityRecord.type}-->
				</td>
				<shiro:hasPermission name="wqr:wqQualityRecord:edit"><td>
    				<a href="${ctx}/wqr/wqQualityRecord/form?id=${wqQualityRecord.id}">修改</a>
					<a href="${ctx}/wqr/wqQualityRecord/delete?id=${wqQualityRecord.id}" onclick="return confirmx('确认要删除该水质检测记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>