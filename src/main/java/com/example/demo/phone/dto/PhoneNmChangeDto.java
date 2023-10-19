package com.example.demo.phone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PhoneNmChangeDto {
    private String userId;  // 사용자 아이디
    private String to;      // 휴대폰 번호
}