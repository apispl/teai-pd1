package pl.pszczolkowski.constructionstore;

import org.springframework.stereotype.Service;
import pl.pszczolkowski.constructionstore.Entity.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    private List<Product> basket;

    public ShopService(){
        basket = new ArrayList<>();
        basket.add(new Product("Wood", new BigDecimal("79.99")));
        basket.add(new Product("Iron", new BigDecimal("69.99")));
        basket.add(new Product("Floor", new BigDecimal("139.99")));
        basket.add(new Product("Door", new BigDecimal("300.00")));
        basket.add(new Product("Sink", new BigDecimal("54.99")));
    }

    public void addProduct(Product product){
        basket.add(product);
        System.out.println("Produkt: " + product.getName() + " - dodany! ");
    }

    public List<Product> getBasket(){
        System.out.println("Produkty w koszyku: ");
        basket.forEach(name -> System.out.println(name.getName() + " - " + name.getPrice()));
        return basket;
    }

    public BigDecimal sumBasket(){
        return basket.stream()
                .map(Product::getPrice)
                .reduce(new BigDecimal("0") , BigDecimal::add);
    }

    public BigDecimal calculateVat(BigDecimal vat){
        System.out.println("Podatek VAT: " + vat.subtract(new BigDecimal("1")).multiply(new BigDecimal("100"))  + "%");
        System.out.println("Cena z podatkiem VAT: " + sumBasket().multiply(vat));
        return sumBasket().multiply(vat);
    }

    public BigDecimal calculateDiscount(BigDecimal dis){
        System.out.println("Procent zniżki: " + dis.subtract(new BigDecimal("1")).abs().multiply(new BigDecimal("100")) + "%");
        System.out.println("Cena z podatkiem VAT: " + sumBasket().multiply(dis));
        return sumBasket().multiply(dis);
    }



}
