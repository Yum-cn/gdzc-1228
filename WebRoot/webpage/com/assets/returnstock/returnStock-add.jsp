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
<script>
function uploadTemplateCallBack(url,name){
//      var point = url.lastIndexOf(".");
//      var type = url.substr(point);
//      if(type==".doc" || type==".docx"){
        $("#template_href").attr('href',url);
        $("#template_href").html(name);
        $("#contractPath").val(url);
        $("#contractName").val(name);
//      }else{
//          alert("模板文件格式不正确");
//      }
}
</script>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
		<form name="form1" id="form1" method="post" action="returnStockController.do?doAdd">
		<div class="formbody">
		  <ul class="forminfo">
		    <li>
		      <label class="from-label-title">资产:</label>
		      	<input name="relationId" id="relationId" type="hidden" value="">
                <input name="relationName" class="dfinput" value="" id="relationName" readonly="readonly" datatype="*" />
                <a href="#" class="layui-btn" plain="true" icon="icon-search" onClick="choose_402881f3635381d50163539e13530004()">选择</a>
                <a href="#" class="layui-btn" plain="true" icon="icon-redo" onClick="clearAll_402881f3635381d50163539e13530004();">清空</a>
		      <i class="Validform_checktip"></i>
		    </li>		  
		    <li>
		      <label class="from-label-title">存放地点:</label>
		      	<input id="depositAddress" name="depositAddress" type="text" class="dfinput" datatype="*"/>
		      <i class="Validform_checktip"></i>
		    </li>
		    <li>
		      <label class="from-label-title">退库时间:</label>
		      	<input id="returnStockTime" name="returnStockTime" type="text" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/>
		      <i class="Validform_checktip"></i>
		    </li>
				<li>
					<label class="from-label-title">
							附件:
					</label>
							<input id="contractPath" name="contractPath" type="hidden" style="width: 150px" class="inputxt">
							<input id="contractName" name="contractName" type="hidden" style="width: 150px" class="inputxt">
							<a id="template_href" style="width:180px;">暂时未上传附件</a>
							<input class="ui-button" type="button" value="上传附件" onclick="commonUpload(uploadTemplateCallBack);"/>   
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">附件</label>
				</li>		    
		    <li>		    
		    <li>
		      <label class="from-label-title">备注:</label>
		      	<input id="remark" name="remark"  class="dfinput" datatype="*"></input>
		      <i class="Validform_checktip"></i>
		    </li>

		    <li>
		      <label>&nbsp;</label>
		      <button class="layui-btn" lay-submit="" lay-filter="demo1" name="saveForm" id="saveForm">确认保存</button>
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
		var url = 'storeController.do?selectList&status=tuiku';
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
