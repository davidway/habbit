package com.blockchain.util;

import com.alibaba.fastjson.JSONObject;
import com.blockchain.exception.ServiceException;
import com.blockchain.exception.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultUtil {

	public static final Logger log = LoggerFactory.getLogger(ResultUtil.class);

	public static void checkResultIfSuccess(String pos, String resultString) throws ServiceException {
		if (StringUtils.isBlank(resultString)) {
			throw new ServiceException().errorCode(StatusCode.SYSTEM_UNKOWN_ERROR).errorMessage("调用SDK网络失败，请检查网络");
		}

		JSONObject applyObject = JSONObject.parseObject(resultString);
		applyObject = applyObject.getJSONObject("Data");
		Integer retcode = applyObject.getInteger("retcode");

		 if (retcode != 0 ) {
			Integer errorCode = StatusCode.SYSTEM_UNKOWN_ERROR;
			String jsonString =applyObject.getString("retmsg");
			throw new ServiceException().pos(pos).data(applyObject).errorCode(errorCode).errorMessage(jsonString);
		} else {
			log.debug( "{}成功！结果为{}",pos , applyObject.toJSONString());
		}
	}

	public static void checkSubmitResultIfSuccess(String pos, String submitParamString, String submitResultString) throws ServiceException {
		if (StringUtils.isBlank(submitResultString)) {
			throw new ServiceException().errorCode(StatusCode.SYSTEM_UNKOWN_ERROR).errorMessage("调用SDK网络失败，请检查网络");
		}
		JSONObject submitResultObject = JSONObject.parseObject(submitResultString);
		JSONObject submitObject = JSONObject.parseObject(submitParamString);

		Integer retcode = submitResultObject.getInteger("retcode");
		if (retcode.equals(83590142)) {

			Integer errorCode = StatusCode.SUBMIT_THREAD_ERROR;
			throw new ServiceException().pos(pos).errorCode(errorCode).errorMessage(submitResultObject.getString("retmsg")).data(submitObject);
		} else if (retcode != 0&&retcode.equals(83590142)==false) {
			Integer errorCode = StatusCode.SYSTEM_UNKOWN_ERROR;
			throw new ServiceException().pos(pos).errorCode(errorCode).errorMessage(submitResultObject.getString("retmsg")).data(submitObject);
		} else {
			log.debug( "{}成功！结果为{}",pos,  submitParamString);
		}
	}

}
