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
class BubbleSortTest {

    // JUnit5 는 public 이나 private 을 쓰지 않아도 된다.
    @Test
    @DisplayName("버블소트 - 리스트를 넣으면 정렬된 결과를 보여준다.")
    void given_List_WhenExecuting_ThenReturnSortedList() {
        // Given
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();

        // When
        List<Integer> actual = bubbleSort.sort(List.of(3, 2, 4, 5, 1));

        // Then
        assertEquals(List.of(1, 2, 3, 4 ,5), actual);
    }
}