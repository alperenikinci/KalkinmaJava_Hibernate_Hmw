package com.alperen.entity;

import com.alperen.utility.enums.EBrans;
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
@Table(name = "tbl_ogretmen")
public class Ogretmen extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private KisiselBilgiler kisiselBilgiler;
    @Enumerated(EnumType.STRING)
    private EBrans brans;
    @Column(name = "ise_baslama_tarihi")
    private Long iseBaslamaTarihi;
}
