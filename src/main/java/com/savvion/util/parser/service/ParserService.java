package com.savvion.util.parser.service;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import br.redecorp.api.utildomain.parser.v1.ParserRequest;
import br.redecorp.api.utildomain.parser.v1.ParserResponse;



@Service
public class ParserService {
	public static int PRETTY_PRINT_INDENT_FACTOR = 1;
	
	public ParserResponse callMS(ParserRequest parserRequest) {
		Assert.notNull(parserRequest.getBody(), "The body  must not be null");
		
		JSONObject xmlJSONObj = XML.toJSONObject(parserRequest.getBody());
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        System.out.println(jsonPrettyPrintString);
		
		
		RestTemplate client = new RestTemplate();
		ResponseEntity<String> response = client.exchange(parserRequest.getUrl(), HttpMethod.GET, null, String.class);
		
		JSONObject json = new JSONObject(response.getBody());
		String xml = XML.toString(json);
		
		ParserResponse parseResponse = new ParserResponse();
		parseResponse.setStatusCode(response.getStatusCodeValue());
		parseResponse.setResult(xml);
		
		return parseResponse;
	}

}
