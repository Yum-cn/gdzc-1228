<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<t:base type="jquery,tools,DatePicker"></t:base>
<!--<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />-->
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/jquery.grid.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.select.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"  media="all"></link>
<script type="text/javascript">
$(document).ready(function(){
  //$(".click").click(function(){
  //$(".tip").fadeIn(200);
  //});
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
</head>
<body>

<div class="rightinfo">
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">入库管理</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
    	<form name="searchForm" id="searchForm" method="post" action="storeController.do?list" class="layui-form layui-form-pane">
    	  <div class="layui-form-item" style="width:1980px;">
		    <label class="layui-form-label">资产编码:</label>
		    <div class="layui-input-inline">
				<input id="code" name="code" type="text" value="${code}" placeholder="请输入资产编码" autocomplete="off" class="layui-input"/>
		    </div>
		    <label class="layui-form-label">所属网络:</label>
		    <div class="layui-input-inline">
		    <t:dictSelect field="netTypeCode" id="netTypeCode" type="list" typeGroupCode="netType" defaultVal="${netTypeCode}" hasLabel="false"  title="所属网络" ></t:dictSelect> 
		    </div>
		    <label class="layui-form-label">资产名称:</label>
		    <div class="layui-input-inline">
				<input id="name" name="name" type="text" value="${name}" placeholder="请输入资产名称" autocomplete="off" class="layui-input" />
		    </div>
		    <label class="layui-form-label">资产分类:</label>
            <div class="layui-input-inline">
            <t:dictSelect field="category" id="category" type="list" typeGroupCode="zcfl" defaultVal="${category}" hasLabel="false"  title="资产分类" ></t:dictSelect> 
            </div>
		    <div class="layui-input-inline">
		      &nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
		    </div>
		  </div>
    	  </form>
    	  
          <button class="layui-btn" onclick="add('storeController.do?goAdd&category=software')"><i class="layui-icon"></i> 软件入库</button>
          <button class="layui-btn" onclick="add('storeController.do?goAdd&category=hardware')"><i class="layui-icon"></i> 硬件入库</button>
          <button class="layui-btn"  onclick="commonUpload(uploadTemplateCallBack);"><i class="layui-icon"></i> 导入</button>
          <button class="layui-btn" onclick="addExport('storeController.do?exportXls')"><i class="layui-icon"></i> 导出</button>
          <button class="layui-btn" onclick="deleteNewStyleALLSelect('提示','storeController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button>
          <button class="layui-btn" onclick="downTemplate('software')"><i class="layui-icon"></i>下载软件导入模板</button>
          <button class="layui-btn" onclick="downTemplate('hardware')"><i class="layui-icon"></i>下载硬件导入模板</button>
          <form name="searchForm" id="searchForm" method="post" action="storeController.do?list" >
            <div class="table">


                <div class="grid" >
					  <table class="layui-table">
					    <thead>
					      <tr>
									<th><input name="selectAll" id="selectAll" type="checkbox"  /></th>		
									
									<th>组别</th>	
									<th>所属网络</th>	
									<th>资产编码</th>
									<th>资产名称</th>
									<th>购买时间</th>
									<th>维保到期时间</th>
<!-- 									<th>品牌</th> -->
<!-- 									<th>渠道</th> -->
									<th>价格</th>
									<th>分类</th>
									<th>状态</th>
<!-- 									<th>过保时间</th> -->
					        <th>操作</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${resultList}" var="resultList" varStatus="stu">
					      <tr>
									<td><input name="id" id="id" type="checkbox" value="${resultList.id}" />	</td>	
									<td><t:listDictParse parseId="${resultList.groupTypeCode}" style="1" typecode="groupType"></t:listDictParse></td>		
									<td><t:listDictParse parseId="${resultList.netTypeCode}" style="1" typecode="netType"></t:listDictParse></td>
									<td>${resultList.code}</td>	
									<td>${resultList.name}</td>
									<td>${resultList.payTime}<%-- <fmt:formatDate value="${resultList.storageTime}" pattern="yyyy-MM-dd"/> --%></td>
									<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set> 
                                    <fmt:parseDate value="${resultList.repairEndTime}" pattern="yyyy-MM-dd" var="textDate"/>
									<td 
									
									<c:if test="${((textDate.time-nowDate) <= 2592000000) && ((textDate.time-nowDate) > 0) }">style="background-color: yellow;"</c:if>
									<c:if test="${(textDate.time-nowDate) <0 }">style="background-color: red;"</c:if>
									><fmt:formatDate value="${textDate}" pattern="yyyy-MM-dd" />
									<c:remove var="textDate"/>
									<%-- ${stu.index==1?'2018-12-11':'' }
									${stu.index==0?'2018-11-11':'' }
									${stu.index!=0 && stu.index!=1?'2019-11-11':'' } --%>
									</td>
<%-- 									<td>${resultList.channel}</td> --%>
									<td>${resultList.amount}</td>
									<td>${resultList.category=='software'?'软件':'硬件'}</td>
									<td><%-- <t:listDictParse parseId="${resultList.deviceStatusCode}" style="1" typecode="deviceStatus"></t:listDictParse> --%>
									<t:listDictParse parseId="${resultList.bpmStatus}" style="1" typecode="zczt"></t:listDictParse></td>	
<%-- 									<td><fmt:formatDate value="${resultList.overInsuranceTime}" pattern="yyyy-MM-dd"/></td> --%>

					        <td><a href="javascript:void(0);" onclick="openWin('${resultList.id}');" class="tablelink">标签打印</a>
					        <a href="javascript:void(0);" onclick="openResumeWin('${resultList.id}');" class="tablelink">查看履历</a>
					          <a href="storeController.do?goUpdate&id=${resultList.id}" class="tablelink">修改</a>
					            <a href="javascript:void(0);" onclick="deleteDialog('提示','storeController.do?doDel','${resultList.id}')" class="tablelink" > 删除</a></td>
					      </tr>
					      </c:forEach>
					      
					    </tbody>
					  </table>
                </div>
                
                <div class="pagin" style="height:300px;">
                
                <%
					String currentPageString = String.valueOf(request.getAttribute("currentPage"));
					String pageRowString = String.valueOf(request.getAttribute("pageRow"));
					String totalRowString = String.valueOf(request.getAttribute("totalRow"));
					String totalPageString = String.valueOf(request.getAttribute("totalPage"));
					int currentPage = Integer.parseInt(currentPageString);
					int pageRow = Integer.parseInt(pageRowString);
					int totalRow = Integer.parseInt(totalRowString);
					int totalPage = Integer.parseInt(totalPageString);
				  %>
				
    				<div class="message">共<i class="blue">&nbsp;&nbsp;${totalRow }&nbsp;&nbsp;</i>条记录，当前显示第&nbsp;<i class="blue">&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;</i>页</div>
				    <ul class="paginList">
				    			<%
										if(currentPage>1){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('storeController.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
								<%
										}else{
								%>
											<li class="paginItem current"><a href="javascript:;" style="width:76px;text-decoration: none;">< 上一页</a></li>
								<%
										}
										int startView = currentPage - 4;
										int endView = currentPage + 4;
										for(int i=0;i<totalPage;i++){
											if((i+1)>=startView && (i+1)<=endView){
											if(String.valueOf((i+1)).equals(currentPageString)){
								%>
											<li class="paginItem current"><a href="javascript:;"><%=i+1 %></a></li>
								<%
											}else{
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('storeController.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
								
								<%
											}
											}
										}
										if(currentPage<totalPage){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('storeController.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
								<%
										}else{
								%>
											<li class="paginItem current"><a href="javascript:;" style="width:76px;text-decoration: none;">下一页 ></a></li>
								<%
										}
									
								%>		
				    </ul>

                
                
                </div>
            </div>
        </div>
    </div>
</div>

</form>

    </div>

  </div>
</div> 

  

<script type="text/javascript">
	function addExport(url){
	    var code = $("#code").val();
	    var netTypeCode = $("#netTypeCode").val();
	    var name = $("#name").val();
	    var category = $("#category").val();
	    
	    var paramStr="";
	    if(code){
	        paramStr += "&code="+$("#code").val();
	    }
	    if(netTypeCode){
	        paramStr += "&netTypeCode="+$("#netTypeCode").val();
	    }
	    if(name){
	        paramStr += "&name="+$("#name").val();
	    }
	    if(category){
	        paramStr += "&category="+$("#category").val();
	    }
	    window.location.href=url+paramStr;
	}
	
	
	function add(url){
		window.location.href=encodeURI(url);
	}
	function update(url){
		window.location.href=url;
	}
	//$('.tablelist tbody tr:odd').addClass('odd');
	function onSearchForm(url){
		$("#searchForm").attr("action",url);
		$("#searchForm").submit();
	}
	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'storeController.do?upload', "storeList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("storeController.do?exportXls","storeList");
	}
	//下载
	function downTemplate(type) {
		if(type=='software'){
			window.location.href="export/template/software_template.xls";
		}else if(type=='hardware'){
			window.location.href="export/template/hardware_template.xls";
		}
		
	}
// 	function openWin(id){
// 		location.href="storeController.do?print&id="+id;
// 	}
	function openWin(id){
		var addurl="storeController.do?print&id="+id;
		$.dialog({
			content: 'url:'+addurl,
			zIndex: 10000,
			lock : true,
			width:350,
			height: 350,
			title:"标签打印",
			opacity : 0.3,
			cache:false, 
			button : [ {
				name : '打印预览',
				callback : function() {
					iframe = this.iframe.contentWindow;
					iframe.myPreview1();
					return false;
				},
				focus : true
			}],
		    cancel: true /*为true等价于function(){}*/
		});
	}
	function openResumeWin(id){
		var addurl="resumeController.do?list&storeId="+id;
		$.dialog({
			content: 'url:'+addurl,
			zIndex: 10000,
			lock : true,
			width:850,
			height: 350,
			title:"资产履历",
			opacity : 0.3,
			cache:false, 
		    cancel: true /*为true等价于function(){}*/
		});
	}
	
	function reloadTable(){
        $('#searchForm').datagrid('reload');
    }
	
	function uploadTemplateCallBack(url,name){
		
		$.post("storeController.do?importExcel",{fileName:url},function(result){
			var jsonObj = JSON.parse(result);
			$.dialog.alert(jsonObj.msg?jsonObj.msg:"操作成功！");
			if(jsonObj.success){
				window.location.href="storeController.do?list";
			}
            //var jsonObj = JSON.parse(result);
            //$("#cardNo").val(jsonObj.obj);
        });
		
		//upload/files/20181223/20181223165957f1J8pFeH.xlsx
//      var point = url.lastIndexOf(".");
//      var type = url.substr(point);
//      if(type==".doc" || type==".docx"){
/*         $("#template_href").attr('href',url);
        $("#template_href").html(name);
        $("#contractPath").val(url);
        $("#contractName").val(name); */
//      }else{
//          alert("模板文件格式不正确");
//      }
}
	
</script>
</body>
</html>
<script src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['form'], function(){
  var form = layui.form()
});
</script>
