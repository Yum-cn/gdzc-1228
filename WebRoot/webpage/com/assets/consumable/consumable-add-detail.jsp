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
function readCardNo(){
    $.post("useController.do?readCardNo",{suggest:''},function(result){
        var jsonObj = JSON.parse(result);
        if(jsonObj.attributes==null || jsonObj.attributes==''){
            alert("卡未绑定，请在用户中心绑定人员");
        }else{
            $("#useUser").val(jsonObj.attributes.username);
            $("#address").val(jsonObj.attributes.departname);
        }

    });
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
		<form name="form1" id="form1" method="post" action="consumableController.do?doAddDetail">
		<div class="formbody">
		  <ul class="forminfo">
		  	<li>
              <label class="from-label-title">耗材名称:</label>
                <input name="consumableOperateType" id="consumableOperateType" type="hidden" value="use">
                <input name="consumableId" id="relationId" type="hidden" value="">
                <input name="softListNames" class="dfinput" style="vertical-align: top; " value="" id="relationName" datatype="*" />
                <a href="#" class="layui-btn" plain="true" icon="icon-search" onClick="choose_402881f3635381d50163539e13530004()">选择</a>
                <a href="#" class="layui-btn" plain="true" icon="icon-redo" onClick="clearAll_402881f3635381d50163539e13530004();">清空</a>
              <i class="Validform_checktip">耗材名称</i>
              <label class="Validform_label" style="display: none;">耗材名称</label>
            </li> 
            <%-- <li>
              <label class="from-label-title">耗材操作类型:</label>
               <t:dictSelect field="consumableOperateType" type="list"
                                        typeGroupCode="consumeOpType" defaultVal="" hasLabel="false"  title="耗材操作类型" datatype="*"></t:dictSelect>
              <i class="Validform_checktip"></i>
              <label class="Validform_label" style="display: none;">耗材操作类型</label>
            </li> --%> 
            <li>
              <label class="from-label-title">领用时间:</label>
                <input id="useTime" name="useTime" type="text" class="dfinput" class="Wdate" onClick="WdatePicker()" datatype="*"/>
              <i class="Validform_checktip"></i>
            </li> 
		    <li>
             <label class="from-label-title">数量:</label>
               <input id="number" name="number" type="text" class="dfinput" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="不能为空" errormsg="必须为数字，可以有小数"/>
             <i class="Validform_checktip"></i>
             <label class="Validform_label" style="display: none;">数量</label>
            </li>
            <li>
		      <label>所属部门:</label>
		      <input id="departname" name="useOrgCode" type="text" readonly="readonly" class="dfinput" value="${departname}">
                <input id="orgIds" name="orgIds" type="hidden" value="${orgIds}">
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" id="departSearch" onclick="openDepartmentSelect()">选择</a>
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" id="departRedo" onclick="callbackClean()">清空</a>
                <span class="Validform_checktip"><t:mutiLang langKey="please.muti.department"/></span>      
		       
		    </li>
		    <li>
              <label class="from-label-title">使用人:</label>
                <input id="useUser" name="usePerson" type="text" class="dfinput" datatype="*"/>
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
var windowapi = frameElement.api, W = windowapi.opener;
    function choose_402881f3635381d50163539e13530004() {
        var url = 'consumableController.do?selectList';
        var initValue = $('#roleid').val();
        url += '&ids=' + initValue;
        if (typeof (windowapi) == 'undefined') {
            $.dialog({
                content : 'url:' + url,
                zIndex : 2100,
                title : '耗材列表',
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
                title : '耗材列表',
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
        var code = iframe.getstoreListSelections('id');
        var name = iframe.getstoreListSelections('name');
        var returnName = name;
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
    
    
    function openDepartmentSelect() {
        $.dialog.setting.zIndex = getzIndex(); 
        
        var orgIds = $("#orgIds").val();
        
        $.dialog({content: 'url:departController.do?departSelect&orgIds='+orgIds, zIndex: 2100, title: '组织机构列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
           {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackDepartmentSelect, focus: true},
           {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
       ]}).zindex();
    }
        
    function callbackDepartmentSelect() {
          var iframe = this.iframe.contentWindow;
          var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
          var nodes = treeObj.getCheckedNodes(true);
          if(nodes.length>0){
          var ids='',names='';
          for(i=0;i<nodes.length;i++){
             var node = nodes[i];
             ids += node.id+',';
            names += node.name+',';
         }
         $('#departname').val(names);
         $('#departname').blur();       
         $('#orgIds').val(ids);     
        }
    }
    
    function callbackClean(){
        $('#departname').val('');
         $('#orgIds').val('');  
    }
    
    function setOrgIds() {}
    $(function(){
        $("#departname").prev().hide();
    });
</script>

