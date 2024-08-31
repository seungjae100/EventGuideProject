package com.web.eventguideproject.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberDTO {

    private Long seq;

    @NotBlank(message = "아이디는 필수 입력 사항입니다.")
    @Size(min = 8, max = 16, message = "아이디는 8자 이상 16자 이하입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 포함할 수 있습니다.")
    private String memberId;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Pattern(
            regexp = "^[a-z\\d]{1,16}$",  // 소문자와 숫자만 허용하며, 1자 이상 16자 이하로 설정
            message = "비밀번호는 소문자와 숫자로만 이루어진 16자 이내여야 합니다."
    )
    private String password;

    private String passwordok; // 사용자 비밀번호 확인

    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String memberName;

    @NotBlank(message = "닉네임은 필수 입력사항입니다.")
    private String nickName;

    private Date birth;

    @NotBlank(message = "성별은 필수 입력 사항입니다.")
    private String sex;

    @NotBlank(message = "핸드폰 번호는 필수 입력 사항입니다.")
    @Pattern(regexp = "^\\d{11}$", message = "핸드폰 번호는 11자리 숫자여야 합니다.")
    private String phone;

    @NotBlank(message = "주소는 필수 입력 사항입니다.")
    private String address;

    private String role;
}
