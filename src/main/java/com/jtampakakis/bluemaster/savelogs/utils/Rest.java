package com.jtampakakis.bluemaster.savelogs.utils;

import java.net.URI;
import java.util.Base64;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class Rest {
	
	private String url;
	private String authString;
	private String contentType;
	private String body;
	
	private String response;
	private int responseCode;

	public Rest(String url, String authString, String contentType, String body) {
		this.url = url;
		this.authString = authString;
		this.contentType = contentType;
		this.body = body;
	}
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void GET(String authorizationType, Logger LOGGER) {
		HttpClient httpclient = HttpClients.createDefault();
		try {
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);
			request.setHeader("Authorization", authorizationType+" "+authString);
			request.setHeader("Content-Type", contentType);

			HttpResponse resp = httpclient.execute(request);
			this.responseCode = resp.getStatusLine().getStatusCode();
			LOGGER.info("GET API Call "+url+" executed with response code "+this.responseCode);
			
			HttpEntity entity = resp.getEntity();

			if (entity != null) {
				this.response=EntityUtils.toString(entity);
				LOGGER.info("GET API Call "+url+" executed with response "+this.response);
			}
		} catch (Exception e) {
			LOGGER.warning("GET API Call "+url+" unable to execute "+e.toString());
		}
	}
	
	public void POST(String authorizationType, Logger LOGGER) {
		HttpClient httpclient = HttpClients.createDefault();

		try {
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Authorization", authorizationType+" "+authString);
			request.setHeader("Content-Type", contentType);
			// Request body
			StringEntity reqEntity = new StringEntity(body);
			request.setEntity(reqEntity);

			HttpResponse resp = httpclient.execute(request);
			this.responseCode = resp.getStatusLine().getStatusCode();
			LOGGER.info("POST API Call "+url+" executed with response code "+this.responseCode);
			
			HttpEntity entity = resp.getEntity();

			if (entity != null) {
				this.response=EntityUtils.toString(entity);
				LOGGER.info("POST API Call "+url+" executed with response "+this.response);
			}
		} catch (Exception e) {
			LOGGER.warning("GET API Call "+url+" unable to execute "+e.toString());
		}
	}
	
	public void PUT(String authorizationType, Logger LOGGER) {
		HttpClient httpclient = HttpClients.createDefault();

		try {
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpPut request = new HttpPut(uri);
			request.setHeader("Authorization", authorizationType+" "+authString);
			request.setHeader("Content-Type", contentType);

			// Request body
			StringEntity reqEntity = new StringEntity(body);
			request.setEntity(reqEntity);

			HttpResponse resp = httpclient.execute(request);
			this.responseCode = resp.getStatusLine().getStatusCode();
			LOGGER.info("PUT API Call "+url+" executed with response code "+this.responseCode);
			
			HttpEntity entity = resp.getEntity();

			if (entity != null) {
				this.response=EntityUtils.toString(entity);
				LOGGER.info("PUT API Call "+url+" executed with response "+this.response);
			}
		} catch (Exception e) {
			LOGGER.warning("PUT API Call "+url+" unable to execute "+e.toString());
		}
	}
	
	public void DELETE(String authorizationType, Logger LOGGER) {
		HttpClient httpclient = HttpClients.createDefault();
		try {
			URIBuilder builder = new URIBuilder(url);

			URI uri = builder.build();
			HttpDelete request = new HttpDelete(uri);
			request.setHeader("Authorization", authorizationType+" "+authString);
			request.setHeader("Content-Type", contentType);
			HttpResponse resp = httpclient.execute(request);
			this.responseCode = resp.getStatusLine().getStatusCode();
			LOGGER.info("DELETE API Call "+url+" executed with response code "+this.responseCode);
			
			HttpEntity entity = resp.getEntity();

			if (entity != null) {
				this.response=EntityUtils.toString(entity);
				LOGGER.info("DELETE API Call "+url+" executed with response "+this.response);
			}
		} catch (Exception e) {
			LOGGER.warning("DELETE API Call "+url+" unable to execute "+e.toString());
		}
	}

	public static BearerToken GetToken(String clientId, String clientSecret, String ip, String username, String password,Logger LOGGER) {
		String authStr = clientId+":"+clientSecret;
	    String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
		Rest auth = new Rest(SV.webProt+ip+"/oauth/token", base64Creds, "application/x-www-form-urlencoded", "grant_type=password&username="+username+"&password="+password);
		auth.POST("Basic", LOGGER);
		if (auth.getResponseCode()!=200) return null;
		BearerToken bt = new Gson().fromJson(auth.getResponse(), BearerToken.class);
		return bt;
	}

}
