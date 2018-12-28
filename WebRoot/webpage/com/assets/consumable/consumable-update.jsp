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
//   	var point = url.lastIndexOf(".");
//   	var type = url.substr(point);
//   	if(type==".doc" || type==".docx"){
		$("#template_href").attr('href',url);
		$("#template_href").html(name);
		$("#contractPath").val(url);
		$("#contractName").val(name);
//   	}else{
//   		alert("模板文件格式不正确");
//   	}
}
</script>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
  </ul>
  <div class="layui-tab-content" >
    <div class="layui-tab-item layui-show">
<form name="form1" id="form1" method="post" action="consumableController.do?doUpdate">
					<input id="id" name="id" type="hidden" value="${storePage.id }">
					<input id="createName" name="createName" type="hidden" value="${storePage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${storePage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${storePage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${storePage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${storePage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${storePage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${storePage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${storePage.sysCompanyCode }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${storePage.bpmStatus }">
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
                <input id="name" name="name" type="text" class="dfinput" value="${storePage.name}" datatype="*"/>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">耗材名称</label>
            </li>  
           <li>
              <label class="from-label-title">价格:</label>
                <input id="code" name="amount" type="text" class="dfinput" value="${storePage.amount}"  datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数"/>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">价格</label>
            </li>
            <li>
             <label class="from-label-title">总数量:</label>
               <input id="number" name="number" type="text" class="dfinput"  value="${storePage.number}" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数" />
             <i class="Validform_checktip"></i>
             <label class="Validform_label" style="display: none;">总数量</label>
            </li>           
            <li>
             <label class="from-label-title">库存量:</label>
               <input id="stockNumber" name="stockNumber" type="text" class="dfinput"  value="${storePage.stockNumber}" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数" />
             <i class="Validform_checktip"></i>
            </li>
            <li>
             <label class="from-label-title">预警库存量:</label>
               <input id="alermNumber" name="alermNumber"  value="${storePage.alermNumber}" type="text" class="dfinput" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数"/>
             <i class="Validform_checktip"></i>
            </li>          
		    <li>
		      <label class="from-label-title">备注:</label>
		      	<textarea id="remark" name="remark" cols="" rows="" class="textinput" >${storePage.remark }</textarea>
		      <i class="Validform_checktip"></i>
		    </li>
    <li>
      <label>&nbsp;</label>
      <input name="saveForm" id="saveForm" type="button" class="layui-btn" lay-submit="" lay-filter="demo1" value="确认保存"/>
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

var windowapi = frameElement.api, W = windowapi.opener;
    function choose_402881f3635381d50163539e13530004() {
        var url = 'storeController.do?selectList&status=ruku&category=software';
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

