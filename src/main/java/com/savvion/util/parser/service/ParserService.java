package com.savvion.util.parser.service;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import io.spring.guides.gs_producing_web_service.ParseRequest;
import io.spring.guides.gs_producing_web_service.ParseResponse;

@Service
public class ParserService {
	
	public ParseResponse callMS(ParseRequest parseRequest) {
		Assert.notNull(parseRequest.getBody(), "The body  must not be null");
		
		RestTemplate client = new RestTemplate();
		ResponseEntity<String> response = client.exchange(parseRequest.getUrl(), HttpMethod.GET, null, String.class);
		
		JSONObject json = new JSONObject(response.getBody());
		String xml = XML.toString(json);
		
		ParseResponse parseResponse = new ParseResponse();
		parseResponse.setStatusCode(response.getStatusCodeValue());
		parseResponse.setResult(xml);
		
		return parseResponse;
	}

}
