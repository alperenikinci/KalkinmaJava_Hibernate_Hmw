package com.alperen.repository;

import com.alperen.entity.Deneme;

public class DenemeRepository extends RepositoryManager<Deneme,Long> {

    public DenemeRepository() {
        super(new Deneme());
    }
}
