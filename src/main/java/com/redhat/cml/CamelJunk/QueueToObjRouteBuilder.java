package com.redhat.cml.CamelJunk;

import org.apache.camel.builder.RouteBuilder;

public class QueueToObjRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("jms:queue:orders")
		.to("direct:javaobject");
	}
	

}
