package com.somnus.utils.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	public static ThreadLocal<String> ipLocal = new ThreadLocal<String>();

	private static final String charset = "utf-8";

	public static String post(String path, String method, String params, String contentType, boolean isSSL,
			String authCode) {

		HttpURLConnection conn = null;
		try {
			logger.info(String.format("request-->Path[%s], RequestMethod[%s],RequestBody[%s]", path, method, params));
			boolean isPost = true;
			if (method.equals("GET")) {
				isPost = false;
				if (params != null && !params.equals("")) {
					path += "?" + params;
				}
			}

			// auto add ssl request
			if (isSSL) {
				initSSL();
			}

			URL url = new URL(path);

			InetAddress address = InetAddress.getByName(url.getHost());
			String ip = address.getHostAddress();
			ipLocal.set(ip);
			conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestMethod(method);
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", charset);
			if (authCode != null && !authCode.isEmpty()) {
				conn.setRequestProperty("Authorization", authCode);
			}

			if (isPost) {
				byte[] data = params.getBytes(charset);
				conn.setRequestProperty("Content-Length", String.valueOf(data.length));
				conn.setRequestProperty("Content-Type", contentType);
				DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
				outStream.write(data);
				outStream.flush();
			}

			InputStream in = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			InputStreamReader reader = new InputStreamReader(in, charset);

			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) > 0) {
				sb.append(buff, 0, len);
			}

			logger.info(String.format("response-->Path[%s], requestParma[%s],ResponseCode[%s], ResponseBody[%s]", path,
					params, conn.getResponseCode(), sb.toString().replace("\n", "")));
			if (conn.getResponseCode() == 200) {
				if (!sb.toString().equals("")) {
					return sb.toString();
				} else {
					logger.error("Response empty string.");
				}
			} else {
				logger.warn("Response not 200 - " + conn.getResponseCode() + ", " + conn.getResponseMessage());
			}
		} catch (IOException ioe) {

			ioe.printStackTrace();
			System.out.println("**********" + ioe);

			try {

				StringBuilder codesb = new StringBuilder("{errcode:");
				if (ioe instanceof SocketTimeoutException) {
					logger.error(String.format("Path[%s], Read Time Out[%s],SocketTimeoutException[%s]", path,
							ioe.getMessage(), ioe));

					codesb.append(Configure.SEND_ERROR.SEND_READ_TIME_OUT).append("}");
					return codesb.toString();
				}
				if (conn != null) {
					switch (conn.getResponseCode()) {
					case HttpStatus.SC_SERVICE_UNAVAILABLE:
						codesb.append(HttpStatus.SC_SERVICE_UNAVAILABLE);
						break;
					case HttpStatus.SC_NOT_FOUND:
						codesb.append(HttpStatus.SC_NOT_FOUND);
						break;
					case HttpStatus.SC_FORBIDDEN:
						codesb.append(HttpStatus.SC_FORBIDDEN);
						break;
					default:
						logger.error("sytem code -10,reponse code " + conn.getResponseCode());
						codesb.append(Configure.SEND_ERROR.API_RESPONSE_EMPTY);
						break;
					}
					logger.error(String.format("Path[%s], Response Code[%s], IOException[%s]", path,
							conn.getResponseCode(), ioe.getMessage()));
				} else {
					codesb.append(Configure.SEND_ERROR.API_RESPONSE_EMPTY);
					logger.error(String.format("Path[%s],Exception[%s]", path, ioe.getMessage()));
				}

				codesb.append("}");
				return codesb.toString();
			} catch (IOException io) {
				logger.error(String.format("Path[%s], GET RESPONSE CODE ERROR[%s]", path, ioe.getMessage()), io);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(String.format("Data[%s],Path[%s], Error[%s]", params, path, e.getMessage()));
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(111);
		logger.debug("--");
		logger.warn("222-1");
		logger.error("333--");
		System.out.println(222);
	}

	protected static void initSSL() {

		try {

			TrustManager[] tmCerts = new javax.net.ssl.TrustManager[1];
			tmCerts[0] = new SimpleTrustManager();
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
			sc.init(null, tmCerts, new java.security.SecureRandom());

			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HostnameVerifier hv = new SimpleHostnameVerifier();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String parse(Map<String, Object> params) {
		if (params == null || params.equals("")) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		for (String key : params.keySet()) {
			try {
				builder.append(key + "=" + URLEncoder.encode(String.valueOf(params.get(key)), charset) + "&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

	public static String[] httpGet(String url, boolean isContent) {
		HttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		HttpClient client = HttpClientBuilder.create().setConnectionManager(connManager).build();

		try {
			client = getSecuredHttpClient(client);

		} catch (Exception e) {
			e.printStackTrace();
		}
		String result[] = new String[] { "200", "" };
		HttpGet get = new HttpGet(url);
		/*
		 * String base = String.valueOf(Base64.encode(
		 * "57b9ef19d4be5de08df12aa0:13ac09b17715bd117163d8a1" .getBytes()));
		 * System.err.println(base); get.setHeader("Authorization", "Basic "
		 * +base);
		 */
		HttpResponse response = null;
		try {
			response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("url = " + url + " #statusCode = " + statusCode);

			if (statusCode != HttpStatus.SC_OK) {
				result[0] = String.valueOf(statusCode);
				return result;
			}
			if (isContent) {
				HttpEntity entity = response.getEntity();
				result[1] = EntityUtils.toString(entity, charset);
			}

		} catch (Exception e) {
			e.printStackTrace();
			result[0] = "500";
			result[1] = "";

			logger.error(e.getMessage(), e);
		} finally {
			get.abort();
		}
		return result;

	}

	private static DefaultHttpClient getSecuredHttpClient(HttpClient httpClient) throws Exception {
		final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {};
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return _AcceptedIssuers;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) {
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, new SecureRandom());

			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, httpClient.getParams());
		} catch (Exception e) {
			throw e;
		}
	}

}

class SimpleHostnameVerifier implements HostnameVerifier {

	@Override
	public boolean verify(String hostname, SSLSession session) {
		return true;
	}
}
