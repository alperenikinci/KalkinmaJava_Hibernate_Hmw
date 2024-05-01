package com.alperen.repository;

import com.alperen.entity.Ogretmen;

public class OgretmenRepository extends RepositoryManager<Ogretmen,Long>{
    public OgretmenRepository() {
        super(new Ogretmen());
    }
}
