package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.special.DiscountedProducts;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import org.skypro.skyshop.model.product.special.FixPriceProduct;
import org.skypro.skyshop.model.product.special.SimpleProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Stream.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() throws InterruptedException {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        fillStorage();
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    private void fillStorage() throws InterruptedException {
        Product apple = new SimpleProduct(UUID.randomUUID(),
                "Apple",
                50);
        Article articleApple = new Article(apple.getName() + " article",
                "delicious apple",
                UUID.randomUUID());
        products.put(apple.getId(), apple);
        articles.put(articleApple.getId(), articleApple);
        Product pen = new FixPriceProduct(UUID.randomUUID(), "Pen");
        Article articlePen = new Article(pen.getName() + " article",
                "beautiful pen",
                UUID.randomUUID());
        products.put(pen.getId(), pen);
        articles.put(articlePen.getId(), articlePen);
        Product orange = new SimpleProduct(UUID.randomUUID(),
                "Orange",
                70);
        Article articleOrange = new Article(orange.getName() + " article",
                "delicious orange",
                UUID.randomUUID());
        products.put(orange.getId(), orange);
        final Article put = articles.put(articleOrange.getId(), articleOrange);
        Product lamp = new DiscountedProducts(UUID.randomUUID(),
                "Lamp",
                520,
                25);
        Article articleLamp = new Article(lamp.getName() + " article",
                "light lamp",
                UUID.randomUUID());
        products.put(lamp.getId(), lamp);
        articles.put(articleLamp.getId(), articleLamp);
        Product fish = new DiscountedProducts(UUID.randomUUID(),
                "Fish",
                120,
                20);
        Article articleFish = new Article(fish.getName() + " article",
                "delicious fish",
                UUID.randomUUID());
        products.put(fish.getId(), fish);
        articles.put(articleFish.getId(), articleFish);
        Product meat = new SimpleProduct(UUID.randomUUID(),
                "Meat",
                220);
        Article articleMeat = new Article(meat.getName() + " article",
                "delicious meat",
                UUID.randomUUID());
        products.put(meat.getId(), meat);
        articles.put(articleMeat.getId(),
                articleMeat);
    }

    public ArrayList<Searchable> searchables() {
        final ArrayList<Searchable> collect;
        collect = concat(products.values().stream(), articles.values().stream()).collect(Collectors.toCollection(ArrayList<Searchable>::new));
        return collect;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public Collection<Searchable> getAllSearchables() {
        return null;
    }
}
