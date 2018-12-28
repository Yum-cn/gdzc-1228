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
/* .from-label-title{font-size:14px;text-align: right;padding-right:16px;width:2100px;} */
</style>
<script>
</script>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
		<form name="form1" id="form1" method="post" action="consumableController.do?doAdd">
		<div class="formbody">
		  <ul class="forminfo">
		  	<li>
             <label class="from-label-title">类别:</label>
               <t:dictSelect field="type" type="list"
               typeGroupCode="zclb" defaultVal="${storePage.type}" hasLabel="false"  title="类别" datatype="*"></t:dictSelect>  
             <i class="Validform_checktip"></i>
             <label class="Validform_label" style="display: none;">类别</label>
           </li>
		    <li>
		      <label class="from-label-title">耗材名称:</label>
		      	<input id="name" name="name" type="text" class="dfinput" datatype="*"/>
		      <i class="Validform_checktip"></i>
		      <label class="Validform_label" style="display: none;">耗材名称</label>
		    </li>
		    <li>
              <label class="from-label-title">价格:</label>
                <input id="code" name="amount" type="text" class="dfinput"  datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数"/>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">价格</label>
            </li>	    
		    <li>
             <label class="from-label-title">数量:</label>
               <input id="number" name="number" type="text" class="dfinput" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数"/>
             <i class="Validform_checktip"></i>
             <label class="Validform_label" style="display: none;">数量</label>
            </li>           
		    <li>
             <label class="from-label-title">库存量:</label>
               <input id="number" name="stockNumber" type="text" class="dfinput" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数"/>
             <i class="Validform_checktip"></i>
            </li>	    
		    <li>
             <label class="from-label-title">预警库存量:</label>
               <input id="alermNumber" name="alermNumber" type="text" class="dfinput" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数"/>
             <i class="Validform_checktip"></i>
            </li>	    
		    <li>
		      <label class="from-label-title">备注:</label>
		      	<textarea id="remark" name="remark" cols="" rows="" class="textinput"></textarea>
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

