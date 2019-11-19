package com.blockchain.service;

import com.blockchain.dto.*;
import com.blockchain.exception.ServiceException;
import com.tencent.trustsql.sdk.exception.TrustSDKException;

import java.io.UnsupportedEncodingException;

public interface AssetService {

	AssetIssueDTO issue(AssetFormDTO assetFormDTO) throws Exception;

	AssetTransferDTO transfer(AssetTransferFormDTO assetTransferFormDTO) throws TrustSDKException, Exception;

	AssetSettleDTO settle(AssetSettleFormDTO assetSettleFormDTO) throws UnsupportedEncodingException, TrustSDKException, Exception;

	
	AssetIssueDTO issueSubmit(AssetSubmitFormDTO assetForm) throws UnsupportedEncodingException, TrustSDKException, Exception;

	AssetTransferDTO transSubmit(AssetTransferSubmitFormDTO assetForm) throws ServiceException, TrustSDKException, Exception;

	AssetSettleDTO settleSubmit(AssetSettleSubmitFormDTO assetForm) throws Exception;

	AssetTransferSubmitFormDTO transferApply(AssetTransferFormDTO assetTransferFormDTO) throws TrustSDKException, Exception;

	AssetSubmitFormDTO issueApply(AssetFormDTO assetFormDTO) throws ServiceException, TrustSDKException, Exception;

	AssetSettleSubmitFormDTO settleApply(AssetSettleFormDTO assetSettleFormDTO) throws ServiceException, TrustSDKException, Exception;

}
