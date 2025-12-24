package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.exceptions.InvalidArticleException;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private UUID id;
    private String title;
    private String text;


    public Article(UUID id, String title, String text) {
        if (id == null) {
            throw new InvalidArticleException("Идентификатор статьи не может быть пустым!");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidArticleException("Название статьи не может быть пустым");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new InvalidArticleException("Текст статьи не может быть пустым");
        }
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Article(String s, String deliciousApple, UUID uuid) {

    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return toString();
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title + ": " + text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }


    @Override
    public String toString() {
        return title + "\\n" + text;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Article)) return false;
        Article other = (Article) obj;
        return Objects.equals(this.id, other.id) &&
                Objects.equals(this.title, other.title) &&
                Objects.equals(this.text, other.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text);
    }
}
