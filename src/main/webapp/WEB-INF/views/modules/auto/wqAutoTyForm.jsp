<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自动投药管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/auto/wqAutoTy/">自动投药列表</a></li>
		<li class="active"><a href="${ctx}/auto/wqAutoTy/form?id=${wqAutoTy.id}">自动投药<shiro:hasPermission name="auto:wqAutoTy:edit">${not empty wqAutoTy.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="auto:wqAutoTy:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wqAutoTy" action="${ctx}/auto/wqAutoTy/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">站点名称：</label>
			<div class="controls">
				<form:input path="zdName" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点类型id：</label>
			<div class="controls">
				<form:select path="zdTypeid" class="input-xlarge ">
					<form:option value="1" label="1"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属水厂id：</label>
			<div class="controls">
				<form:select path="scId" class="input-xlarge ">
					<form:option value="1" label="1"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备id：</label>
			<div class="controls">
				<form:select path="deviceId" class="input-xlarge ">
					<form:option value="1" label="1"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">站点图片：</label>
			<div class="controls">
				<form:hidden id="picture" path="picture" htmlEscape="false" class="input-xlarge"/>
				<sys:ckfinder input="picture" type="files" uploadPath="/auto/wqAutoTy" selectMultiple="true"/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">监测配置：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>阀门名称</th>
								<th>流量数据id</th>
<%-- 								<shiro:hasPermission name="auto:wqAutoTy:edit"><th width="10">&nbsp;</th></shiro:hasPermission> --%>
							</tr>
						</thead>
						<tbody id="wqMonitoringConfList">
						</tbody>
<%-- 						<shiro:hasPermission name="auto:wqAutoTy:edit"><tfoot> --%>
<!-- 							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#wqMonitoringConfList', wqMonitoringConfRowIdx, wqMonitoringConfTpl);wqMonitoringConfRowIdx = wqMonitoringConfRowIdx + 1;" class="btn">新增</a></td></tr> -->
<%-- 						</tfoot></shiro:hasPermission> --%>
					</table>
					<script type="text/template" id="wqMonitoringConfTpl">//<!--
						<tr id="wqMonitoringConfList{{idx}}">
							<td class="hide">
								<input id="wqMonitoringConfList{{idx}}_id" name="wqMonitoringConfList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="wqMonitoringConfList{{idx}}_delFlag" name="wqMonitoringConfList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="wqMonitoringConfList{{idx}}_valveName" name="wqMonitoringConfList[{{idx}}].valveName" type="text" value="{{row.valveName}}" maxlength="50" class="input-small "/>
							</td>
							<td>
								<select id="wqMonitoringConfList{{idx}}_flowdataId" name="wqMonitoringConfList[{{idx}}].flowdataId" data-value="{{row.flowdataId}}" class="input-small ">
									<option label="1" value="1"></option>
									<c:forEach items="${fns:getDictList('')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<!--<shiro:hasPermission name="auto:wqAutoTy:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#wqMonitoringConfList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>-->
						</tr>//-->
					</script>
					<script type="text/javascript">
						var wqMonitoringConfRowIdx = 0, wqMonitoringConfTpl = $("#wqMonitoringConfTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(wqAutoTy.wqMonitoringConfList)};
							//for (var i=0; i<data.length; i++){
								addRow('#wqMonitoringConfList', wqMonitoringConfRowIdx, wqMonitoringConfTpl, data[0]);
								wqMonitoringConfRowIdx = wqMonitoringConfRowIdx + 1;
							//}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">药罐：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>药罐名称</th>
								<th>数据id</th>
								<th>位置编号</th>
								<shiro:hasPermission name="auto:wqAutoTy:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="wqMonitoringYgList">
						</tbody>
						<shiro:hasPermission name="auto:wqAutoTy:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#wqMonitoringYgList', wqMonitoringYgRowIdx, wqMonitoringYgTpl);wqMonitoringYgRowIdx = wqMonitoringYgRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="wqMonitoringYgTpl">//<!--
						<tr id="wqMonitoringYgList{{idx}}">
							<td class="hide">
								<input id="wqMonitoringYgList{{idx}}_id" name="wqMonitoringYgList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="wqMonitoringYgList{{idx}}_delFlag" name="wqMonitoringYgList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="wqMonitoringYgList{{idx}}_ygName" name="wqMonitoringYgList[{{idx}}].ygName" type="text" value="{{row.ygName}}" maxlength="50" class="input-small "/>
							</td>
							<td>
								<select id="wqMonitoringYgList{{idx}}_dataId" name="wqMonitoringYgList[{{idx}}].dataId" data-value="{{row.dataId}}" class="input-small ">
									<option label="1" value="1"></option>
									<c:forEach items="${fns:getDictList('')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="wqMonitoringYgList{{idx}}_situtationId" name="wqMonitoringYgList[{{idx}}].situtationId" type="text" value="{{row.situtationId}}" maxlength="11" class="input-small  digits"/>
							</td>
							<shiro:hasPermission name="auto:wqAutoTy:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#wqMonitoringYgList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var wqMonitoringYgRowIdx = 0, wqMonitoringYgTpl = $("#wqMonitoringYgTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(wqAutoTy.wqMonitoringYgList)};
							for (var i=0; i<data.length; i++){
								addRow('#wqMonitoringYgList', wqMonitoringYgRowIdx, wqMonitoringYgTpl, data[i]);
								wqMonitoringYgRowIdx = wqMonitoringYgRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">逻辑配置：</label>
				<div class="controls">
<%-- 								<shiro:hasPermission name="auto:wqAutoTy:edit"><th width="10">&nbsp;</th></shiro:hasPermission> --%>
						<div id="wqLogconfigList">
						</div>
<%-- 						<shiro:hasPermission name="auto:wqAutoTy:edit"><tfoot> --%>
<!-- 							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#wqLogconfigList', wqLogconfigRowIdx, wqLogconfigTpl);wqLogconfigRowIdx = wqLogconfigRowIdx + 1;" class="btn">新增</a></td></tr> -->
<%-- 						</tfoot></shiro:hasPermission> --%>
					<script type="text/template" id="wqLogconfigTpl">//<!--
						<div id="wqLogconfigList{{idx}}">
							<span class="hide">
								<input id="wqLogconfigList{{idx}}_id" name="wqLogconfigList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="wqLogconfigList{{idx}}_delFlag" name="wqLogconfigList[{{idx}}].delFlag" type="hidden" value="0"/>
							</span>
							<span>
								<input id="wqLogconfigList{{idx}}_sTime" name="wqLogconfigList[{{idx}}].sTime" readonly type="text" maxlength="2" class="input-mini digits"/><span>点至</span>
							</span>
							<span>
								<input id="wqLogconfigList{{idx}}_oTime" name="wqLogconfigList[{{idx}}].oTime" readonly type="text" maxlength="2" class="input-mini  digits"/><span>点，保持</span>
							</span>
							<span>
								<input id="wqLogconfigList{{idx}}_flow" name="wqLogconfigList[{{idx}}].flow" type="text" value="{{row.flow}}" maxlength="11" class="input-small digits"/><span>L/H的流量；</span>
							</span>
							<!--<shiro:hasPermission name="auto:wqAutoTy:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#wqLogconfigList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>-->
						</div>//-->
					</script>
					<script type="text/javascript">
						var wqLogconfigRowIdx = 0, wqLogconfigTpl = $("#wqLogconfigTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(wqAutoTy.wqLogconfigList)};
							for (let i=0; i<24; i++){
								addRow('#wqLogconfigList', wqLogconfigRowIdx, wqLogconfigTpl, data[i]);
								var id_sTime=$('input[id*=sTime]')[i].id;
								var id_oTime=$('input[id*=oTime]')[i].id;
								$('#'+id_sTime).attr({"value": i});
								$('#'+id_oTime).attr({"value": i+1});
								wqLogconfigRowIdx = wqLogconfigRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="auto:wqAutoTy:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>