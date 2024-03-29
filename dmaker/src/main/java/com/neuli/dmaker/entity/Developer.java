package com.neuli.dmaker.entity;

import com.neuli.dmaker.code.StatusCode;
import com.neuli.dmaker.type.DeveloperLevel;
import com.neuli.dmaker.type.DeveloperSkillType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
// @EntityListeners(AuditingEntityListener.class)
public class Developer extends BaseTimeEntity {

    // Builder 는 NoArgsConstructor, AllArgsConstructor 과 함께 쓰기

    /**
     * IDENTITY: 기본 키 생성을 데이터베이스에 위임 (AUTO_INCREMENT) ex) MySQL, PostgreSQL, DB2
     * SEQUENCE: 데이터베이스 Sequence Object 를 사용 (DB가 자동으로 숫자를 generate) ex) Oracle, PostgreSQL, DB2, H2
     * TABLE: 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략
     * AUTO: 기본 설정 값, 위 세 가지 전략을 자동으로 지정한다.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;

    @Enumerated(EnumType.STRING)
    private DeveloperSkillType developerSkillType;

    private Integer experienceYears;

    private String memberId;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;

    /**
     *  - JPA auditing: 생성일, 수정일 자동화
     *  row 가 생성되거나 없어질 때 자동으로 값 설정
     *
     *  대부분의 entity 에서 많이 사용하는 값이다.
     *  따라서 모든 entity 에서 생성일, 수정일을 만들고 수정하려면 코드가 복잡해지고 지저분해진다.
     *
     *  아래의 생성일, 수정일을 BaseTimeEntity 를 상속받도록 수정!

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

     */
}
