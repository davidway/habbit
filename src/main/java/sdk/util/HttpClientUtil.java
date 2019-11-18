package sdk.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

	private static Log logger = LogFactory.getLog(HttpClientUtil.class);
	public static PoolingHttpClientConnectionManager poolConnManager;

	static {
		try {
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
			X509HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, hostnameVerifier);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register(
					"https", sslsf).build();
			poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} catch (Exception e) {
			logger.error("init http client exception", e);
			e.printStackTrace();
		}
	}

	public static String post(String url, String reqBodyJson) throws Exception {
		int retryTime = 0;
		String result = "";
			try {
				HttpPost httpPost = new HttpPost(url.trim());

				StringEntity strEntity = new StringEntity(reqBodyJson, "UTF-8");
				httpPost.setEntity(strEntity);
				CloseableHttpResponse response = getConnection().execute(httpPost);
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					result = entity != null ? EntityUtils.toString(entity, "UTF-8") : null;

					return result;

				} else {

					logger.error(String.format("Unexpected cft http response statuts: [%s]", status + ""));

					throw new ClientProtocolException(String.format("Unexpected cft http response statuts: [%s]", status + ""));
				}

			} catch (UnsupportedEncodingException e) {
				logger.error(e);
				e.printStackTrace();
				Thread.sleep(1000);
				if (retryTime >= 3) {
					Thread.sleep(1000);
					throw new Exception("UnsupportedEncodingException", e);
				}

			} catch (ClientProtocolException e) {
				logger.error(e);
				e.printStackTrace();
				Thread.sleep(1000);
				if (retryTime >= 3) {
					
					throw new Exception("ClientProtocolException", e);
				}
			} catch (IOException e) {
				Thread.sleep(1000);
					
					throw new Exception("IOException", e);
			} catch (Exception e) {
				logger.error(e);
				Thread.sleep(1000);
				e.printStackTrace();
				if (retryTime >= 3) {
					
					throw new Exception("未知错误", e);
				}
			}
		return result;
	}

	public String post(String url, Map<String, String> reqbody) throws Exception {
		try {
			HttpPost httpPost = new HttpPost(url.trim());
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (String key : reqbody.keySet()) {
				if (reqbody.get(key) != null && !StringUtils.isEmpty(reqbody.get(key))) {
					params.add(new BasicNameValuePair(key, reqbody.get(key)));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			CloseableHttpResponse response = getConnection().execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
			} else {
				logger.error(String.format("Unexpected http response statuts: [%s]", status + ""));
				throw new ClientProtocolException(String.format("Unexpected http response statuts: [%s]", status + ""));
			}
		} catch (UnsupportedEncodingException e) {
			throw new Exception("UnsupportedEncodingException", e);
		} catch (ClientProtocolException e) {
			throw new Exception("ClientProtocolException", e);
		} catch (IOException e) {
			throw new Exception("IOException", e);
		}
	}

	protected static CloseableHttpClient getConnection() {
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(poolConnManager).build();
		return httpclient;
	}

}