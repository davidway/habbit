package blockchain.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="资产发行表单")
public class AssetSubmitFormDTO extends BaseDto{
	@ApiModelProperty(value="平台唯一标识一次交易的ID",required=true)
	@NotEmpty(message="平台交易id不能为空")
	private String transactionId;
	@ApiModelProperty(value="资产的唯一标识",required=true)
	@NotEmpty(message="平台唯一标志id不能为空")
	private String assetId;
	
	@ApiModelProperty(value="待签名串，是个json数组",required=true)
	@NotEmpty(message="待签名串不能为空")
	private String signStr;
	@ApiModelProperty(value="用户私钥",required=true)
	@NotEmpty(message="用户私钥不能为空")
	private String userPrivateKey;
	

	
	public String getSignStr() {
		return signStr;
	}
	public void setSignStr(String signStr) {
		this.signStr = signStr;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getUserPrivateKey() {
		return userPrivateKey;
	}
	public void setUserPrivateKey(String userPrivateKey) {
		this.userPrivateKey = userPrivateKey;
	}
	@Override
	public String toString() {
		return "AssetSubmitFormDTO [transactionId=" + transactionId + ", assetId=" + assetId + ", signStr=" + signStr + ", userPrivateKey=" + userPrivateKey + "]";
	}
	
	
}
