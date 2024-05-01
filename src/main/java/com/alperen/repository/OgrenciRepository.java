package com.alperen.repository;

import com.alperen.entity.Ogrenci;

public class OgrenciRepository extends RepositoryManager<Ogrenci,Long>{
    public OgrenciRepository() {
        super(new Ogrenci());
    }
}
