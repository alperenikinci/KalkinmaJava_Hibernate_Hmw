package com.alperen.service;

import com.alperen.entity.Ogrenci;
import com.alperen.repository.OgrenciRepository;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OgrenciService {
    OgrenciRepository ogrenciRepository;
    public OgrenciService() {
        this.ogrenciRepository = new OgrenciRepository();
    }

    public Ogrenci saveOgrenci(Ogrenci ogrenci){
        return ogrenciRepository.save(ogrenci);
    }
    public List<Ogrenci> dogumTarihineGoreOgrencileriBul(Long longTarih, boolean buyukMu) {
        return ogrenciRepository.dogumTarihineGoreOgrencileriBul(longTarih,buyukMu);
    }
}
