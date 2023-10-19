package com.example.demo.common.dto;

import lombok.*;

/**
 * packageName    : com.pip.picks.common.dto
 * fileName       : CompaniesDto
 * author         : TaeJeongPark
 * date           : 2023-08-27
 * description    : 회사 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-27        TaeJeongPark       최초 생성
 * 2023-09-04        TaeJeongPark       필트 추가
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CompaniesDto {

    private String id;      // 사업자등록번호
    private String name;    // 회사명
    private String ceoName; // 대표자명
    private String csLink;  // CS 접수 링크

}
