package com.example.demo.common.dto;

import lombok.*;

/**
 * packageName    : com.pip.picks.user.dto
 * fileName       : UserDto
 * author         : TaeJeongPark
 * date           : 2023-08-16
 * description    : 사용자 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-16        TaeJeongPark       최초 생성
 * 2023-08-27        TaeJeongPark       클래스명 변경 및 필드 수정
 * 2023-09-23        TaeJeongPark       필드 수정
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UsersDto {

    private Long id;                                // 사용자 코드
    private String name;                            // 사용자 이름
    private int phoneNum;                           // 사용자 핸드폰번호
    private String recommendedCode;                 // 사용자 추천인 코드
    private String joinPath;                        // 가입 경로 ( 카카오 로그인, 구글 로그인, 네이버 로그인, 간편 로그인 )
    private boolean promotionAgree;                 // 프로모션, 이벤트 정보 알림 동의여부 ( true : 동의, false : 비동의)
    private boolean noticeAgree;                    // 공지사항, 업데이트 정보 알림 동의여부 ( true : 동의, false : 비동의)

}
