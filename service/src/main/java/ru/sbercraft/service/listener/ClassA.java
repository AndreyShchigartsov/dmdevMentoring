package ru.sbercraft.service.listener;

import jakarta.annotation.PostConstruct;

public class ClassA {

    @PostConstruct
    private void init(){
        System.out.println();
    }
}
