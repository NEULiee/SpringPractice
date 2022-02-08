package com.neuli.dmaker.controller;

import com.neuli.dmaker.dto.DeveloperDto;
import com.neuli.dmaker.entity.Developer;
import com.neuli.dmaker.service.DMakerService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.neuli.dmaker.type.DeveloperLevel.JUNIOR;
import static com.neuli.dmaker.type.DeveloperLevel.SENIOR;
import static com.neuli.dmaker.type.DeveloperSkillType.BACK_END;
import static com.neuli.dmaker.type.DeveloperSkillType.FRONT_END;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 컨트롤러 관련된 bean 들만 올려서 테스트
@WebMvcTest(DMakerController.class)
class DMakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DMakerService dMakerService;

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getType(),
            StandardCharsets.UTF_8);

    @Test
    void getAllDevelopers() throws Exception {
        DeveloperDto juniorDeveloperDto = DeveloperDto.builder()
                .developerSkillType(BACK_END)
                .developerLevel(JUNIOR)
                .memberId("memberId1")
                .build();
        DeveloperDto seniorDeveloperDto = DeveloperDto.builder()
                .developerSkillType(FRONT_END)
                .developerLevel(SENIOR)
                .memberId("memberId2")
                .build();

        given(dMakerService.getAllEmployedDevelopers())
                .willReturn(Arrays.asList(juniorDeveloperDto, seniorDeveloperDto));

        mockMvc.perform(get("/developers").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        // 응답의 첫번째 배열의 developerSkillType 이 BACK_END 일 것이다.
                        jsonPath("$[0].developerSkillType",
                                // static 으로 처리 가능 (CorMatchers.is())
                                is(BACK_END.name())))
                .andExpect(
                        jsonPath("$[0].developerLevel",
                                is(JUNIOR.name())))
                .andExpect(
                        jsonPath("$[1].developerSkillType",
                                is(FRONT_END.name())))
                .andExpect(
                        jsonPath("$[1].developerLevel",
                                is(SENIOR.name())));
    }
}