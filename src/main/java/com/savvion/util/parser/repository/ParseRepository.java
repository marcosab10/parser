package com.savvion.util.parser.repository;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ParseRepository {

	public String findCountry(String body) {
		Assert.notNull(body, "The body  must not be null");
		return "Ok";
	}
}
