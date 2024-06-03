package springframework.springreactiveclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}