package com.alperen.repository;

import com.alperen.entity.Ogretmen;
import com.alperen.entity.Sinif;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OgretmenRepository extends RepositoryManager<Ogretmen,Long>{
    EntityManager entityManager;
    public OgretmenRepository() {
        super(new Ogretmen());
    }


    public List<Sinif> getSiniflarByOgretmenId(Long ogretmenId) {
        String nativeQuery = "SELECT s.* " +
                "FROM tbl_sinif s " +
                "JOIN tbl_siniflar_ogretmenler so ON s.id = so.sinif_id " +
                "WHERE so.ogretmen_id = :ogretmenId";
        TypedQuery<Sinif> query = (TypedQuery<Sinif>) getEm().createNativeQuery(nativeQuery, Sinif.class);
        query.setParameter("ogretmenId", ogretmenId);
        return query.getResultList();
    }
}
