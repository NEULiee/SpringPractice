package com.fastcampus.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class JavaSort <T extends Comparable<T>> implements Sort<T> {

    public List<T> sort(List<T> list) {
        List<T> output = new ArrayList<>(list);
        Collections.sort(output);

        return output;
    }
}
