package com.savvion.util.parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.savvion.util.parser.service.ParserService;

import br.redecorp.api.utildomain.parser.v1.ParserRequest;
import br.redecorp.api.utildomain.parser.v1.ParserResponse;



@Endpoint
public class ParseEndpoint {
	private static final String NAMESPACE_URI = "http://utildomain.api.redecorp.br/Parser/v1/";

	private ParserService parserService;

	@Autowired
	public ParseEndpoint(ParserService parserService) {
		this.parserService = parserService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "parserRequest")
	@ResponsePayload
	public ParserResponse parse(@RequestPayload ParserRequest request) {
		return parserService.callMS(request);
	}
}
