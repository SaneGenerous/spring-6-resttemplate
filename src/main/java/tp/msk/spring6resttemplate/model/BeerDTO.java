package tp.msk.spring6resttemplate.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BeerDTO {
    private UUID id;
    private String beerName;
    private Integer version;
    private BeerStyle beerStyle;
    private String ups;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
