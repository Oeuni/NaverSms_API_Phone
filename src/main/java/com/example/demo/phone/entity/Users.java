package com.example.demo.phone.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.pip.picks.user.entity
 * fileName       : UserEntity
 * author         : TaeJeongPark
 * date           : 2023-09-04
 * description    : 사용자 엔티티
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        TaeJeongPark       최초 생성
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;                        // 사용자코드

//    @Column(nullable = false)
//    private String name;                    // 이름
//
//    @Column(nullable = false)
//    private LocalDateTime lastLoginAt;      // 최종 로그인일시
//
//    @Column(nullable = false)
//    private String recommendedCode;         // 추천인 코드
//
//    @Column(nullable = false)
//    private String joinPath;                // 가입경로
//
//    @Column(nullable = false)
//    private LocalDateTime joinAt;           // 가입일시
//
//    @Column(nullable = false, columnDefinition = "boolean default false")
//    private boolean promotionYesNo;         // 프로모션 알림 동의 여부
//
//    @Column(nullable = false, columnDefinition = "boolean default false")
//    private boolean noticeYesNo;            // 공지 알림 동의 여부
//
//    @Column(nullable = false, columnDefinition = "int default 1")
//    private int accountStatusesCode;        // 상태 코드

    private Long uid;                       // 유니크 ID
    private String accessToken;             // 액세스 토큰
    private String refreshToken;            // 리프레시 토큰
    private String phoneNum;                // 핸드폰번호
    private String pw;                      // 간편비밀번호
    private LocalDateTime withdrawalAt;     // 탈퇴일시

}
