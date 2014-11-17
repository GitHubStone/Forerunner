package com.forerunner.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * 服务器请求处理助手
 * @author GQ
 */
public class HttpRequestHelper {
	private static Log log = LogFactory.getLog(HttpRequestHelper.class);

	private HttpRequestHelper() {
	}

	/**
	 * Get 请求 + base 认证 XXX 未完成
	 * @param callback
	 */
	public void requestByGetWithAuthentication(ResponseCallback<String> callback) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.getCredentialsProvider().setCredentials(new AuthScope("localhost", 443), new UsernamePasswordCredentials("username", "password"));

			HttpGet httpget = new HttpGet("https://localhost/protected");

			if (log.isDebugEnabled()) {
				log.debug("executing request " + httpget.getRequestLine());
			}

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (log.isDebugEnabled()) {
				log.debug("----------------------------------------");
				log.debug(response.getStatusLine());
			}

			if (entity != null) {
				callback.invoke(response, entity);
			}

			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 发起Get请求
	 */
	public static <T> T requestByGet(String url, ResponseCallback<T> callback) {
		T t = null;

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);

			if (log.isDebugEnabled()) {
				log.debug("executing request " + httpget.getRequestLine());
			}

			callback.preExecute(httpclient, httpget);

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (log.isDebugEnabled()) {
				log.debug("----------------------------------------");
				log.debug(response.getStatusLine());
			}

			if (entity != null) {
				t = callback.invoke(response, entity);
			}

			EntityUtils.consume(entity);
		} catch (IOException e) {
			throw new RuntimeException("Failed to Request data form " + url + " : " + e.getMessage(), e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return t;
	}

	/**
	 * 发起Get请求
	 */
	public static <T> T requestByPost(String url, Map<String, String> args, ResponseCallback<T> callback) {
		T t = null;

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(url);

			if (args != null) {
				HttpParams params = new BasicHttpParams();
				for (Map.Entry<String, String> entry : args.entrySet()) {
					params = params.setParameter(entry.getKey(), entry.getValue());
				}
				httppost.setParams(params);
			}

			if (log.isDebugEnabled()) {
				log.debug("executing request " + httppost.getRequestLine());
			}

			callback.preExecute(httpclient, httppost);

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (log.isDebugEnabled()) {
				log.debug("----------------------------------------");
				log.debug(response.getStatusLine());
			}

			if (entity != null) {
				t = callback.invoke(response, entity);
			}

			EntityUtils.consume(entity);
		} catch (IOException e) {
			throw new RuntimeException("Failed to Request data form " + url + " : " + e.getMessage(), e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return t;
	}

	/**
	 * 响应回调
	 */
	public static abstract class ResponseCallback<T> {
		private HttpResponse response;
		private HttpEntity entity;

		protected final Charset getCharset() {
			ContentType contentType = ContentType.getOrDefault(entity);
			return contentType.getCharset();
		}

		protected final int getStatusCode() {
			StatusLine statusLine = response.getStatusLine();
			return statusLine.getStatusCode();
		}

		private final T invoke(HttpResponse response, HttpEntity entity) throws IOException {
			this.response = response;
			this.entity = entity;
			return process(entity);
		}

		public void preExecute(HttpClient httpclient, HttpRequestBase request) {
		}

		public abstract T process(HttpEntity entity) throws IOException;
	}
}