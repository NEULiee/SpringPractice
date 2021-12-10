package com.neuli.developer.dto;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class DevUtil {

    public static void printLog() {
        System.out.println(LocalDateTime.of(2021, 8, 21, 3, 15));
    }

    public static void printNow() {
        System.out.println(LocalDateTime.now());
    }
}
