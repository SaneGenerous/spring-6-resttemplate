package tp.msk.spring6resttemplate.client;

import org.springframework.data.domain.Page;
import tp.msk.spring6resttemplate.model.BeerDTO;
import tp.msk.spring6resttemplate.model.BeerStyle;

import java.util.UUID;

public interface BeerClient {

    Page<BeerDTO> listBeers();
    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber,
                             Integer pageSize);
    BeerDTO getBeerById(UUID BeerId);

    BeerDTO createBeer(BeerDTO newDTO);

    BeerDTO updateBeer(BeerDTO beerDTO);

    void deleteBeer(UUID beerId);
}
