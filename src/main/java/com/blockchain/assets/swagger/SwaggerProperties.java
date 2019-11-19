package com.blockchain.assets.swagger;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * 
 * @author zhaojing
 * 
 */
public class SwaggerProperties {
	static Logger logger = Logger.getLogger(SwaggerProperties.class);
	static Properties prop = new Properties();
	public static String isOpen;

	public static final String OPEN = "1";
	public static final String CLOSE = "0";

	static {
		//在try 里加上这个代码块，java会自动帮我们关闭，及时在发生异常的情况下也会，不用再用finally
		try(InputStream in = SwaggerProperties.class.getClassLoader()
				.getResourceAsStream("../main-resources/swagger.properties")){
			 
			prop.load(in);
			isOpen = getProperty(prop, "is_open");
		}
		catch (IOException e) {
			logger.error(e);
		}
		
	}

	private static String getProperty(Properties prop, String key) {
		String value = prop.getProperty(key);
		if (value != null) {
			return value = value.trim();
		}
		return value;
	}

	/** Prevent instantiation */
	private SwaggerProperties() {
	}

}
