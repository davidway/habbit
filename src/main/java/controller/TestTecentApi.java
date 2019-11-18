package controller;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.tbaas.v20180416.TbaasClient;

import com.tencentcloudapi.tbaas.v20180416.models.ApplyUserCertRequest;
import com.tencentcloudapi.tbaas.v20180416.models.ApplyUserCertResponse;
import org.testng.annotations.Test;


public class TestTecentApi {

@Test
    public static void testApplyAccount() {

        try {
            Credential cred = new Credential("AKIDEdxIkFJS2SOHFERuW0JUjlcIm4GGHg2i", "udDbqvpkvGVZTNG2byFLFw9FNg1DE5kK");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tbaas.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            TbaasClient client = new TbaasClient(cred, "ap-guangzhou", clientProfile);
            String params = "{\"Module\":\"cert_mng\",\"Operation\":\"cert_apply_for_user\",\"ClusterId\":\"ch3a5nqj860reumytw\",\"GroupName\":\"a\",\"UserIdentity\":\"a\",\"Applicant\":\"a\",\"IdentityNum\":\"a\",\"CsrData\":\"a\"}";
            ApplyUserCertRequest req = ApplyUserCertRequest.fromJsonString(params, ApplyUserCertRequest.class);
            ApplyUserCertResponse resp = client.ApplyUserCert(req);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }
}
