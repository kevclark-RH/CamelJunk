package com.redhat.cml.CamelJunk;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class AddDestProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		exchange.getIn().getHeaders().put("destination", "txt_queue");

	}

}
