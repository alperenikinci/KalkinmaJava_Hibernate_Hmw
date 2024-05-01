package com.alperen;

import com.alperen.entity.Deneme;
import com.alperen.repository.DenemeRepository;

public class Main {
    public static void main(String[] args) {
        DenemeRepository denemeRepository = new DenemeRepository();
        denemeRepository.save(new Deneme());
    }
}