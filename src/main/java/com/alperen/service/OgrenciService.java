package com.alperen.service;

import com.alperen.entity.Ogrenci;
import com.alperen.repository.OgrenciRepository;

public class OgrenciService {
    OgrenciRepository ogrenciRepository;
    public OgrenciService() {
        this.ogrenciRepository = new OgrenciRepository();
    }

    public Ogrenci saveOgrenci(Ogrenci ogrenci){
        return ogrenciRepository.save(ogrenci);
    }
}
