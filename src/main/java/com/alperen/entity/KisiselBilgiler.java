package com.alperen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class KisiselBilgiler {

    private String isim;
    private String soyisim;
    @Column(length = 11,name = "tc_kimlik")
    private String tcKimlik;

}
