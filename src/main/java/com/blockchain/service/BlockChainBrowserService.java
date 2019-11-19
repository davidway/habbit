package com.blockchain.service;

import com.blockchain.dto.*;
import com.blockchain.exception.ServiceException;
import com.tencent.trustsql.sdk.exception.TrustSDKException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface BlockChainBrowserService {


	
	TransDetailsVo getTransInfoDetailsByHash(AssetTransQueryFormDTO transInfoDto) throws ServiceException, TrustSDKException, Exception;

	
	

	List<TransInfoDto> getTransInfoList(AssetTransQueryFormDTO assetTransQueryFormDTO) throws TrustSDKException, Exception;

	List<BlockTransDto> getBlockInfoList(TransHeightDto transHeightDto) throws UnsupportedEncodingException, TrustSDKException, Exception;


	BlockDetailsInfo getBlockInfoDetails(TransHeightDto transHeightDto) throws UnsupportedEncodingException, TrustSDKException, Exception;

	BlockChainInfoDto getChainInfo(ConfigDto configDto) throws UnsupportedEncodingException, TrustSDKException, Exception;


}
