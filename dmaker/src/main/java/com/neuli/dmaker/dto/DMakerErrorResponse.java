package com.neuli.dmaker.dto;

import com.neuli.dmaker.exception.DMakerErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DMakerErrorResponse {

    private DMakerErrorCode errorCode;
    private String errorMessage;
}
