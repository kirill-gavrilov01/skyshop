package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.exceptions.InvalidProductException;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Product implements Searchable {

    private UUID id;
    private String name;

    public Product(UUID id, String name) {
        if (id == null) {
            throw new InvalidProductException("Идентификатор продукта не может быть пустым!");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidProductException("Наименование товара не может быть пустым или отсутствовать!");
        }
        this.id = id;
        this.name = name;
    }

    public Product(long l, String productName, double fixedPrice) {
        super();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return 0;
    }

    public boolean isSpecial() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product that = (Product) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}