package com.neuli.dmaker.service;

import com.neuli.dmaker.entity.Developer;
import com.neuli.dmaker.repository.DeveloperRepository;
import com.neuli.dmaker.type.DeveloperLevel;
import com.neuli.dmaker.type.DeveloperSkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DMakerService {

    // @RequiredArgsConstructor 필요
    private final DeveloperRepository developerRepository;

    // @Autowired, @Inject 문제점
    // 서비스 코드가 어노테이션에 종속적으로 만들어져
    // 서비스를 단독으로 테스트 하기가 어려웠기 때문에
    // 생성자를 생성하는 것이 만들어졌다.
    // 하지만 생성자도 불편함이 있어 final 을 사용

    /**
     *  Transaction
     *  데이터베이스의 상태를 변화시키기 해서 수행하는 작업의 단위
     *
     *  Transaction 의 특징
     *  [ACID]
     *  Atomic 원자성
     *  Consistency 일관성 : 항상 정해진 규칙에 의해서 DB 에 저장되어 있어야 한다.
     *  Isolation 고립성
     *  Durability 지속성 : commit 이 된 시점의 이력을 모두 남겨야한다.
     */

    @Transactional
    public void createDeveloper() {
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.Junior)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYears(2)
                .name("Olaf")
                .age(5)
                .build();

        developerRepository.save(developer);
    }

}
