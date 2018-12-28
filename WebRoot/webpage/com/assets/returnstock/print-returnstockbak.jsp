<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:useBean id="now" class="java.util.Date" scope="page"/>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打印预览</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/lodop/LodopFuncs.js"></script>
<style>
table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
}
table.gridtable th {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #dedede;
}
table.gridtable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
}
</style>
</head>
<body style="margin:0px auto;">
<a href="javascript:prn1_preview()">打印预览</a>
<div id="form1" style="margin:0 auto;">
<table cellpadding="0" cellspacing="0"style="width: 60%;margin:auto">
    <tr>
      <td height="50" colspan="10" ><div align="center" style="font-family:'宋体'; font-size:22pt">资产退库单</div></td>
    </tr>
    <tr>
      <td height="25" colspan="10"><div align="right"><span style=" float:right; font-family:仿宋;font-size:16pt"><fmt:formatDate value="${now}" pattern="yyyy年MM月dd日" /></span></div></td>
    </tr>
    <tr style="border:solid 1px black;border-collapse:collapse">
      <td height="50" colspan="3" style="border:solid 1px black;border-collapse:collapse"><div align="center" style="font-family:仿宋;font-size:16pt">退库单号</div></td>
      <td colspan="7" style="border:solid 1px black;border-collapse:collapse"><div align="center"style="font-family:仿宋;font-size:16pt">${se.oddNumbers}</div></td>
    </tr>
    <tr style="border:solid 1px black;border-collapse:collapse">
      <td height="50" colspan="3" style="border:solid 1px black;border-collapse:collapse"><div align="center" style="font-family:仿宋;font-size:16pt">存放地点</div></td>
      <td colspan="7" style="border:solid 1px black;border-collapse:collapse"><div align="center"style="font-family:仿宋;font-size:16pt">${se.depositAddress}</div></td>
    </tr>
    <tr style="border:solid 1px black;border-collapse:collapse">
      <td height="50" colspan="3" style="border:solid 1px black;border-collapse:collapse"><div align="center" style="font-family:仿宋;font-size:16pt">退库时间</div></td>
      <td colspan="7" style="border:solid 1px black;border-collapse:collapse"><div align="center"style="font-family:仿宋;font-size:16pt"><fmt:formatDate value="${se.returnStockTime}" pattern="yyyy-MM-dd"/></div></td>
    </tr>
    <tr style="border:solid 1px black;border-collapse:collapse">
      <td colspan="3" style="border:solid 1px black;border-collapse:collapse"><p align="center" style="font-family:仿宋;font-size:16pt">退库明细</p></td>
      <td height="39" colspan="7" style="border:solid 1px black;border-collapse:collapse"><div align="center">${se.remark}</div></td>
    </tr>
  </table>
</div>
<script language="javascript" type="text/javascript">   
        var LODOP; //声明为全局变量 
    function prn1_preview() {   
        CreateOneFormPage();    
        LODOP.PREVIEW();    
    };
    function prn1_print() {     
        CreateOneFormPage();
        LODOP.PRINT();  
    };
    function prn1_printA() {        
        CreateOneFormPage();
        LODOP.PRINTA();     
    };  
    function CreateOneFormPage(){
        LODOP=getLodop();  
        LODOP.SET_PRINT_PAGESIZE (3,'210mm','297mm',2);
//      LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
//      LODOP.SET_PRINT_STYLE("FontSize",18);
//      LODOP.SET_PRINT_STYLE("Bold",1);
//      LODOP.ADD_PRINT_TEXT(50,231,260,39,"打印页面部分内容");
        LODOP.ADD_PRINT_HTM(50,50,670,900,document.getElementById("form1").innerHTML);
    };                       
    function prn2_preview() {   
        CreateTwoFormPage();    
        LODOP.PREVIEW();    
    };
    function prn2_manage() {    
        CreateTwoFormPage();
        LODOP.PRINT_SETUP();    
    };  
    function CreateTwoFormPage(){
        LODOP=getLodop();  
        LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单二");
        LODOP.ADD_PRINT_RECT(70,27,634,242,0,1);
        LODOP.ADD_PRINT_TEXT(29,236,279,38,"页面内容改变布局打印");
        LODOP.SET_PRINT_STYLEA(2,"FontSize",18);
        LODOP.SET_PRINT_STYLEA(2,"Bold",1);
        LODOP.ADD_PRINT_HTM(88,40,321,185,document.getElementById("form1").innerHTML);
        LODOP.ADD_PRINT_HTM(87,355,285,187,document.getElementById("form2").innerHTML);
        LODOP.ADD_PRINT_TEXT(319,58,500,30,"注：其中《表单一》按显示大小，《表单二》在程序控制宽度(285px)内自适应调整");
    };              
    function prn3_preview(){
        LODOP=getLodop();  
        LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_全页");
        LODOP.ADD_PRINT_HTM(0,0,"100%","100%",document.documentElement.innerHTML);
        LODOP.PREVIEW();    
    };  
</script> 


</body>
</html>