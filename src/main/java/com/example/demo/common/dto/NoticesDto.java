package com.example.demo.common.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.pip.picks.common.dto
 * fileName       : NoticeDto
 * author         : TaeJeongPark
 * date           : 2023-08-20
 * description    : 공지사항 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-20        TaeJeongPark       최초 생성
 * 2023-08-27        TaeJeongPark       클래스명 변경
 * 2023-09-04        TaeJeongPark       필트 추가
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NoticesDto {

    private int code;               // 글 번호
    private String title;           // 제목
    private String content;         // 내용
    private String classification;  // 구분 ( 공지, FAQ, 이벤트 )
    private LocalDateTime writeAt;  // 작성일시

}
