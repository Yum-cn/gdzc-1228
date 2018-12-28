package com.assets.consumables.controller;

import java.awt.Color;
import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.poi.excel.entity.ExportParams;
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
import org.qihuasoft.core.util.MyBeanUtils;
import org.qihuasoft.core.util.ResourceUtil;
import org.qihuasoft.core.util.StringUtil;
import org.qihuasoft.tag.core.easyui.TagUtil;
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

import com.assets.consumables.entity.ConsumableDetailEntity;
import com.assets.consumables.entity.ConsumableEntity;
import com.assets.consumables.service.ConsumableDetailService;
import com.assets.consumables.service.ConsumableService;
import com.assets.resume.entity.ResumeEntity;
import com.assets.store.entity.StoreEntity;

/**
 * @Title: Controller
 * @Description: 入库管理
 * @author onlineGenerator
 * @date 2018-05-12 11:09:34
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/consumableController")
public class ConsumableController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConsumableController.class);

	@Autowired
	private ConsumableService consumableService;
	@Autowired
	private ConsumableDetailService consumableDetailService;
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
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ConsumableEntity.class);
			
			String query_type = request.getParameter("type");
			if (query_type != null && !"".equals(query_type)) {

				request.setAttribute("type", query_type);
				detachedCriteria.add(Restrictions.like("type", "%" + query_type + "%"));
			}
			String query_name = request.getParameter("name");
			if (query_name != null && !"".equals(query_name)) {

				request.setAttribute("name", query_name);
				detachedCriteria.add(Restrictions.like("name", "%" + query_name + "%"));
			}
			/*String category = request.getParameter("category");
			if (StringUtils.isNotBlank(category)) {

				request.setAttribute("category", category);
				detachedCriteria.add(Restrictions.like("category", "%" + category + "%"));
			}*/
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList = this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/assets/consumable/consumableList");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	

	/**
	 * 入库管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "detailList")
	public ModelAndView detailList(HttpServletRequest request) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ConsumableDetailEntity.class);
			
			/*String query_type = request.getParameter("type");
			if (query_type != null && !"".equals(query_type)) {

				request.setAttribute("type", query_type);
				detachedCriteria.add(Restrictions.like("type", "%" + query_type + "%"));
			}
			String query_name = request.getParameter("name");
			if (query_name != null && !"".equals(query_name)) {

				request.setAttribute("name", query_name);
				detachedCriteria.add(Restrictions.like("name", "%" + query_name + "%"));
			}*/
			/*String category = request.getParameter("category");
			if (StringUtils.isNotBlank(category)) {

				request.setAttribute("category", category);
				detachedCriteria.add(Restrictions.like("category", "%" + category + "%"));
			}*/
			detachedCriteria.addOrder(Order.desc("createDate"));
			List resultList = this.pageBaseMethod(request, detachedCriteria, commonService, 10);
			request.setAttribute("resultList", resultList);
			return new ModelAndView("com/assets/consumable/consumableDetailList");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@RequestMapping(params = "selectList")
	public ModelAndView selectList(HttpServletRequest request) {
		try {
			/*String status = request.getParameter("status");
			request.setAttribute("status", status);
			String category = request.getParameter("category");
			request.setAttribute("category", category);*/
			return new ModelAndView("com/assets/consumable/selectList");
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
		CriteriaQuery cq = new CriteriaQuery(ConsumableEntity.class, dataGrid);
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
		this.consumableService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除入库管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConsumableEntity store, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		store = systemService.getEntity(ConsumableEntity.class, store.getId());
		message = "入库管理删除成功";
		try {
			consumableService.delete(store);
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
	 * 删除入库管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDelDetail")
	@ResponseBody
	public AjaxJson doDelDetail(ConsumableDetailEntity store, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		store = systemService.getEntity(ConsumableDetailEntity.class, store.getId());
		
		ConsumableEntity bean=consumableService.get(ConsumableEntity.class, store.getConsumableId());
		int number = NumberUtils.toInt(bean.getNumber());
		int stockNumber = NumberUtils.toInt(bean.getStockNumber());
		
		int useNumber = NumberUtils.toInt(store.getNumber());
		
		
		bean.setNumber(String.valueOf(number+useNumber));
		bean.setStockNumber(String.valueOf(stockNumber+useNumber));
		systemService.saveOrUpdate(bean);
		message = "入库管理删除成功";
		try {
			consumableDetailService.delete(store);
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
				ConsumableEntity store = systemService.getEntity(ConsumableEntity.class, id);
				consumableService.delete(store);
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
	public ModelAndView doAdd(ConsumableEntity store, HttpServletRequest request) {
		String message = null;
		message = "入库管理添加成功";
		try {
			store.setBpmStatus("wy");
			consumableService.save(store);

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
		request.setAttribute("returnURL", "consumableController.do?list");
		return new ModelAndView("success");
	}
	
	/**
	 * 耗材领用|录入
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddDetail")
	public ModelAndView goAddDetail(ConsumableDetailEntity storeDetail,HttpServletRequest req) {
		if (StringUtil.isNotEmpty(storeDetail.getId())) {
			storeDetail = consumableService.getEntity(ConsumableDetailEntity.class, storeDetail.getId());
		} else {
			storeDetail = new ConsumableDetailEntity();
		}
		req.setAttribute("storePage", storeDetail);
		
		/*if (StringUtils.equals(category, "software")) {
			store.setType("software");
			req.setAttribute("storePage", store);
			return new ModelAndView("com/assets/store/store-add-software");
		} else if (StringUtils.equals(category, "hardware")) {
			req.setAttribute("storePage", store);
			return new ModelAndView("com/assets/store/store-add-hardware");
		}*/
		
		return new ModelAndView("com/assets/consumable/consumable-add-detail");
	}
	/**
	 * 添加入库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddDetail")
	public ModelAndView doAddDetail(ConsumableDetailEntity detailStore, HttpServletRequest request) {
		String message = null;
		message = "入库管理添加成功";
		try {
			
			String ids = detailStore.getConsumableId();
			
			if(StringUtils.isBlank(ids)){
				message = "入库管理添加失败";
				throw new BusinessException(message);
			}
			
			String[] idArray = ids.split(",");
			
			if(idArray!=null&&idArray.length>0){
				TSUser user = ResourceUtil.getSessionUserName();
				
				for (int i = 0; i < idArray.length; i++) {
					
					ConsumableDetailEntity tempEntity = new ConsumableDetailEntity();
					MyBeanUtils.copyBeanNotNull2Bean(detailStore, tempEntity);
					
					ConsumableEntity store = systemService.getEntity(ConsumableEntity.class, idArray[i]);
					int number = NumberUtils.toInt(store.getNumber());
					int stockNumber = NumberUtils.toInt(store.getStockNumber());
					int useNumber = NumberUtils.toInt(detailStore.getNumber());
					
					if(StringUtils.equals("use", detailStore.getConsumableOperateType())){
						if(stockNumber > 0 && stockNumber>=useNumber){
							store.setStockNumber(String.valueOf(stockNumber-useNumber));
							systemService.save(store);
							
							tempEntity.setType(store.getType());
							tempEntity.setName(store.getName());
							tempEntity.setConsumableId(idArray[i]);
							tempEntity.setBpmStatus("ly");
							//tempEntity.setUseTime(store.getnew Date());
							tempEntity.setUsePerson(detailStore.getUsePerson());
						}else{
							message = "操作失败,库存数量不足!";
							break;
						}
						
					}else if(StringUtils.equals("add", detailStore.getConsumableOperateType())){
						store.setNumber(String.valueOf(number+useNumber));
						store.setStockNumber(String.valueOf(stockNumber+useNumber));
						systemService.save(store);
						
						tempEntity.setType(store.getType());
						tempEntity.setName(store.getName());
						tempEntity.setConsumableId(idArray[i]);
						tempEntity.setBpmStatus("add");
						tempEntity.setAddTime(new Date());
						tempEntity.setUsePerson(null);
						tempEntity.setAddPerson(detailStore.getUsePerson());
					}
					consumableService.save(tempEntity);
					
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
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "consumableController.do?detailList");
		return new ModelAndView("success");
	}

	/**
	 * 更新入库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	public ModelAndView doUpdate(ConsumableEntity store, HttpServletRequest request) {
		String message = null;
		message = "入库管理更新成功";
		ConsumableEntity t = consumableService.get(ConsumableEntity.class, store.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(store, t);
			consumableService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "consumableController.do?list");
		return new ModelAndView("success");
	}
	/**
	 * 更新入库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdateDetail")
	public ModelAndView doUpdateDetail(ConsumableDetailEntity store, HttpServletRequest request) {
		String message = null;
		message = "入库管理更新成功";
		ConsumableDetailEntity t = consumableService.get(ConsumableDetailEntity.class, store.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(store, t);
			consumableDetailService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		request.setAttribute("message", message);
		request.setAttribute("returnURL", "consumableController.do?detailList");
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
	public ModelAndView goAdd(ConsumableEntity store, String category, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = consumableService.getEntity(ConsumableEntity.class, store.getId());
		} else {
			store = new ConsumableEntity();
		}
		req.setAttribute("storePage", store);
		/*if (StringUtils.equals(category, "software")) {
			store.setType("software");
			req.setAttribute("storePage", store);
			return new ModelAndView("com/assets/store/store-add-software");
		} else if (StringUtils.equals(category, "hardware")) {
			req.setAttribute("storePage", store);
			return new ModelAndView("com/assets/store/store-add-hardware");
		}*/

		return new ModelAndView("com/assets/consumable/consumable-add");
	}

	/**
	 * 入库管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConsumableEntity store, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = consumableService.getEntity(ConsumableEntity.class, store.getId());
			req.setAttribute("storePage", store);

			/*if (StringUtils.equals(store.getCategory(), "software")) {
				return new ModelAndView("com/assets/store/store-update-software");
			} else if (StringUtils.equals(store.getCategory(), "hardware")) {
				return new ModelAndView("com/assets/store/store-update-hardware");
			}*/
		}
		return new ModelAndView("com/assets/consumable/consumable-update");
	}
	@RequestMapping(params = "goUpdateDetail")
	public ModelAndView goUpdateDetail(ConsumableDetailEntity store, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(store.getId())) {
			store = consumableDetailService.getEntity(ConsumableDetailEntity.class, store.getId());
			req.setAttribute("consumableDetailPage", store);
			
			/*if (StringUtils.equals(store.getCategory(), "software")) {
				return new ModelAndView("com/assets/store/store-update-software");
			} else if (StringUtils.equals(store.getCategory(), "hardware")) {
				return new ModelAndView("com/assets/store/store-update-hardware");
			}*/
		}
		return new ModelAndView("com/assets/consumable/consumable-update-detail");
	}


	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<StoreEntity> list() {
		List<StoreEntity> listStores = consumableService.getList(StoreEntity.class);
		return listStores;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		StoreEntity task = consumableService.get(StoreEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ConsumableEntity store, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConsumableEntity>> failures = validator.validate(store);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			consumableService.save(store);
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
	public ResponseEntity<?> update(@RequestBody ConsumableEntity store) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConsumableEntity>> failures = validator.validate(store);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			consumableService.saveOrUpdate(store);
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
		consumableService.deleteEntityById(StoreEntity.class, id);
	}
	@RequestMapping(params = "print")
	public ModelAndView print(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			ConsumableDetailEntity se = systemService.get(ConsumableDetailEntity.class, id);
			String fileName = java.util.UUID.randomUUID().toString();
			String path = request.getRealPath("/") + "/upload/temp/" + fileName + ".png";
			File qrFile = new File(path);
			String qrPath = "http://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/upload/temp/" + fileName + ".png";
			StringBuffer sb = new StringBuffer();
			
			se.setUseOrgCode(StringUtils.replace(se.getUseOrgCode(), ",", " "));
			
			request.setAttribute("se", se);

			int font = 14; // 字体大小
			int fontStyle = 1; // 字体风格
			String newImageWithText = request.getRealPath("/") + "/upload/temp/" + fileName + "_result.png";
			qrPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
					+ "/upload/temp/" + fileName + "_result.png";
			String targetImage = path;// "D:/newPic.jpg";
			String text = "";
			DimensionCode.pressText(text, newImageWithText, targetImage, fontStyle, Color.black, font, 300, 310);
			request.setAttribute("qrPath", qrPath);
			return new ModelAndView("com/assets/consumable/print-consumable-detail");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ConsumableDetailEntity use,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ConsumableDetailEntity.class, dataGrid);
		try {
			String id = request.getParameter("id");
			if(StringUtils.isNotBlank(id)){
				request.setAttribute("id", id);
				cq.add(Restrictions.eq("id", id));
			}
			
			org.qihuasoft.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, use, request.getParameterMap());
			List<ConsumableDetailEntity> uses = this.consumableDetailService.getListByCriteriaQuery(cq,false);
			modelMap.put(NormalExcelConstants.FILE_NAME,"耗材领用");
			modelMap.put(NormalExcelConstants.CLASS,ConsumableDetailEntity.class);
			modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("耗材领用列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
				"导出信息"));
			modelMap.put(NormalExcelConstants.DATA_LIST,uses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}
