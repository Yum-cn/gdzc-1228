<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,com.document.template.entity.*"
	pageEncoding="gb2312"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%@include file="/context/mytags.jsp"%>
<%
//******************************׿��PageOffice�����ʹ��*******************************
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
poCtrl1.setCaption("InnovationSoft");//poCtrlΪPageOfficeCtrl����
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
	//���ز˵���
	poCtrl1.setMenubar(false);
// 	poCtrl1.setCustomToolbar(false);//�����Զ��幤����
	poCtrl1.addCustomToolButton("����","Save()",1);

	TemplateEntity dfe = (TemplateEntity)request.getAttribute("template");
	String type="";
	if(dfe.getAttFile()!=null && !"".equals(dfe.getAttFile())){
		int dot = dfe.getAttFile().lastIndexOf('.');   
        if ((dot >-1) && (dot < (dfe.getAttFile().length() - 1))) {   
        	type = dfe.getAttFile().substring(dot + 1);   
        } 
	}
	
	//���ñ���ҳ��
	
	poCtrl1.setSaveFilePage("templateController.do?saveEditTemplate&id="+dfe.getId());
	//���ļ�

	if(type!=null && ("doc".equals(type) || "docx".equals(type))){
		poCtrl1.webOpen(dfe.getAttFile(), OpenModeType.docNormalEdit, "�������");
	}
	if(type!=null && ("xls".equals(type) || "xlsx".equals(type))){
		poCtrl1.webOpen(dfe.getAttFile(), OpenModeType.xlsNormalEdit, "�������");
	}
	if(type!=null && ("ppt".equals(type) || "pptx".equals(type))){
		poCtrl1.webOpen(dfe.getAttFile(), OpenModeType.pptNormalEdit, "�������");
	}
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
    <script type="text/javascript">
        function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();
            //alert('����ɹ���');
        }
    </script>