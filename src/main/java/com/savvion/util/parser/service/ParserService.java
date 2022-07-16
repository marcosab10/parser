package com.savvion.util.parser.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ParserService {
	
	public String callMS(String body) {
		Assert.notNull(body, "The body  must not be null");
		return "Ok";
	}

}
