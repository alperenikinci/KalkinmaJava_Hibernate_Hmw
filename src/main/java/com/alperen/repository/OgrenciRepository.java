package com.alperen.repository;

import com.alperen.entity.Ogrenci;
import com.alperen.entity.Sinif;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

public class OgrenciRepository extends RepositoryManager<Ogrenci,Long>{
    EntityManager entityManager;
    public OgrenciRepository() {
        super(new Ogrenci());
    }

    public List<Ogrenci> dogumTarihineGoreOgrencileriBul(Long longTarih, boolean buyukMu) {
        String operator = buyukMu ? ">" : "<";
        String nativeQuery = "SELECT o.* " +
                "FROM tbl_ogrenci o " +
                "WHERE o.dogum_tarihi " + operator + " :longTarih";
        TypedQuery<Ogrenci> query = (TypedQuery<Ogrenci>) getEm().createNativeQuery(nativeQuery, Ogrenci.class);
        query.setParameter("longTarih", longTarih);
        return query.getResultList();
    }
}
