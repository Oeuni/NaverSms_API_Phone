package com.example.demo.common.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.pip.picks.common.dto
 * fileName       : PointDto
 * author         : TaeJeongPark
 * date           : 2023-08-20
 * description    : 포인트 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-20        TaeJeongPark       최초 생성
 * 2023-08-27        TaeJeongPark       클래스명 변경 및 필드 수정
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PointsDto {

    private String classification;      // 이용구분 ( 사용, 충전, 소멸 )
    private int price;                  // 금액
    private LocalDateTime recordAt;     // 기록일시
    private String content;             // 내용
    private int result;                 // 현재 포인트

}
