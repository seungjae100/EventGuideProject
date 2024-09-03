package com.web.eventguideproject.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank  // 이 필드는 비어 있을 수 없음을 나타냅니다.
    private String memberId;  // 사용자의 아이디를 저장하는 필드입니다.

    @NotBlank  // 이 필드는 비어 있을 수 없음을 나타냅니다.
    private String password;  // 사용자의 비밀번호를 저장하는 필드입니다.

}
