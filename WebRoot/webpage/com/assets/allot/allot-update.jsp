<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<t:base type="jquery,tools,DatePicker"></t:base>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/css/style.css" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/Validform_v5.3.2/js/Validform_v5.3.2_min.js"></script>
<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<style>
.from-label-title{font-size:14px;text-align: right;padding-right:16px;}
</style>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content" >
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="allotController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${allotPage.id }">
					<input id="createName" name="createName" type="hidden" value="${allotPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${allotPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${allotPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${allotPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${allotPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${allotPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${allotPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${allotPage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${allotPage.bpmStatus }">
<div class="formbody">

  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">资产:</label>
		      	<input name="relationId" id="relationId" type="hidden" value="${allotPage.relationId }" >
                <input name="relationName" class="dfinput" value="${allotPage.relationName }" id="relationName" readonly="readonly" datatype="*"  readonly="readonly"/>
<!--                 <a href="#" class="layui-btn" plain="true" icon="icon-search" onClick="choose_402881f3635381d50163539e13530004()">选择</a> -->
<!--                 <a href="#" class="layui-btn" plain="true" icon="icon-redo" onClick="clearAll_402881f3635381d50163539e13530004();">清空</a> -->
		      <i class="Validform_checktip"></i>
		    </li>  
    <li>
      <label class="from-label-title">使用人:</label>
      	<input id="useUser" name="useUser" type="text" value="${allotPage.useUser }" class="dfinput" datatype="*" readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">使用室:</label>
      	<input id="address" name="address" type="text" value="${allotPage.address }" class="dfinput" datatype="*" readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
    <li>
      <label class="from-label-title">转移时间:</label>
      	<input id="allotTime" name="allotTime" type="text" value="<fmt:formatDate value="${allotPage.allotTime }" pattern="yyyy-MM-dd"/>" class="dfinput" class="Wdate"  datatype="*"  readonly="readonly"/>
      <i class="Validform_checktip"></i>
    </li>
		    <li>
		      <label class="from-label-title">备注:</label>
		      	<input id="remark" name="remark"  class="dfinput" datatype="*" value="${allotPage.remark }" readonly="readonly"  readonly="readonly"></input>
		      <i class="Validform_checktip"></i>
		    </li>

    <li>
      <label>&nbsp;</label>
      <input onclick="window.history.back();" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="返回"/>
      <!-- <input onclick="window.history.back();" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="导出"/>
      <input onclick="window.history.back();" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="打印"/> --><br></br>
    </li>
  </ul>
</div>
</form>

	</div>
  </div>
</div> 
</body>
</html>
<script type="text/javascript">
$(function(){
	$("#form1").Validform({
		tiptype:3
	});
	$("#saveForm").click(function(){
    	$("#form1").submit();
  	});
})
</script>
<script type="text/javascript">
	var windowapi = frameElement.api, W = windowapi.opener;
	function choose_402881f3635381d50163539e13530004() {
		var url = 'storeController.do?selectList&status=diaobo';
		var initValue = $('#roleid').val();
		url += '&ids=' + initValue;
		if (typeof (windowapi) == 'undefined') {
			$.dialog({
				content : 'url:' + url,
				zIndex : 2100,
				title : '资产列表',
				lock : true,
				width : 800,
				height : 350,
				left : '85%',
				top : '65%',
				opacity : 0.4,
				button : [ {
					name : '确定',
					callback : clickcallback_402881f3635381d50163539e13530004,
					focus : true
				}, {
					name : '取消',
					callback : function() {
					}
				} ]
			});
		} else {
			$.dialog({
				content : 'url:' + url,
				zIndex : 2100,
				title : '资产列表',
				lock : true,
				parent : windowapi,
				width : 800,
				height : 350,
				left : '85%',
				top : '65%',
				opacity : 0.4,
				button : [ {
					name : '确定',
					callback : clickcallback_402881f3635381d50163539e13530004,
					focus : true
				}, {
					name : '取消',
					callback : function() {
					}
				} ]
			});
		}
	}
	function clearAll_402881f3635381d50163539e13530004() {
		if ($('#relationName').length >= 1) {
			$('#relationName').val('');
			$('#relationName').blur();
		}
		if ($("input[name='relationName']").length >= 1) {
			$("input[name='relationName']").val('');
			$("input[name='relationName']").blur();
		}
		$('#relationId').val("");
	}
	function clickcallback_402881f3635381d50163539e13530004() {
		iframe = this.iframe.contentWindow;
		var code = iframe.getstoreListSelections('code');
		var name = iframe.getstoreListSelections('name');
		var returnName = name + "("+code+")";
		if ($('#relationName').length >= 1) {
			$('#relationName').val(returnName);
			$('#relationName').blur();
		}
		if ($("input[name='relationName']").length >= 1) {
			$("input[name='relationName']").val(returnName);
			$("input[name='relationName']").blur();
		}
		var id = iframe.getstoreListSelections('id');
		if (id !== undefined && id != "") {
			$('#relationId').val(id);
		}
	}
</script>

