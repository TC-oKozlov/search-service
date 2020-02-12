package com.tigerconnect.searchservice.camel.routes;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class PulsarConsumerRouteBuilder extends RouteBuilder {


    /**
     * https://camel.apache.org/components/latest/pulsar-component.html
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        from("pulsar:persistent://public/default/test-topic4" +
                "?pulsarClient=#pulsarClient&numberOfConsumers=1&subscriptionType=Shared&subscriptionName=first-subscription&consumerQueueSize=1&consumerName=camel-consumer&allowManualAcknowledgement=false&ackTimeoutMillis=1000")
                .process((exch) -> {
                    System.out.println("===== GOT MSG =====");
                    Message msg = exch.getMessage();
                    System.out.println(msg);
                    byte[] b = (byte[]) msg.getBody();
                    System.out.println("BODY:" + new String(b, Charset.forName("UTF-8")));
                    System.out.println("HEADERS:" + msg.getHeaders());
                });
    }
}
