package com.alperen.service;

import com.alperen.entity.Ogrenci;
import com.alperen.entity.Sinif;
import com.alperen.repository.SinifRepository;

public class SinifService {
    SinifRepository sinifRepository;
    public SinifService() {
        this.sinifRepository = new SinifRepository();
    }

    public Sinif saveSinif(Sinif sinif){
        return sinifRepository.save(sinif);
    }
}
