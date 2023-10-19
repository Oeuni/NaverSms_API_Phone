package com.example.demo.common.dto;

import lombok.*;

/**
 * packageName    : com.pip.picks.common.dto
 * fileName       : AdressDto
 * author         : TaeJeongPark
 * date           : 2023-09-04
 * description    : 주소정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-04        TaeJeongPark       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdressDto {

    private int postalCode;         // 수령인 우편번호
    private String sido;            // 시도
    private String gugun;           // 구군
    private String dong;            // 동
    private String li;              // 리
    private String bungi;           // 번지
    private String doro;            // 도로명주소
    private String detailAddress;   // 상세주소

}
