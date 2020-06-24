package com.albertojarabo.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import com.albertojarabo.camel.model.DogResponse;
import com.albertojarabo.camel.processors.MyDogProcessor;

public class MyRouteBuilder extends RouteBuilder {

	private JacksonDataFormat dataFormat;

	public MyRouteBuilder() {
		dataFormat = new JacksonDataFormat(DogResponse.class);
	}

	public void configure() {
		from("timer:simple?period=1000").to("direct:getRestRandomDog").end();

		from("direct:getRestRandomDog").setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.to("https://dog.ceo/api/breeds/image/random").unmarshal(dataFormat).process(new MyDogProcessor())
				.end();
	}
}
