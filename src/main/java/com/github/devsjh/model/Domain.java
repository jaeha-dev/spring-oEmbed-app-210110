package com.github.devsjh.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Domain {

    private String name;
    private String endpoint;

    @Builder
    public Domain(String name, String endpoint) {
        this.name = name;
        this.endpoint = endpoint;
    }
}