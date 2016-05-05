package com.redhat.cml.CamelJunk;

import javax.jms.BytesMessage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Inverter implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		String payload = exchange.getIn().getBody(String.class);
		String newPayload = "";
		for(int x = payload.length()-1; x >= 0; x--){
			newPayload+=payload.charAt(x);
		}
		exchange.getIn().setBody(newPayload.getBytes());
		
	}

	
}
