package com.web.eventguideproject.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Member 엔티티를 위한 JPA 레포지토리 인터페이스입니다. Long 타입의 기본키를 사용합니다.
    // 기본적인 CRUD 메서드를 자동으로 제공합니다.
}
