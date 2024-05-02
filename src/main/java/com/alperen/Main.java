package com.alperen;

import com.alperen.entity.*;
import com.alperen.service.OgrenciService;
import com.alperen.service.OgretmenService;
import com.alperen.service.SinifOgretmenService;
import com.alperen.service.SinifService;
import com.alperen.utility.DataGenerator;
import com.alperen.utility.enums.EBrans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    OgretmenService ogretmenService;
    OgrenciService ogrenciService;
    SinifService sinifService;
    SinifOgretmenService sinifOgretmenService;
    Scanner scanner = new Scanner(System.in);

    public Main() {
        this.ogretmenService = new OgretmenService();
        this.ogrenciService = new OgrenciService();
        this.sinifService = new SinifService();
        this.sinifOgretmenService = new SinifOgretmenService();
    }

    public static void main(String[] args) {
        Main main = new Main();
        DataGenerator dataGenerator = new DataGenerator();
//        dataGenerator.dataOlustur();
//        dataGenerator.siniflariOlustur();
//        dataGenerator.ogretmenleriOlustur();
//        dataGenerator.sinifOgretmenEsle();
        dataGenerator.ogrencileriOlustur();
//        System.out.println(main.ogretmenService.getSiniflarByOgretmenId(1L));
        main.uygulama();
    }

    public void uygulama() {
        menu();
    }

    public void menu() {
        int secim = 0;
        boolean exitStatus = true;
        while (exitStatus) {
            menuOptions();
            System.out.print("Lutfen seciminizi yapiniz: ");
            secim = Integer.parseInt(scanner.nextLine());
            switch (secim) {
                case 1: {
                    ogretmenOlustur();
                    break;
                }

                case 2: {
                    ogrenciOlustur();
                    break;
                }

                case 3: {
                    sinifOlustur();
                    break;
                }

                case 4: {
                    sinifaOgretmenAta();
                    break;
                }
                case 5:
                    dogumTarihineGoreOgrencileriBul();
                    break;
                case 0: {
                    System.err.println("Görüşmek üzere!!!");
                    exitStatus = false;
                    break;
                }
                default: {
                    System.err.println("Lutfen gecerli bir değer giriniz!!!");
                    break;
                }
            }
        }
    }

    private List<Ogrenci> dogumTarihineGoreOgrencileriBul() {
        Long longTarih = -1L;
        Boolean buyukMu =  null;
        while (true) {
            try {
                longTarih = getDate("Lutfen bir tarih giriniz (gg/AA/yyyy) : ");
                try {
                    System.out.println("1 - Bu tarihten once doganlari getirmek icin. ");
                    System.out.println("2 - Bu tarihten sonra doganlari getirmek icin. ");
                    System.out.print("Seciminiz : ");
                    int secim = Integer.parseInt(scanner.nextLine());
                    switch (secim){
                        case 1: {
                            buyukMu = false;
                            break;
                        }
                        case 2: {
                            buyukMu = true;
                            break;
                        }
                    }
                } catch (Exception e){
                    System.out.println("Lutfen gecerli bir secim yapiniz ( 1 / 2 ).");
                }

            } catch (Exception e) {
                System.out.println("Lutfen istenilen formatta tarihi giriniz (gg/AA/yyyy)");
            }
            List<Ogrenci> ogrenciList = ogrenciService.dogumTarihineGoreOgrencileriBul(longTarih,buyukMu);
            ogrenciService.dogumTarihineGoreOgrencileriBul(longTarih,buyukMu);
            ogrenciList.forEach(o -> System.out.println(o.getKisiselBilgiler().getIsim() + " " + o.getKisiselBilgiler().getSoyisim()));
            return ogrenciList;
        }
    }

    private void sinifaOgretmenAta() {
        List<Sinif> sinifList = new ArrayList<>();
        List<Ogretmen> ogretmenList = new ArrayList<>();
        try {
            sinifList = sinifService.findAll();
            ogretmenList = ogretmenService.findAll();
        } catch (Exception e) {
            System.out.println("Beklenmedik bir sorunla karsilasildi... Uygulama sonlandiriliyor.");
            System.exit(1);
        }
        if (!sinifList.isEmpty() && !ogretmenList.isEmpty()) {
            while (true) {
                try {
                    sinifList.forEach(sinif -> {
                        System.out.println("Sinif adi : " + sinif.getSinifAdi() + " ID -> " + sinif.getId());
                    });
                    System.out.print("Lutfen atama yapmak istediginiz sinifin id'sini giriniz : ");
                    Long sinifId = Long.parseLong(scanner.nextLine());
                    Optional<Sinif> sinifOptional = Optional.ofNullable(sinifList.stream().filter(x -> Objects.equals(x.getId(), sinifId)).collect(Collectors.toList()).get(0));
                    if (sinifOptional.isPresent()) {

                        while (true) {
                            try {
                                ogretmenList.forEach(ogretmen -> {
                                    System.out.println(
                                            "\nOgretmen adi : " + ogretmen.getKisiselBilgiler().getIsim() +
                                                    "\nOgretmen soyadi : " + ogretmen.getKisiselBilgiler().getSoyisim() +
                                                    "\nOgretmen brans : " + ogretmen.getBrans() +
                                                    "\nOgretmen ID :  " + ogretmen.getId() + "\n"
                                    );
                                });

                                System.out.print("Lutfen atama yapmak istediginiz ogretmenin id'sini giriniz : ");
                                Long ogretmenId = Long.parseLong(scanner.nextLine());
                                Optional<Ogretmen> ogretmenOptional = Optional.ofNullable(ogretmenList.stream().filter(x -> Objects.equals(x.getId(), ogretmenId)).collect(Collectors.toList()).get(0));
                                if (ogretmenOptional.isPresent()) {
                                    SinifOgretmen sinifOgretmen = SinifOgretmen.builder()
                                            .ogretmenId(ogretmenOptional.get().getId())
                                            .sinifId(sinifOptional.get().getId())
                                            .build();
                                    sinifOgretmenService.saveSinifOgretmen(sinifOgretmen);
                                    break;
                                }
                                //TODO sinifOgretmen ataması birden fazla kez yapılabiliyor. Bunun kontrol edilmesi lazım.

                            } catch (Exception e) {
                                System.out.println("Lutfen gecerli bir ogretmen id'si giriniz!!! ");
                            }
                        }
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Lutfen gecerli bir sinif id'si giriniz!!! ");
                }
            }
        } else {
            System.out.println("Sinif ya da ogretmen bilgisi bulunamıyor.\n");
        }
    }


    private void ogretmenOlustur() {
        System.out.println("\n############################");
        System.out.println("#### OGRETMEN OLUSTURMA ####");
        System.out.println("############################\n");


        Ogretmen ogretmen = Ogretmen.builder()
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim(getStringValue("Lutfen bir isim giriniz : "))
                        .soyisim(getStringValue("Lutfen bir soyisim giriniz : "))
                        .tcKimlik(getStringValue("Lutfen tcKimlik giriniz ( max 11 ) : "))
                        .build())
                .brans(bransSec("Lutfen bir brans giriniz : "))
                .build();
        ogretmenService.saveOgretmen(ogretmen);
    }

    private void ogrenciOlustur() {
        System.out.println("\n#############################");
        System.out.println("##### OGRENCI OLUSTURMA #####");
        System.out.println("#############################\n");

        Long sinifId = sinifSec("Lutfen ogrenciyi kaydetmek istediginiz sinifin id'sini giriniz, (Ana Sayfa Icin : 0 )  : ");
        if (sinifId != 0) {
        Ogrenci ogrenci1 = Ogrenci.builder()
                .sinifId(sinifId)
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim(getStringValue("Lutfen bir isim giriniz : "))
                        .soyisim(getStringValue("Lutfen bir soyisim giriniz : "))
                        .tcKimlik(getStringValue("Lutfen tcKimlik giriniz ( max 11 ) : "))
                        .build())
                .dogumTarihi(getDate("Lutfen dogum tarihinizi giriniz (gun/ay/yil) : "))
                .build();
        ogrenciService.saveOgrenci(ogrenci1);
    }
    }

    private Long sinifSec(String message) {
        while (true) {
            try {
                List<Sinif> sinifList = sinifService.findAll();
                sinifList.forEach(sinif -> {
                    System.out.println("Sinif adi : " + sinif.getSinifAdi() + " ID -> " + sinif.getId());
                });
                System.out.print(message);
                Long sinifId = Long.parseLong(scanner.nextLine());
                try {
                    if (sinifId == 0){
                       return sinifId;
                    }
                    Optional<Sinif> sinifOptional = Optional.ofNullable(sinifList.stream().filter(x -> Objects.equals(x.getId(), sinifId)).collect(Collectors.toList()).get(0));
                    if (sinifOptional.isPresent()) {
                        return sinifOptional.get().getId();
                    }

                } catch (Exception e) {
                    System.out.println("Lutfen gecerli bir sinif id'si giriniz!!! ");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Lutfen sadece sayi giriniz.");
            }
        }
    }

    private void sinifOlustur() {
        System.out.println("\n###########################");
        System.out.println("##### SINIF OLUSTURMA #####");
        System.out.println("###########################\n");

        Sinif sinif = Sinif.builder()
                .sinifAdi(getStringValue("Lutfen bir sinif adi giriniz : "))
                .build();
        sinifService.saveSinif(sinif);
    }


    public String getStringValue(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public Long getDate(String message) {
        while (true) {
            try {
                System.out.print(message);
                String tarihString = scanner.nextLine();

                // Tarih formatı
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


                // String tarihini Date nesnesine parse etme
                Date tarih = dateFormat.parse(tarihString);

                // Date nesnesini epoch milisaniyesine çevirme
                long epochMillisaniye = tarih.getTime();

                // Sonucu yazdırma
                return epochMillisaniye;
            } catch (ParseException e) {
                // ParseException hatası varsa
                System.out.println("\nLutfen istenilen formatta giriniz!!!");
            }
        }
    }

    public EBrans bransSec(String message) {
        while (true) {
            System.out.println("\n## Secilebilecek Branslar ##\n");
            for (int i = 0; i < EBrans.values().length; i++) {
                System.out.println(EBrans.values()[i]);
            }
            System.out.print(message);
            try {
                EBrans brans = EBrans.valueOf(scanner.nextLine());
                return brans;
            } catch (Exception e) {
                System.err.println("Lutfen gecerli bir deger giriniz !!! ");
            }
        }
    }

    public void menuOptions() {
        System.out.println("\n###########################");
        System.out.println("######## ANA SAYFA ########");
        System.out.println("###########################\n");
        System.out.println("1- Ogretmen Olustur");
        System.out.println("2- Ogrenci Olustur");
        System.out.println("3- Sinif Olustur");
        System.out.println("4- Sinifa Ogretmen Ata");
        System.out.println("5- Dogum Tarihine Gore Ogrencileri Bul");
        System.out.println("0- Cikis yap");
    }
}