package com.blockchain.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class BaseDto {
	@ApiModelProperty(value = "配置文件信息", required = true)
	@NotNull(message = "配置信息")
	@Valid
	private ConfigDto configDto;

	public ConfigDto getConfigDto() {
		return configDto;
	}

	public void setConfigDto(ConfigDto configDto) {
		this.configDto = configDto;
	}
	
}
