package com.neuli.dmaker.service;

import com.neuli.dmaker.code.StatusCode;
import com.neuli.dmaker.dto.CreateDeveloper;
import com.neuli.dmaker.dto.DeveloperDetailDto;
import com.neuli.dmaker.dto.DeveloperDto;
import com.neuli.dmaker.dto.EditDeveloper;
import com.neuli.dmaker.entity.Developer;
import com.neuli.dmaker.entity.RetiredDeveloper;
import com.neuli.dmaker.exception.DMakerException;
import com.neuli.dmaker.repository.DeveloperRepository;
import com.neuli.dmaker.repository.RetiredDeveloperRepository;
import com.neuli.dmaker.type.DeveloperLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.neuli.dmaker.exception.DMakerErrorCode.*;

@Service
@RequiredArgsConstructor
public class DMakerService {

    // @RequiredArgsConstructor 필요
    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    // @Autowired, @Inject 문제점
    // 서비스 코드가 어노테이션에 종속적으로 만들어져
    // 서비스를 단독으로 테스트 하기가 어려웠기 때문에
    // 생성자를 생성하는 것이 만들어졌다.
    // 하지만 생성자도 불편함이 있어 final 을 사용

    /**
     * Transaction
     * 데이터베이스의 상태를 변화시키기 해서 수행하는 작업의 단위
     * 예를들어, 게시글을 작성하는 작업 -> 게시글 작성 insert, 게시글 불러오기 select 작업들을 하나의 트랜잭션으로 본다.
     * <p>
     * Transaction 의 특징
     * [ACID]
     * Atomic 원자성
     * Consistency 일관성 : 항상 정해진 규칙에 의해서 DB 에 저장되어 있어야 한다.
     * Isolation 고립성
     * Durability 지속성 : commit 이 된 시점의 이력을 모두 남겨야한다.
     */

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .statusCode(StatusCode.EMPLOYED)
                .name(request.getName())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);
        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        // business validation

        /* control + alt + v : 동일한 변수 뽑아내기 */
        validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYears());

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                }));
    }

    public List<DeveloperDto> getAllEmployedDevelopers() {
        /**
         *  collect()
         *  1) 스트림의 아이템들을 List 또는 Set 자료형으로 변환
         *  2) joining
         *  3) Sorting 하여 가장 큰 객체 리턴
         *  4) 평균값을 리턴
         *  등등
         */

        return developerRepository.findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));
    }

    // 메서드를 들어가기 전에 트랜젝션을 시작했다가 값을 바꿔준 후
    // 전체 체킹을 하여 변경되는 부분이 커밋이 되도록 @Transactional
    @Transactional
    public DeveloperDetailDto editDeveloperDetail(String memberId, EditDeveloper.Request request) {
        validateEditDeveloperRequest(request, memberId);

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateEditDeveloperRequest(EditDeveloper.Request request, String memberId) {
        // business validation

        /* control + alt + v : 동일한 변수 뽑아내기 */
        validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYears());
    }

    private void validateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYears) {
        if (developerLevel == DeveloperLevel.SENIOR
                && experienceYears < 10) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNGNIOR
                && (experienceYears < 4 || experienceYears > 10)) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    public void deleteAllDevelopers() {
        developerRepository.deleteAll();
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloperByMemberId(String memberId) {
        // developerRepository.deleteByMemberId(memberId);

        // 1. EMPLOYED -> RETIRED
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));
        developer.setStatusCode(StatusCode.RETIRED);

        // 2. save into RetiredDeveloper
        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();
        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }
}
