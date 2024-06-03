package springframework.springreactiveclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springframework.springreactiveclient.client.model.BeerDTO;

import java.util.Map;

public interface BeerClient {

    Flux<String> beerList();

    //if the structure of the data is not known yet, I can use a map for a while
    Flux<Map> beerMapList();

    //preferred over map since it has more functionality available while dealing with unknown JSON payload
    Flux<JsonNode> beerJsonList();

//deserializing JSON payload with Jackson into POJO, so it is going to be a Flux of beers
    Flux<BeerDTO> beerDtoList();

    Mono<BeerDTO> getBeerById(String id);

    Flux<BeerDTO> getBeerByStyle(String beerStyle);
}
