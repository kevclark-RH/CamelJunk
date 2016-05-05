package com.redhat.cml.CamelJunk;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRouter {

	public static void main(String[] args) throws Exception{
		
		AbstractXmlApplicationContext springCtx
			= new ClassPathXmlApplicationContext("META-INF/camelContext.xml");
		springCtx.start();
		Thread.sleep(5000);
		springCtx.stop();
		springCtx.destroy();
		System.out.println("SPRING ROUTE");
		
		
	}
}
