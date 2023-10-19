package com.example.demo.phone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PhoneNmDto {   //사용자가 입력한 휴대폰번호
    private String to;
}