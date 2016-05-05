package com.redhat.cml.CamelJunk;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class PaymentAggregator implements AggregationStrategy{

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if(oldExchange == null){
			return newExchange;
		}
		
		String newBody = newExchange.getIn().getBody(String.class);
		String oldBody = oldExchange.getIn().getBody(String.class);
		newBody=newBody.concat("\n"+oldBody);
		
		newExchange.getIn().setBody(newBody);
		
		return newExchange;
	}
	

}
