package com.savvion.util.parser.service;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.savvion.util.parser.api.ParserApi;

import br.redecorp.api.utildomain.parser.v1.ParserRequest;
import br.redecorp.api.utildomain.parser.v1.ParserResponse;
import br.redecorp.api.utildomain.parser.v1.QueryParam;
import br.redecorp.api.utildomain.parser.v1.TefHeaderType;



@Service
public class ParserService implements ParserApi{
	public static int PRETTY_PRINT_INDENT_FACTOR = 1;
	
	public ParserResponse callMS(TefHeaderType tefHeaderType, ParserRequest parserRequest) {
		ParserResponse parseResponse = new ParserResponse();
		
		Assert.notNull(parserRequest.getUrl(), "The url  must not be null");
		Assert.notNull(parserRequest.getHttpMethod(), "The verb  must not be null");
	
		if(parserRequest.getUrl() != null && !parserRequest.getUrl().isEmpty()
				&& parserRequest.getHttpMethod() != null && !parserRequest.getHttpMethod().isEmpty())	{
			
			JSONObject xmlJSONObj = XML.toJSONObject(parserRequest.getBody());
	        String jsonRequest = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
	        // System.out.println(jsonRequest);
			
			RestTemplate client = new RestTemplate();
			HttpHeaders headers = buildAuthorizationHeader(tefHeaderType.getToken());
			
			ResponseEntity<String> response = null;
			String url = getUrl(parserRequest);
			
			String verbUpperCase = parserRequest.getHttpMethod().toUpperCase();
			if(HttpMethod.GET.toString().equals(verbUpperCase)){
				HttpEntity<String> entity = new HttpEntity<>(null, headers);
				response = client.exchange(url, HttpMethod.GET, entity, String.class);
			} else if(HttpMethod.PUT.toString().equals(verbUpperCase)){
				HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
				response = client.exchange(url, HttpMethod.PUT, entity, String.class);
			} else if(HttpMethod.POST.toString().equals(verbUpperCase)){
				HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
				response = client.exchange(url, HttpMethod.POST, entity, String.class);
			} else if(HttpMethod.DELETE.toString().equals(verbUpperCase)){
				HttpEntity<String> entity = new HttpEntity<>(null, headers);
				response = client.exchange(url, HttpMethod.DELETE, entity, String.class);
			}

			
			if(response != null) {
				JSONObject json = new JSONObject(response.getBody());
				String xml = XML.toString(json);
				parseResponse.setStatusCode(response.getStatusCodeValue());
				parseResponse.setResult(xml);
			}
		}
		
		return parseResponse;
	}
	
	 /**
     * buildHeaders
     * @return
     */
    public HttpHeaders buildAuthorizationHeader(String header) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", header);
        
        return headers;
    }
    
    private String getUrl(ParserRequest parserRequest) {
    	String url = parserRequest.getUrl();
    	
    	if(parserRequest.getQueryParam() != null && parserRequest.getQueryParam().size() > 0) {
    		for (QueryParam queryPar : parserRequest.getQueryParam()) {
    			if(!url.contains("?")) {
    				url = url + "?" + queryPar.getKey() + "=" + queryPar.getValue();
    			} else {
    				url = url + "&" + queryPar.getKey() + "=" + queryPar.getValue();
    			}
			}
    	}
    	return url;
    }

}
