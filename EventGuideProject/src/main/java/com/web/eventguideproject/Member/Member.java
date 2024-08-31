package com.web.eventguideproject.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; // 기본키로 사용, 자동으로 ID에 숫자 생성

    @Column(nullable = false, unique = true)
    @NotBlank(message = "아이디는 필수 입력 사항입니다.")
    @Size(min = 8, max = 16, message = "아이디는 8자 이상 16자 이하입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 포함할 수 있습니다.")
    private String memberId; // 사용자 아이디

    @Column(nullable = false)
    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Pattern(
            regexp = "^[a-z\\d]{1,16}$",  // 소문자와 숫자만 허용하며, 1자 이상 16자 이하로 설정
            message = "비밀번호는 소문자와 숫자로만 이루어진 16자 이내여야 합니다."
    )
    private String password;

    @Transient // 데이터베이스에 영속되지 않도록 지정, 데이터베이스 테이블의 컬럼으로 매핑되지 않습니다.
    private String passwordok; // 사용자 비밀번호 확인

    @Column(nullable = false, unique = true)
    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email; // 사용자 이메일

    @Column(nullable = false)
    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String memberName; // 사용자 이름

    @Column(nullable = false, unique = true)
    @NotBlank(message = "닉네임은 필수 입력사항입니다.")
    private String nickName; // 닉네임


    @Column(nullable = false)
    private Date birth; // 사용자 생년월일

    @Column(nullable = false)
    @NotBlank(message = "성별은 필수 입력 사항입니다.")
    private String sex; // 사용자 성별

    @Column(nullable = false)
    @NotBlank(message = "핸드폰 번호는 필수 입력 사항입니다.")
    @Pattern(regexp = "^\\d{11}$", message = "핸드폰 번호는 11자리 숫자여야 합니다.")
    private String phone; // 사용자 핸드폰번호

    @Column(nullable = false)
    @NotBlank(message = "주소는 필수 입력 사항입니다.")
    private String address; // 사용자 주소

    private String role; // 사용자 역할 ( 일반, 관리자 )

    @PrePersist
    protected void onCreate() {
        if (role == null) {
            role = "ROLE_MEMBER";
        }
    }
}