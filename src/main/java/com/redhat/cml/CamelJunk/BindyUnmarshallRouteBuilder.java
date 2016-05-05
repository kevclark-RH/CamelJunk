package com.redhat.cml.CamelJunk;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;

public class BindyUnmarshallRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(Classroom.class);
		
		from("direct:javaobject")
		.marshal(bindy)
		.to("jms:queue:orders");
	}
	

}
