package com.fastcampus.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
class JavaSortTest {

    @Test
    @DisplayName("자바소트 - 리스트를 넣으면 정렬된 결과를 보여준다.")
    void given_List_WhenExecuting_ThenReturnSortedList() {
        // Given
        JavaSort<Integer> javaSort = new JavaSort<>();

        // When
        List<Integer> actual = javaSort.sort(List.of(3, 2, 4, 5, 1));

        // Then
        assertEquals(List.of(1, 2, 3, 4 ,5), actual);
    }
}