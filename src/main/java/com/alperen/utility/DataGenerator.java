package com.alperen.utility;

import com.alperen.entity.*;
import com.alperen.service.OgrenciService;
import com.alperen.service.OgretmenService;
import com.alperen.service.SinifOgretmenService;
import com.alperen.service.SinifService;
import com.alperen.utility.enums.EBrans;
import jakarta.transaction.Transactional;

public class DataGenerator {
    //TODO Initialization metot içinde yapıldığında veri üretilmiyor neden?
    SinifService sinifService;
    OgretmenService ogretmenService;
    SinifOgretmenService sinifOgretmenService;
    OgrenciService ogrenciService;

    public DataGenerator() {
        this.sinifService = new SinifService();
        this.ogretmenService = new OgretmenService();
        this.sinifOgretmenService = new SinifOgretmenService();
        this.ogrenciService = new OgrenciService();
    }

    public void dataOlustur() {
        siniflariOlustur();
        ogretmenleriOlustur();
        sinifOgretmenEsle();
        ogrencileriOlustur();
    }

    public void siniflariOlustur() {
//        SinifService sinifService = new SinifService();
        Sinif sinif = Sinif.builder()
                .sinifAdi("9-A")
                .build();
        sinifService.saveSinif(sinif);
        Sinif sinif2 = Sinif.builder()
                .sinifAdi("10-A")
                .build();
        sinifService.saveSinif(sinif2);
        Sinif sinif3 = Sinif.builder()
                .sinifAdi("11-A")
                .build();
        sinifService.saveSinif(sinif3);
    }

    public  void ogretmenleriOlustur() {
//        OgretmenService ogretmenService = new OgretmenService();
        Ogretmen ogretmen1 = Ogretmen.builder()
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Alperen")
                        .soyisim("Ikinci")
                        .tcKimlik("12341234")
                        .build())
                .brans(EBrans.FIZIK)
                .build();
        ogretmenService.saveOgretmen(ogretmen1);

        Ogretmen ogretmen2 = Ogretmen.builder()
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Muhammet Ali")
                        .soyisim("Kaya")
                        .tcKimlik("1234125")
                        .build())
                .brans(EBrans.MATEMATIK)
                .build();
        ogretmenService.saveOgretmen(ogretmen2);

        Ogretmen ogretmen3 = Ogretmen.builder()
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Mehmet")
                        .soyisim("Gocmen")
                        .tcKimlik("124125")
                        .build())
                .brans(EBrans.MATEMATIK)
                .build();
        ogretmenService.saveOgretmen(ogretmen3);
        Ogretmen ogretmen4 = Ogretmen.builder()
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Cagri")
                        .soyisim("Turkmen")
                        .tcKimlik("12124125")
                        .build())
                .brans(EBrans.FIZIK)
                .build();
        ogretmenService.saveOgretmen(ogretmen4);
    }

    public  void sinifOgretmenEsle() {
//        SinifOgretmenService sinifOgretmenService = new SinifOgretmenService();
        SinifOgretmen sinifOgretmen1 = SinifOgretmen.builder()
                .sinifId(1L)
                .ogretmenId(1L)
                .build();
        sinifOgretmenService.saveSinifOgretmen(sinifOgretmen1);

        SinifOgretmen sinifOgretmen2 = SinifOgretmen.builder()
                .sinifId(1L)
                .ogretmenId(2L)
                .build();
        sinifOgretmenService.saveSinifOgretmen(sinifOgretmen2);

        SinifOgretmen sinifOgretmen3 = SinifOgretmen.builder()
                .sinifId(2L)
                .ogretmenId(1L)
                .build();
        sinifOgretmenService.saveSinifOgretmen(sinifOgretmen3);
        SinifOgretmen sinifOgretmen4 = SinifOgretmen.builder()
                .sinifId(2L)
                .ogretmenId(3L)
                .build();
        sinifOgretmenService.saveSinifOgretmen(sinifOgretmen4);

        SinifOgretmen sinifOgretmen5 = SinifOgretmen.builder()
                .sinifId(3L)
                .ogretmenId(3L)
                .build();
        sinifOgretmenService.saveSinifOgretmen(sinifOgretmen5);
        SinifOgretmen sinifOgretmen6 = SinifOgretmen.builder()
                .sinifId(3L)
                .ogretmenId(4L)
                .build();
        sinifOgretmenService.saveSinifOgretmen(sinifOgretmen6);
    }

    public void ogrencileriOlustur() {
//        OgrenciService ogrenciService = new OgrenciService();
        Ogrenci ogrenci1 = Ogrenci.builder()
                .sinifId(1L)
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Yusuf")
                        .soyisim("Akbas")
                        .tcKimlik("12345678910")
                        .build())
                .dogumTarihi(123412356L)
                .build();
        ogrenciService.saveOgrenci(ogrenci1);

        Ogrenci ogrenci2 = Ogrenci.builder()
                .sinifId(1L)
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Ruveyda")
                        .soyisim("Yilmaz")
                        .tcKimlik("31265789")
                        .build())
                .dogumTarihi(46547489L)
                .build();
        ogrenciService.saveOgrenci(ogrenci2);

        Ogrenci ogrenci3 = Ogrenci.builder()
                .sinifId(1L)
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim("Aziz")
                        .soyisim("Bilgin")
                        .tcKimlik("123")
                        .build())
                .dogumTarihi(4547489L)
                .build();
        ogrenciService.saveOgrenci(ogrenci3);

        // Sınıf 2 için öğrencilerin oluşturulması
        for (int i = 0; i < 3 + (int) (Math.random() * 3); i++) {
            Ogrenci ogrenci = Ogrenci.builder()
                    .sinifId(2L)
                    .kisiselBilgiler(KisiselBilgiler.builder()
                            .isim("Isim" + (i + 1))
                            .soyisim("Soyisim" + (i + 1))
                            .tcKimlik("123456789" + (i + 1))
                            .build())
                    .dogumTarihi(123412356L)
                    .build();
            ogrenciService.saveOgrenci(ogrenci);
        }

        // Sınıf 3 için öğrencilerin oluşturulması
        for (int i = 0; i < 3 + (int) (Math.random() * 3); i++) {
            Ogrenci ogrenci = Ogrenci.builder()
                    .sinifId(3L)
                    .kisiselBilgiler(KisiselBilgiler.builder()
                            .isim("Isim" + (i + 1))
                            .soyisim("Soyisim" + (i + 1))
                            .tcKimlik("987654321" + (i + 1))
                            .build())
                    .dogumTarihi(123412356L)
                    .build();
            ogrenciService.saveOgrenci(ogrenci);
        }

    }
}

