<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,com.document.docmyfile.entity.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%@include file="/context/mytags.jsp"%>
<%
//******************************׿��PageOffice�����ʹ��*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
poCtrl1.setCaption("InnovationSoft");//poCtrlΪPageOfficeCtrl����

	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
	//���ز˵���
	poCtrl1.setAllowCopy(false);//��ֹ����
	poCtrl1.setMenubar(false);//���ز˵���
	poCtrl1.setOfficeToolbars(false);//����Office������
	poCtrl1.setCustomToolbar(false);//�����Զ��幤����
	poCtrl1.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
	//���ñ���ҳ��
	poCtrl1.setSaveFilePage("officeExcelController.do?saveCreateExcel");
	//��Excel�ļ�
	DocMyFileEntity dfe = (DocMyFileEntity)request.getAttribute("docMyFilePage");
	poCtrl1.webOpen(dfe.getFilePath(), OpenModeType.xlsReadOnly, "�������");
	poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div style=" width:auto; height:100%;">
    <po:PageOfficeCtrl id="PageOfficeCtrl1"></po:PageOfficeCtrl>
    </div>
  </body>
</html>