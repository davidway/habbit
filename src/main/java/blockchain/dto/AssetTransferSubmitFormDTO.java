package blockchain.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="转让仅提交表单")
public class AssetTransferSubmitFormDTO extends BaseDto{
	@NotEmpty(message="平台唯一标识一次交易的ID不能为空")
	@ApiModelProperty(value="平台唯一标识一次交易的ID")
	private String transactionId;
	
	@NotEmpty(message="待签名串不能为空")
	@ApiModelProperty(value="待签名串")
	private String signList;
	
	@NotEmpty(message="申请人私钥不能为空")
	@ApiModelProperty(value="申请人私钥")
	private String userPrivateKey;
	
	private String srcAssetId;
	private String dstAssetId;
	
	
	public String getSrcAssetId() {
		return srcAssetId;
	}
	public void setSrcAssetId(String srcAssetId) {
		this.srcAssetId = srcAssetId;
	}
	public String getDstAssetId() {
		return dstAssetId;
	}
	public void setDstAssetId(String dstAssetId) {
		this.dstAssetId = dstAssetId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getSignList() {
		return signList;
	}
	public void setSignList(String signList) {
		this.signList = signList;
	}
	public String getUserPrivateKey() {
		return userPrivateKey;
	}
	public void setUserPrivateKey(String userPrivateKey) {
		this.userPrivateKey = userPrivateKey;
	}
	@Override
	public String toString() {
		return "AssetTransferSubmitForm [transactionId=" + transactionId + ", signList=" + signList + ", userPrivateKey=" + userPrivateKey + "]";
	}
	
	
}
