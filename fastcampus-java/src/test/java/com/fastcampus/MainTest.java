package com.fastcampus;

import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import static com.sun.tools.javac.Main.main;

/**
 * author : neuli
 * since : 2022/02/10
 * version : 1.0
 * description : class
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2022/02/10       neuli         최초 생성
 */
class MainTest {

    @Test
    void main_test() {
        // Given
        String[] args = {"3", "2", "1"};

        // When
        main(args);

        // Then
    }
}