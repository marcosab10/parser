package com.savvion.util.parser.api;

import br.redecorp.api.utildomain.parser.v1.ParserRequest;
import br.redecorp.api.utildomain.parser.v1.ParserResponse;

public interface ParserApi {
	
	public ParserResponse callMS(ParserRequest parserRequest);

}
