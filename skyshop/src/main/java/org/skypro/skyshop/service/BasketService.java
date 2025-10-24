package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.basket.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket basketComponent;
    private final StorageService storageService;

    public BasketService(ProductBasket basketComponent, StorageService storageService) {
        this.basketComponent = basketComponent;
        this.storageService = storageService;

    }

    public void addToBasket(UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (!product.isPresent()) {
            throw new IllegalArgumentException("Продукт с указанным ID не найден");
        }
        basketComponent.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> items = basketComponent.getAllProducts().entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Optional<Product> optProduct = storageService.getProductById(productId);


                    return optProduct.map(p -> new BasketItem(p, quantity)).orElse(null);
                })
                .filter(item -> item != null)
                .collect(Collectors.toList());


        double totalCost = items.stream()
                .mapToDouble(BasketService::applyAsDouble)
                .sum();

        return new UserBasket(items, totalCost);
    }

    private static double applyAsDouble(BasketItem basketItem) {

        return 0;
    }
}