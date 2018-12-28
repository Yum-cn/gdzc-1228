package com.assets.store.controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.qihuasoft.core.beanvalidator.BeanValidators;
import org.qihuasoft.core.common.controller.BaseController;
import org.qihuasoft.core.common.exception.BusinessException;
import org.qihuasoft.core.common.hibernate.qbc.CriteriaQuery;
import org.qihuasoft.core.common.model.json.AjaxJson;
import org.qihuasoft.core.common.model.json.DataGrid;
import org.qihuasoft.core.common.service.CommonService;
import org.qihuasoft.core.constant.Globals;
import org.qihuasoft.core.util.DimensionCode;
import org.qihuasoft.core.util.ExceptionUtil;
import org.qihuasoft.core.util.MyBeanUtils;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
import org.qihuasoft.web.system.pojo.base.TSType;
import org.qihuasoft.web.system.pojo.base.TSUser;
import org.qihuasoft.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.assets.resume.entity.ResumeEntity;
import com.assets.store.entity.ExportStoreEntity;
import com.assets.store.entity.StoreEntity;
import com.assets.store.service.StoreServiceI;
import com.assets.store.util.MapUtil;

/**
 * @Title: Controller
 * @Description: 入库管理
 * @author onlineGenerator
 * @date 2018-05-12 11:09:34
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/storeController")
public class StoreController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StoreController.class);

	@Autowired
	private StoreServiceI storeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private Validator validator;

	/**
	 * 入库管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoreEntity.class);
			String query_code = request.getParameter("code");
			if (query_code != null && !"".equals(query_code)) {

				request.setAttribute("code", query_code);
				detachedCriteria.add(Restrictions.like("code", "%" + query_code + "%"));
			}
			String query_type = request.getParameter("netTypeCode");
			if (query_type != null && !"".equals(query_type)) {

				request.setAttribute("netTypeCode", query_type);
				detachedCriteria.add(Restrictions.like("netTypeCode", "%" + query_type + "%"));
			}
			String query_name = request.getParameter("name");
			if (query_name != null && !"".equals(query_name)) {

				request.setAttribute("name", query_name);
				detachedCriteria.add(Restrictions.like("name", "%" + query_name + "%"));
			}
			String category = request.getParameter("category");
			if (StringUtils.isNotBlank(category)) {

				request.setAttribute("category", category);
				detachedCriteria.add(Restrictions.like("category", "%" + category + "%"));
			}
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList = this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/assets/store/storeList");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * 入库管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "/statistics/list")
	public ModelAndView statisticsList(HttpServletRequest request) {
		try {
			
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoreEntity.class);
			
			String groupTypeCode = request.getParameter("groupTypeCode");
			StringBuffer sqlPart = new StringBuffer("");
			if (StringUtils.isNotBlank(groupTypeCode)) {

				request.setAttribute("groupTypeCode", groupTypeCode);
				detachedCriteria.add(Restrictions.eq("groupTypeCode", groupTypeCode));
				sqlPart.append(" and groupTypeCode = '").append(groupTypeCode).append("' ");
			}
			
			String netTypeCode = request.getParameter("netTypeCode");
			if (StringUtils.isNotBlank(netTypeCode)) {

				request.setAttribute("netTypeCode", netTypeCode);
				detachedCriteria.add(Restrictions.eq("netTypeCode", netTypeCode));
				sqlPart.append(" and netTypeCode = '").append(netTypeCode).append("' ");
			}
			
			String category = request.getParameter("category");
			if (StringUtils.isNotBlank(category)) {

				request.setAttribute("category", category);
				detachedCriteria.add(Restrictions.eq("category", category));
				sqlPart.append(" and category = '").append(category).append("' ");
			}
			String startTime = request.getParameter("startTime");
			if (StringUtils.isNotBlank(startTime)) {
				
				request.setAttribute("startTime", new SimpleDateFormat("yyyy-MM-dd").parse(startTime));
				startTime = startTime+" 00:00:00";
				detachedCriteria.add(Restrictions.ge("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime)));
				sqlPart.append(" and createDate >= '").append(startTime).append("' ");
			}
			String endTime = request.getParameter("endTime");
			if (StringUtils.isNotBlank(endTime)) {
				
				request.setAttribute("endTime", new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
				endTime = endTime+" 23:59:59";
				detachedCriteria.add(Restrictions.le("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime)));
				sqlPart.append(" and createDate <= '").append(startTime).append("' ");
			}
			
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList = this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			
			//未使用总数
			String sql = "select count(*) from assets_store where bpm_status='wy'"+sqlPart.toString();
			List wyList = systemService.findListbySql(sql);
			request.setAttribute("wyCount", wyList!=null?wyList.get(0):0);
			
			//已使用总数
			sql = "select count(*) from assets_store where bpm_status='yy'"+sqlPart.toString();
			List yyList = systemService.findListbySql(sql);
			request.setAttribute("yyCount",  yyList!=null?yyList.get(0):0);
			
			//闲置总数
			sql = "select count(*) from assets_store where bpm_status='xz'"+sqlPart.toString();
			List xzList = systemService.findListbySql(sql);
			request.setAttribute("xzCount",  xzList!=null?xzList.get(0):0);
			
			//报废总数
			sql = "select count(*) from assets_store where bpm_status='bf'"+sqlPart.toString();
			List bfList = systemService.findListbySql(sql);
			request.setAttribute("bfCount",  bfList!=null?bfList.get(0):0);
			
			//维修总数
			sql = "select count(*) from assets_store where bpm_status='wx'"+sqlPart.toString();
			List wxList = systemService.findListbySql(sql);
			request.setAttribute("wxCount",  wxList!=null?wyList.get(0):0);
			
			//资产总数
			sql = "select count(*) from assets_store where 1=1 "+sqlPart.toString();
			List contList = systemService.findListbySql(sql);
			request.setAttribute("sumCount", contList!=null?contList.get(0):0);
			
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/assets/store/storeStatisticsList");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	

	@RequestMapping(params = "selectList")
	public ModelAndView selectList(HttpServletRequest request) {
		try {
			String status = request.getParameter("status");
			request.setAttribute("status", status);
			String category = request.getParameter("category");
			request.setAttribute("category", category);
			return new ModelAndView("com/assets/store/selectList");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(params = "print")
	public ModelAndView print(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			StoreEntity se = systemService.get(StoreEntity.class, id);
			String fileName = java.util.UUID.randomUUID().toString();
			// String url = "http://" + request.getServerName() + ":"
			// + request.getServerPort()
			// + request.getContextPath()
			// + "/questionnaireController.do?generatePaper&id=";
			String path = request.getRealPath("/") + "/upload/temp/" + fileName + ".png";
			File qrFile = new File(path);
			String qrPath = "http://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/upload/temp/" + fileName + ".png";
			StringBuffer sb = new StringBuffer();
			sb.append("资产编码：" + se.getCode() + "\n");
			// sb.append("资产类别：\n");
			sb.append("入库时间：" + se.getStorageTime() + "\n");
			sb.append("资产名称：" + se.getName() + "\n");
			// sb.append("品牌："+se.getBrand()+"\n");
			// sb.append("渠 道："+se.getSource()+"\n");
			sb.append("金 额：" + se.getAmount() + "\n");
			// sb.append("过保时间："+se.getOverInsuranceTime()+"\n");
			sb.append("过保时间：" + se.getRepairEndTime() + "\n");
			DimensionCode.encoderCode(sb.toString(), response, qrFile);
			request.setAttribute("se", se);

			int font = 14; // 字体大小
			int fontStyle = 1; // 字体风格
			String newImageWithText = request.getRealPath("/") + "/upload/temp/" + fileName + "_result.png";
			qrPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
					+ "/upload/temp/" + fileName + "_result.png";
			// 用来存放带有logo+文字的二维码图片
			// String newImageWithText = "D:/imageWithText.jpg";
			// // 带有logo的二维码图片
			String targetImage = path;// "D:/newPic.jpg";
			// // 附加在图片上的文字信息
			// String text = "资产编码：" + se.getCode();
			String text = "";
			//
			// // 在二维码下方添加文字（文字居中）
			DimensionCode.pressText(text, newImageWithText, targetImage, fontStyle, Color.black, font, 300, 310);
			request.setAttribute("qrPath", qrPath);
			return new ModelAndView("com/assets/store/print");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(StoreEntity store, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(StoreEntity.class, dataGrid);
		// 查询条件组装器
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, store, request.getParameterMap());
		try {

			// 入库、出库、转移、退库、维修、报废

			String status = request.getParameter("status");
			if (status != null && "lingyong".equals(status)) {// 领用功能
				CriteriaQuery cq1 = new CriteriaQuery(StoreEntity.class, dataGrid);
				cq1.eq("bpmStatus", "wy");
				cq1.eq("bpmStatus", "xz");
				cq.add(cq.or(cq1, 0, 1));
			} else if (status != null && "diaobo".equals(status)) {// 调拨功能
				cq.eq("bpmStatus", "yy");
			} else if (status != null && "tuiku".equals(status)) {// 退库功能
				cq.eq("bpmStatus", "yy");
			} else if (status != null && "weixiu".equals(status)) {// 维修功能
				cq.notEq("bpmStatus", "wx");
			} else if (status != null && "ruku".equals(status)) {// 领用功能
				cq.eq("bpmStatus", "wy");
			}
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.storeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除入库管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(StoreEntity store, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		store = systemService.getEntity(StoreEntity.class, store.getId());
		message = "入库管理删除成功";
		try {
			storeService.delete(store);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除入库管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库管理删除成功";
		try {
			for (String id : ids.split(",")) {
				StoreEntity store = systemService.getEntity(StoreEntity.class, id);
				storeService.delete(store);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加入库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	public ModelAndView doAdd(StoreEntity store, HttpServletRequest request) {
		String message = null;
		message = "入库管理添加成功";
		try {
			store.setBpmStatus("wy");
			storeService.save(store);

			TSUser user = ResourceUtil.getSessionUserName();

			ResumeEntity resume = new ResumeEntity();
			resume.setCreateBy(user.getId());
			resume.setHandleUser(user.getRealName());
			resume.setHandleTime(new java.util.Date());
			resume.setStoreId(store.getId());
			resume.setStoreName(store.getName());
			resume.setContent("将资产(" + store.getName() + ")入库");
			resume.setType("资产入库");
			systemService.save(resume);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "storeController.do?list");
		return new ModelAndView("success");
	}

	/**
	 * 更新入库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(StoreEntity store, HttpServletRequest request) {
		String message = null;
		message = "入库管理更新成功";
		StoreEntity t = storeService.get(StoreEntity.class, store.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(store, t);
			storeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "storeController.do?list");
		return new ModelAndView("success");
	}

	/**
	 * 入库管理新增页面跳转
	 * 
	 * @return
	 */
	/**
	 * @author Yum
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(StoreEntity store, String category, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = storeService.getEntity(StoreEntity.class, store.getId());
		} else {
			store = new StoreEntity();
		}
		store.setCategory(category);
		if (StringUtils.equals(category, "software")) {
			store.setType("software");
			req.setAttribute("storePage", store);
			return new ModelAndView("com/assets/store/store-add-software");
		} else if (StringUtils.equals(category, "hardware")) {
			req.setAttribute("storePage", store);
			return new ModelAndView("com/assets/store/store-add-hardware");
		}

		return new ModelAndView("");
	}

	/**
	 * 入库管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(StoreEntity store, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = storeService.getEntity(StoreEntity.class, store.getId());
			req.setAttribute("storePage", store);

			if (StringUtils.equals(store.getCategory(), "software")) {
				return new ModelAndView("com/assets/store/store-update-software");
			} else if (StringUtils.equals(store.getCategory(), "hardware")) {
				return new ModelAndView("com/assets/store/store-update-hardware");
			}
		}
		return new ModelAndView("com/assets/store/store-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "storeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(StoreEntity store, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(StoreEntity.class, dataGrid);
		
		String query_code = request.getParameter("code");
		if (query_code != null && !"".equals(query_code)) {

			request.setAttribute("code", query_code);
			cq.add(Restrictions.like("code", "%" + query_code + "%"));
		}
		String query_type = request.getParameter("netTypeCode");
		if (query_type != null && !"".equals(query_type)) {

			request.setAttribute("netTypeCode", query_type);
			cq.add(Restrictions.like("netTypeCode", "%" + query_type + "%"));
		}
		String query_name = request.getParameter("name");
		if (query_name != null && !"".equals(query_name)) {

			request.setAttribute("name", query_name);
			cq.add(Restrictions.like("name", "%" + query_name + "%"));
		}
		String category = request.getParameter("category");
		if (StringUtils.isNotBlank(category)) {

			request.setAttribute("category", category);
			cq.add(Restrictions.like("category", "%" + category + "%"));
		}
		
		org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, store, request.getParameterMap());
		List<StoreEntity> stores = this.storeService.getListByCriteriaQuery(cq, false);
		
		
		List<TSType> categoryList = systemService.getTypeList("402882f967cbab1a0167cbc4fcc30001");
		List<TSType> groupList = systemService.getTypeList("402882f967ac02f40167ac4fe2b8002c");
		List<TSType> netList = systemService.getTypeList("402882f967ac02f40167ac59dc9e0038");
		List<TSType> proList = systemService.getTypeList("402882f967ac02f40167ac9936db0058");
		List<TSType> zrdwList = systemService.getTypeList("402882f967ac02f40167ace423fd0062");
		List<TSType> deviceStatusList = systemService.getTypeList("402882f967c160400167c162bfde0001");
		List<TSType> manufacturerList = systemService.getTypeList("402882f967ac02f40167ac6d93e40044");
		
		Map<String,Object> categoryMap = listMapTransMap(categoryList);
		Map<String,Object> groupMap = listMapTransMap(groupList);
		Map<String,Object> netMap = listMapTransMap(netList);
		Map<String,Object> proMap = listMapTransMap(proList);
		Map<String,Object> zrdwMap = listMapTransMap(zrdwList);
		Map<String,Object> deviceMap = listMapTransMap(deviceStatusList);
		Map<String,Object> mfrsMap = listMapTransMap(manufacturerList);
		
		List<StoreEntity> resultList = new ArrayList<StoreEntity>();
		if(CollectionUtils.isNotEmpty(stores)){
			
			for (int i = 0; i < stores.size(); i++) {
				
				StoreEntity bean = stores.get(i);
				bean.setCategory(String.valueOf(categoryMap.get(bean.getCategory())==null?"":categoryMap.get(bean.getCategory())));
				bean.setGroupTypeCode(String.valueOf(groupMap.get(bean.getGroupTypeCode())==null?"":groupMap.get(bean.getGroupTypeCode())));
				bean.setNetTypeCode(String.valueOf(netMap.get(bean.getNetTypeCode())==null?"":netMap.get(bean.getNetTypeCode())));
				bean.setProTypeCode(String.valueOf(proMap.get(bean.getProTypeCode())==null?"":proMap.get(bean.getProTypeCode())));
				bean.setDevicePartmentCode(String.valueOf(zrdwMap.get(bean.getDevicePartmentCode())==null?"":zrdwMap.get(bean.getDevicePartmentCode())));
				bean.setDeviceStatusCode(String.valueOf(deviceMap.get(bean.getDeviceStatusCode())==null?"":deviceMap.get(bean.getDeviceStatusCode())));
				bean.setManufacturer(String.valueOf(mfrsMap.get(bean.getManufacturer())==null?"":mfrsMap.get(bean.getManufacturer())));
				resultList.add(bean);
			}
		}
		
		modelMap.put(NormalExcelConstants.FILE_NAME, "入库管理");
		modelMap.put(NormalExcelConstants.CLASS, StoreEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("入库管理列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, resultList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportDetailXls")
	public String exportDetailXls(StoreEntity store, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoreEntity.class);
			
			String groupTypeCode = request.getParameter("groupTypeCode");
			StringBuffer sqlPart = new StringBuffer("");
			if (StringUtils.isNotBlank(groupTypeCode)) {

				request.setAttribute("groupTypeCode", groupTypeCode);
				detachedCriteria.add(Restrictions.eq("groupTypeCode", groupTypeCode));
				sqlPart.append(" and groupTypeCode = '").append(groupTypeCode).append("' ");
			}
			
			String netTypeCode = request.getParameter("netTypeCode");
			if (StringUtils.isNotBlank(netTypeCode)) {

				request.setAttribute("netTypeCode", netTypeCode);
				detachedCriteria.add(Restrictions.eq("netTypeCode", netTypeCode));
				sqlPart.append(" and netTypeCode = '").append(netTypeCode).append("' ");
			}
			
			String category = request.getParameter("category");
			if (StringUtils.isNotBlank(category)) {

				request.setAttribute("category", category);
				detachedCriteria.add(Restrictions.eq("category", category));
				sqlPart.append(" and category = '").append(category).append("' ");
			}
			String startTime = request.getParameter("startTime");
			if (StringUtils.isNotBlank(startTime)) {
				
				request.setAttribute("startTime", new SimpleDateFormat("yyyy-MM-dd").parse(startTime));
				startTime = startTime+" 00:00:00";
				detachedCriteria.add(Restrictions.ge("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime)));
				sqlPart.append(" and createDate >= '").append(startTime).append("' ");
			}
			String endTime = request.getParameter("endTime");
			if (StringUtils.isNotBlank(endTime)) {
				
				request.setAttribute("endTime", new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
				endTime = endTime+" 23:59:59";
				detachedCriteria.add(Restrictions.le("createDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime)));
				sqlPart.append(" and createDate <= '").append(startTime).append("' ");
			}
			
			detachedCriteria.addOrder(Order.desc("createDate"));
			List<StoreEntity> stores = (List<StoreEntity>) this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			
			
			
			//未使用总数
			String sql = "select count(*) from assets_store where bpm_status='wy'"+sqlPart.toString();
			List wyList = systemService.findListbySql(sql);
			request.setAttribute("wyCount", wyList!=null?wyList.get(0):0);
			
			//已使用总数
			sql = "select count(*) from assets_store where bpm_status='yy'"+sqlPart.toString();
			List yyList = systemService.findListbySql(sql);
			request.setAttribute("yyCount",  yyList!=null?yyList.get(0):0);
			
			//闲置总数
			sql = "select count(*) from assets_store where bpm_status='xz'"+sqlPart.toString();
			List xzList = systemService.findListbySql(sql);
			request.setAttribute("xzCount",  xzList!=null?xzList.get(0):0);
			
			//报废总数
			sql = "select count(*) from assets_store where bpm_status='bf'"+sqlPart.toString();
			List bfList = systemService.findListbySql(sql);
			request.setAttribute("bfCount",  bfList!=null?bfList.get(0):0);
			
			//维修总数
			sql = "select count(*) from assets_store where bpm_status='wx'"+sqlPart.toString();
			List wxList = systemService.findListbySql(sql);
			request.setAttribute("wxCount",  wxList!=null?wyList.get(0):0);
			
			//资产总数
			sql = "select count(*) from assets_store where 1=1 "+sqlPart.toString();
			List contList = systemService.findListbySql(sql);
			request.setAttribute("sumCount", contList!=null?contList.get(0):0);
			
			/*request.setAttribute("resultList", resultList);*/
			
			
			/*CriteriaQuery cq = new CriteriaQuery(StoreEntity.class, dataGrid);
			
			org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, store, request.getParameterMap());
			List<StoreEntity> stores = this.storeService.getListByCriteriaQuery(cq, false);*/
			
			
			List<TSType> categoryList = systemService.getTypeList("402882f967cbab1a0167cbc4fcc30001");
			List<TSType> groupList = systemService.getTypeList("402882f967ac02f40167ac4fe2b8002c");
			List<TSType> netList = systemService.getTypeList("402882f967ac02f40167ac59dc9e0038");
			List<TSType> proList = systemService.getTypeList("402882f967ac02f40167ac9936db0058");
			List<TSType> zrdwList = systemService.getTypeList("402882f967ac02f40167ace423fd0062");
			List<TSType> deviceStatusList = systemService.getTypeList("402882f967c160400167c162bfde0001");
			List<TSType> manufacturerList = systemService.getTypeList("402882f967ac02f40167ac6d93e40044");
			List<TSType> statusList = systemService.getTypeList("402881f36352970f0163539441e00013");
			
			Map<String,Object> categoryMap = listMapTransMap(categoryList);
			Map<String,Object> groupMap = listMapTransMap(groupList);
			Map<String,Object> netMap = listMapTransMap(netList);
			Map<String,Object> proMap = listMapTransMap(proList);
			Map<String,Object> zrdwMap = listMapTransMap(zrdwList);
			Map<String,Object> deviceMap = listMapTransMap(deviceStatusList);
			Map<String,Object> mfrsMap = listMapTransMap(manufacturerList);
			Map<String,Object> statusMap = listMapTransMap(statusList);
			
			//List<StoreEntity> resultList = new ArrayList<StoreEntity>();
			List<ExportStoreEntity> resultList = new ArrayList<ExportStoreEntity>();
			ExportStoreEntity firstBean = new ExportStoreEntity();
			String str1 = String.valueOf(contList!=null?contList.get(0):0);
			String str2 = String.valueOf(yyList!=null?yyList.get(0):0);
			String str3 = String.valueOf(wyList!=null?wyList.get(0):0);
			String str4 = String.valueOf(xzList!=null?xzList.get(0):0);
			String str5 = String.valueOf(bfList!=null?bfList.get(0):0);
			String str6 = String.valueOf(wxList!=null?wyList.get(0):0);
			
			firstBean.setUseDesc("资产总数量："+str1
					+" 已使用数量："+str2
					+"    未使用数量："+str3+"     闲置数量："+str4+"     报废数量："+str5+"     维修数量："+str6);
			//resultList.add(firstBean);
			if(CollectionUtils.isNotEmpty(stores)){
				
				for (int i = 0; i < stores.size(); i++) {
					
					StoreEntity bean = stores.get(i);
					
					ExportStoreEntity tempBean = new ExportStoreEntity();
					//组别	所属网络	资产编码	资产名称	购买时间	维保到期时间	价格	分类	状态
					tempBean.setGroupTypeCode(String.valueOf(groupMap.get(bean.getGroupTypeCode())==null?"":groupMap.get(bean.getGroupTypeCode())));
					tempBean.setNetTypeCode(String.valueOf(netMap.get(bean.getNetTypeCode())==null?"":netMap.get(bean.getNetTypeCode())));
					tempBean.setCode(bean.getCode());
					tempBean.setName(bean.getName());
					tempBean.setPayTime(bean.getPayTime());
					tempBean.setRepairEndTime(bean.getRepairEndTime());
					tempBean.setAmount(bean.getAmount());
					tempBean.setCategory(String.valueOf(categoryMap.get(bean.getCategory())==null?"":categoryMap.get(bean.getCategory())));
					tempBean.setBpmStatus(String.valueOf(statusMap.get(bean.getBpmStatus())==null?"":statusMap.get(bean.getBpmStatus())));
					resultList.add(tempBean);
				}
			}
			
			modelMap.put(NormalExcelConstants.FILE_NAME, "统计分析");
			modelMap.put(NormalExcelConstants.CLASS, ExportStoreEntity.class);
			modelMap.put(NormalExcelConstants.PARAMS,
					new ExportParams("统计分析列表", "资产总数量："+str1
							+" 已使用数量："+str2
							+"    未使用数量："+str3+"     闲置数量："+str4+"     报废数量："+str5+"     维修数量："+str6, "导出信息"));
			modelMap.put(NormalExcelConstants.DATA_LIST, resultList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	/**
	 * 将List<Map<String, Object>>中，将mapKeyName、mapValueName健的值转换成Map的key、value
	 * @author   Yum
	 */
	public static Map<String,Object> listMapTransMap(List<TSType> list) {
		Map<String,Object> newMap = new HashMap<String,Object>();
		for (int i = 0; i < list.size(); i++) {
			
			TSType bean = list.get(i);
			if(bean==null){
				continue;
			}
			newMap.put(String.valueOf(bean.getTypecode()), bean.getTypename());
		}
		return newMap;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(StoreEntity store, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME, "入库管理");
		modelMap.put(NormalExcelConstants.CLASS, StoreEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("入库管理列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		String fileName = request.getParameter("fileName");
		 System.out.println(request.getContextPath());
		 
		File file = new File(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"\\/"+"upload/files/20181223/20181223170447kje1rv3Y.xlsx");
		
		URL url = null;
		InputStream in = null;
		try {
			//url = new URL("http://localhost:8082/upload/files/20181223/20181223170447kje1rv3Y.xlsx");
			url = new URL(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+fileName);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            //设置超时间为3秒
		    conn.setConnectTimeout(3*1000);
		    //防止屏蔽程序抓取而返回403错误
		    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			in = conn.getInputStream();
			
			//in = new FileInputStream(file1);
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setHeadRows(1);
			//params.setNeedSave(true);

			List<StoreEntity> listStoreEntitys = ExcelImportUtil.importExcel(in, StoreEntity.class, params);
			for (StoreEntity store : listStoreEntitys) {
				store.setBpmStatus("wy");
				storeService.save(store);
			}
			j.setMsg("文件导入成功！");
		} catch (Exception e) {
			j.setMsg("文件导入失败！");
			j.setSuccess(false);
			logger.error(ExceptionUtil.getExceptionMessage(e));
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return j;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<StoreEntity> list() {
		List<StoreEntity> listStores = storeService.getList(StoreEntity.class);
		return listStores;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		StoreEntity task = storeService.get(StoreEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody StoreEntity store, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<StoreEntity>> failures = validator.validate(store);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			storeService.save(store);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = store.getId();
		URI uri = uriBuilder.path("/rest/storeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody StoreEntity store) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<StoreEntity>> failures = validator.validate(store);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			storeService.saveOrUpdate(store);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		storeService.deleteEntityById(StoreEntity.class, id);
	}
}
