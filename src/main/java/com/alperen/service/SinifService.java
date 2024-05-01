package com.alperen.service;

import com.alperen.entity.Ogrenci;
import com.alperen.entity.Sinif;
import com.alperen.repository.SinifRepository;

import java.util.List;

public class SinifService {
    SinifRepository sinifRepository;
    public SinifService() {
        this.sinifRepository = new SinifRepository();
    }

    public Sinif saveSinif(Sinif sinif){
        return sinifRepository.save(sinif);
    }
    public List<Sinif> findAll(){
        return sinifRepository.findAll();
    }
}
