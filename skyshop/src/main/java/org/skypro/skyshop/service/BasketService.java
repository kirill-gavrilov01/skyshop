package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public UserBasket getUserBasket() {
        List<BasketItem> items = new ArrayList<>();
        double total = 0.0;

        for (Map.Entry<Object, Object> entry : productBasket.getItems().entrySet()) {
            UUID productId = (UUID) entry.getKey();
            int quantity = (int) entry.getValue();

            var product = storageService.getProductById(productId).orElse(null);

            if (product != null) {
                BasketItem basketItem = new BasketItem(product, quantity);
                items.add(basketItem);
                total += quantity * product.getPrice();
            }
        }

        return new UserBasket(items, total);
    }

    public void addToBasket(UUID id) {

    }
}




