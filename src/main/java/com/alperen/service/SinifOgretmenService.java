package com.alperen.service;

import com.alperen.entity.Ogrenci;
import com.alperen.entity.SinifOgretmen;
import com.alperen.repository.SinifOgretmenRepository;

import java.util.List;

public class SinifOgretmenService {
    SinifOgretmenRepository sinifOgretmenRepository;
    public SinifOgretmenService() {
        this.sinifOgretmenRepository = new SinifOgretmenRepository();
    }
    public SinifOgretmen saveSinifOgretmen (SinifOgretmen sinifOgretmen){
        return sinifOgretmenRepository.save(sinifOgretmen);
    }

    public List<SinifOgretmen> findAll(){
        return sinifOgretmenRepository.findAll();
    }
}
