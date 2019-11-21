package com.blockchain.util;

import com.blockchain.exception.ServiceException;
import com.blockchain.exception.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ValidatorUtil {
	
	public static void validate(BindingResult bindingResult) throws ServiceException {
		boolean first =true;
		StringBuffer sb = new StringBuffer("");
		if (bindingResult.hasErrors()) {

			for (ObjectError objectError : bindingResult.getAllErrors()) {
				String string = objectError.getDefaultMessage();
				if (first){
					sb.append(string);
					first=false;
				}else{
					sb.append(","+string);
				}
			}
		}
		if ( StringUtils.isNotBlank(sb)){
			
			throw new ServiceException().pos("检查参数是否为空").errorCode(StatusCode.PARAM_ERROR).errorMessage(sb.toString());
		}
	}

}
