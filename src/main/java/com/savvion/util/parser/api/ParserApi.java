package com.savvion.util.parser.api;

import br.redecorp.api.utildomain.parser.v1.ParserRequest;
import br.redecorp.api.utildomain.parser.v1.ParserResponse;
import br.redecorp.api.utildomain.parser.v1.TefHeaderType;

public interface ParserApi {
	
	public ParserResponse callMS(TefHeaderType tefHeaderType, ParserRequest parserRequest);

}
