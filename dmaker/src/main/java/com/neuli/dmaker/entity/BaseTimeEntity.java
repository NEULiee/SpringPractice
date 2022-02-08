package com.neuli.dmaker.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 *  [Auditing Entity]
 *
 *  모든 entity 들의 createdDate, modifiedDate 를 관리하는 entity
 *
 *  @MappedSuperclass
 *  : JPA Entity 클래스들이 BaseTimeEntity 를 상속할 경우
 *  필드들 (createdDate, modifiedDate) 도 칼럼으로 인식하도록 하는 annotation
 *  : Entity 로 인식하지 않게해준다
 *
 *  @EntityListeners(AuditingEntityListener.class)
 *  : BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
 *
 *  @CreatedDate : Entity 가 생성되어 저장될 때 시간이 자동저장
 *
 *  @LastModifiedDate : 조회한 Entity 의 값을 변경할 때 시간이 자동저장
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
