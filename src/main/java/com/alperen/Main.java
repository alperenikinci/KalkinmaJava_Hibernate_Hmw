package com.alperen;

import com.alperen.entity.*;
import com.alperen.repository.SinifRepository;
import com.alperen.service.OgrenciService;
import com.alperen.service.OgretmenService;
import com.alperen.service.SinifOgretmenService;
import com.alperen.service.SinifService;
import com.alperen.utility.enums.EBrans;

public class Main {
    static SinifService sinifService = new SinifService();
    static OgretmenService ogretmenService = new OgretmenService();
    static OgrenciService ogrenciService = new OgrenciService();
    static SinifOgretmenService sinifOgretmenService = new SinifOgretmenService();

//    public Main() {
//        this.sinifService = new SinifService();
//        this.ogretmenService = new OgretmenService();
//        this.ogrenciService = new OgrenciService();
//        this.sinifOgretmenService= new SinifOgretmenService();
//    }

    public static void main(String[] args) {
//        Main main = new Main();
        Sinif sinif = Sinif.builder()
                .sinifAdi("10-A")
                .build();
        sinifService.saveSinif(sinif);

        Ogretmen ogretmen1 = Ogretmen.builder()
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Alperen")
                        .soyisim("İkinci")
                        .tcKimlik("12341234")
                        .build())
                .brans(EBrans.FIZIK)
                .build();
        Ogretmen ogretmen2 = Ogretmen.builder()
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Muhammet Ali")
                        .soyisim("Kaya")
                        .tcKimlik("1234125")
                        .build())
                .brans(EBrans.MATEMATIK)
                .build();
        ogretmenService.saveOgretmen(ogretmen1);
        ogretmenService.saveOgretmen(ogretmen2);
        SinifOgretmen sinifOgretmen1 = SinifOgretmen.builder()
                .sinifId(sinif.getId())
                .ogretmenId(ogretmen1.getId())
                .build();
        SinifOgretmen sinifOgretmen2 = SinifOgretmen.builder()
                .sinifId(sinif.getId())
                .ogretmenId(ogretmen2.getId())
                .build();
        sinifOgretmenService.saveSinifOgretmen(sinifOgretmen1);
        sinifOgretmenService.saveSinifOgretmen(sinifOgretmen2);


        Ogrenci ogrenci1 = Ogrenci.builder()
                .sinifId(sinif.getId())
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Yusuf")
                        .soyisim("Akbaş")
                        .tcKimlik("12345678910")
                        .build())
                .dogumTarihi(123412356L)
                .build();
        Ogrenci ogrenci2 = Ogrenci.builder()
                .sinifId(sinif.getId())
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Rüveyda")
                        .soyisim("Yılmaz")
                        .tcKimlik("31265789")
                        .build())
                .dogumTarihi(46547489L)
                .build();
        ogrenciService.saveOgrenci(ogrenci1);
        ogrenciService.saveOgrenci(ogrenci2);
    }
}