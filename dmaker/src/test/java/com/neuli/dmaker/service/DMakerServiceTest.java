package com.neuli.dmaker.service;

import com.neuli.dmaker.dto.CreateDeveloper;
import com.neuli.dmaker.dto.DeveloperDetailDto;
import com.neuli.dmaker.entity.Developer;
import com.neuli.dmaker.exception.DMakerErrorCode;
import com.neuli.dmaker.exception.DMakerException;
import com.neuli.dmaker.repository.DeveloperRepository;
import com.neuli.dmaker.repository.RetiredDeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.neuli.dmaker.code.StatusCode.EMPLOYED;
import static com.neuli.dmaker.type.DeveloperLevel.SENIOR;
import static com.neuli.dmaker.type.DeveloperSkillType.FRONT_END;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    @InjectMocks
    private DMakerService dMakerService;

    private final Developer defaultDeveloper = Developer.builder()
            .developerLevel(SENIOR)
            .developerSkillType(FRONT_END)
            .experienceYears(12)
            .statusCode(EMPLOYED)
            .name("name")
            .age(12)
            .build();

    private final CreateDeveloper.Request defaultCreateRequest = CreateDeveloper.Request.builder()
            .developerLevel(SENIOR)
            .developerSkillType(FRONT_END)
            .experienceYears(12)
            .memberId("memberId")
            .name("name")
            .age(32)
            .build();

    @Test
    public void testSomething() {
        // given
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultDeveloper));

        // when
        DeveloperDetailDto developerDetail = dMakerService.getDeveloperDetail("memberId");

        // then
        assertEquals(SENIOR, developerDetail.getDeveloperLevel());
        assertEquals(FRONT_END, developerDetail.getDeveloperSkillType());
        assertEquals(12, developerDetail.getExperienceYears());

    }

    @Test
    void createDeveloperTest_success() {
        // given : mocking, test 에 활용될 변수
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());

        // Mock 객체가 파라미터로 받은 값을 캡쳐해서 사용할 수 있게 해준다.
        // 여기서는 developerRepository.save(developer) 에서 developer
        // create 로직이 있을 땐 ArgumentCaptor 를 사용할 수 있다.
        ArgumentCaptor<Developer> captor =
                ArgumentCaptor.forClass(Developer.class);

        // when : test 동작, 결과 받아오기
        CreateDeveloper.Response developer = dMakerService.createDeveloper(defaultCreateRequest);

        // then : assertion 등 예상 동작 확인하기
        verify(developerRepository, times(1))
                .save(captor.capture());    // developerRepository 의 save
        Developer savedDeveloper = captor.getValue();

        assertEquals(SENIOR, savedDeveloper.getDeveloperLevel());
        assertEquals(FRONT_END, savedDeveloper.getDeveloperSkillType());
        assertEquals(12, savedDeveloper.getExperienceYears());
    }

    @Test
    void createDeveloperTest_failed_with_duplicated() {
        /** given : mocking, test 에 활용될 변수 */
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultDeveloper));

        /** when : test 동작, 결과 받아오기 */
        /** then : assertion 등 예상 동작 확인하기 */
        // 응답을 받지 못하므로 verify 를 쓰지 못한다.
        // 동작과 검증을 함께
        DMakerException dMakerException = assertThrows(DMakerException.class,
                () -> dMakerService.createDeveloper(defaultCreateRequest));

        assertEquals(DMakerErrorCode.DUPLICATED_MEMBER_ID, dMakerException.getDMakerErrorCode());
    }
}