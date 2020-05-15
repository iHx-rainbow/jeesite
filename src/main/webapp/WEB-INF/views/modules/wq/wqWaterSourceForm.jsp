<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>水源地站点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/wq/wqWaterSource/">水源地站点列表</a></li>
		<li class="active"><a href="${ctx}/wq/wqWaterSource/form?id=${wqWaterSource.id}">水源地站点<shiro:hasPermission name="wq:wqWaterSource:edit">${not empty wqWaterSource.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="wq:wqWaterSource:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wqWaterSource" action="${ctx}/wq/wqWaterSource/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">站点名称：</label>
			<div class="controls">
				<form:input path="zdName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点类型：</label>
			<div class="controls">
				<form:select path="zdTypeid" class="input-xlarge required">
					<form:option value="1" label="1"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属水厂：</label>
			<div class="controls">
				<form:select path="scId" class="input-xlarge required">
					<form:option value="1" label="1"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备ID：</label>
			<div class="controls">
				<form:select path="deviceId" class="input-xlarge ">
					<form:option value="1" label="1"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点经度：</label>
			<div class="controls">
				<form:input path="pointLon" htmlEscape="false" maxlength="45" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点纬度：</label>
			<div class="controls">
				<form:input path="pointLat" htmlEscape="false" maxlength="45" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水位数据ID：</label>
			<div class="controls">
				<form:select path="wsDataId" class="input-xlarge ">
					<form:option value="1" label="1"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警上限值：</label>
			<div class="controls">
				<form:input path="wsWarningMax" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警下限值：</label>
			<div class="controls">
				<form:input path="wsWarningMin" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片路径：</label>
			<div class="controls">
				<form:hidden id="picture" path="picture" htmlEscape="false" maxlength="45" class="input-xlarge"/>
				<sys:ckfinder input="picture" type="files" uploadPath="/wq/wqWaterSource" selectMultiple="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="wq:wqWaterSource:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>