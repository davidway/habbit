package com.blockchain.dto;

import com.wordnik.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel(value="交易信息详情对象")
public class TransInfoDetailsDto extends BaseDto {
	@NotEmpty(message="transHash不能为空")
	String transHash;
	
	
	
	

	public String getTransHash() {
		return transHash;
	}

	public void setTransHash(String transHash) {
		this.transHash = transHash;
	}
	
	
}
