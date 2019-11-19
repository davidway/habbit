package com.blockchain.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel(value="验证公私钥json")
public class KeyInfoDTO {
	@ApiModelProperty(value="用户私钥")
	@NotEmpty(message="私钥不能为空")
	private String privateKey;

	@ApiModelProperty(value="用户公钥")
	@NotEmpty(message="公钥不能为空")
	private String publicKey;
	
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	@Override
	public String toString() {
		return "KeyInfoDTO [privateKey=" + privateKey + ", publicKey=" + publicKey + "]";
	}
	
	
}
