package com.alperen.repository;

import com.alperen.entity.Sinif;
import com.alperen.entity.SinifOgretmen;

public class SinifOgretmenRepository extends RepositoryManager<SinifOgretmen,Long>{
    public SinifOgretmenRepository() {
        super(new SinifOgretmen());
    }
}
