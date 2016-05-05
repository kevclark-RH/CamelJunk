package com.redhat.cml.CamelJunk;

import org.apache.camel.builder.RouteBuilder;

public class ObjToQueueRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:javaobject")
		.to("jms:queue:orders");
	}
	

}
