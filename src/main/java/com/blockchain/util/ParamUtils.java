package com.blockchain.util;

import com.blockchain.dto.AssetTransferFormDTO;
import com.blockchain.exception.ServiceException;
import com.blockchain.exception.StatusCode;
import org.apache.commons.lang3.StringUtils;

public class ParamUtils {

	public static void checkAssetNum(String srcAsset) throws ServiceException {
		String[] temp = srcAsset.split(",");
		if (temp.length > 20) {
			throw new ServiceException().pos("检查资产id是否超过20个").errorCode(StatusCode.PARAM_ERROR).errorMessage("资产id不能超过20个");
		}
	}

	public static void checkSumAmount(AssetTransferFormDTO assetTransferFormDTO) throws ServiceException {
		long sum =0;
		if (StringUtils.isNotBlank(assetTransferFormDTO.getFeeAmount() )){
			sum = Long.valueOf(assetTransferFormDTO.getAmount())+Long.valueOf(assetTransferFormDTO.getFeeAmount());
		}else{
			sum =Long.valueOf(assetTransferFormDTO.getAmount());
		}
	
		if (sum>1_000_000_000_000_000_000L) {
			throw new ServiceException().pos("转账金额检查").errorCode(StatusCode.PARAM_ERROR).errorMessage("金额不能超过1_000_000_000_000_000_000L");
		}
		
	}

	public static void checkNum(Long beginHeight, Long endHeight) throws ServiceException {
		if ( beginHeight>endHeight){
			throw new ServiceException().pos("区块高度检查").errorCode(StatusCode.PARAM_ERROR).errorMessage("区块开始高度不能大于结束高度");
		}
		if ( endHeight-beginHeight>20){
			throw new ServiceException().pos("区块高度检查").errorCode(StatusCode.PARAM_ERROR).errorMessage("区块高度的范围值不能大于20");
		}
	}

	public static void checkNumForDetails(Long beginHeight, Long endHeight) throws ServiceException {
		if ( !beginHeight.equals(endHeight)){
			throw new ServiceException().pos("区块详情获取").errorCode(StatusCode.PARAM_ERROR).errorMessage("详情时区块 开始与终止高度 理应相等");
		}
		
	}

}
