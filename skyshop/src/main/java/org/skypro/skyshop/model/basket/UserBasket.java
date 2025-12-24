package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> basketItems;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int getTotal() {
        return basketItems.stream()
                .mapToInt(basketItem -> (int) basketItem.getTotalPrice())
                .sum();
    }
}
