package cn.sp.ftldemo;

import java.io.Serializable;

public class Delivery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4095681910687074906L;
	
	private String llNo;
	private String reqDate;
	private String submitBuName;
	private String createDate;
	private String linkman;
	private String linkTel;
	
	private String deliveryLoc;
	private String remark;
	
	private String pzText;
	
	public String getLlNo() {
		return llNo;
	}
	public void setLlNo(String llNo) {
		this.llNo = llNo;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getSubmitBuName() {
		return submitBuName;
	}
	public void setSubmitBuName(String submitBuName) {
		this.submitBuName = submitBuName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkTel() {
		return linkTel;
	}
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	public String getDeliveryLoc() {
		return deliveryLoc;
	}
	public void setDeliveryLoc(String deliveryLoc) {
		this.deliveryLoc = deliveryLoc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPzText() {
		return pzText;
	}
	public void setPzText(String pzText) {
		this.pzText = pzText;
	}
	

}
