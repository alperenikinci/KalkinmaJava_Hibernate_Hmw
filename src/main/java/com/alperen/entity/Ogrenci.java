package com.alperen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tbl_ogrenci")
public class Ogrenci extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private KisiselBilgiler kisiselBilgiler;
    @Column(name = "dogum_tarihi")
    private Long dogumTarihi;
    @Column(name = "sinif_id")
    private Long sinifId;
}
