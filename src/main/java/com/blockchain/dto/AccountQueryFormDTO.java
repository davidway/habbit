package com.blockchain.dto;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;


@ApiModel(value="资产查询")

public class AccountQueryFormDTO {
	@ApiModelProperty(value= "用户id")
	private String ownerUid;
	@ApiModelProperty(value= "用户账户")
	private String assetAccount;
	@ApiModelProperty(value= "用户资产id")
	private String assetId;
	@ApiModelProperty(value= "页码limit",required=true)
	@Max(value=20,message="页码limit最大是20")
	@NotNull(message="页码limit必须存在")
	private Integer pageLimit;
	@ApiModelProperty(value= "页码",required=true)
	@NotNull(message="页码必须存在")
	@Min(value=1,message="页码必须大于1")
	private Integer pageNo;
	@ApiModelProperty(value= "状态值,0：正常持有 3：已全额转让 5：已清算",required=true)
	@NotNull(message="状态值不能为空")
	private int[] state;
	@ApiModelProperty(value= "资产内容json串，用来匹配对应的资产使用",required=true)
	private JSONObject content;
	
	
	@ApiModelProperty(value="配置文件信息",required=true)
	@NotNull(message="配置信息")
	@Valid
	private ConfigDto configDto;

	
	public ConfigDto getConfigDto() {
		return configDto;
	}
	public void setConfigDto(ConfigDto configDto) {
		this.configDto = configDto;
	}
	public String getOwnerUid() {
		return ownerUid;
	}
	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}
	public String getAssetAccount() {
		return assetAccount;
	}
	public void setAssetAccount(String assetAccount) {
		this.assetAccount = assetAccount;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public Integer getPageLimit() {
		return pageLimit;
	}
	public void setPageLimit(Integer pageLimit) {
		this.pageLimit = pageLimit;
	}

	public JSONObject getContent() {
		return content;
	}
	public void setContent(JSONObject content) {
		this.content = content;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public int[] getState() {
		return state;
	}
	public void setState(int[] state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "AccountQueryFormDTO [ownerUid=" + ownerUid + ", assetAccount=" + assetAccount + ", assetId=" + assetId + ", pageLimit=" + pageLimit + ", pageNo=" + pageNo + ", state="
				+ Arrays.toString(state) + ", content=" + content + "]";
	}


	
	
}
