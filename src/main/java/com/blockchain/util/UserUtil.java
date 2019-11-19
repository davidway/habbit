package com.blockchain.util;

import com.alibaba.fastjson.JSONObject;
import com.blockchain.dto.AccountQueryFormDTO;
import com.blockchain.dto.AssetTransQueryFormDTO;
import com.blockchain.dto.ConfigDto;
import com.blockchain.dto.UserFormDTO;
import com.blockchain.vo.UserInfoVO;
import com.tencent.trustsql.sdk.TrustSDK;
import com.tencent.trustsql.sdk.bean.AccountCert;
import com.tencent.trustsql.sdk.bean.PairKey;
import com.tencent.trustsql.sdk.bean.RequestData;
import com.tencent.trustsql.sdk.bean.UserCert;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import com.tencent.trustsql.sdk.util.SignStrUtil;
import com.tencent.trustsql.sdk.util.SignUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class UserUtil {

	private static final int TRANS_SUBMIT_SUCCESS = 4;
	static boolean isTest = false;

	public static String generateUserRequest(UserInfoVO userInfoVO, UserFormDTO userFormDTO) throws Exception {
		ConfigDto configDto = userFormDTO.getConfigDto();
		String mchId = configDto.getMchId();
		String prvKey = configDto.getCreateUserPrivateKey();
		String createUserPublicKey = configDto.getCreateUserPublicKey();

		String chainId = configDto.getChainId();
		Map<String, Object> paramMap = new TreeMap<String, Object>();

		PairKey pairKey = TrustSDK.generatePairKey(true);
		String publicKey = pairKey.getPublicKey();
		String privateKey = pairKey.getPrivateKey();
		userInfoVO.setBasePublicKey(publicKey);
		userInfoVO.setBasePrivateKey(privateKey);

		paramMap.put("chain_id", chainId);
		paramMap.put("sign_type", "ECDSA");
		paramMap.put("mch_id", mchId);
		paramMap.put("version", "1.0");

		paramMap.put("user_id", userInfoVO.getId());
		paramMap.put("user_pub_key", publicKey);
		paramMap.put("full_name", userInfoVO.getName());

		paramMap.put("timestamp", System.currentTimeMillis() / 1000);

		paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes("UTF-8"), false));

		// generate post data
		JSONObject postJson = new JSONObject(true);
		for (String key : paramMap.keySet()) {
			postJson.put(key, paramMap.get(key));
		}
		return postJson.toJSONString();
	}

	public static String generateRegisterAccountRequest(UserCert userCert, boolean isHost) throws Exception {
		ConfigUtils configUtils = new ConfigUtils();
		RequestData req = new RequestData();
		String sqeNo = RequestUtil.getSequenceNumber();
		req.setMch_id(configUtils.getMchId());
		req.setProduct_code("productA");
		req.setSeq_no(sqeNo);
		req.setType("sign");
		AccountCert account = new AccountCert();
		account.setUser_id(userCert.getUser_id());
		String publicKey = "";
		if (isHost) {
			PairKey pairKey = TrustSDK.generatePairKey(true);
			publicKey = pairKey.getPublicKey();
			account.setPublic_key(publicKey);
		} else {
			account.setUser_id(userCert.getPublic_key());
		}

		req.setReq_data(JSONObject.toJSONString(account));
		req.setTime_stamp(System.currentTimeMillis() / 1000L);
		String prvKey = configUtils.getCreateUserPrivateKey();
		// String prvKey = "gaxUIUD76vmEaJwxUZEcqoM0LDESKtpc3M4FDSlPSV0";
		String signSrc = SignUtil.genSignSrc(req);

		String sign = TrustSDK.signString(prvKey, signSrc.getBytes("UTF-8"), false);
		req.setSign(sign);

		return JSONObject.toJSONString(req);
	}

	public static String generateuserAccoutForm(UserInfoVO userInfoVO) throws Exception {
		ConfigUtils configUtils = new ConfigUtils();
		String mchId = configUtils.getMchId();
		String prvKey = configUtils.getCreateUserPrivateKey();

		String chainId = configUtils.getChainId();
		Map<String, Object> paramMap = new TreeMap<String, Object>();

		paramMap.put("chain_id", chainId);
		paramMap.put("sign_type", "ECDSA");
		paramMap.put("mch_id", mchId);
		paramMap.put("version", "1.0");

		paramMap.put("user_id", userInfoVO.getId());
		PairKey pairKey = TrustSDK.generatePairKey(true);
		String publicKey = userInfoVO.getBasePublicKey();

		paramMap.put("acc_pub_key", publicKey);
		paramMap.put("acc_type", 1);

		paramMap.put("timestamp", System.currentTimeMillis() / 1000);

		paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes("UTF-8"), false));

		// generate post data
		JSONObject postJson = new JSONObject(true);
		for (String key : paramMap.keySet()) {
			postJson.put(key, paramMap.get(key));
		}

		return JSONObject.toJSONString(postJson);
	}

	public static String generateAccountQueryParam(AccountQueryFormDTO assetForm) throws TrustSDKException, Exception {
		ConfigDto configDto = assetForm.getConfigDto();
		String mchId = configDto.getMchId();
		String prvKey = configDto.getCreateUserPrivateKey();
		String mchPublicKehy = configDto.getCreateUserPublicKey();
		String chainId = configDto.getChainId();
		String nodeId = configDto.getNodeId();
		Map<String, Object> paramMap = new TreeMap<String, Object>();
		paramMap.put("version", "2.0");
		paramMap.put("sign_type", "ECDSA");
		paramMap.put("mch_id", mchId);
		paramMap.put("mch_pubkey", mchPublicKehy);
		paramMap.put("node_id", nodeId);

		paramMap.put("chain_id", chainId);
		if (StringUtils.isNotBlank(assetForm.getAssetAccount())) {
			paramMap.put("owner_account", assetForm.getAssetAccount());
		}
		if (StringUtils.isNotBlank(assetForm.getOwnerUid())) {
			paramMap.put("owner_uid", assetForm.getOwnerUid());
		}
		if (StringUtils.isNotBlank(assetForm.getAssetId())) {
			paramMap.put("asset_id", assetForm.getAssetId());
		}
		int[] stateArray = assetForm.getState();
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int i : stateArray) {
			array.add(i);
		}

		paramMap.put("state", array);
		paramMap.put("page_limit", Long.valueOf(assetForm.getPageLimit()));
		paramMap.put("page_no", Long.valueOf(assetForm.getPageNo()));
		paramMap.put("timestamp", System.currentTimeMillis() / 1000);
		paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes("UTF-8"), false));
		// generate post data
		JSONObject postJson = new JSONObject();
		for (String key : paramMap.keySet()) {
			postJson.put(key, paramMap.get(key));
		}
		return postJson.toJSONString();
	}

	public static String generateTransQueryParam(AssetTransQueryFormDTO assetForm) throws TrustSDKException, Exception {
		ConfigDto configDto = assetForm.getConfigDto();
		String mchId = "";
		String prvKey = "";
		String chainId ="";	
		String mchPublicKey = "";
		mchPublicKey = configDto.getCreateUserPublicKey();
		chainId = configDto.getChainId();
		prvKey = configDto.getCreateUserPrivateKey();
		mchId = configDto.getMchId();
		String nodeId = configDto.getNodeId();
		Map<String, Object> paramMap = new TreeMap<String, Object>();
		paramMap.put("version", "2.0");
		paramMap.put("sign_type", "ECDSA");
		paramMap.put("mch_id", mchId);
		paramMap.put("mch_pubkey", mchPublicKey);
		paramMap.put("node_id", nodeId);

		if (null != assetForm.getBlockHeightRange()) {
			
			paramMap.put("b_height", assetForm.getBlockHeightRange());
		}

		paramMap.put("chain_id", chainId);
		if (StringUtils.isNotBlank(assetForm.getSrcAccount())) {
			paramMap.put("src_account", assetForm.getSrcAccount());
		}

		if (StringUtils.isNotBlank(assetForm.getDstAccount())) {
			paramMap.put("dst_account", assetForm.getDstAccount());
		}
		if (StringUtils.isNotBlank(assetForm.getTransactionId())) {
			paramMap.put("transaction_id", assetForm.getTransactionId());
		}
		if (StringUtils.isNotBlank(assetForm.getTransHash())) {
			paramMap.put("trans_hash", assetForm.getTransHash());
		}
		if (assetForm.getState() != null) {
			ArrayList<Integer> statusArray = new ArrayList();
			Integer[] array = assetForm.getState();
			for (Integer integer : array) {
				statusArray.add(integer);
			}

			paramMap.put("state", statusArray);
		}
		if (assetForm.getTransType() != null) {

			ArrayList<Integer> statusArray = new ArrayList();
			Integer[] array = assetForm.getTransType();
			for (Integer integer : array) {
				statusArray.add(integer);
			}
			paramMap.put("trans_type", statusArray);
		}

		paramMap.put("page_limit", assetForm.getPageLimit());
		paramMap.put("page_no", assetForm.getPageNo());
		paramMap.put("timestamp", System.currentTimeMillis() / 1000);
		paramMap.put("mch_sign", TrustSDK.signString(prvKey, SignStrUtil.mapToKeyValueStr(paramMap).getBytes("UTF-8"), false));
		// generate post data
		JSONObject postJson = new JSONObject();
		for (String key : paramMap.keySet()) {
			postJson.put(key, paramMap.get(key));
		}
		return postJson.toJSONString();
	}

	public static String generateuserAccoutFormOnlyHostAccount(UserInfoVO userInfoVO, UserFormDTO userFormDTO) throws Exception {
		ConfigUtils configUtils = new ConfigUtils();
		String sequenceNumber = RequestUtil.getSequenceNumber();
		String accountPublicKey = "";
		String accountPrivateKey = "";
		RequestData req = new RequestData();
		req.setMch_id(configUtils.getMchId());
		req.setProduct_code("productA");
		req.setSeq_no(sequenceNumber);
		req.setType("sign");
		AccountCert account = new AccountCert();
		account.setUser_id(userFormDTO.getId());

		PairKey pairKey = TrustSDK.generatePairKey(true);
		accountPublicKey = pairKey.getPublicKey();
		accountPrivateKey = pairKey.getPrivateKey();
		account.setPublic_key(accountPublicKey);
		userInfoVO.setHostWalletPublicKey(accountPublicKey);
		userInfoVO.setHostWalletPrivateKey(accountPrivateKey);

		req.setReq_data(JSONObject.toJSONString(account));
		req.setTime_stamp(System.currentTimeMillis() / 1000L);
		String prvKey = configUtils.getCreateUserPrivateKey();
		// String prvKey = "gaxUIUD76vmEaJwxUZEcqoM0LDESKtpc3M4FDSlPSV0";
		String signSrc = SignUtil.genSignSrc(req);

		String sign = TrustSDK.signString(prvKey, signSrc.getBytes(), false);
		req.setSign(sign);

		return JSONObject.toJSONString(req);
	}

}
