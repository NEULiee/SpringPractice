package com.neuli.developer.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DevDtoTest {

    @Test
    void test() {
        DevDto devDto = DevDto.builder()
                .name("snow")
                .age(12)
                .startAt(LocalDateTime.now())
                .build();

        System.out.println(devDto);

        devDto.printLog();
    }
}