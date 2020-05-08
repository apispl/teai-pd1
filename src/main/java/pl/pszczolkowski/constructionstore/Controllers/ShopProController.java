package pl.pszczolkowski.constructionstore.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import pl.pszczolkowski.constructionstore.ShopService;

import java.math.BigDecimal;

@Controller
@Profile("Pro")
public class ShopProController {
    private ShopService shopService;

    @Value("${shop-vat.index}")
    private BigDecimal vatIndex;

    @Value("${shop-discount.value}")
    private BigDecimal discountValue;

    @Autowired
    private ShopProController(ShopService shopService){
        this.shopService = shopService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ourBasket(){
        shopService.calculateVat(vatIndex);
        shopService.calculateDiscount(discountValue);
    }
}
