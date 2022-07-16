package com.savvion.util.parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.savvion.util.parser.repository.ParseRepository;

import io.spring.guides.gs_producing_web_service.ParseRequest;
import io.spring.guides.gs_producing_web_service.ParseResponse;

@Endpoint
public class ParseEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private ParseRepository parseRepository;

	@Autowired
	public ParseEndpoint(ParseRepository countryRepository) {
		this.parseRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "parseRequest")
	@ResponsePayload
	public ParseResponse getCountry(@RequestPayload ParseRequest request) {
		ParseResponse response = new ParseResponse();
		response.setResult(parseRepository.findCountry(request.getBody()));

		return response;
	}
}
