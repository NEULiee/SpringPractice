package com.fastcampus;

import com.fastcampus.logic.BubbleSort;

import java.util.Arrays;

/**
 * author : neuli
 * since : 2022/02/09
 * version : 1.0
 * description : class
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2022/02/09       neuli         최초 생성
 */
public class Main {

    public static void main(String[] args) {
        BubbleSort<String> sort = new BubbleSort<>();
        System.out.println("[result] " + sort.sort(Arrays.asList(args)));
    }
}
