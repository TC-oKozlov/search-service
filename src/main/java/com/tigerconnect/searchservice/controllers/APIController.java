package com.tigerconnect.searchservice.controllers;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://docs.spring.io/spring-data/elasticsearch/docs/current-SNAPSHOT/reference/html/#elasticsearch.reactive.template.usage
 */
@RestController
public class APIController {

    @Autowired
    CamelContext camelContext;




    @GetMapping("/directStart")
    public String simpleSearch() {
        camelContext.createProducerTemplate().sendBody("direct:start", "directStart from API");
        return "Done!";
    }


}


