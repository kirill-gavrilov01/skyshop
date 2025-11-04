package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {
    UUID getId();

    String getName();

    @JsonIgnore
    String getSearchTerm();

    String getContentType();
}
