package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket basket;
    private final StorageService storage;

    @Autowired
    public BasketService(ProductBasket basket, StorageService storage) {
        this.basket = basket;
        this.storage = storage;
    }

    public void addProduct(UUID id) {
        Optional<Product> product = storage.getProductById(id);
        if (!product.isPresent()) {
            throw new IllegalStateException("Продукт не найден: " + id);
        }
        basket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        // Получаем содержимое корзины
        Map<UUID, Integer> productsInBasket = basket.getProductBasket();

        // Проверяем наличие каждого продукта и формируем итоговые элементы корзины
        List<BasketItem> items = productsInBasket.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();

                    Optional<Product> productOptional = storage.getProductById(productId);
                    if (!productOptional.isPresent()) {
                        throw new IllegalStateException("Продукт не найден: " + productId);
                    }

                    Product product = productOptional.get();
                    return new BasketItem(product, quantity);
                }).collect(Collectors.toList());

        return new UserBasket(items);
    }
}

