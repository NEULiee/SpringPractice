package com.neuli.developer.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * Part 1. Spring Framework
 * 02. 롬복 설명
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Slf4j
public class DevDto {
    @NonNull
    String name;
    Integer age;
    LocalDateTime startAt;

    public void printLog() {
        log.info(getName());
    }
}
