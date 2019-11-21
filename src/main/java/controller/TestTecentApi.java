package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blockchain.vo.UserInfoVO;
import com.tencent.trustsql.sdk.TrustSDK;
import com.tencent.trustsql.sdk.bean.PairKey;
import com.tencent.trustsql.sdk.exception.TrustSDKException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tbaas.v20180416.TbaasClient;
import com.tencentcloudapi.tbaas.v20180416.models.ApplyUserCertRequest;
import com.tencentcloudapi.tbaas.v20180416.models.ApplyUserCertResponse;
import com.tencentcloudapi.tbaas.v20180416.models.SrvInvokeRequest;
import com.tencentcloudapi.tbaas.v20180416.models.SrvInvokeResponse;
import org.testng.annotations.Test;


import java.io.UnsupportedEncodingException;


public class TestTecentApi {
    @Test
    public void testGenerateAccount() throws UnsupportedEncodingException, TrustSDKException {


    }
    @Test
    public void testIssue(){
        String content = "{'wsy':'a'}";
        JSONObject json = JSONObject.parseObject(content);
        System.out.println(json.toJSONString());
    }

    @Test
    public static void testApplyAccount() {

            Credential cred = new Credential("AKIDEdxIkFJS2SOHFERuW0JUjlcIm4GGHg2i", "udDbqvpkvGVZTNG2byFLFw9FNg1DE5kK");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tbaas.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            TbaasClient client = new TbaasClient(cred, "ap-guangzhou", clientProfile);
            String params = "{\"Module\":\"cert_mng\",\"Operation\":\"cert_apply_for_user\",\"ClusterId\":\"ch3a5nqj860reumytw\",\"GroupName\":\"a\",\"UserIdentity\":\"a\",\"Applicant\":\"a\",\"IdentityNum\":\"a\",\"CsrData\":\"a\"}";
            ApplyUserCertRequest req = ApplyUserCertRequest.fromJsonString(params, ApplyUserCertRequest.class);
            //ApplyUserCertResponse resp = client.ApplyUserCert(req);


    }
}
