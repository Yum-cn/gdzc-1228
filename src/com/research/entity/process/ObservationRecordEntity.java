package com.research.entity.process;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.String;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 观察记录
 * @author onlineGenerator
 * @date 2016-08-12 20:34:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "st_observation_record", schema = "")
@SuppressWarnings("serial")
public class ObservationRecordEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**课题ID*/
	private java.lang.String topId;
	/**记录标题*/
	private java.lang.String title;
	/**观察描述*/
	private java.lang.String content;
	/**创建时间*/
	private java.util.Date createTime;
	/**创建单位*/
	private java.lang.String createOrg;
	/**创建人*/
	private java.lang.String createUser;
	private java.lang.String attFile;
	private java.lang.String attFileName;
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
	 *@return: java.lang.String  课题ID
	 */
	@Column(name ="TOP_ID",nullable=true)
	public java.lang.String getTopId(){
		return this.topId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  课题ID
	 */
	public void setTopId(java.lang.String topId){
		this.topId = topId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  记录标题
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  记录标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  观察描述
	 */
	@Column(name ="CONTENT",nullable=true)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  观察描述
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建单位
	 */
	@Column(name ="CREATE_ORG",nullable=true,length=50)
	public java.lang.String getCreateOrg(){
		return this.createOrg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建单位
	 */
	public void setCreateOrg(java.lang.String createOrg){
		this.createOrg = createOrg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_USER",nullable=true,length=50)
	public java.lang.String getCreateUser(){
		return this.createUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateUser(java.lang.String createUser){
		this.createUser = createUser;
	}

	public java.lang.String getAttFile() {
		return attFile;
	}

	public void setAttFile(java.lang.String attFile) {
		this.attFile = attFile;
	}

	public java.lang.String getAttFileName() {
		return attFileName;
	}

	public void setAttFileName(java.lang.String attFileName) {
		this.attFileName = attFileName;
	}
}
