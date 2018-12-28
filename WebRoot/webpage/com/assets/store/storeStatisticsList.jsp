<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<t:base type="jquery,tools,DatePicker"></t:base>
<!--<link href="${pageContext.request.contextPath}/plug-in/frame/default/css/style.css" rel="stylesheet" type="text/css" />-->
<link
	href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/skin_/table.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/css/jquery.grid.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plug-in/frame/default/mainFrame/js/jquery.select.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/css/layui.css"
	media="all"></link>
<script type="text/javascript">
	$(document).ready(function() {
		//$(".click").click(function(){
		//$(".tip").fadeIn(200);
		//});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
</script>
</head>
<body>

	<div class="rightinfo">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			<ul class="layui-tab-title">
				<li class="layui-this">统计分析</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<div id="container">
						<div id="hd"></div>
						<div id="bd">
							<div id="main">
								<form name="searchForm" id="searchForm" method="post"
									action="storeController.do?/statistics/list"
									class="layui-form layui-form-pane">
									<div class="layui-form-item" style="width: 1980px;">

										<label class="layui-form-label">所属组别:</label>
										<div class="layui-input-inline">
											<t:dictSelect field="groupTypeCode" id="groupTypeCode" type="list"
												typeGroupCode="groupType" defaultVal="${groupTypeCode}"
												hasLabel="false" title="所属组别"></t:dictSelect>
										</div>
										<label class="layui-form-label">所属网络:</label>
										<div class="layui-input-inline">
											<t:dictSelect field="netTypeCode"  id="netTypeCode" type="list"
												typeGroupCode="netType" defaultVal="${netTypeCode}"
												hasLabel="false" title="所属网络"></t:dictSelect>
										</div>

										<label class="layui-form-label">资产分类:</label>
										<div class="layui-input-inline">
											<t:dictSelect field="category" id="category" type="list"
												typeGroupCode="zcfl" defaultVal="${category}"
												hasLabel="false" title="资产分类"></t:dictSelect>
										</div>

										<label class="layui-form-label">开始时间:</label>
										<div class="layui-input-inline">
											<input type="text" id="startTime" name="startTime"
												class="layui-input"
												value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd"/>">
										</div>
										<label class="layui-form-label">结束时间:</label>
										<div class="layui-input-inline">
											<input type="text" id="endTime" name="endTime"
												class="layui-input"
												value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd"/>">
										</div>


										<div class="layui-input-inline">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<button class="layui-btn layui-btn-normal"
												onclick="$('#searchForm').submit();">查&nbsp;&nbsp;询</button>
										</div>
									</div>
								</form>

								<button class="layui-btn"
									onclick="addExport('storeController.do?exportDetailXls')">
									<i class="layui-icon"></i> 导出
								</button>
								<form name="searchForm" id="searchForm" method="post"
									action="storeController.do?/statistics/list">
									<div class="table">


										<div class="grid">
											<table class="layui-table">
												<thead>

													<tr>
														<th colspan="10" style="text-align: center;">资产总数量：${sumCount}&nbsp;&nbsp;&nbsp;&nbsp;
															已使用数量：${yyCount}&nbsp;&nbsp;&nbsp;&nbsp;
															未使用数量：${wyCount}&nbsp;&nbsp;&nbsp;&nbsp;
															闲置数量：${xzCount}&nbsp;&nbsp;&nbsp;&nbsp;
															报废数量：${bfCount}&nbsp;&nbsp;&nbsp;&nbsp; 维修数量：${wxCount}</th>
													</tr>

													<tr>

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
														<!-- <th>操作</th> -->
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${resultList}" var="resultList"
														varStatus="stu">
														<tr>
															<td><t:listDictParse
																	parseId="${resultList.groupTypeCode}" style="1"
																	typecode="groupType"></t:listDictParse></td>
															<td><t:listDictParse
																	parseId="${resultList.netTypeCode}" style="1"
																	typecode="netType"></t:listDictParse></td>
															<td>${resultList.code}</td>
															<td>${resultList.name}</td>
															<td>${resultList.payTime}<%-- <fmt:formatDate value="${resultList.storageTime}" pattern="yyyy-MM-dd"/> --%></td>
															<c:set var="nowDate"
																value="<%=System.currentTimeMillis()%>"></c:set>
															<fmt:parseDate value="${resultList.repairEndTime}" pattern="yyyy-MM-dd" var="textDate" />
															<td
																<c:if test="${((textDate.time-nowDate) <= 2592000000) && ((textDate.time-nowDate) > 0) }">style="background-color: yellow;"</c:if>
																<c:if test="${(textDate.time-nowDate) <0 }">style="background-color: red;"</c:if>>
																<fmt:formatDate value="${textDate}" pattern="yyyy-MM-dd" />
																<c:remove var="textDate" /> <%-- ${stu.index==1?'2018-12-11':'' }
									${stu.index==0?'2018-11-11':'' }
									${stu.index!=0 && stu.index!=1?'2019-11-11':'' } --%>
															</td>
															<%-- 									<td>${resultList.channel}</td> --%>
															<td>${resultList.amount}</td>
															<td>${resultList.category=='software'?'软件':'硬件'}</td>
															<td><t:listDictParse
																	parseId="${resultList.bpmStatus}" style="1"
																	typecode="zczt"></t:listDictParse></td>
															<%-- 									<td><fmt:formatDate value="${resultList.overInsuranceTime}" pattern="yyyy-MM-dd"/></td> --%>
															<%-- <td><a href="javascript:void(0);"
																onclick="openWin('${resultList.id}');" class="tablelink">标签打印</a>
																</td> --%>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>

										<div class="pagin" style="height: 300px;">

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

											<div class="message">
												共<i class="blue">&nbsp;&nbsp;${totalRow }&nbsp;&nbsp;</i>条记录，当前显示第&nbsp;<i
													class="blue">&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;</i>页
											</div>
											<ul class="paginList">
												<%
													if (currentPage > 1) {
												%>
												<li class="paginItem"><a href="javascript:void(0);"
													onclick="onSearchForm('storeController.do?/statistics/list&str_currentPage=<%=currentPage - 1%>')"
													style="width: 76px; text-decoration: none;"><span
														class="pagepre"> < 上一页</span></a></li>
												<%
													} else {
												%>
												<li class="paginItem current"><a href="javascript:;"
													style="width: 76px; text-decoration: none;">< 上一页</a></li>
												<%
													}
													int startView = currentPage - 4;
													int endView = currentPage + 4;
													for (int i = 0; i < totalPage; i++) {
														if ((i + 1) >= startView && (i + 1) <= endView) {
															if (String.valueOf((i + 1)).equals(currentPageString)) {
												%>
												<li class="paginItem current"><a href="javascript:;"><%=i + 1%></a></li>
												<%
													} else {
												%>
												<li class="paginItem"><a href="javascript:void(0);"
													onclick="onSearchForm('storeController.do?/statistics/list&str_currentPage=<%=i + 1%>')"><%=i + 1%></a></li>

												<%
													}
														}
													}
													if (currentPage < totalPage) {
												%>
												<li class="paginItem"><a href="javascript:void(0);"
													onclick="onSearchForm('storeController.do?/statistics/list&str_currentPage=<%=currentPage + 1%>')"
													style="width: 76px; text-decoration: none;"><span
														class="pagenxt">下一页 > </span></a></li>
												<%
													} else {
												%>
												<li class="paginItem current"><a href="javascript:;"
													style="width: 76px; text-decoration: none;">下一页 ></a></li>
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
			function add(url) {
				window.location.href = encodeURI(url);
			}
			
			function addExport(url){
				debugger;
		        var groupTypeCode = $("#groupTypeCode").val();
		        var netTypeCode = $("#netTypeCode").val();
		        var category = $("#category").val();
		        var startTime = $("#startTime").val();
		        var endTime = $("#endTime").val();
		        
		        var paramStr="";
		        if(groupTypeCode){
		            paramStr += "&groupTypeCode="+$("#groupTypeCode").val();
		        }
		        if(netTypeCode){
		            paramStr += "&netTypeCode="+$("#netTypeCode").val();
		        }
		        if(category){
		            paramStr += "&category="+$("#category").val();
		        }
		        if(startTime){
		            paramStr += "&startTime="+$("#startTime").val();
		        }
		        if(endTime){
		            paramStr += "&endTime="+$("#endTime").val();
		        }
		        window.location.href=url+paramStr;
		    }
			
			function update(url) {
				window.location.href = url;
			}
			//$('.tablelist tbody tr:odd').addClass('odd');
			function onSearchForm(url) {
				$("#searchForm").attr("action", url);
				$("#searchForm").submit();
			}
			//导入
			function ImportXls() {
				openuploadwin('Excel导入', 'storeController.do?upload',
						"storeList");
			}

			//导出
			function ExportXls() {
				JeecgExcelExport("storeController.do?exportXls", "storeList");
			}
			// 	function openWin(id){
			// 		location.href="storeController.do?print&id="+id;
			// 	}
			function openWin(id) {
				var addurl = "storeController.do?print&id=" + id;
				$.dialog({
					content : 'url:' + addurl,
					zIndex : 10000,
					lock : true,
					width : 350,
					height : 350,
					title : "标签打印",
					opacity : 0.3,
					cache : false,
					button : [ {
						name : '打印预览',
						callback : function() {
							iframe = this.iframe.contentWindow;
							iframe.myPreview1();
							return false;
						},
						focus : true
					} ],
					cancel : true
				/*为true等价于function(){}*/
				});
			}
			function openResumeWin(id) {
				var addurl = "resumeController.do?list&storeId=" + id;
				$.dialog({
					content : 'url:' + addurl,
					zIndex : 10000,
					lock : true,
					width : 850,
					height : 350,
					title : "资产履历",
					opacity : 0.3,
					cache : false,
					cancel : true
				/*为true等价于function(){}*/
				});
			}

			function reloadTable() {
				$('#searchForm').datagrid('reload');
			}

			function uploadTemplateCallBack(url, name) {

				$.post("storeController.do?importExcel", {
					fileName : url
				}, function(result) {
					var jsonObj = JSON.parse(result);
					$.dialog.alert(jsonObj.msg ? jsonObj.msg : "操作成功！");
					if (jsonObj.success) {
						window.location.href = "storeController.do?list";
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
<script
	src="${pageContext.request.contextPath}/plug-in/skin2.0/plugins/layui/layui.js"
	charset="utf-8"></script>
<script>
	layui.use([ 'form' ], function() {
		var form = layui.form()
	});
</script>
