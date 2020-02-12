package com.tigerconnect.searchservice.controllers;

import com.tigerconnect.searchservice.domain.Account;
import com.tigerconnect.searchservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * https://docs.spring.io/spring-data/elasticsearch/docs/current-SNAPSHOT/reference/html/#elasticsearch.reactive.template.usage
 */
@RestController
public class SearchController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ReactiveElasticsearchTemplate esTemplate;

    @GetMapping("/simpleSearch")
    public Flux<Account> simpleSearch(@RequestParam String name) {
        return accountRepository.findByName(name);

    }

    @GetMapping("/createIndex")
    public String createIndex(@RequestParam String name) {
        return "create index results will be here";
    }

        @GetMapping("/reactiveIndex")
    public String reactiveIndex(
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String status,
            @RequestParam String org,
            @RequestParam String token
            ) {
            esTemplate.save(new Account(status, org, name, token, type))
                    .doOnNext(System.out::println)
                    .subscribe();

            return "index results will be here";
    }

    private String randomID() {
        return UUID.randomUUID().toString();
    }
}


