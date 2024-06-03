package springframework.springreactiveclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springframework.springreactiveclient.client.model.BeerDTO;

import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {

    public static final String BEER_PATH = "/api/v3/beer";
    public static final String BEER_PATH_ID = "/{beerId}";
    private final WebClient webClient;

    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Flux<String> beerList() {
        return webClient.get()
                .uri(BEER_PATH)
                .retrieve()
                .bodyToFlux(String.class);
    }

    @Override
    public Flux<Map> beerMapList() {
        return webClient.get()
                .uri(BEER_PATH)
                .retrieve()
                .bodyToFlux(Map.class);
    }

    @Override
    public Flux<JsonNode> beerJsonList() {
        return webClient.get()
                .uri(BEER_PATH)
                .retrieve()
                .bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<BeerDTO> beerDtoList() {
        return webClient.get()
                .uri(BEER_PATH)
                .retrieve()
                .bodyToFlux(BeerDTO.class);
    }

    @Override
    public Mono<BeerDTO> getBeerById(String id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BEER_PATH_ID)
                        .build(id))
                .retrieve()
                .bodyToMono(BeerDTO.class);
    }

    @Override
    public Flux<BeerDTO> getBeerByStyle(String beerStyle) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BEER_PATH)
                        .queryParam("beerStyle", beerStyle)
                        .build())
                .retrieve()
                .bodyToFlux(BeerDTO.class);
    }
}
