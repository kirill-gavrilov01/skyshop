package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.product.Product;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public interface StorageService {
    Optional<Product> getProductById(UUID id);




    @Component
    class StorageServiceImpl implements StorageService {
        private final Map<UUID, Product> products;

        public StorageServiceImpl(Map<UUID, Product> products) {
            this.products = products;
        }

        @Override
        public Optional<Product> getProductById(UUID id) {
            return Optional.ofNullable(products.get(id));
        }
    }
}
