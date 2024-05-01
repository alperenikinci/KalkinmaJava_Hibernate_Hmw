package com.alperen.repository;

import com.alperen.entity.Sinif;

public class SinifRepository extends RepositoryManager<Sinif,Long>{
    public SinifRepository() {
        super(new Sinif());
    }
}
