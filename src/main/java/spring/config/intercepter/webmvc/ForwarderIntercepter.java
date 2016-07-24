package spring.config.intercepter.webmvc;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ForwarderIntercepter extends HandlerInterceptorAdapter {

	private HttpClient httpclient;
//	private static String urlClient = "http://localhost/mobile/";
	private static String urlClient = "https://maps.googleapis.com/";
	


	/**
	 * This implementation is empty.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		httpclient = new DefaultHttpClient();	
		HttpResponse httpResponse;

		
		Enumeration<String> parameterNames = request.getParameterNames();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String paramValue = request.getParameter(paramName);
			nameValuePairs.add(new BasicNameValuePair(paramName, paramValue));	
		}
		
		if (request.getMethod().equalsIgnoreCase("post")) {
			HttpPost httppost = new HttpPost(urlClient + getContext(request));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// Execute HTTP Post Request
			httpResponse = httpclient.execute(httppost);
		}else{
			String paramString = URLEncodedUtils.format(nameValuePairs, "utf-8");
			HttpGet httpGet = new HttpGet(urlClient + getContext(request) + "?" + paramString);			
			httpResponse = httpclient.execute(httpGet);
		}
		


//		HttpEntity entity = httpResponse.getEntity();
//		String responseString = EntityUtils.toString(entity, "UTF-8");
//		System.out.println(responseString);
		
		response.setContentType(httpResponse.getEntity().getContentType().getValue());
		response.setContentLength((int) httpResponse.getEntity().getContentLength());
		response.setStatus(httpResponse.getStatusLine().getStatusCode());
		response.getOutputStream().write(IOUtils.toByteArray(httpResponse.getEntity().getContent()));
		response.getOutputStream().flush();
		response.getOutputStream().close();
		System.out.println("this is the response " + response);
		
	}

	// Ex. http://localhost:8080/spring/{context}
	private String getContext(HttpServletRequest request){
		String reqUri = request.getRequestURI();
		String context = reqUri.substring(request.getSession().getServletContext().getContextPath().length() + 1,  reqUri.length());
		return context;
	}
}
