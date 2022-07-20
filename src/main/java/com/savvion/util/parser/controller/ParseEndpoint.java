package com.savvion.util.parser.controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import com.savvion.util.parser.api.ParserApi;

import br.redecorp.api.utildomain.parser.v1.ParserRequest;
import br.redecorp.api.utildomain.parser.v1.ParserResponse;
import br.redecorp.api.utildomain.parser.v1.TefHeaderType;



@Endpoint
public class ParseEndpoint {
	private static final String NAMESPACE_URI = "http://utildomain.api.redecorp.br/Parser/v1/";

	private ParserApi parserApi;

	@Autowired
	public ParseEndpoint(ParserApi parserApi) {
		this.parserApi = parserApi;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "parserRequest")
	@ResponsePayload
	public ParserResponse parse(@RequestPayload ParserRequest request, @SoapHeader(value = "{http://utildomain.api.redecorp.br/Parser/v1/}tefHeaderType") SoapHeaderElement soapHeader) {
		TefHeaderType tefHeaderType = unmarshallTefHeaderFromSoapHeader(soapHeader);
		return parserApi.callMS(tefHeaderType, request);
	}
	
	private TefHeaderType unmarshallTefHeaderFromSoapHeader(SoapHeaderElement header) {
		TefHeaderType security = null;
        try {

            JAXBContext context = JAXBContext.newInstance(TefHeaderType.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            security = (TefHeaderType) unmarshaller.unmarshal(header.getSource());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return security;
    }

}
