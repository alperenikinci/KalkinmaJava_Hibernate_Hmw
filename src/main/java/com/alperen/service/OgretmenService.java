package com.alperen.service;

import com.alperen.entity.Ogretmen;
import com.alperen.entity.Sinif;
import com.alperen.repository.OgretmenRepository;
public class OgretmenService {
    OgretmenRepository ogretmenRepository;
    public OgretmenService() {
        this.ogretmenRepository = new OgretmenRepository();
    }

    public Ogretmen saveOgretmen(Ogretmen ogretmen){

        ogretmen.setIseBaslamaTarihi(System.currentTimeMillis());
        return ogretmenRepository.save(ogretmen);
    }
}
