package springframework.springreactiveclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springframework.springreactiveclient.client.model.BeerDTO;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void beerList() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.beerList()
                .subscribe(response -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);
    }

    @Test
    void beerMapList() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.beerMapList()
                .subscribe(response -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);
    }

    @Test
    void beerJsonList() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.beerJsonList()
                .subscribe(response -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);
    }

    @Test
    void beerDtoList() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.beerDtoList()
                .subscribe(response -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);
    }

    @Test
    void getBeerById() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.beerDtoList()
                .flatMap(dto -> beerClient.getBeerById(dto.getId()))
                .subscribe(byIdDto -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);
    }

    @Test
    void getBeerByStyle() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.getBeerByStyle("tocene")
                .subscribe(response -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);

    }

    @Test
    void createBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("No idea")
                .beerStyle("creative style")
                .price(new BigDecimal("0.99"))
                .quantityOnHand(123)
                .upc("700700")
                .build();

        beerClient.createBeer(beerDTO)
                .subscribe(response -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);
    }

    @Test
    void updateBeer() {
        final String UPDATED_NAME = "Updated Name";
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.beerDtoList()
                .next()
                .doOnNext(beerDTO -> beerDTO.setBeerName(UPDATED_NAME))
                .flatMap(dto -> beerClient.updateBeer(dto))
                .subscribe(byIdDTO -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);
    }

    @Test
    void patchBeer() {
        final String PATCHED_NAME = "Patched Name";
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.beerDtoList()
                .next()
                .doOnNext(beerDTO -> beerDTO.setBeerName(PATCHED_NAME))
                .flatMap(dto -> beerClient.patchBeer(dto))
                .subscribe(byIdDTO -> atomicBoolean.set(true));
        await().untilTrue(atomicBoolean);
    }

    @Test
    void deleteBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.beerDtoList()
                .next()
                .flatMap(beerDTO -> beerClient.deleteBeer(beerDTO))
                .doOnSuccess(response -> atomicBoolean.set(true))
                .subscribe();
        await().untilTrue(atomicBoolean);
    }
}