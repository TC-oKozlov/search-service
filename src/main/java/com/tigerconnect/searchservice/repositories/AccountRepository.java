package com.tigerconnect.searchservice.repositories;

import com.tigerconnect.searchservice.domain.Account;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

/**
 * https://docs.spring.io/spring-data/elasticsearch/docs/current-SNAPSHOT/reference/html/#elasticsearch.reactive.repositories
 */
public interface AccountRepository extends ReactiveSortingRepository<Account, String> {

    Flux<Account> findByName(String name);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"account.name\": \"?0\"}}]}}")
    Flux<Account> findByNameUsingCustomQuery(String name);
}
