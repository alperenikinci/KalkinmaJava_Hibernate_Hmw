package com.alperen.utility;

import com.alperen.entity.*;
import com.alperen.service.OgrenciService;
import com.alperen.service.OgretmenService;
import com.alperen.service.SinifOgretmenService;
import com.alperen.service.SinifService;
import com.alperen.utility.enums.EBrans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main_chatGPT_refactored {

    private final OgretmenService ogretmenService;
    private final OgrenciService ogrenciService;
    private final SinifService sinifService;
    private final SinifOgretmenService sinifOgretmenService;
    private final Scanner scanner;

    public Main_chatGPT_refactored() {
        this.ogretmenService = new OgretmenService();
        this.ogrenciService = new OgrenciService();
        this.sinifService = new SinifService();
        this.sinifOgretmenService = new SinifOgretmenService();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main_chatGPT_refactored mainChatGPTrefactored = new Main_chatGPT_refactored();
        mainChatGPTrefactored.uygulama();
    }

    public void uygulama() {
        menu();
    }

    public void menu() {
        int secim;
        boolean exitStatus = true;
        while (exitStatus) {
            menuOptions();
            System.out.print("Lutfen seciminizi yapiniz: ");
            secim = Integer.parseInt(scanner.nextLine());
            switch (secim) {
                case 1:
                    ogretmenOlustur();
                    break;
                case 2:
                    ogrenciOlustur();
                    break;
                case 3:
                    sinifOlustur();
                    break;
                case 4:
                    sinifaOgretmenAta();
                    break;
                case 0:
                    System.err.println("Görüşmek üzere!!!");
                    exitStatus = false;
                    break;
                default:
                    System.err.println("Lutfen gecerli bir değer giriniz!!!");
                    break;
            }
        }
    }

    private void sinifaOgretmenAta() {
        List<Sinif> sinifList;
        List<Ogretmen> ogretmenList;
        try {
            sinifList = sinifService.findAll();
            ogretmenList = ogretmenService.findAll();
        } catch (Exception e) {
            System.out.println("Beklenmedik bir sorunla karsilasildi... Uygulama sonlandiriliyor.");
            return;
        }
        if (sinifList.isEmpty() || ogretmenList.isEmpty()) {
            System.out.println("Sinif ya da ogretmen bilgisi bulunamıyor.\n");
            return;
        }

        while (true) {
            try {
                sinifList.forEach(sinif -> {
                    System.out.println("Sinif adi : " + sinif.getSinifAdi() + " ID -> " + sinif.getId());
                });
                System.out.print("Lutfen atama yapmak istediginiz sinifin id'sini giriniz : ");
                Long sinifId = Long.parseLong(scanner.nextLine());
                Optional<Sinif> sinifOptional = sinifList.stream().filter(x -> Objects.equals(x.getId(), sinifId)).findFirst();
                if (sinifOptional.isPresent()) {
                    while (true) {
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
                        Optional<Ogretmen> ogretmenOptional = ogretmenList.stream().filter(x -> Objects.equals(x.getId(), ogretmenId)).findFirst();
                        if (ogretmenOptional.isPresent()) {
                            SinifOgretmen sinifOgretmen = SinifOgretmen.builder()
                                    .ogretmenId(ogretmenOptional.get().getId())
                                    .sinifId(sinifOptional.get().getId())
                                    .build();
                            sinifOgretmenService.saveSinifOgretmen(sinifOgretmen);
                            break;
                        }
                        System.out.println("Lutfen gecerli bir ogretmen id'si giriniz!!! ");
                    }
                    break;
                }
                System.out.println("Lutfen gecerli bir sinif id'si giriniz!!! ");
            } catch (Exception e) {
                System.out.println("Lutfen gecerli bir deger giriniz!!! ");
            }
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

        Ogrenci ogrenci1 = Ogrenci.builder()
                .sinifId(sinifSec("Lutfen ogrenciyi kaydetmek istediginiz sinifin id'sini giriniz : "))
                .kisiselBilgiler(KisiselBilgiler.builder()
                        .isim(getStringValue("Lutfen bir isim giriniz : "))
                        .soyisim(getStringValue("Lutfen bir soyisim giriniz : "))
                        .tcKimlik(getStringValue("Lutfen tcKimlik giriniz ( max 11 ) : "))
                        .build())
                .dogumTarihi(getBirthDate("Lutfen dogum tarihinizi giriniz (gun/ay/yil) : "))
                .build();
        ogrenciService.saveOgrenci(ogrenci1);
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
                Optional<Sinif> sinifOptional = sinifList.stream().filter(x -> Objects.equals(x.getId(), sinifId)).findFirst();
                if (sinifOptional.isPresent()) {
                    return sinifOptional.get().getId();
                }
                System.out.println("Lutfen gecerli bir sinif id'si giriniz!!! ");
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

    private String getStringValue(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private Long getBirthDate(String message) {
        while (true) {
            try {
                System.out.print(message);
                String tarihString = scanner.nextLine();

                // Tarih formatı
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                // String tarihini Date nesnesine parse etme
                Date tarih = dateFormat.parse(tarihString);

                // Date nesnesini epoch milisaniyesine çevirme
                return tarih.getTime();
            } catch (ParseException e) {
                // ParseException hatası varsa
                System.out.println("\nLutfen istenilen formatta giriniz!!!");
            }
        }
    }

    private EBrans bransSec(String message) {
        while (true) {
            System.out.println("\n## Secilebilecek Branslar ##\n");
            for (EBrans brans : EBrans.values()) {
                System.out.println(brans);
            }
            System.out.print(message);
            try {
                return EBrans.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.err.println("Lutfen gecerli bir deger giriniz !!! ");
            }
        }
    }

    private void menuOptions() {
        System.out.println("1- Ogretmen Olustur");
        System.out.println("2- Ogrenci Olustur");
        System.out.println("3- Sinif Olustur");
        System.out.println("4- Sinifa Ogretmen Ata");
        System.out.println("5- Dogum Tarihine Gore Ogrenci Getir.");
        System.out.println("0- Cikis Yap");
    }
}
