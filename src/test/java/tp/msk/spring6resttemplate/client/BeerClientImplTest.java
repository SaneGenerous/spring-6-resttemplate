package tp.msk.spring6resttemplate.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;
import tp.msk.spring6resttemplate.model.BeerDTO;
import tp.msk.spring6resttemplate.model.BeerStyle;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {
    @Autowired
    BeerClientImpl beerClient;

    @Test
    void testDelete() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .beerStyle(BeerStyle.ALE)
                .ups("123425")
                .quantityOnHand(600)
                .build();
        BeerDTO savedDTO = beerClient.createBeer(newDTO);

        beerClient.deleteBeer(savedDTO.getId());

        assertThrows(HttpClientErrorException.class, () -> {
           //should error
           beerClient.getBeerById(savedDTO.getId());
        });
    }

    @Test
    void testUpdateBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs 2")
                .beerStyle(BeerStyle.ALE)
                .ups("123425")
                .quantityOnHand(600)
                .build();

        BeerDTO savedBeer = beerClient.createBeer(newDTO);

        final String newName = "Mango Bobs 3";
        savedBeer.setBeerName(newName);
        BeerDTO updatedBeer = beerClient.updateBeer(savedBeer);

        assertEquals(newName, updatedBeer.getBeerName());

    }

    @Test
    void testCreateBeer() {
        BeerDTO newDTO = BeerDTO.builder()
                .beerName("Mango Bobs")
                .price(new BigDecimal("10.99"))
                .beerStyle(BeerStyle.IPA)
                .ups("123456")
                .quantityOnHand(500)
                .build();

        BeerDTO savedDTO = beerClient.createBeer(newDTO);

        assertNotNull(savedDTO);
    }

    @Test
    void getBeerById() {
        Page<BeerDTO> beerDTOS = beerClient.listBeers();
        BeerDTO dto = beerDTOS.getContent().get(0);
        BeerDTO byId = beerClient.getBeerById(dto.getId());

        assertNotNull(byId);
    }

    @Test
    void listBeersNoBeerName() {
        beerClient.listBeers();
    }

    @Test
    void listBeers() {
        beerClient.listBeers("ALE", null, null, null, null);
    }
}