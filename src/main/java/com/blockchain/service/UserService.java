package com.blockchain.service;

import com.blockchain.dto.*;
import com.blockchain.exception.ServiceException;
import com.blockchain.vo.UserInfoVO;
import com.tencent.trustsql.sdk.exception.TrustSDKException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 操作用户消息的service
 * 
 * @author lupf
 * 
 */
public interface UserService {

	public UserKeyDTO generatePairKey(UserKeyDTO userKeyModel) throws TrustSDKException, UnsupportedEncodingException;

	List<AssetDTO> accountQuery(AccountQueryFormDTO assetForm) throws TrustSDKException, Exception;

	
	public UserInfoVO addUserHasBaseAccount(UserFormDTO userFormDTO) throws ServiceException, TrustSDKException, UnsupportedEncodingException, Exception;


	public UserInfoVO addUserHasBaseAccountWithoutTpki(UserFormDTO userFormDTO) throws ServiceException, TrustSDKException, UnsupportedEncodingException, Exception;


	public void checkPairKey(KeyInfoDTO keyInfo) throws TrustSDKException, ServiceException;

}