package com.blockchain.util;

import com.alibaba.fastjson.JSONObject;
import com.blockchain.controller.AssetController;
import com.blockchain.dto.ConfigDto;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tbaas.v20180416.TbaasClient;
import com.tencentcloudapi.tbaas.v20180416.models.SrvInvokeRequest;
import com.tencentcloudapi.tbaas.v20180416.models.SrvInvokeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TBassUtil {
    private static Logger logger  = LoggerFactory.getLogger(TBassUtil.class);
    private static ConfigDto configDto ;

    public static ConfigDto getConfigDto() {
        return configDto;
    }

    public static void setConfigDto(ConfigDto configDto) {
        TBassUtil.configDto = configDto;
    }

    public static String request(String methodName, String applyString) throws TencentCloudSDKException {
        String secretId = configDto.getSecretId();
        String secretKey = configDto.getSecretKey();
        Credential cred = new Credential(secretId, secretKey);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("tbaas.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        TbaasClient client = new TbaasClient(cred, "", clientProfile);
        JSONObject params =  new JSONObject();
        params.put("Service","dam");
        params.put("Method",methodName);
        params.put("Param",applyString);
        String paramString = params.toJSONString();

        SrvInvokeRequest req = SrvInvokeRequest.fromJsonString(paramString, SrvInvokeRequest.class);

        SrvInvokeResponse resp = client.SrvInvoke(req);

        return SrvInvokeRequest.toJsonString(resp);
    }
}
