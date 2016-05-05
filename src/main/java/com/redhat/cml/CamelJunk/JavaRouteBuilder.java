package com.redhat.cml.CamelJunk;

import org.apache.camel.builder.RouteBuilder;

public class JavaRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:/home/kevclark/jbworkspace/CamelJunk/data/in?noop=true")
		.choice()
			.when(header("CamelFileNameOnly").endsWith(".txt"))
				.split(xpath("/Payments/Payment"))
				.process(new AddDestProcessor())
					.to("jms:queue:orders").endChoice()
			.when(header("CamelFileNameOnly").endsWith(".xml"))		
				.process(new DestinationProcessor())
				.recipientList(header("destination"))
				.wireTap("file:data/out/log/${in.header.logfile}")
					.split(xpath("/Payments/Payment"))
						.to("jms:queue:orders").endChoice()
		.end();
	
	}
}
