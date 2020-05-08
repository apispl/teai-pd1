package pl.pszczolkowski.constructionstore.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import pl.pszczolkowski.constructionstore.Entity.Product;
import pl.pszczolkowski.constructionstore.ShopService;

import java.math.BigDecimal;

@Controller
public class ShopController {

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startShop() {
        shopService.addProduct(new Product("Floor", new BigDecimal("200")));
        shopService.getBasket();
        System.out.println("Wartość produktów w koszyku: " + shopService.sumBasket());
    }
}
