package com.savvion.util.parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.savvion.util.parser.repository.TransformRepository;

import io.spring.guides.gs_producing_web_service.TransformRequest;
import io.spring.guides.gs_producing_web_service.TransformResponse;

@Endpoint
public class TransformEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private TransformRepository countryRepository;

	@Autowired
	public TransformEndpoint(TransformRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "transformRequest")
	@ResponsePayload
	public TransformResponse getCountry(@RequestPayload TransformRequest request) {
		TransformResponse response = new TransformResponse();
		response.setResult(countryRepository.findCountry(request.getBody()));

		return response;
	}
}
