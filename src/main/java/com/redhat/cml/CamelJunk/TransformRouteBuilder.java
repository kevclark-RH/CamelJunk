package com.redhat.cml.CamelJunk;

import org.apache.camel.builder.RouteBuilder;

public class TransformRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:data/in")
		.transform(body().regexReplaceAll("<Payments>", "<Listings>"))
		.to("file:data/out/transformed");
	}
	

}
