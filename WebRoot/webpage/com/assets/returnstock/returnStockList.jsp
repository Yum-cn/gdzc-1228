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
    <li class="layui-this">资产退库</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
<div id="container">
	<div id="hd"></div>
    <div id="bd">
    	<div id="main">
    	<form name="searchForm" id="searchForm" method="post" action="returnStockController.do?list" class="layui-form layui-form-pane">
    	  <div class="layui-form-item">
		    <label class="layui-form-label">存放地点:</label>
		    <div class="layui-input-inline">
				<input id="depositAddress" name="depositAddress" type="text" value="${depositAddress}" placeholder="请输入存放地点" autocomplete="off" class="layui-input"/>
		    </div>
		    <label class="layui-form-label">退库时间:</label>
		    <div class="layui-input-inline">
				<input id="returnStockTime" name="returnStockTime" type="text"  onClick="WdatePicker()" value="<fmt:formatDate value="${returnStockTime}" pattern="yyyy-MM-dd"/>" placeholder="请选择日期" autocomplete="off" class="layui-input"/>
		    </div>
		    <div class="layui-input-inline">
		      &nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-normal" onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
		    </div>
		  </div>
    	  </form>
          <button class="layui-btn" onclick="add('returnStockController.do?goAdd')"><i class="layui-icon"></i> 退库登记</button>
          <button class="layui-btn" onclick="addExport('returnStockController.do?exportXls')"><i class="layui-icon"></i> 导出</button>
<!--           <button class="layui-btn" onclick="deleteNewStyleALLSelect('提示','returnStockController.do?doBatchDel','')"><i class="layui-icon">&#xe640;</i> 批量删除</button> -->
          <form name="searchForm" id="searchForm" method="post" action="returnStockController.do?list" >
            <div class="table">


                <div class="grid">
					  <table class="layui-table">
					    <thead>
					      <tr>
									<th><input name="selectAll" id="selectAll" type="checkbox"  /></th>		
									<th>退库单号</th>	
									<th>存放地点</th>
									<th>退库时间</th>
									<th>退库明细</th>
					        <th>操作</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${resultList}" var="resultList">
					      <tr>
									<td><input name="id" id="id" type="checkbox" value="${resultList.id}" />	</td>			
									<td>${resultList.oddNumbers}</td>	
									<td>${resultList.depositAddress}</td>

									<td><fmt:formatDate value="${resultList.returnStockTime}" pattern="yyyy-MM-dd"/></td>
									<td>${resultList.relationName}</td>

					        <td><a href="javascript:void(0);" onclick="openWin('${resultList.id}');" class="tablelink">打印</a>
					        <a href="#" onclick="add('returnStockController.do?exportXls&id=${resultList.id}')" class="tablelink">导出</a>
					        <a href="returnStockController.do?goUpdate&id=${resultList.id}" class="tablelink">查看退库单</a>  
<%-- 					        <a href="javascript:void(0);" onclick="deleteDialog('提示','returnStockController.do?doDel','${resultList.id}')" class="tablelink" > 删除</a> --%>
					        </td>
					      </tr>
					      </c:forEach>
					      
					    </tbody>
					  </table>
                </div>
                
                <div class="pagin">
                
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('returnStockController.do?list&str_currentPage=<%=currentPage-1 %>')"  style="width:76px;text-decoration: none;"><span class="pagepre"> < 上一页</span></a></li>
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
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('returnStockController.do?list&str_currentPage=<%=i+1 %>')"><%=i+1 %></a></li>
								
								<%
											}
											}
										}
										if(currentPage<totalPage){
								%>
								<li class="paginItem"><a href="javascript:void(0);" onclick="onSearchForm('returnStockController.do?list&str_currentPage=<%=currentPage+1 %>')"  style="width:76px;text-decoration: none;"><span class="pagenxt">下一页 > </span></a></li>
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
	function add(url){
		window.location.href=url;
	}
	function addExport(url){
        var depositAddress = $("#depositAddress").val();
        var returnStockTime = $("#returnStockTime").val();
        
        var paramStr="";
        if(depositAddress){
            paramStr += "&depositAddress="+$("#depositAddress").val();
        }
        if(returnStockTime){
            paramStr += "&returnStockTime="+$("#returnStockTime").val();
        }
        window.location.href=url+paramStr;
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
		openuploadwin('Excel导入', 'returnStockController.do?upload', "returnStockList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("returnStockController.do?exportXls","returnStockList");
	}
	function openWin(id){
        var addurl="returnStockController.do?print&id="+id;
        $.dialog({
            content: 'url:'+addurl,
            zIndex: 10000,
            lock : true,
            width:500,
            height: 500,
            title:"打印",
            opacity : 0.3,
            cache:false, 
            button : false,
            cancel: true /*为true等价于function(){}*/
        });
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
