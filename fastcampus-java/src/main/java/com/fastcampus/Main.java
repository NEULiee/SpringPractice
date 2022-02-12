package com.fastcampus;

import com.fastcampus.logic.BubbleSort;
import com.fastcampus.logic.JavaSort;
import com.fastcampus.logic.Sort;

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
        Sort<String> sort = new JavaSort<>();

        System.out.println("[result] " + sort.sort(Arrays.asList(args)));

    }
}
