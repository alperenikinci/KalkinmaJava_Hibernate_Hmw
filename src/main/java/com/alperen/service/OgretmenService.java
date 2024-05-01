package com.alperen.service;

import com.alperen.entity.Ogretmen;
import com.alperen.entity.Sinif;
import com.alperen.repository.OgretmenRepository;

import java.util.List;

public class OgretmenService {
    OgretmenRepository ogretmenRepository;
    public OgretmenService() {
        this.ogretmenRepository = new OgretmenRepository();
    }

    public Ogretmen saveOgretmen(Ogretmen ogretmen){
        ogretmen.setIseBaslamaTarihi(System.currentTimeMillis());
        return ogretmenRepository.save(ogretmen);
    }
    public List<Sinif> getSiniflarByOgretmenId(Long id){
        if(ogretmenRepository.findById(id).isEmpty()){
            return null;
        } else{
            return ogretmenRepository.getSiniflarByOgretmenId(id);
        }
    }
    public List<Ogretmen> findAll(){
        return ogretmenRepository.findAll();
    }
}
