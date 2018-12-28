package com.assets.store.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity  
 * @Description: 入库管理
 * @author onlineGenerator
 * @date 2018-05-12 11:09:34
 * @version V1.0   
 *
 */
@Entity
@SuppressWarnings("serial")
public class ExportStoreEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	//@Excel(name="用量")
	private java.lang.String useDesc;
	/**组别*/
	@Excel(name="组别")
	private String groupTypeCode;
	private String groupTypeName;
	/**所属网络*/
	@Excel(name="所属网络")
	private String netTypeCode;
	/**资产编码*/
	@Excel(name="资产编码")
	private java.lang.String code;
	/**资产名称*/
	@Excel(name="资产名称")
	private java.lang.String name;
	
	/**购买时间*/
	@Excel(name="购买时间")
	private String payTime;
	/**维修到期时间*/
	@Excel(name="维保到期时间")
	private String repairEndTime;

	/**单价*/
	@Excel(name="价格")
	private java.lang.String amount;
	/**流程状态*/
	@Excel(name="分类")
	private java.lang.String category;
	/**流程状态*/
	@Excel(name="状态")
	private java.lang.String bpmStatus;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}

	public java.lang.String getCreateName() {
		return createName;
	}

	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}

	public java.lang.String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.lang.String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}

	public java.lang.String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public java.lang.String getSysOrgCode() {
		return sysOrgCode;
	}

	public void setSysOrgCode(java.lang.String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public java.lang.String getSysCompanyCode() {
		return sysCompanyCode;
	}

	public void setSysCompanyCode(java.lang.String sysCompanyCode) {
		this.sysCompanyCode = sysCompanyCode;
	}

	public String getGroupTypeCode() {
		return groupTypeCode;
	}

	public void setGroupTypeCode(String groupTypeCode) {
		this.groupTypeCode = groupTypeCode;
	}

	public String getGroupTypeName() {
		return groupTypeName;
	}

	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
	}

	public String getNetTypeCode() {
		return netTypeCode;
	}

	public void setNetTypeCode(String netTypeCode) {
		this.netTypeCode = netTypeCode;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getRepairEndTime() {
		return repairEndTime;
	}

	public void setRepairEndTime(String repairEndTime) {
		this.repairEndTime = repairEndTime;
	}

	public java.lang.String getAmount() {
		return amount;
	}

	public void setAmount(java.lang.String amount) {
		this.amount = amount;
	}

	public java.lang.String getCategory() {
		return category;
	}

	public void setCategory(java.lang.String category) {
		this.category = category;
	}

	public java.lang.String getBpmStatus() {
		return bpmStatus;
	}

	public void setBpmStatus(java.lang.String bpmStatus) {
		this.bpmStatus = bpmStatus;
	}

	public java.lang.String getUseDesc() {
		return useDesc;
	}

	public void setUseDesc(java.lang.String useDesc) {
		this.useDesc = useDesc;
	}
	
	
	
}
