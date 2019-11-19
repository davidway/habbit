package com.blockchain.service.impl;

import com.blockchain.dto.ConfigPropertiesFormDTO;
import com.blockchain.dto.CrmConfigDto;
import com.blockchain.dto.CrmServiceDTO;
import com.blockchain.exception.ServiceException;
import com.blockchain.service.ConfigPropertiesService;
import com.blockchain.util.ConfigUtils;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ConfigPropertiesService")
public class ConfigPropertiesServiceImpl implements ConfigPropertiesService {

	Logger logger = LoggerFactory.getLogger(ConfigPropertiesServiceImpl.class);

	@Override
	public void add(ConfigPropertiesFormDTO configPropertiesFormDTO) throws TrustSDKException, ServiceException {
		String chainId = configPropertiesFormDTO.getChainId();
		String ledgerId = configPropertiesFormDTO.getLedgerId();
		String mchId = configPropertiesFormDTO.getMchId();
		String nodeId = configPropertiesFormDTO.getNodeId();
		String createUserPrivateKey = configPropertiesFormDTO.getCreateUserPrivateKey();
		String createUserPublicKey = configPropertiesFormDTO.getCreateUserPublicKey();
		String host = configPropertiesFormDTO.getHost();
		ConfigUtils configUtils = new ConfigUtils();

		if (StringUtils.isNotBlank(chainId)) {
			configUtils.setChainId(chainId);
		}
		if (StringUtils.isNotBlank(ledgerId)) {

			configUtils.setLedgerId(ledgerId);
		}
		if (StringUtils.isNotBlank(host)) {

			configUtils.setHost(host);
		}
		if (StringUtils.isNotBlank(mchId)) {
			configUtils.setMchId(mchId);
		}
		if (StringUtils.isNotBlank(nodeId)) {
			configUtils.setNodeId(nodeId);
		}
		if (StringUtils.isNotBlank(createUserPrivateKey)) {
			configUtils.setCreateUserPrivateKey(createUserPrivateKey);
		}
		if (StringUtils.isNotBlank(createUserPublicKey)) {
			configUtils.setCreateUserPublicKey(createUserPublicKey);
		}

		return;
	}

	@Override
	public ConfigPropertiesFormDTO get() {
		ConfigPropertiesFormDTO configPropertiesFormDTO = new ConfigPropertiesFormDTO();
		ConfigUtils configUtils = new ConfigUtils();
		String chainId = configUtils.getChainId();
		String ledgerId = configUtils.getLedgerId();
		String mchId = configUtils.getMchId();
		String nodeId = configUtils.getNodeId();
		String createUserPrivateKey = configUtils.getCreateUserPrivateKey();
		String createUserPublicKey = configUtils.getCreateUserPublicKey();

		String host = configUtils.getHost();
		configPropertiesFormDTO.setChainId(chainId);

		configPropertiesFormDTO.setLedgerId(ledgerId);

		configPropertiesFormDTO.setMchId(mchId);

		configPropertiesFormDTO.setNodeId(nodeId);

		configPropertiesFormDTO.setHost(host);
		configPropertiesFormDTO.setCreateUserPrivateKey(createUserPrivateKey);

		configPropertiesFormDTO.setCreateUserPublicKey(createUserPublicKey);
		return configPropertiesFormDTO;

	}

	@Override
	public CrmConfigDto getCrmConfig() {
		CrmConfigDto crmConfigDto = new CrmConfigDto();
		
		CrmServiceDTO crmServiceDto = new CrmServiceDTO();
		String crmUrl = crmServiceDto.getCrmBaseUrls().trim();
		String serverCode = crmServiceDto.getServerCode().trim();
		String serverId = crmServiceDto.getServerId().trim();
		crmConfigDto.setCrmUrl(crmUrl);

		crmConfigDto.setServerCode(serverCode);

		crmConfigDto.setServerId(serverId);
		return crmConfigDto;
	}

}
