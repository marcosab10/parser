package com.savvion.util.parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.savvion.util.parser.service.ParserService;

import io.spring.guides.gs_producing_web_service.ParseRequest;
import io.spring.guides.gs_producing_web_service.ParseResponse;

@Endpoint
public class ParseEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private ParserService parserService;

	@Autowired
	public ParseEndpoint(ParserService parserService) {
		this.parserService = parserService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "parseRequest")
	@ResponsePayload
	public ParseResponse parse(@RequestPayload ParseRequest request) {
		return parserService.callMS(request);
	}
}
