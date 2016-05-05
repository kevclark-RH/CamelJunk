package com.redhat.cml.CamelJunk;


import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

public class JavaRouter {

    private int counter;


    
    public static void main(String args[]) throws Exception{
    	
    	        CamelContext context = new DefaultCamelContext();
    	        
    	        // Define JMS component with ActiveMQ factory
    	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.useJmx=false");
    	        
    	        //MUST BE ADDED OR BROKER WON'T ACCEPT MESSAGE TYPES FOR SECURITY REASONS
    	        connectionFactory.setTrustAllPackages(true);
    	        
    	        
    	        
    	        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    	        
    	        
    	        
    	        //context.addRoutes(new JavaRouteBuilder());
    	        //context.addRoutes(new TransformRouteBuilder());
    	        //context.addRoutes(new ObjToQueueRouteBuilder());
    	        context.addRoutes(new BindyUnmarshallRouteBuilder());
    	        
    	        
    	        context.start();
    	        Thread.sleep(5000);
    	        
    	        ProducerTemplate producerTemplate = context.createProducerTemplate();
    	        
    	        //make classroom object
    	    	Classroom testClass = new Classroom();
    	    	testClass.setProfessor("Mr. Peters");
    	    	testClass.setStudents(new ArrayList(Arrays.asList("Mary", "Todd", "Elizabeth")));
    	    	testClass.setRoom_number(203);
    	    	
    	        producerTemplate.sendBody("direct:javaobject", testClass);
    	        	        
    	        //ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
    	        
    	       // Classroom ex_object = consumerTemplate.receiveBody("direct:javaobject2", Classroom.class);
    	    	
    	       // System.out.println("\n###\n####\n"+ex_object.toString()+"\n####\n###\n");
       	        
    	        Connection connection = connectionFactory.createConnection();
    	        connection.start();
    	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	        Destination queue = session.createQueue("orders");
    	        

    	        MessageConsumer consumer = session.createConsumer(queue);
    	        Message message = consumer.receive(1000);
    	        
    	            	        
    	        while (message != null) {
    	        	System.out.println("\n\t\tMessage type "+message.getClass().toString());
    	        	if (message instanceof BytesMessage) {
        	        	byte[] bytes = new byte[(int) ((BytesMessage) message).getBodyLength()];
    	        		((BytesMessage) message).readBytes(bytes);
    	        		System.out.println("**********Got byte message: \n\""+ new String(bytes)+"\"");
    	        	}
    	        	else if(message instanceof TextMessage){
    	        		System.out.println("**********Got text message: \n\""+ ((TextMessage)message).getText()+"\"");
        	        	
    	        	}
    	        	else if(message instanceof ObjectMessage){
    	        		System.out.println(message.toString());
    	        		
						Classroom class1 = ((Classroom)((ActiveMQObjectMessage)message).getObject());
						System.out.println("\n\nTO STRING: "+class1.toString()+"\n\n");
    	        		
    	        	}
    	        	message = consumer.receive(1000);
    	        }
    	        
    	        
    	        
    	        consumer.close();
    	        session.close();
    	        connection.close();
    	        
    	        
    	        context.stop();
    			System.out.println("JAVA ROUTE");
    	    
    }

}
