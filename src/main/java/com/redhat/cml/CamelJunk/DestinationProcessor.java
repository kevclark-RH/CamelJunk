package com.redhat.cml.CamelJunk;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class DestinationProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {

		Message mesg = exchange.getIn();
		String destination = "";
		if(((String) mesg.getHeader("CamelFileNameOnly")).contains("Thailand")){
			mesg.getHeaders().put("destination", "file:data/out/Tony-Thailand");
			mesg.getHeaders().put("logfile", "Thailand");
		}else if(((String) mesg.getHeader("CamelFileNameOnly")).contains("China")){
			mesg.getHeaders().put("destination", "file:data/out/Chris-China, file:data/out/Mary-SEAsia");
			mesg.getHeaders().put("logfile", "China");
		}else{
			mesg.getHeaders().put("destination", "file:data/out/misc-Interns");
			mesg.getHeaders().put("logfile", "misc");
		}
	}

	
}
