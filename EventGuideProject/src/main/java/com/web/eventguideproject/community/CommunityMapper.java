package com.web.eventguideproject.community;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // 스프링 컴포넌트로 등록
public interface CommunityMapper {

    @Mapping(source = "member.nickName", target = "authorNickName") // 작성자의 닉네임을 DTO에 매핑
    CommunityDTO toDTO(Community community);

    @Mapping(source = "authorNickName", target = "member.nickName")
    Community toEntity(CommunityDTO communityDTO);
}
