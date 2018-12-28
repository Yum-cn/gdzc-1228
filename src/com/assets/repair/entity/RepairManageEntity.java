package com.assets.repair.entity;

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
 * @Description: 维修登记
 * @author onlineGenerator
 * @date 2018-05-12 12:21:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "assets_repair", schema = "")
@SuppressWarnings("serial")
public class RepairManageEntity implements java.io.Serializable {
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
	private java.lang.String bpmStatus;
	/**报修人*/
	@Excel(name="报修人")
	private java.lang.String repairUser;
	/**报修时间*/
	@Excel(name="报修时间",format = "yyyy-MM-dd")
	private java.util.Date repairTime;
	/**报修原因*/
	@Excel(name="报修原因")
	private java.lang.String reason;
	/**维修内容*/
	@Excel(name="维修内容")
	private java.lang.String content;
	/**维修费用*/
	@Excel(name="维修费用")
	private java.lang.String cost;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;
	/**关联资产设备ID*/
	private java.lang.String relationId;
	/**关联资产设备名称*/
	private java.lang.String relationName;
	/**转移单号*/
	private java.lang.String oddNumbers;
	
	/**审批人*/
	@Excel(name="审批人")
	private java.lang.String approvalUser;
	/**审批时间*/
	@Excel(name="审批时间",format = "yyyy-MM-dd")
	private java.util.Date approvalTime;
	/**审批意见*/
	@Excel(name="审批意见")
	private java.lang.String approvalDesc;
	
	/**维修管理人*/
	@Excel(name="维修管理人")
	private java.lang.String repairManagerUser;
	/**维修完成时间*/
	@Excel(name="维修完成时间",format = "yyyy-MM-dd")
	private java.util.Date finishTime;
	/**维修完成描述*//*
	@Excel(name="维修完成描述")
	private java.lang.String finishDesc;*/
	
	
	/**状态:已登记、已提交、已审批、审批未通过、维修完成*/
	private java.lang.String status;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报修人
	 */
	@Column(name ="REPAIR_USER",nullable=true,length=50)
	public java.lang.String getRepairUser(){
		return this.repairUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报修人
	 */
	public void setRepairUser(java.lang.String repairUser){
		this.repairUser = repairUser;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  报修时间
	 */
	@Column(name ="REPAIR_TIME",nullable=true,length=32)
	public java.util.Date getRepairTime(){
		return this.repairTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  报修时间
	 */
	public void setRepairTime(java.util.Date repairTime){
		this.repairTime = repairTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报修原因
	 */
	@Column(name ="REASON",nullable=true,length=500)
	public java.lang.String getReason(){
		return this.reason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报修原因
	 */
	public void setReason(java.lang.String reason){
		this.reason = reason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修内容
	 */
	@Column(name ="CONTENT",nullable=true,length=500)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  维修费用
	 */
	@Column(name ="COST",nullable=true,length=32)
	public java.lang.String getCost(){
		return this.cost;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  维修费用
	 */
	public void setCost(java.lang.String cost){
		this.cost = cost;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=32)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	@Column(name ="relation_id",nullable=true)
	public java.lang.String getRelationId() {
		return relationId;
	}

	public void setRelationId(java.lang.String relationId) {
		this.relationId = relationId;
	}
	
	@Column(name ="relation_name",nullable=true)
	public java.lang.String getRelationName() {
		return relationName;
	}

	public void setRelationName(java.lang.String relationName) {
		this.relationName = relationName;
	}
	@Column(name ="odd_numbers",nullable=true,length=50)
	public java.lang.String getOddNumbers() {
		return oddNumbers;
	}

	public void setOddNumbers(java.lang.String oddNumbers) {
		this.oddNumbers = oddNumbers;
	}

	public java.lang.String getApprovalUser() {
		return approvalUser;
	}

	public void setApprovalUser(java.lang.String approvalUser) {
		this.approvalUser = approvalUser;
	}

	public java.util.Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(java.util.Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public java.lang.String getApprovalDesc() {
		return approvalDesc;
	}

	public void setApprovalDesc(java.lang.String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public java.lang.String getRepairManagerUser() {
		return repairManagerUser;
	}

	public void setRepairManagerUser(java.lang.String repairManagerUser) {
		this.repairManagerUser = repairManagerUser;
	}

	public java.util.Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(java.util.Date finishTime) {
		this.finishTime = finishTime;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	
}
