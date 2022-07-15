package com.savvion.util.parser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.savvion.util.parser.api.ParserApi;

@RestController
@RequestMapping("/transform")
public class ParserCoontroller implements ParserApi {
	
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> tranform() {
		
		String retorno = "{\"teste\": \"fff\"}";
		
		return ResponseEntity.ok().header(CONTENT_TYPE, APPLICATION_JSON).body(retorno);
	}

}
