package org.skypro.skyshop.model.basket;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@SessionScope
public class ProductBasket {

    private final Map<UUID, Integer> products = new ConcurrentHashMap<>();

    public void addProduct(UUID productId) {
        products.merge(productId, 1, Integer::sum);
    }

    public Map<UUID, Integer> getAllProducts() {
        return Collections.unmodifiableMap(products);
    }
}